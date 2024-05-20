package pedidosMeroy_Lerlin.consultaPedidos;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pedidosMeroy_Lerlin.Conexion;
import pedidosMeroy_Lerlin.Detalle_pedido;
import pedidosMeroy_Lerlin.Pedidos;
import pedidosMeroy_Lerlin.PortadaPedidos;
import java.sql.PreparedStatement;

public class ConsultaPedidos extends javax.swing.JFrame {

    int IdPedido;
    float totalPedido = 0;
    int xMouse, yMouse;
    private static HashMap<Integer, Pedidos> almaPedidos;
    private ArrayList<Detalle_pedido> almaDetalles;
    private static final DecimalFormat df = new DecimalFormat("#.##");
    DefaultTableModel tablaInicial;

    public ConsultaPedidos() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargaDatos();
        cargarDetallesPedido();
        jComboBoxEstado.setSelectedIndex(3);
        tablaInicial = new DefaultTableModel();
        tablaInicial.addColumn("Linea");
        tablaInicial.addColumn("Cod. Producto");
        tablaInicial.addColumn("Cantidad");
        tablaInicial.addColumn("Precio UD.");
        tablaInicial.addColumn("Precio Total");
        tablaInicial.addColumn("Cod. Pedido");
        jTableRecibeConsultas.setModel(tablaInicial);

    }

    public static HashMap<Integer, Pedidos> getAlmaPedidos() {
        return almaPedidos;
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
            if (pedido.getEstado().equals("Pendiente")) {
                jComboBoxEstado.setSelectedIndex(0);
                jComboBoxEstado.setEnabled(true);
            } else if (pedido.getEstado().equals("Entregado")) {
                jComboBoxEstado.setSelectedIndex(1);
                jComboBoxEstado.setEnabled(false);
            } else if (pedido.getEstado().equals("Rechazado")) {
                jComboBoxEstado.setSelectedIndex(2);
                jComboBoxEstado.setEnabled(false);
            } else {
                jComboBoxEstado.setSelectedIndex(3);
                jComboBoxEstado.setEnabled(false);
            }
        }

        if (pedido != null) {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            jLabelMostrarFechaPedido.setText(formatoFecha.format(pedido.getFechapedido()));
            jLabelMostrarFechaEsperada.setText(formatoFecha.format(pedido.getFechaEsperada()));
            if (pedido.getFechaEntrega() != null) {
                jLabelMostrarFechaEntrega.setText(formatoFecha.format(pedido.getFechaEntrega()));
            } else {
                jLabelMostrarFechaEntrega.setText("No Entregado");
            }
            jComboBoxEstado.setSelectedItem(pedido.getEstado());
            jTextPaneComentarios.setText(pedido.getComentario());
            jLabelMostrarCodCliente.setText(pedido.getCodigoCliente() + "");
            jLabelMostrarCodPedido.setText(pedido.getCodigoPedido() + "");

        } else {
            // Manejo del caso en que no se encuentra el pedido
            JOptionPane.showMessageDialog(null, "El pedido no existe.", "Pedido no encontrado", JOptionPane.ERROR_MESSAGE);
            jLabelMostrarFechaPedido.setText("-");
            jLabelMostrarFechaEsperada.setText("-");
            jLabelMostrarFechaEntrega.setText("-");
            jComboBoxEstado.setSelectedItem("No Encontrado");
            jTextPaneComentarios.setText("-");
        }
    }

    public void buscarIdPedidoDetalles(int codigoPedido) {
        DefaultTableModel model = (DefaultTableModel) jTableRecibeConsultas.getModel();
        model.setRowCount(0); 

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        jTableRecibeConsultas.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTableRecibeConsultas.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTableRecibeConsultas.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTableRecibeConsultas.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTableRecibeConsultas.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTableRecibeConsultas.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        Collections.sort(almaDetalles, Comparator.comparingInt(Detalle_pedido::getNumeroLinea));
        for (Detalle_pedido detalle : almaDetalles) {
            if (detalle.getCodigoPedido() == codigoPedido) {
                float precioTotalLinea = detalle.getCantidad() * detalle.getPrecioUnidad();
                totalPedido += precioTotalLinea;
                model.addRow(new Object[]{
                    detalle.getNumeroLinea(),
                    detalle.getCodigoProducto(),
                    detalle.getCantidad(),
                    df.format(detalle.getPrecioUnidad()) + "€",
                    df.format(precioTotalLinea) + "€",
                    detalle.getCodigoPedido()
                });
            }
            jLabelMostrarPVP.setText(String.valueOf(df.format(totalPedido)) + "€");

        }
        totalPedido = 0;
    }

    public void devolverStockProductos(int pedido) {

    }

    public void actualizarPedidoEntregado(int codigoPedido) {
        Connection conn = Conexion.getConnection();

        try {
            // Actualizar el estado del pedido en la tabla pedido
            String consulta = "UPDATE pedido SET estado = 'Entregado', fecha_entrega = ? WHERE codigo_pedido = ?";
            PreparedStatement statement = conn.prepareStatement(consulta);

            // Obtener la fecha actual del sistema como un objeto java.sql.Date
            java.sql.Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
            statement.setDate(1, fechaActual);
            statement.setInt(2, codigoPedido);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente en la base de datos.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el pedido en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // No cerramos la conexión aquí para permitir que sea manejada externamente
        }
    }

    public void actualizarStockProductosRechazados(int codigoPedido) {
        Connection conn = Conexion.getConnection();
        PreparedStatement statementDetalle = null;
        PreparedStatement statementProducto = null;

        try {
            // Obtener detalles del pedido rechazado
            String consultaDetalle = "SELECT codigo_producto, cantidad FROM detalle_pedido WHERE codigo_pedido = ?";
            statementDetalle = conn.prepareStatement(consultaDetalle);
            statementDetalle.setInt(1, codigoPedido);
            ResultSet resultSetDetalle = statementDetalle.executeQuery();

            while (resultSetDetalle.next()) {
                String codigoProducto = resultSetDetalle.getString("codigo_producto");
                int cantidad = resultSetDetalle.getInt("cantidad");

                // Actualizar el stock del producto
                String consultaProducto = "UPDATE producto SET cantidad_en_stock = cantidad_en_stock + ? WHERE codigo_producto = ?";
                statementProducto = conn.prepareStatement(consultaProducto);
                statementProducto.setInt(1, cantidad);
                statementProducto.setString(2, codigoProducto);
                statementProducto.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statementDetalle != null) {
                    statementDetalle.close();
                }
                if (statementProducto != null) {
                    statementProducto.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarEstadoPedido(int codigoPedido, String nuevoEstado) {
        Connection conn = Conexion.getConnection();

        try {
            // Obtener el estado actual del pedido
            String consultaEstadoActual = "SELECT estado FROM pedido WHERE codigo_pedido = ?";
            PreparedStatement statementEstadoActual = conn.prepareStatement(consultaEstadoActual);
            statementEstadoActual.setInt(1, codigoPedido);
            ResultSet resultSetEstado = statementEstadoActual.executeQuery();

            if (resultSetEstado.next()) {
                String estadoActual = resultSetEstado.getString("estado");

                if (estadoActual.equals("Pendiente")) {
                    String consulta = "UPDATE pedido SET estado = ?, fecha_entrega = ? WHERE codigo_pedido = ?";
                    PreparedStatement statement = conn.prepareStatement(consulta);
                    statement.setString(1, nuevoEstado);

                    if (nuevoEstado.equals("Entregado")) {
                        java.sql.Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
                        statement.setDate(2, fechaActual);
                    } else {
                        statement.setNull(2, java.sql.Types.DATE);
                    }

                    statement.setInt(3, codigoPedido);

                    int filasAfectadas = statement.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Estado del pedido actualizado correctamente en la base de datos.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado del pedido en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado del pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerEstadoPedido(int codigoPedido) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String estado = null;

        try {
            conn = Conexion.getConnection();
            String consulta = "SELECT estado FROM pedido WHERE codigo_pedido = ?";
            statement = conn.prepareStatement(consulta);
            statement.setInt(1, codigoPedido);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                estado = resultSet.getString("estado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el estado del pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return estado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelBtnCerrar = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelBuscaID = new javax.swing.JLabel();
        jTextFieldIntroduceID = new javax.swing.JTextField();
        jLabelBuscar = new javax.swing.JLabel();
        jLabelPVPTotal = new javax.swing.JLabel();
        jLabelMostrarPVP = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jLabelBtnInforme = new javax.swing.JLabel();
        jLabelBtnRechazados = new javax.swing.JLabel();
        jLabelBtnVolver = new javax.swing.JLabel();
        jPanelCentral = new javax.swing.JPanel();
        jPanelCentralTop = new javax.swing.JPanel();
        jLabelCodPedido = new javax.swing.JLabel();
        jLabelMostrarCodPedido = new javax.swing.JLabel();
        jLabelFechaPedido = new javax.swing.JLabel();
        jLabelMostrarFechaPedido = new javax.swing.JLabel();
        jLabelCodcliente = new javax.swing.JLabel();
        jLabelMostrarCodCliente = new javax.swing.JLabel();
        jLabelFechaEsperada = new javax.swing.JLabel();
        jLabelMostrarFechaEsperada = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelFechaEntrega = new javax.swing.JLabel();
        jLabelMostrarFechaEntrega = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jLabelComentarios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneComentarios = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRecibeConsultas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("centrado");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(865, 607));
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

        jLabelBtnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnCerrar.setText("X");
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
        jLabelTitulo.setText("CONSULTA PEDIDOS");

        jLabelBuscaID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBuscaID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBuscaID.setText("Buscar Por ID:");

        jTextFieldIntroduceID.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldIntroduceID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldIntroduceID.setText("Introduce ID");
        jTextFieldIntroduceID.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldIntroduceID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldIntroduceIDMouseClicked(evt);
            }
        });

        jLabelBuscar.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBuscar.setText("Buscar");
        jLabelBuscar.setOpaque(true);
        jLabelBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBuscarMouseClicked(evt);
            }
        });

        jLabelPVPTotal.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPVPTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPVPTotal.setText("PVP Total:");
        jLabelPVPTotal.setOpaque(true);

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabelBuscaID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIntroduceID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabelPVPTotal)
                        .addGap(29, 29, 29)
                        .addComponent(jLabelMostrarPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 68, Short.MAX_VALUE))))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBuscaID, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jTextFieldIntroduceID)
                            .addComponent(jLabelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelPVPTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMostrarPVP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelPrincipal.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelLeft.setBackground(new java.awt.Color(141, 179, 52));

        jLabelBtnInforme.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnInforme.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnInforme.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnInforme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnInforme.setText("Informe Pedidos");
        jLabelBtnInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnInforme.setOpaque(true);
        jLabelBtnInforme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeMouseExited(evt);
            }
        });

        jLabelBtnRechazados.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnRechazados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnRechazados.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnRechazados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnRechazados.setText("Informe Rechazados");
        jLabelBtnRechazados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnRechazados.setOpaque(true);
        jLabelBtnRechazados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnRechazadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnRechazadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnRechazadosMouseExited(evt);
            }
        });

        jLabelBtnVolver.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnVolver.setText("Volver");
        jLabelBtnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnVolver.setOpaque(true);
        jLabelBtnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnVolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnVolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnVolverMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addGroup(jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelBtnRechazados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBtnInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addComponent(jLabelBtnInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 375, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelLeft, java.awt.BorderLayout.LINE_START);

        jPanelCentral.setLayout(new java.awt.BorderLayout());

        jPanelCentralTop.setBackground(new java.awt.Color(255, 255, 255));

        jLabelCodPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCodPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCodPedido.setText("Cod. Pedido:");

        jLabelMostrarCodPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelFechaPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFechaPedido.setText("Fecha Pedido:");

        jLabelMostrarFechaPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelCodcliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCodcliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCodcliente.setText("Cod. Cliente:");

        jLabelMostrarCodCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelFechaEsperada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEsperada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFechaEsperada.setText("Fecha Esperada:");

        jLabelMostrarFechaEsperada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEstado.setText("Estado:");

        jLabelFechaEntrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFechaEntrega.setText("Fecha Entrega:");

        jLabelMostrarFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Entregado", "Rechazado", "No Encontrado" }));
        jComboBoxEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEstadoItemStateChanged(evt);
            }
        });

        jLabelComentarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelComentarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelComentarios.setText("Comentarios:");

        jScrollPane2.setViewportView(jTextPaneComentarios);

        javax.swing.GroupLayout jPanelCentralTopLayout = new javax.swing.GroupLayout(jPanelCentralTop);
        jPanelCentralTop.setLayout(jPanelCentralTopLayout);
        jPanelCentralTopLayout.setHorizontalGroup(
            jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCentralTopLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                        .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelMostrarCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEsperada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFechaEsperada, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelCodcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelMostrarCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEntrega)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                                .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                        .addComponent(jLabelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGap(16, 16, 16))
        );
        jPanelCentralTopLayout.setVerticalGroup(
            jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralTopLayout.createSequentialGroup()
                .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelMostrarCodCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCodcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMostrarCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelFechaEsperada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMostrarFechaEsperada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelCentral.add(jPanelCentralTop, java.awt.BorderLayout.PAGE_START);

        jTableRecibeConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Linea", "Cod. Producto", "Cantidad", "Precio UD.", "Precio Total", "Cod. Pedido"
            }
        ));
        jScrollPane1.setViewportView(jTableRecibeConsultas);

        jPanelCentral.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelPrincipal.add(jPanelCentral, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.CENTER);

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

    private void jLabelBtnInformeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeMouseEntered
        jLabelBtnInforme.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnInformeMouseEntered

    private void jLabelBtnInformeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeMouseExited
        jLabelBtnInforme.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnInformeMouseExited

    private void jLabelBtnInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeMouseClicked
        InformePedidos consulta = new InformePedidos();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelBtnInformeMouseClicked

    private void jLabelBtnRechazadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnRechazadosMouseEntered
        jLabelBtnRechazados.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnRechazadosMouseEntered

    private void jLabelBtnRechazadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnRechazadosMouseExited
        jLabelBtnRechazados.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnRechazadosMouseExited

    private void jLabelBtnRechazadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnRechazadosMouseClicked
        PedidosRechazados consulta = new PedidosRechazados();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelBtnRechazadosMouseClicked

    private void jLabelBtnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseEntered
        jLabelBtnVolver.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnVolverMouseEntered

    private void jLabelBtnVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseExited
        jLabelBtnVolver.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnVolverMouseExited

    private void jLabelBtnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseClicked
        PortadaPedidos consulta = new PortadaPedidos();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelBtnVolverMouseClicked

    private void jTextFieldIntroduceIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIntroduceIDMouseClicked
        jTextFieldIntroduceID.setText("");
    }//GEN-LAST:event_jTextFieldIntroduceIDMouseClicked

    private void jLabelBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarMouseClicked
        String input = jTextFieldIntroduceID.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int IdPedido = Integer.parseInt(input);
            buscarIdPedido(IdPedido);
            buscarIdPedidoDetalles(IdPedido);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de pedido debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabelBuscarMouseClicked

    private void jComboBoxEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEstadoItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            try {
                String codigo = jTextFieldIntroduceID.getText().trim();
                if (!codigo.isEmpty() && codigo.matches("\\d+")) {
                    int IdPedido = Integer.parseInt(codigo);
                    String selectedState = (String) jComboBoxEstado.getSelectedItem();

                    // Verificar el estado actual del pedido antes de realizar cualquier actualización
                    String estadoActual = obtenerEstadoPedido(IdPedido);

                    if (estadoActual == null) {
                        JOptionPane.showMessageDialog(null, "Pedido no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (estadoActual.equals("Pendiente")) {
                        if (selectedState.equals("Rechazado")) {
                            actualizarStockProductosRechazados(IdPedido);
                            actualizarEstadoPedido(IdPedido, "Rechazado");
                            cargaDatos();
                            cargarDetallesPedido();
                            buscarIdPedido(IdPedido);
                            buscarIdPedidoDetalles(IdPedido);
                        } else if (selectedState.equals("Entregado")) {
                            LocalDate fechaActual = LocalDate.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fechaFormateada = fechaActual.format(formatter);
                            jLabelMostrarFechaEntrega.setText(fechaFormateada);
                            actualizarEstadoPedido(IdPedido, "Entregado");
                            cargaDatos();
                            cargarDetallesPedido();
                            buscarIdPedido(IdPedido);
                            buscarIdPedidoDetalles(IdPedido);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID de pedido debe ser un número.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al procesar el cambio de estado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jComboBoxEstadoItemStateChanged

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JLabel jLabelBtnCerrar;
    private javax.swing.JLabel jLabelBtnInforme;
    private javax.swing.JLabel jLabelBtnRechazados;
    private javax.swing.JLabel jLabelBtnVolver;
    private javax.swing.JLabel jLabelBuscaID;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelCodPedido;
    private javax.swing.JLabel jLabelCodcliente;
    private javax.swing.JLabel jLabelComentarios;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaEntrega;
    private javax.swing.JLabel jLabelFechaEsperada;
    private javax.swing.JLabel jLabelFechaPedido;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMostrarCodCliente;
    private javax.swing.JLabel jLabelMostrarCodPedido;
    private javax.swing.JLabel jLabelMostrarFechaEntrega;
    private javax.swing.JLabel jLabelMostrarFechaEsperada;
    private javax.swing.JLabel jLabelMostrarFechaPedido;
    private javax.swing.JLabel jLabelMostrarPVP;
    private javax.swing.JLabel jLabelPVPTotal;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelCentralTop;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableRecibeConsultas;
    private javax.swing.JTextField jTextFieldIntroduceID;
    private javax.swing.JTextPane jTextPaneComentarios;
    // End of variables declaration//GEN-END:variables
}
