package pedidosMeroy_Lerlin.gestioPedidosExtra;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidosMeroy_Lerlin.Conexion;
import pedidosMeroy_Lerlin.Detalle_pedido;
import pedidosMeroy_Lerlin.Pedidos;
import pedidosMeroy_Lerlin.Producto;

public class GestionPedidos extends javax.swing.JFrame {

    int linea = 1;
    private static HashMap<Integer, Pedidos> almaPedidos;
    private static HashMap<String, Producto> almaProducto;
    private ArrayList<Detalle_pedido> almaDetalles;
    int xMouse, yMouse;
    DefaultTableModel tablaInicial;
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public GestionPedidos() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargaDatos();
        cargarDetallesPedido();
        cargarProductos();
        tablaInicial = new DefaultTableModel();
        tablaInicial.addColumn("Linea");
        tablaInicial.addColumn("Cod. Producto");
        tablaInicial.addColumn("Cantidad");
        tablaInicial.addColumn("Precio UD.");
        tablaInicial.addColumn("Precio Total");
        jTableMostrarProductos.setModel(tablaInicial);
    }

    public void cargarProductos() {
        Connection conn = Conexion.getConnection();
        almaProducto = new HashMap<>();

        try {
            Statement st = conn.createStatement();
            String consulta = "SELECT codigo_producto, nombre, gama, dimensiones, proveedor, descripcion, cantidad_en_stock, precio_venta, precio_proveedor FROM producto";
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                String codigoProducto = rs.getString("codigo_producto");
                String nombre = rs.getString("nombre");
                String gama = rs.getString("gama");
                String dimensiones = rs.getString("dimensiones");
                String proveedor = rs.getString("proveedor");
                String descripcion = rs.getString("descripcion");
                int stock = rs.getInt("cantidad_en_stock");
                float precio = rs.getFloat("precio_venta");
                float precioPro = rs.getFloat("precio_proveedor");

                Producto producto = new Producto(codigoProducto, nombre, gama, dimensiones, proveedor, descripcion, stock, precio, precioPro);
                almaProducto.put(nombre, producto);
            }
            rs.close();
            st.close();

        } catch (SQLException o) {
            System.out.println(o.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargaDatos() {
        Connection conn = Conexion.getConnection();
        almaPedidos = new HashMap<>();

        try {
            Statement st = conn.createStatement();
            String consulta = "SELECT codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, codigo_cliente FROM pedido";
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                int codigoPedido = rs.getInt("codigo_pedido");
                Date fechapedido = rs.getDate("fecha_pedido");
                Date fechaEsperada = rs.getDate("fecha_esperada");
                Date fechaEntrega = rs.getDate("fecha_entrega");
                String estado = rs.getString("estado");
                String comentario = rs.getString("comentarios");
                int codigoCliente = rs.getInt("codigo_cliente");

                Pedidos pedido = new Pedidos(codigoPedido, fechapedido, fechaEsperada, fechaEntrega, estado, comentario, codigoCliente);
                almaPedidos.put(codigoPedido, pedido);
            }
            rs.close();
            st.close();

        } catch (SQLException o) {
            System.out.println(o.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDetallesPedido() {
        almaDetalles = new ArrayList<>();

        Connection conn = Conexion.getConnection();
        if (conn == null) {
            return;
        }

        try {
            Statement st = conn.createStatement();
            String consulta = "SELECT codigo_pedido, codigo_producto, cantidad, precio_unidad, numero_linea FROM detalle_pedido";
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                int codigoPedido = rs.getInt("codigo_pedido");
                String codigoProducto = rs.getString("codigo_producto");
                int cantidad = rs.getInt("cantidad");
                float precioUnidad = rs.getFloat("precio_unidad");
                int numeroLinea = rs.getInt("numero_linea");

                Detalle_pedido detalle = new Detalle_pedido(codigoPedido, codigoProducto, cantidad, precioUnidad, numeroLinea);
                almaDetalles.add(detalle);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void nuevoPedido() {
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ultimoCodigo = 0;
            String consulta = "SELECT MAX(codigo_pedido) AS max_codigo FROM pedido";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            if (rs.next()) {
                ultimoCodigo = rs.getInt("max_codigo");
                jLabelMostrarCodigo.setText(String.valueOf(ultimoCodigo + 1));
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaFormateada = fechaActual.format(formatter);
                jLabelMostrarFecha.setText(fechaFormateada);
                jLabelMostrarEstado.setText("Pendiente");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el último código de pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // No cerramos la conexión aquí para evitar problemas
        }
    }

    public void anadirProducto() {
        DefaultTableModel model = (DefaultTableModel) jTableMostrarProductos.getModel();

        String productoSeleccionado = (String) jComboBoxProducto.getSelectedItem();
        Producto producto = almaProducto.get(productoSeleccionado);
        String cantidadCombo = jComboBoxCantidad.getSelectedItem() + "";
        int cantidad = Integer.parseInt(cantidadCombo);
        try {

            model.addRow(new Object[]{
                linea,
                producto.getCodigoProducto(),
                cantidad,
                df.format(producto.getPrecio()) + "€",
                df.format(producto.getPrecio() * cantidad) + "€"
            });
            linea++;
        } catch (Exception e) {
        }
    }

    public void generarPedido() {
        Connection conn = Conexion.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            for (int i = 0; i < jTableMostrarProductos.getRowCount(); i++) {
                String codigoProducto = (String) jTableMostrarProductos.getValueAt(i, 1);
                int cantidad = (int) jTableMostrarProductos.getValueAt(i, 2);
                float precioUnidad = Float.parseFloat(((String) jTableMostrarProductos.getValueAt(i, 3)).replace("€", ""));
                int numeroLinea = (int) jTableMostrarProductos.getValueAt(i, 0);

                // Verificar si el detalle de pedido ya existe
                if (!existeDetallePedido(numeroLinea)) {
                    Detalle_pedido detalle = new Detalle_pedido(Integer.parseInt(jLabelMostrarCodigo.getText()), codigoProducto, cantidad, precioUnidad, numeroLinea);
                    guardarDetallePedido(detalle);
                } else {
                    System.out.println("El detalle de pedido con número de línea " + numeroLinea + " ya existe.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean existeDetallePedido(int numeroLinea) {
    Connection conn = Conexion.getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try {
        String consulta = "SELECT 1 FROM detalle_pedido WHERE numero_linea = ?";
        PreparedStatement pstmt = conn.prepareStatement(consulta);
        pstmt.setInt(1, numeroLinea);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public void guardarPedido(Pedidos pedido) throws SQLException {
        Connection conn = Conexion.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String consulta = "INSERT INTO pedido (codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, codigo_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setInt(1, pedido.getCodigoPedido());
            pstmt.setDate(2, new java.sql.Date(pedido.getFechapedido().getTime()));
            pstmt.setDate(3, new java.sql.Date(pedido.getFechaEsperada().getTime()));
            pstmt.setDate(4, pedido.getFechaEntrega() != null ? new java.sql.Date(pedido.getFechaEntrega().getTime()) : null);
            pstmt.setString(5, pedido.getEstado());
            pstmt.setString(6, pedido.getComentario());
            pstmt.setInt(7, pedido.getCodigoCliente());
            pstmt.executeUpdate();
        } finally {
        }
    }

    public void actualizarStockProducto(Connection conn, Producto producto) throws SQLException {
        String consulta = "UPDATE producto SET cantidad_en_stock = ? WHERE codigo_producto = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setInt(1, producto.getStock());
            pstmt.setString(2, producto.getCodigoProducto());
            pstmt.executeUpdate();
        }
    }

    public void guardarDetallePedido(Detalle_pedido detalle) throws SQLException {
        Connection conn = Conexion.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String consulta = "INSERT INTO detalle_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad, numero_linea) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(consulta);
            pstmt.setInt(1, detalle.getCodigoPedido());
            pstmt.setString(2, detalle.getCodigoProducto());
            pstmt.setInt(3, detalle.getCantidad());
            pstmt.setFloat(4, detalle.getPrecioUnidad());
            pstmt.setInt(5, detalle.getNumeroLinea());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelBtnCerrar = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jLabelBtnNuevoPedido = new javax.swing.JLabel();
        jLabelBtnModificar = new javax.swing.JLabel();
        jLabelBtnEliminar = new javax.swing.JLabel();
        jPanelCenter = new javax.swing.JPanel();
        jPanelCenterTop = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelMostrarCodigo = new javax.swing.JLabel();
        jLabelFechaPedido = new javax.swing.JLabel();
        jLabelMostrarFecha = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMostrarEstado = new javax.swing.JLabel();
        jLabelComentarios = new javax.swing.JLabel();
        jTextFieldIntroducirComentario = new javax.swing.JTextField();
        jLabelProducto = new javax.swing.JLabel();
        jComboBoxProducto = new javax.swing.JComboBox<>();
        jLabelCantidad = new javax.swing.JLabel();
        jLabelCodCliente = new javax.swing.JLabel();
        jTextFieldIntroducirCliente = new javax.swing.JTextField();
        jComboBoxCantidad = new javax.swing.JComboBox<>();
        jLabelBtnAnadirProducto = new javax.swing.JLabel();
        jLabelBtnGenerarPedido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMostrarProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(865, 607));
        setMinimumSize(new java.awt.Dimension(865, 607));
        setUndecorated(true);
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipal.setMaximumSize(new java.awt.Dimension(865, 607));
        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(865, 607));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(865, 607));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelTop.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelTopMouseDragged(evt);
            }
        });
        jPanelTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelTopMousePressed(evt);
            }
        });

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidosMeroy_Lerlin/imagenes/meroyLerlin.png"))); // NOI18N

        jLabelBtnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBtnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelBtnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnCerrar.setText("X");
        jLabelBtnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnCerrar.setOpaque(true);
        jLabelBtnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnCerrarMouseExited(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("GESTIÓN PEDIDOS");

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelLeft.setBackground(new java.awt.Color(141, 179, 52));

        jLabelBtnNuevoPedido.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnNuevoPedido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnNuevoPedido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnNuevoPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnNuevoPedido.setText("Nuevo");
        jLabelBtnNuevoPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnNuevoPedido.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabelBtnNuevoPedido.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabelBtnNuevoPedido.setOpaque(true);
        jLabelBtnNuevoPedido.setPreferredSize(new java.awt.Dimension(140, 25));
        jLabelBtnNuevoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnNuevoPedidoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnNuevoPedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnNuevoPedidoMouseExited(evt);
            }
        });

        jLabelBtnModificar.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnModificar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnModificar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnModificar.setText("Modificar");
        jLabelBtnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnModificar.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabelBtnModificar.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabelBtnModificar.setOpaque(true);
        jLabelBtnModificar.setPreferredSize(new java.awt.Dimension(140, 25));
        jLabelBtnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnModificarMouseExited(evt);
            }
        });

        jLabelBtnEliminar.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnEliminar.setText("Eliminar");
        jLabelBtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnEliminar.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabelBtnEliminar.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabelBtnEliminar.setOpaque(true);
        jLabelBtnEliminar.setPreferredSize(new java.awt.Dimension(140, 25));
        jLabelBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnEliminarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBtnNuevoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(jLabelBtnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(jLabelBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addComponent(jLabelBtnNuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 387, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelLeft, java.awt.BorderLayout.LINE_START);

        jPanelCenter.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCenter.setLayout(new java.awt.BorderLayout());

        jPanelCenterTop.setBackground(new java.awt.Color(255, 255, 255));

        jLabelCodigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCodigo.setText("Código Pedido:");

        jLabelFechaPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaPedido.setText("Fecha Pedido:");

        jLabelEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEstado.setText("Estado:");

        jLabelComentarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelComentarios.setText("Comentarios:");

        jLabelProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelProducto.setText("Producto:");

        jComboBoxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abelia Floribunda", "Acer Negundo", "Acer platanoides", "Acer Pseudoplatanus", "Ajedrea", "Albaricoquero", "Albaricoquero Corbato", "Albaricoquero Kurrot", "Albaricoquero Moniqui", "Albaricoquero", "Arbustos Mix Maceta", "Archontophoenix Cunninghamiana", "Azadón", "Beucarnea Recurvata", "Bismarckia Nobilis", "Bougamvillea roja, naranja", "Bougamvillea Sanderiana", "Bougamvillea Sanderiana Espaldera", "Bougamvillea Sanderiana Tutor", "Brahea Armata", "Brahea Edulis", "Brachychiton Acerifolius", "Brachychiton Discolor", "Brachychiton Rupestris", "Butia Capitata", "Calamondin Copa", "Calamondin Copa EXTRA Con FRUTA", "Calamondin Mini", "Callistemom (Mix)", "Callistemom COPA", "Camelia Blanco", "Camelia japonica", "Camelia japonica ejemplar", "Cassia Corimbosa", "Cedrus Deodara", "Cedrus Deodara \"Feeling Blue\" Novedad", "Ce rezo", "Cerezo Burlat", "Cerezo Napoleón", "Cerezo Picota", "Chamaerops Humilis", "Chamaerops Humilis \"Cerifera\"", "Chrysalidocarpus Lutescens -ARECA", "Chrysler Rojo", "Chitalpa Summer Bells", "Ciruelo", "Ciruelo Claudia Negra", "Ciruelo Friar", "Ciruelo Golden Japan", "Ciruelo R. Claudia Verde", "Ciruelo Reina C. De Ollins", "Ciruelo Santa Rosa", "Cordyline Australis -DRACAENA", "Corylus Avellana \"Contorta\"", "Dracaena Drago", "Erytrina Kafra", "Escallonia (Mix)", "Evonimus Emerald Gayeti", "Evonimus Pulchellus", "Eucalyptus Citriodora", "Eucalyptus Ficifolia", "Expositor árboles borde del mar", "Expositor árboles clima continental", "Expositor árboles clima mediterráneo", "Expositor Cítricos Mix", "Expositor Mimosa Semilla Mix", "Forsytia Intermedia \"Lynwood\"", "Granado", "Granado Mollar de Elche", "Hibiscus Syriacus  \"Diana\" -Blanco Puro", "Hibiscus Syriacus  \"Helene\" -Blanco-C.rojo", "Hibiscus Syriacus  \"Pink Giant\" Rosa", "Hibiscus Syriacus  Var. Injertadas 1 Tallo", "Higuera", "Higuera Breva", "Higuera Napolitana", "Higuera Verdal", "Juniperus Chinensis Stricta", "Juniperus chinensis \"Blue Alps\"", "Juniperus horizontalis Wiltonii", "Juniperus squamata \"Blue Star\"", "Juniperus x media Phitzeriana verde", "Jubaea Chilensis", "Kaki", "Kaki Rojo Brillante", "Kordes Perfect bicolor rojo-amarillo", "Kunquat", "Kunquat  EXTRA con FRUTA", "Lagunaria patersonii  calibre 8/10", "Lagunaria Patersonii", "Landora Amarillo", "Laurus Nobilis Arbusto - Ramificado Bajo", "Lavándula Dentata", "Leptospermum COPA", "Leptospermum formado PIRAMIDE", "Limonero -Plantón joven", "Limonero 2 años injerto", "Limonero 30/40", "Limonero calibre 8/10", "Livistonia Australis", "Livistonia Decipiens", "Lonicera Nitida", "Lonicera Nitida \"Maigrum\"", "Lonicera Pileata", "Manzano", "Manzano Golden Delicious", "Manzano Reineta", "Manzano Starking Delicious", "Margarita blanca", "Margarita rosa", "Margarita amarilla", "Margarita roja", "Margarita violeta", "Margarita azul", "Margarita mix", "Mandarino calibre 8/10", "Mandarino -Plantón joven", "Mandarino 2 años injerto", "Mimosa DEALBATA Gaulois Astier", "Mimosa Injerto CLASICA Dealbata", "Mimosa Semilla Bayleyana", "Mimosa Semilla Cyanophylla", "Mimosa Semilla Espectabilis", "Mimosa Semilla Floribunda 4 estaciones", "Mimosa Semilla Longifolia", "Membrillero", "Membrillero Gigante de Wranja", "Melissa", "Melocotonero", "Melocotonero Amarillo de Agosto", "Melocotonero Federica", "Melocotonero Paraguayo", "Melocotonero Spring Crest", "Mentha Sativa", "Mejorana", "Mimosa Semilla", "Mimosa", "Moniqui", "Naranjo -Plantón joven 1 año injerto", "Naranjo 2 años injerto", "Naranjo calibre 8/10", "Nectarina", "Níspero", "Níspero Tanaca", "Nogal", "Nogal Comón", "Olea-Olivos", "Parra Uva de Mesa", "Peral", "Peral Blanq. de Aranjuez", "Peral Castell", "Peral Conference", "Peral Williams", "Petrosilium Hortense (Peregil)", "Philadelphus \"Virginal\"", "Phoenix Canariensis", "Phylostachys aurea", "Phylostachys Bambusa Spectabilis", "Phylostachys biseti", "Pitimini rojo", "Pinus Canariensis", "Pinus Halepensis", "Pinus Pinea -Pino Piñonero", "Platanus Acerifolia", "Pleioblastus distichus-Bambú enano", "Prunus pisardii", "Rastrillo de Jardin", "Rhaphis Excelsa", "Rhaphis Humilis", "Robinia Pseudoacacia Casque Rouge", "Rosal bajo 1ª -En maceta-inicio brotación", "Rosal copa", "Rose Gaujard bicolor blanco-rojo", "Roundelay rojo fuerte", "Sabal Minor", "Salix Babylonica  Pendula", "Salvia Mix", "Santolina Chamaecyparys", "Sasa palmata", "Sesbania Punicea", "Sierra de Poda 400MM", "Solanum Jazminoide", "Soraya Naranja", "Thujia orientalis \"Aurea nana\"", "Thymus Citriodra (Tomillo limón)", "Thymus Vulgaris", "Tecoma Stands", "Thuja Esmeralda", "Tuja Occidentalis Woodwardii", "Viburnum Tinus \"Eve Price\"", "Washingtonia Robusta", "Weigelia \"Bristol Ruby\"", "Wisteria Sinensis  azul", "Wisteria Sinensis blanca", "Wisteria Sinensis INJERTADAS DECÓ", "Wisteria Sinensis rosa", "Yucca Jewel", "Zamia Furfuracaea" }));

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCantidad.setText("Cantidad:");

        jLabelCodCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCodCliente.setText("Código Cliente:");

        jComboBoxCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", " " }));

        jLabelBtnAnadirProducto.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnAnadirProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBtnAnadirProducto.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnAnadirProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnAnadirProducto.setText("Añadir Producto");
        jLabelBtnAnadirProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnAnadirProducto.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabelBtnAnadirProducto.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabelBtnAnadirProducto.setOpaque(true);
        jLabelBtnAnadirProducto.setPreferredSize(new java.awt.Dimension(140, 25));
        jLabelBtnAnadirProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnAnadirProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnAnadirProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnAnadirProductoMouseExited(evt);
            }
        });

        jLabelBtnGenerarPedido.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnGenerarPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBtnGenerarPedido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnGenerarPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnGenerarPedido.setText("Generar Pedido");
        jLabelBtnGenerarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnGenerarPedido.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabelBtnGenerarPedido.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabelBtnGenerarPedido.setOpaque(true);
        jLabelBtnGenerarPedido.setPreferredSize(new java.awt.Dimension(140, 25));
        jLabelBtnGenerarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnGenerarPedidoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnGenerarPedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnGenerarPedidoMouseExited(evt);
            }
        });

        jTableMostrarProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Linea", "Cod. Producto", "Cantidad", "Precio UD.", "Precio Total"
            }
        ));
        jScrollPane1.setViewportView(jTableMostrarProductos);

        javax.swing.GroupLayout jPanelCenterTopLayout = new javax.swing.GroupLayout(jPanelCenterTop);
        jPanelCenterTop.setLayout(jPanelCenterTopLayout);
        jPanelCenterTopLayout.setHorizontalGroup(
            jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCenterTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBtnAnadirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jLabelBtnGenerarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(jPanelCenterTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCenterTopLayout.createSequentialGroup()
                        .addComponent(jLabelProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxProducto, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabelCodCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIntroducirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCenterTopLayout.createSequentialGroup()
                        .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCenterTopLayout.createSequentialGroup()
                                .addComponent(jLabelComentarios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldIntroducirComentario))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCenterTopLayout.createSequentialGroup()
                                .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelFechaPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCenterTopLayout.setVerticalGroup(
            jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterTopLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMostrarEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMostrarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMostrarFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldIntroducirComentario, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIntroducirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanelCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBtnGenerarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBtnAnadirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelCenter.add(jPanelCenterTop, java.awt.BorderLayout.CENTER);

        jPanelPrincipal.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBtnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelBtnCerrarMouseClicked

    private void jLabelBtnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnCerrarMouseEntered
        jLabelBtnCerrar.setBackground(Color.RED);
        jLabelBtnCerrar.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabelBtnCerrarMouseEntered

    private void jLabelBtnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnCerrarMouseExited
        jLabelBtnCerrar.setBackground(Color.white);
        jLabelBtnCerrar.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabelBtnCerrarMouseExited

    private void jPanelTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTopMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelTopMousePressed

    private void jPanelTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanelTopMouseDragged

    private void jLabelBtnNuevoPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnNuevoPedidoMouseEntered
        jLabelBtnNuevoPedido.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnNuevoPedidoMouseEntered

    private void jLabelBtnNuevoPedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnNuevoPedidoMouseExited
        jLabelBtnNuevoPedido.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnNuevoPedidoMouseExited

    private void jLabelBtnAnadirProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAnadirProductoMouseEntered
        jLabelBtnAnadirProducto.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnAnadirProductoMouseEntered

    private void jLabelBtnAnadirProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAnadirProductoMouseExited
        jLabelBtnAnadirProducto.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnAnadirProductoMouseExited

    private void jLabelBtnGenerarPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGenerarPedidoMouseEntered
        jLabelBtnGenerarPedido.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnGenerarPedidoMouseEntered

    private void jLabelBtnGenerarPedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGenerarPedidoMouseExited
        jLabelBtnGenerarPedido.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnGenerarPedidoMouseExited

    private void jLabelBtnModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnModificarMouseEntered
        jLabelBtnModificar.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnModificarMouseEntered

    private void jLabelBtnModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnModificarMouseExited
        jLabelBtnModificar.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnModificarMouseExited

    private void jLabelBtnNuevoPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnNuevoPedidoMouseClicked
        nuevoPedido();
    }//GEN-LAST:event_jLabelBtnNuevoPedidoMouseClicked

    private void jLabelBtnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnEliminarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelBtnEliminarMouseEntered

    private void jLabelBtnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnEliminarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelBtnEliminarMouseExited

    private void jLabelBtnAnadirProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnAnadirProductoMouseClicked
        anadirProducto();
    }//GEN-LAST:event_jLabelBtnAnadirProductoMouseClicked

    private void jLabelBtnGenerarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGenerarPedidoMouseClicked
        generarPedido();
    }//GEN-LAST:event_jLabelBtnGenerarPedidoMouseClicked

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxCantidad;
    private javax.swing.JComboBox<String> jComboBoxProducto;
    private javax.swing.JLabel jLabelBtnAnadirProducto;
    private javax.swing.JLabel jLabelBtnCerrar;
    private javax.swing.JLabel jLabelBtnEliminar;
    private javax.swing.JLabel jLabelBtnGenerarPedido;
    private javax.swing.JLabel jLabelBtnModificar;
    private javax.swing.JLabel jLabelBtnNuevoPedido;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelCodCliente;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelComentarios;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaPedido;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMostrarCodigo;
    private javax.swing.JLabel jLabelMostrarEstado;
    private javax.swing.JLabel jLabelMostrarFecha;
    private javax.swing.JLabel jLabelProducto;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelCenterTop;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMostrarProductos;
    private javax.swing.JTextField jTextFieldIntroducirCliente;
    private javax.swing.JTextField jTextFieldIntroducirComentario;
    // End of variables declaration//GEN-END:variables
}
