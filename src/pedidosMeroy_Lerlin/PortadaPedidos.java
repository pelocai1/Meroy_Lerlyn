
package pedidosMeroy_Lerlin;

import pedidosMeroy_Lerlin.consultaPedidos.ConsultaPedidos;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;


public class PortadaPedidos extends javax.swing.JFrame {

    int xMouse, yMouse;
    
    public PortadaPedidos() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jLabelChooseGestionPedidos = new javax.swing.JLabel();
        jLabelChooseConsultaPedidos = new javax.swing.JLabel();
        jPanelEncabezadoPortada = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelExitPortada = new javax.swing.JLabel();
        jLabelTituloPortada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1024, 720));
        jPanelPrincipal.setRequestFocusEnabled(false);
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelChooseGestionPedidos.setBackground(new java.awt.Color(141, 179, 52));
        jLabelChooseGestionPedidos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelChooseGestionPedidos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelChooseGestionPedidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelChooseGestionPedidos.setText("GESTIÓN PEDIDOS");
        jLabelChooseGestionPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelChooseGestionPedidos.setOpaque(true);
        jLabelChooseGestionPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChooseGestionPedidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelChooseGestionPedidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelChooseGestionPedidosMouseExited(evt);
            }
        });
        jPanelPrincipal.add(jLabelChooseGestionPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 300, 290));

        jLabelChooseConsultaPedidos.setBackground(new java.awt.Color(141, 179, 52));
        jLabelChooseConsultaPedidos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelChooseConsultaPedidos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelChooseConsultaPedidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelChooseConsultaPedidos.setText("CONSULTAS PEDIDOS");
        jLabelChooseConsultaPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelChooseConsultaPedidos.setOpaque(true);
        jLabelChooseConsultaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChooseConsultaPedidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelChooseConsultaPedidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelChooseConsultaPedidosMouseExited(evt);
            }
        });
        jPanelPrincipal.add(jLabelChooseConsultaPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 280, 290));

        jPanelEncabezadoPortada.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEncabezadoPortada.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelEncabezadoPortadaMouseDragged(evt);
            }
        });
        jPanelEncabezadoPortada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelEncabezadoPortadaMousePressed(evt);
            }
        });

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidosMeroy_Lerlin/imagenes/meroyLerlin.png"))); // NOI18N

        jLabelExitPortada.setBackground(new java.awt.Color(255, 255, 255));
        jLabelExitPortada.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelExitPortada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExitPortada.setText("X");
        jLabelExitPortada.setOpaque(true);
        jLabelExitPortada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitPortadaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelExitPortadaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelExitPortadaMouseExited(evt);
            }
        });

        jLabelTituloPortada.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloPortada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloPortada.setText("SECCIÓN PEDIDOS");

        javax.swing.GroupLayout jPanelEncabezadoPortadaLayout = new javax.swing.GroupLayout(jPanelEncabezadoPortada);
        jPanelEncabezadoPortada.setLayout(jPanelEncabezadoPortadaLayout);
        jPanelEncabezadoPortadaLayout.setHorizontalGroup(
            jPanelEncabezadoPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEncabezadoPortadaLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGap(18, 18, 18)
                .addGroup(jPanelEncabezadoPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEncabezadoPortadaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelExitPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEncabezadoPortadaLayout.createSequentialGroup()
                        .addComponent(jLabelTituloPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 138, Short.MAX_VALUE))))
        );
        jPanelEncabezadoPortadaLayout.setVerticalGroup(
            jPanelEncabezadoPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
            .addGroup(jPanelEncabezadoPortadaLayout.createSequentialGroup()
                .addComponent(jLabelExitPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabelTituloPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(jPanelEncabezadoPortada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelChooseGestionPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseGestionPedidosMouseClicked
        GestionPedidos gestion = new GestionPedidos();
        gestion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelChooseGestionPedidosMouseClicked

    private void jLabelChooseConsultaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseConsultaPedidosMouseClicked
        ConsultaPedidos consulta = new ConsultaPedidos();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelChooseConsultaPedidosMouseClicked

    private void jLabelExitPortadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitPortadaMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitPortadaMouseClicked

    private void jLabelExitPortadaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitPortadaMouseEntered
        jLabelExitPortada.setBackground(Color.RED);
        jLabelExitPortada.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabelExitPortadaMouseEntered

    private void jLabelExitPortadaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitPortadaMouseExited
        jLabelExitPortada.setBackground(Color.white);
        jLabelExitPortada.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabelExitPortadaMouseExited

    private void jPanelEncabezadoPortadaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEncabezadoPortadaMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelEncabezadoPortadaMousePressed

    private void jPanelEncabezadoPortadaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEncabezadoPortadaMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x -xMouse, y - yMouse);
    }//GEN-LAST:event_jPanelEncabezadoPortadaMouseDragged

    private void jLabelChooseGestionPedidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseGestionPedidosMouseEntered
        jLabelChooseGestionPedidos.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelChooseGestionPedidosMouseEntered

    private void jLabelChooseGestionPedidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseGestionPedidosMouseExited
        jLabelChooseGestionPedidos.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelChooseGestionPedidosMouseExited

    private void jLabelChooseConsultaPedidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseConsultaPedidosMouseEntered
        jLabelChooseConsultaPedidos.setBackground(new Color(163, 207,60));
    }//GEN-LAST:event_jLabelChooseConsultaPedidosMouseEntered

    private void jLabelChooseConsultaPedidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseConsultaPedidosMouseExited
        jLabelChooseConsultaPedidos.setBackground(new Color(141, 179,52));
    }//GEN-LAST:event_jLabelChooseConsultaPedidosMouseExited

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
            java.util.logging.Logger.getLogger(PortadaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PortadaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PortadaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PortadaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PortadaPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelChooseConsultaPedidos;
    private javax.swing.JLabel jLabelChooseGestionPedidos;
    private javax.swing.JLabel jLabelExitPortada;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTituloPortada;
    private javax.swing.JPanel jPanelEncabezadoPortada;
    private javax.swing.JPanel jPanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
