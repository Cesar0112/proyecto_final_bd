/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package cu.edu.cujae.structbd.visual.player;

import cu.edu.cujae.structbd.dto.player.ReadPlayerDTO;
import cu.edu.cujae.structbd.services.ServicesLocator;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose
 */
public class Player_UI extends javax.swing.JFrame
{

    private LinkedList<ReadPlayerDTO> list_player;
    /** Creates new form Player_UI */
    public Player_UI() {
        initComponents();
        try
        {
            this.list_player = new LinkedList<>(ServicesLocator.PlayerServices.readAllPlayer());
            Iterator<ReadPlayerDTO> it_list_player = list_player.iterator();
            while(it_list_player.hasNext())
            {
                ReadPlayerDTO readPlayerDTO = it_list_player.next();
                ((DefaultTableModel) table.getModel()).addRow(new Object[]
                {
                    readPlayerDTO.getTeam_member_name(), readPlayerDTO.getTeam_name(), readPlayerDTO.getMember_number(),
                    readPlayerDTO.getPosition_name(), readPlayerDTO.getYears_in_team()
                });
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Player_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Player_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuMod = new javax.swing.JMenuItem();
        menuDel = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        menuMod.setText("jMenuItem1");
        menuMod.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuModActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuMod);

        menuDel.setText("jMenuItem2");
        menuDel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuDelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuDel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Nombre", "Equipo", "Número", "Posición", "Años en el equipo"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        table.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(table);

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuModActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuModActionPerformed
    {//GEN-HEADEREND:event_menuModActionPerformed
        int row = table.getSelectedRow();
        if (row > 0)
        {
            String name = table.getValueAt(row, 0).toString();
            String team = table.getValueAt(row, 1).toString();
            Integer number = Integer.valueOf(table.getValueAt(row, 2).toString());
            String position = table.getValueAt(row, 3).toString();
            Integer years_in_team = Integer.valueOf(table.getValueAt(row, 4).toString());
            Iterator<ReadPlayerDTO> it_list = this.list_player.iterator();
            boolean found = false;

            //Buscando el id del player seleccionado 
            while (it_list.hasNext() && !found)
            {
                ReadPlayerDTO readPlayerDTO = it_list.next();
                if (readPlayerDTO.getTeam_name().
                    equalsIgnoreCase(team) && readPlayerDTO.getMember_number() == number)
                {
                    String id = readPlayerDTO.getTeam_member_ID();
                    found = true;
                }
            }
            if (found)
            {
                //Llamadas a los servicios de update
                if (position == "P")
                {
                    //UpdatePitcherDTO updatePitcherDTO = new UpdatePitcherDTO();
                    //ServicesLocator.PitcherServices.
                    //repaint();
                }
                else
                {
                    //UpdateBatterDTO updateBatterDTO = new UpdateBatterDTO();
                    //ServicesLocator.BatterServices.
                    //repaint();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,
                                              "El identificador del jugador no se encuentra en la base de datos",
                                              "Información",
                                              HEIGHT);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un jugador para poder modificarlo", "Información",
                                          HEIGHT);
        }
    }//GEN-LAST:event_menuModActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuDelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuDelActionPerformed
    {//GEN-HEADEREND:event_menuDelActionPerformed
        int row = table.getSelectedRow();
        if (row > 0)
        {
            String name = table.getValueAt(row, 0).toString();
            String team = table.getValueAt(row, 1).toString();
            Integer number = Integer.valueOf(table.getValueAt(row, 2).toString());
            String position = table.getValueAt(row, 3).toString();
            Integer years_in_team = Integer.valueOf(table.getValueAt(row, 4).toString());
            Iterator<ReadPlayerDTO> it_list = this.list_player.iterator();
            boolean found = false;

            //Buscando el id del player seleccionado 
            while (it_list.hasNext() && !found)
            {
                ReadPlayerDTO readPlayerDTO = it_list.next();
                if (readPlayerDTO.getTeam_name().
                    equalsIgnoreCase(team) && readPlayerDTO.getMember_number() == number)
                {
                    String id = readPlayerDTO.getTeam_member_ID();
                    found = true;
                }
            }
            if (found)
            {
                //Llamada a los servicios de delete
                if (position == "P")
                {
                    //DeletePitcherDTO deletePitcherDTO = new DeletePitcherDTO();
                    //ServicesLocator.PitcherServices.
                    //repaint();
                }
                else
                {
                    //DeleteBatterDTO deleteBatterDTO = new DeleteBatterDTO();
                    //ServicesLocator.BatterServices.
                    //repaint();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,
                                              "El identificador del jugador no se encuentra en la base de datos",
                                              "Información",
                                              HEIGHT);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un jugador para poder eliminarlo", "Información",
                                          HEIGHT);
        }
    }//GEN-LAST:event_menuDelActionPerformed

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
            java.util.logging.Logger.getLogger(Player_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Player_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Player_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Player_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Player_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuDel;
    private javax.swing.JMenuItem menuMod;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
