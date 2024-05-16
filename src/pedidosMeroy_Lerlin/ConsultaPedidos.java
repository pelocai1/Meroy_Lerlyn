/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pedidosMeroy_Lerlin;

import java.awt.Color;


public class ConsultaPedidos extends javax.swing.JFrame {

    int xMouse, yMouse;
    
    public ConsultaPedidos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipalConsultas = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jLabelBuscarCodigo = new javax.swing.JLabel();
        jLabelInforma = new javax.swing.JLabel();
        jLabelPedidosRechazados = new javax.swing.JLabel();
        jLabelCambiarEstado = new javax.swing.JLabel();
        jLabelVolver = new javax.swing.JLabel();
        jPanelCerrar = new javax.swing.JPanel();
        jLabelCerrarX = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelContenido = new javax.swing.JPanel();
        jLabelIdProducto = new javax.swing.JLabel();
        jTextFieldIntroducirId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelIdPedido = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMostrarFechaPedido = new javax.swing.JLabel();
        jLabelFechaPedido = new javax.swing.JLabel();
        jLabelFechaEstimada = new javax.swing.JLabel();
        jLabelMostrarFechaEstimada = new javax.swing.JLabel();
        jLabelFechaEntrega = new javax.swing.JLabel();
        jLabelMostrarFechaEntrega = new javax.swing.JLabel();
        jLabelFechaEntrega1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneMostrarComentarios = new javax.swing.JTextPane();
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

        jLabelBuscarCodigo.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBuscarCodigo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBuscarCodigo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBuscarCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBuscarCodigo.setText("Buscar por ID");
        jLabelBuscarCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBuscarCodigo.setOpaque(true);
        jLabelBuscarCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBuscarCodigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBuscarCodigoMouseExited(evt);
            }
        });

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
            .addComponent(jLabelBuscarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelInforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelPedidosRechazados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelCambiarEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addComponent(jLabelBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelInforma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPedidosRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 246, Short.MAX_VALUE))
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

        jLabelIdProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIdProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdProducto.setText("ID Pedido:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Linea", "ID Producto", "Cantidad", "Precio UD.", "ID Pedido"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Linea");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("ID Producto");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Cantidad");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Precio UD.");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("ID Pedido");
        }

        jLabelIdPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIdPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdPedido.setText("Estado:");

        jLabelEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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

        jLabelFechaEntrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaEntrega.setText("Fecha Entrega:");

        jLabelMostrarFechaEntrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMostrarFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelFechaEntrega1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFechaEntrega1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaEntrega1.setText("Comentarios:");

        jScrollPane2.setViewportView(jTextPaneMostrarComentarios);

        jButtonBuscarPedido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonBuscarPedido.setText("Buscar");
        jButtonBuscarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelContenidoLayout = new javax.swing.GroupLayout(jPanelContenido);
        jPanelContenido.setLayout(jPanelContenidoLayout);
        jPanelContenidoLayout.setHorizontalGroup(
            jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMostrarFechaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jButtonBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addComponent(jLabelFechaEntrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addGap(35, 35, 35))
        );
        jPanelContenidoLayout.setVerticalGroup(
            jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContenidoLayout.createSequentialGroup()
                        .addComponent(jButtonBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                                .addComponent(jLabelFechaEntrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 46, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenidoLayout.createSequentialGroup()
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        this.setLocation(x -xMouse, y - yMouse);
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

    private void jLabelBuscarCodigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarCodigoMouseEntered
        jLabelBuscarCodigo.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelBuscarCodigoMouseEntered

    private void jLabelBuscarCodigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarCodigoMouseExited
        jLabelBuscarCodigo.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelBuscarCodigoMouseExited

    private void jLabelInformaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInformaMouseEntered
        jLabelInforma.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelInformaMouseEntered

    private void jLabelInformaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInformaMouseExited
        jLabelInforma.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelInformaMouseExited

    private void jLabelPedidosRechazadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPedidosRechazadosMouseEntered
        jLabelPedidosRechazados.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelPedidosRechazadosMouseEntered

    private void jLabelPedidosRechazadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPedidosRechazadosMouseExited
        jLabelPedidosRechazados.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelPedidosRechazadosMouseExited

    private void jLabelCambiarEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCambiarEstadoMouseEntered
        jLabelCambiarEstado.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelCambiarEstadoMouseEntered

    private void jLabelCambiarEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCambiarEstadoMouseExited
        jLabelCambiarEstado.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelCambiarEstadoMouseExited

    private void jLabelVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseEntered
        jLabelVolver.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelVolverMouseEntered

    private void jLabelVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseExited
        jLabelVolver.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelVolverMouseExited

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
    private javax.swing.JLabel jLabelBuscarCodigo;
    private javax.swing.JLabel jLabelCambiarEstado;
    private javax.swing.JLabel jLabelCerrarX;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaEntrega;
    private javax.swing.JLabel jLabelFechaEntrega1;
    private javax.swing.JLabel jLabelFechaEstimada;
    private javax.swing.JLabel jLabelFechaPedido;
    private javax.swing.JLabel jLabelIdPedido;
    private javax.swing.JLabel jLabelIdProducto;
    private javax.swing.JLabel jLabelInforma;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMostrarFechaEntrega;
    private javax.swing.JLabel jLabelMostrarFechaEstimada;
    private javax.swing.JLabel jLabelMostrarFechaPedido;
    private javax.swing.JLabel jLabelPedidosRechazados;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelVolver;
    private javax.swing.JPanel jPanelCerrar;
    private javax.swing.JPanel jPanelContenido;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPrincipalConsultas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldIntroducirId;
    private javax.swing.JTextPane jTextPaneMostrarComentarios;
    // End of variables declaration//GEN-END:variables
}
