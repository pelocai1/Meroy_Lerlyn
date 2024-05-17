package pedidosMeroy_Lerlin;

import java.awt.Color;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultaPedidos extends javax.swing.JFrame {

    int xMouse, yMouse;
    private HashMap<Integer, Pedidos> almaPedidos;
    private ArrayList<Detalle_pedido> almaDetalles;

    public ConsultaPedidos() {
        initComponents();
        cargaDatos();
        cargarDetallesPedido();
        DefaultTableModel tablaInicial = new DefaultTableModel();
        tablaInicial.addColumn("Linea");
        tablaInicial.addColumn("Cod. Producto");
        tablaInicial.addColumn("Cantidad");
        tablaInicial.addColumn("Precio UD.");
        tablaInicial.addColumn("Cod. Pedido");
        jTableRecibeConsultas.setModel(tablaInicial);

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
            // Manejar el error de conexión
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

    public void buscarIdPedido(int codigoPedido) {
        Pedidos pedido = almaPedidos.get(codigoPedido);

        if (pedido != null) {

            jLabelMostrarFechaPedido.setText(pedido.getFechapedido().toString());
            jLabelMostrarFechaEstimada.setText(pedido.getFechaEsperada().toString());
            if (pedido.getFechaEntrega() != null) {
                jLabelMostrarFechaEntrega.setText(pedido.getFechaEntrega().toString());
            } else {
                jLabelMostrarFechaEntrega.setText("No Entregado");
            }
            jLabelMostrarEstado.setText(pedido.getEstado());
            jTextPaneMostrarComentarios.setText(pedido.getComentario());

        } else {
            // Manejo del caso en que no se encuentra el pedido
            JOptionPane.showMessageDialog(null, "El pedido no existe.", "Pedido no encontrado", JOptionPane.ERROR_MESSAGE);
            jLabelMostrarFechaPedido.setText("-");
            jLabelMostrarFechaEstimada.setText("-");
            jLabelMostrarFechaEntrega.setText("-");
            jLabelMostrarEstado.setText("-");
            jTextPaneMostrarComentarios.setText("-");

        }
    }

    public void buscarIdPedidoDetalles(int codigoPedido) {
        DefaultTableModel model = (DefaultTableModel) jTableRecibeConsultas.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevas filas
        Collections.sort(almaDetalles, Comparator.comparingInt(Detalle_pedido::getNumeroLinea));
        for (Detalle_pedido detalle : almaDetalles) {
            if (detalle.getCodigoPedido() == codigoPedido) {
                model.addRow(new Object[]{
                    detalle.getNumeroLinea(),
                    detalle.getCodigoProducto(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnidad(),
                    detalle.getCodigoPedido()
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipalConsultas = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jLabelInforma = new javax.swing.JLabel();
        jLabelPedidosRechazados = new javax.swing.JLabel();
        jLabelCambiarEstado = new javax.swing.JLabel();
        jLabelVolver = new javax.swing.JLabel();
        jPanelCerrar = new javax.swing.JPanel();
        jLabelCerrarX = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelContenido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRecibeConsultas = new javax.swing.JTable();
        jLabelFechaEntrega = new javax.swing.JLabel();
        jLabelMostrarEstado = new javax.swing.JLabel();
        jLabelMostrarFechaPedido = new javax.swing.JLabel();
        jLabelFechaPedido = new javax.swing.JLabel();
        jLabelFechaEstimada = new javax.swing.JLabel();
        jLabelMostrarFechaEstimada = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMostrarFechaEntrega = new javax.swing.JLabel();
        jLabelComentarios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneMostrarComentarios = new javax.swing.JTextPane();
        jPanelActivaBuscar = new javax.swing.JPanel();
        jLabelIdPedido = new javax.swing.JLabel();
        jTextFieldIntroducirId = new javax.swing.JTextField();
        jButtonBuscarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanelPrincipalConsultas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipalConsultas.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelPrincipalConsultas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidosMeroy_Lerlin/imagenes/meroyLerlin.png"))); // NOI18N
        jPanelPrincipalConsultas.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 90));

        jPanelMenu.setBackground(new java.awt.Color(141, 179, 52));

        jLabelInforma.setBackground(new java.awt.Color(141, 179, 52));
        jLabelInforma.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelInforma.setForeground(new java.awt.Color(255, 255, 255));
        jLabelInforma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInforma.setText("Informe Pedidos");
        jLabelInforma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelInforma.setOpaque(true);
        jLabelInforma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelInformaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelInformaMouseExited(evt);
            }
        });

        jLabelPedidosRechazados.setBackground(new java.awt.Color(141, 179, 52));
        jLabelPedidosRechazados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPedidosRechazados.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPedidosRechazados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPedidosRechazados.setText("Pedidos Rechazados");
        jLabelPedidosRechazados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelPedidosRechazados.setOpaque(true);
        jLabelPedidosRechazados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelPedidosRechazadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelPedidosRechazadosMouseExited(evt);
            }
        });

        jLabelCambiarEstado.setBackground(new java.awt.Color(141, 179, 52));
        jLabelCambiarEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCambiarEstado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCambiarEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCambiarEstado.setText("Cambiar Estado");
        jLabelCambiarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelCambiarEstado.setOpaque(true);
        jLabelCambiarEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCambiarEstadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCambiarEstadoMouseExited(evt);
            }
        });

        jLabelVolver.setBackground(new java.awt.Color(141, 179, 52));
        jLabelVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelVolver.setForeground(new java.awt.Color(255, 255, 255));
        jLabelVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVolver.setText("Volver");
        jLabelVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelVolver.setOpaque(true);
        jLabelVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelVolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelVolverMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelInforma, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPedidosRechazados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jLabelCambiarEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addComponent(jLabelInforma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPedidosRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 302, Short.MAX_VALUE))
        );

        jPanelPrincipalConsultas.add(jPanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 190, 520));

        jPanelCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCerrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelCerrarMouseDragged(evt);
            }
        });
        jPanelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelCerrarMousePressed(evt);
            }
        });

        jLabelCerrarX.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCerrarX.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelCerrarX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCerrarX.setText("X");
        jLabelCerrarX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelCerrarX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelCerrarX.setOpaque(true);
        jLabelCerrarX.setPreferredSize(new java.awt.Dimension(40, 40));
        jLabelCerrarX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCerrarXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCerrarXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCerrarXMouseExited(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("CONSULTA PEDIDOS");

        javax.swing.GroupLayout jPanelCerrarLayout = new javax.swing.GroupLayout(jPanelCerrar);
        jPanelCerrar.setLayout(jPanelCerrarLayout);
        jPanelCerrarLayout.setHorizontalGroup(
            jPanelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCerrarLayout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218)
                .addComponent(jLabelCerrarX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelCerrarLayout.setVerticalGroup(
            jPanelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCerrarLayout.createSequentialGroup()
                .addGroup(jPanelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCerrarX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelPrincipalConsultas.add(jPanelCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 40));

        jPanelContenido.setBackground(new java.awt.Color(255, 255, 255));

        jTableRecibeConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Linea", "Cod. Producto", "Cantidad", "Precio UD.", "Cod. Pedido"
            }
        ));
        jScrollPane1.setViewportView(jTableRecibeConsultas);

        jLabelFechaEntrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaEntrega.setText("Fecha Entrega:");

        jLabelMostrarEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMostrarEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelMostrarFechaPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMostrarFechaPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelFechaPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaPedido.setText("Fecha Pedido:");

        jLabelFechaEstimada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEstimada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaEstimada.setText("Fecha Estimada:");

        jLabelMostrarFechaEstimada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMostrarFechaEstimada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEstado.setText("Estado:");

        jLabelMostrarFechaEntrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMostrarFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelComentarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelComentarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelComentarios.setText("Comentarios:");

        jScrollPane2.setViewportView(jTextPaneMostrarComentarios);

        jPanelActivaBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelActivaBuscar.setMinimumSize(new java.awt.Dimension(674, 58));

        jLabelIdPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIdPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdPedido.setText("ID Pedido:");

        jTextFieldIntroducirId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButtonBuscarPedido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonBuscarPedido.setText("Buscar");
        jButtonBuscarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActivaBuscarLayout = new javax.swing.GroupLayout(jPanelActivaBuscar);
        jPanelActivaBuscar.setLayout(jPanelActivaBuscarLayout);
        jPanelActivaBuscarLayout.setHorizontalGroup(
            jPanelActivaBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActivaBuscarLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabelIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jButtonBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanelActivaBuscarLayout.setVerticalGroup(
            jPanelActivaBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActivaBuscarLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanelActivaBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActivaBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonBuscarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelContenidoLayout = new javax.swing.GroupLayout(jPanelContenido);
        jPanelContenido.setLayout(jPanelContenidoLayout);
        jPanelContenidoLayout.setHorizontalGroup(
            jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addComponent(jLabelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFechaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(61, 61, 61))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContenidoLayout.createSequentialGroup()
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelActivaBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelContenidoLayout.setVerticalGroup(
            jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelActivaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMostrarFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelPrincipalConsultas.add(jPanelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 680, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipalConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipalConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseClicked
        PortadaPedidos portada = new PortadaPedidos();
        portada.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelVolverMouseClicked

    private void jPanelCerrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCerrarMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelCerrarMousePressed

    private void jPanelCerrarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCerrarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanelCerrarMouseDragged

    private void jLabelCerrarXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarXMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCerrarXMouseClicked

    private void jLabelCerrarXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarXMouseEntered
        jLabelCerrarX.setBackground(Color.RED);
        jLabelCerrarX.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabelCerrarXMouseEntered

    private void jLabelCerrarXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarXMouseExited
        jLabelCerrarX.setBackground(Color.white);
        jLabelCerrarX.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabelCerrarXMouseExited

    private void jLabelInformaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInformaMouseEntered
        jLabelInforma.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelInformaMouseEntered

    private void jLabelInformaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInformaMouseExited
        jLabelInforma.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelInformaMouseExited

    private void jLabelPedidosRechazadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPedidosRechazadosMouseEntered
        jLabelPedidosRechazados.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelPedidosRechazadosMouseEntered

    private void jLabelPedidosRechazadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPedidosRechazadosMouseExited
        jLabelPedidosRechazados.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelPedidosRechazadosMouseExited

    private void jLabelCambiarEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCambiarEstadoMouseEntered
        jLabelCambiarEstado.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelCambiarEstadoMouseEntered

    private void jLabelCambiarEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCambiarEstadoMouseExited
        jLabelCambiarEstado.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelCambiarEstadoMouseExited

    private void jLabelVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseEntered
        jLabelVolver.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelVolverMouseEntered

    private void jLabelVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseExited
        jLabelVolver.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelVolverMouseExited

    private void jButtonBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPedidoActionPerformed
        String codigoPedidoStr = jTextFieldIntroducirId.getText();
        int pedido = Integer.parseInt(codigoPedidoStr);
        buscarIdPedido(pedido);
        buscarIdPedidoDetalles(pedido);
    }//GEN-LAST:event_jButtonBuscarPedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarPedido;
    private javax.swing.JLabel jLabelCambiarEstado;
    private javax.swing.JLabel jLabelCerrarX;
    private javax.swing.JLabel jLabelComentarios;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaEntrega;
    private javax.swing.JLabel jLabelFechaEstimada;
    private javax.swing.JLabel jLabelFechaPedido;
    private javax.swing.JLabel jLabelIdPedido;
    private javax.swing.JLabel jLabelInforma;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMostrarEstado;
    private javax.swing.JLabel jLabelMostrarFechaEntrega;
    private javax.swing.JLabel jLabelMostrarFechaEstimada;
    private javax.swing.JLabel jLabelMostrarFechaPedido;
    private javax.swing.JLabel jLabelPedidosRechazados;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelVolver;
    private javax.swing.JPanel jPanelActivaBuscar;
    private javax.swing.JPanel jPanelCerrar;
    private javax.swing.JPanel jPanelContenido;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPrincipalConsultas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableRecibeConsultas;
    private javax.swing.JTextField jTextFieldIntroducirId;
    private javax.swing.JTextPane jTextPaneMostrarComentarios;
    // End of variables declaration//GEN-END:variables
}
