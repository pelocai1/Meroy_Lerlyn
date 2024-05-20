package pedidosMeroy_Lerlin.consultaPedidos;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pedidosMeroy_Lerlin.Pedidos;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PedidosRechazados extends javax.swing.JFrame {

    int xMouse, yMouse;

    public PedidosRechazados() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableModel tablaInicial = new DefaultTableModel();
        tablaInicial.addColumn("Cod. Pedido");
        tablaInicial.addColumn("Fecha Pedido");
        tablaInicial.addColumn("Fecha Esperada");
        tablaInicial.addColumn("Fecha Entrega");
        tablaInicial.addColumn("Estado");
        tablaInicial.addColumn("Comentarios");
        tablaInicial.addColumn("Cod. Cliente");
        jTableMostrarRechazados.setModel(tablaInicial);
    }

    public void mostrarPedidosRechazados(HashMap<Integer, Pedidos> almaPedidos) {
        DefaultTableModel model = (DefaultTableModel) jTableMostrarRechazados.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevas filas

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        for (Pedidos pedido : almaPedidos.values()) {
            if (pedido.getEstado().equalsIgnoreCase("rechazado")) {
                Object fechaEntrega = (pedido.getFechaEntrega() != null) ? formatoFecha.format(pedido.getFechaEntrega()) : "No Entregado";
                model.addRow(new Object[]{
                    pedido.getCodigoPedido(),
                    formatoFecha.format(pedido.getFechapedido()),
                    formatoFecha.format(pedido.getFechaEsperada()),
                    fechaEntrega,
                    pedido.getEstado(),
                    pedido.getComentario(),
                    pedido.getCodigoCliente()
                });
            }
        }
    }

    public void guardarPedidosRechazadosEnArchivo(String filePath) {
        HashMap<Integer, Pedidos> almaPedidos = ConsultaPedidos.getAlmaPedidos();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Pedidos pedido : almaPedidos.values()) {
                if (pedido.getEstado().equalsIgnoreCase("rechazado")) {
                    writer.write("Código Pedido: " + pedido.getCodigoPedido());
                    writer.write(", Fecha Pedido: " + pedido.getFechapedido());
                    writer.write(", Fecha Esperada: " + pedido.getFechaEsperada());
                    writer.write(", Fecha Entrega: " + (pedido.getFechaEntrega() != null ? pedido.getFechaEntrega() : "No Entregado"));
                    writer.write(", Estado: " + pedido.getEstado());
                    writer.write(", Comentarios: " + pedido.getComentario());
                    writer.write(", Código Cliente: " + pedido.getCodigoCliente() + "\n");
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(this, "Los pedidos rechazados se han guardado en el archivo: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los pedidos rechazados en el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelBtnCerrar = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jLabelBtnInformeRechazados = new javax.swing.JLabel();
        jLabelBtnGuardarInforme = new javax.swing.JLabel();
        jLabelBtnVolver = new javax.swing.JLabel();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMostrarRechazados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(865, 607));
        setMinimumSize(new java.awt.Dimension(865, 607));
        setUndecorated(true);
        setResizable(false);

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
        jLabelBtnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        jLabelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("PEDIDOS RECHAZADOS");

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo)
                    .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(jLabelBtnCerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelLeft.setBackground(new java.awt.Color(141, 179, 52));

        jLabelBtnInformeRechazados.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnInformeRechazados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnInformeRechazados.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnInformeRechazados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnInformeRechazados.setText("Informe Rechazados");
        jLabelBtnInformeRechazados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnInformeRechazados.setMaximumSize(new java.awt.Dimension(141, 45));
        jLabelBtnInformeRechazados.setMinimumSize(new java.awt.Dimension(141, 45));
        jLabelBtnInformeRechazados.setOpaque(true);
        jLabelBtnInformeRechazados.setPreferredSize(new java.awt.Dimension(141, 45));
        jLabelBtnInformeRechazados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeRechazadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeRechazadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnInformeRechazadosMouseExited(evt);
            }
        });

        jLabelBtnGuardarInforme.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnGuardarInforme.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnGuardarInforme.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnGuardarInforme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnGuardarInforme.setText("Guardar Informe");
        jLabelBtnGuardarInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnGuardarInforme.setMaximumSize(new java.awt.Dimension(141, 45));
        jLabelBtnGuardarInforme.setMinimumSize(new java.awt.Dimension(141, 45));
        jLabelBtnGuardarInforme.setOpaque(true);
        jLabelBtnGuardarInforme.setPreferredSize(new java.awt.Dimension(141, 45));
        jLabelBtnGuardarInforme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBtnGuardarInformeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBtnGuardarInformeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBtnGuardarInformeMouseExited(evt);
            }
        });

        jLabelBtnVolver.setBackground(new java.awt.Color(141, 179, 52));
        jLabelBtnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBtnVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnVolver.setText("Volver");
        jLabelBtnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBtnVolver.setMaximumSize(new java.awt.Dimension(141, 45));
        jLabelBtnVolver.setMinimumSize(new java.awt.Dimension(141, 45));
        jLabelBtnVolver.setOpaque(true);
        jLabelBtnVolver.setPreferredSize(new java.awt.Dimension(141, 45));
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
                .addGroup(jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBtnInformeRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBtnGuardarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addComponent(jLabelBtnInformeRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabelBtnGuardarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabelBtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 378, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelLeft, java.awt.BorderLayout.LINE_START);

        jPanelCenter.setBackground(new java.awt.Color(255, 255, 255));

        jTableMostrarRechazados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cod. Pedido", "Fecha Pedido", "Fecha Esperada", "Fecha Entrega", "Estado", "Comentarios", "Cod. Cliente"
            }
        ));
        jScrollPane1.setViewportView(jTableMostrarRechazados);

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );

        jPanelPrincipal.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTopMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelTopMousePressed

    private void jPanelTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanelTopMouseDragged

    private void jLabelBtnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseClicked
        ConsultaPedidos consulta = new ConsultaPedidos();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelBtnVolverMouseClicked

    private void jLabelBtnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseEntered
        jLabelBtnVolver.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnVolverMouseEntered

    private void jLabelBtnVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnVolverMouseExited
        jLabelBtnVolver.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnVolverMouseExited

    private void jLabelBtnGuardarInformeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGuardarInformeMouseEntered
        jLabelBtnGuardarInforme.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnGuardarInformeMouseEntered

    private void jLabelBtnGuardarInformeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGuardarInformeMouseExited
        jLabelBtnGuardarInforme.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnGuardarInformeMouseExited

    private void jLabelBtnInformeRechazadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeRechazadosMouseEntered
        jLabelBtnInformeRechazados.setBackground(new Color(163, 207, 60));
    }//GEN-LAST:event_jLabelBtnInformeRechazadosMouseEntered

    private void jLabelBtnInformeRechazadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeRechazadosMouseExited
        jLabelBtnInformeRechazados.setBackground(new Color(141, 179, 52));
    }//GEN-LAST:event_jLabelBtnInformeRechazadosMouseExited

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

    private void jLabelBtnInformeRechazadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInformeRechazadosMouseClicked
        mostrarPedidosRechazados(ConsultaPedidos.getAlmaPedidos());
    }//GEN-LAST:event_jLabelBtnInformeRechazadosMouseClicked

    private void jLabelBtnGuardarInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnGuardarInformeMouseClicked
        jFileChooser1.setDialogTitle("Guardar pedidos");
        int userSelection = jFileChooser1.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jFileChooser1.getSelectedFile(); // Obtiene el archivo seleccionado
            String ruta = fileToSave.getAbsolutePath() + ".txt"; // Obtiene la ruta del archivo seleccionado
            guardarPedidosRechazadosEnArchivo(ruta); // Llama al método para guardar los pedidos en el archivo
        }
    }//GEN-LAST:event_jLabelBtnGuardarInformeMouseClicked

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabelBtnCerrar;
    private javax.swing.JLabel jLabelBtnGuardarInforme;
    private javax.swing.JLabel jLabelBtnInformeRechazados;
    private javax.swing.JLabel jLabelBtnVolver;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMostrarRechazados;
    // End of variables declaration//GEN-END:variables
}
