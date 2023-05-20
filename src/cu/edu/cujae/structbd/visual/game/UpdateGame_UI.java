/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package cu.edu.cujae.structbd.visual.game;

import cu.edu.cujae.structbd.dto.game.ReadAGameDTO;
import cu.edu.cujae.structbd.dto.game.ReadGameDTO;
import cu.edu.cujae.structbd.dto.game.UpdateGameDTO;
import cu.edu.cujae.structbd.dto.phase.ReadAPhaseDTO;
import cu.edu.cujae.structbd.dto.phase.ReadPhaseDTO;
import cu.edu.cujae.structbd.dto.stadium.ReadStadiumDTO;
import cu.edu.cujae.structbd.dto.team.FindTeamDTO;
import cu.edu.cujae.structbd.dto.team.ReadTeamDTO;
import cu.edu.cujae.structbd.services.ServicesLocator;
import cu.edu.cujae.structbd.utils.UtilsConnector;
import cu.edu.cujae.structbd.visual.snb.SerieUI;
import java.util.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Amaya
 */
public class UpdateGame_UI extends javax.swing.JDialog {

    private ReadAPhaseDTO readAPhaseDTO;
    private FindTeamDTO readATeamDTO;
    private ReadGameDTO readGameDTO;
    /**
     * Creates new form CreateGameUI
     */
    public UpdateGame_UI(java.awt.Frame parent, boolean modal, ReadGameDTO readGameDTO, ReadAPhaseDTO readAPhaseDTO)
    {
        super(parent, modal);
        try {
            initComponents();
            //Contiene el codigo de la fase activa actualmente para buscar los equipos
            this.readAPhaseDTO = readAPhaseDTO;
            this.readGameDTO = readGameDTO;
            jComboBoxHomeClub.addItem(this.readGameDTO.getHcTeamName());
            jComboBoxHomeClub.addItem(this.readGameDTO.getVisTeamName());
            jSpinnerRHC.setValue(this.readGameDTO.getRuns_home_club());
            jSpinnerRV.setValue(this.readGameDTO.getRuns_visitant());
            jSpinnerAudience.setValue(this.readGameDTO.getAudience());

            ReadPhaseDTO readPhaseDTO = ServicesLocator.PhaseServices.readAPhase(readAPhaseDTO);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date start_date = Date.from(readPhaseDTO.getStart_date().atStartOfDay(defaultZoneId).toInstant());
            Date actual_date = Date.from(this.readGameDTO.getDate().atStartOfDay(defaultZoneId).toInstant());
            jDate_Game.setMinSelectableDate(start_date);
            jDate_Game.setDate(actual_date);
            Date finish_date = Date.from(readPhaseDTO.getFinish_date().atStartOfDay(defaultZoneId).toInstant());
            jDate_Game.setMaxSelectableDate(finish_date);

            //recuperar id del equipo local
            String teamHC = this.readGameDTO.getHcTeamName();
            ArrayList<ReadTeamDTO> teamsListHC = ServicesLocator.TeamServices.readTeams();
            boolean foundTeamHC = false;
            int teamIdHC = -1;
            for (int i = 0; i < teamsListHC.size() && !foundTeamHC; i++)
            {
                if (teamsListHC.get(i).getTeam_name().equalsIgnoreCase(teamHC))
                {
                    foundTeamHC = true;
                    teamIdHC = teamsListHC.get(i).getTeam_id();
                    this.readATeamDTO = new FindTeamDTO(teamIdHC);
                }
            }

            //Validando que la audiencia del juego no sobrepase la capacidad del estadio del equipo local
            ReadStadiumDTO readStadiumDTO = ServicesLocator.StadiumServices.getStadiumByTeam(readATeamDTO);
            jSpinnerAudience.setModel(new SpinnerNumberModel(1, 1, readStadiumDTO.getCapacity(), 1));
        } catch (SQLException | ClassNotFoundException ex) {
            UtilsConnector.viewMessagesUtils.showConecctionErrorMessage(this, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabelHomeClub = new javax.swing.JLabel();
        jComboBoxHomeClub = new javax.swing.JComboBox<>();
        jLabelVisitor = new javax.swing.JLabel();
        jComboBoxVisitor = new javax.swing.JComboBox<>();
        jLabelHomeClub1 = new javax.swing.JLabel();
        jSpinnerRHC = new javax.swing.JSpinner();
        jLabelVisitor1 = new javax.swing.JLabel();
        jSpinnerRV = new javax.swing.JSpinner();
        jLabelDate = new javax.swing.JLabel();
        jDate_Game = new com.toedter.calendar.JDateChooser();
        jSpinnerAudience = new javax.swing.JSpinner();
        jLabelAudience = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();
        jButtonInsert = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insertar Juego");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHomeClub.setText("Equipo Local:");

        jComboBoxHomeClub.setEnabled(false);
        jComboBoxHomeClub.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxHomeClubActionPerformed(evt);
            }
        });
        jComboBoxHomeClub.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jComboBoxHomeClubKeyReleased(evt);
            }
        });

        jLabelVisitor.setText("Equipo Visitante:");

        jComboBoxVisitor.setEnabled(false);
        jComboBoxVisitor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxVisitorActionPerformed(evt);
            }
        });

        jLabelHomeClub1.setText("Carreas equipo local:");

        jSpinnerRHC.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        jLabelVisitor1.setText("Carreras equipo visitante:");

        jSpinnerRV.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        jLabelDate.setText("Fecha:");

        jSpinnerAudience.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabelAudience.setText("Audiencia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelHomeClub1)
                        .addGap(31, 31, 31)
                        .addComponent(jSpinnerRHC)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelHomeClub)
                                .addGap(69, 69, 69)
                                .addComponent(jComboBoxHomeClub, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelVisitor)
                                .addGap(52, 52, 52)
                                .addComponent(jComboBoxVisitor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelVisitor1)
                                .addGap(8, 8, 8)
                                .addComponent(jSpinnerRV))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelDate)
                                .addGap(106, 106, 106)
                                .addComponent(jDate_Game, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelAudience)
                                .addGap(84, 84, 84)
                                .addComponent(jSpinnerAudience, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHomeClub)
                    .addComponent(jComboBoxHomeClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVisitor)
                    .addComponent(jComboBoxVisitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHomeClub1)
                    .addComponent(jSpinnerRHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVisitor1)
                    .addComponent(jSpinnerRV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDate)
                    .addComponent(jDate_Game, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAudience)
                    .addComponent(jSpinnerAudience, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 300, 200));

        jButtonClose.setText("Cerrar");
        jButtonClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonInsert.setText("Aceptar");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonInsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jButtonInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClose)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose)
                    .addComponent(jButtonInsert))
                .addGap(5, 5, 5))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 300, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        int audience = (int) jSpinnerAudience.getValue();
        
        Date date = jDate_Game.getDate();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant iStart = date.toInstant();
        LocalDate game_date = iStart.atZone(defaultZoneId).toLocalDate();

        int runs_home_club = (int) jSpinnerRHC.getValue();
        int runs_visitant = (int) jSpinnerRV.getValue();
        try {

            //recuperar id del equipo local
            String teamHC = readGameDTO.getHcTeamName();
            ArrayList<ReadTeamDTO> teamsListHC = ServicesLocator.TeamServices.readTeams();
            boolean foundTeamHC = false;
            int teamIdHC = -1;
            for (int i = 0; i < teamsListHC.size() && !foundTeamHC; i++) {
                if (teamsListHC.get(i).getTeam_name().equalsIgnoreCase(teamHC)) {
                    foundTeamHC = true;
                    teamIdHC = teamsListHC.get(i).getTeam_id();
                    this.readATeamDTO = new FindTeamDTO(teamIdHC);
                }
            }

            //recuperar id del equipo visitante
            String teamVis = readGameDTO.getVisTeamName();
            ArrayList<ReadTeamDTO> teamsListVis = ServicesLocator.TeamServices.readTeams();
            boolean foundTeamV = false;
            int teamIdVis = -1;
            for (int i = 0; i < teamsListVis.size() && !foundTeamV; i++) {
                if (teamsListVis.get(i).getTeam_name().equalsIgnoreCase(teamVis)) {
                    foundTeamV = true;
                    teamIdVis = teamsListVis.get(i).getTeam_id();
                }
            }

            //Determinando ganador
            int winner = runs_home_club > runs_visitant ? teamIdHC : teamIdVis;
            
            //BUSCAR CODIGO DE JUEGO
            LinkedList<ReadGameDTO> list_games = new LinkedList<>(ServicesLocator.GameServices.readAllGamesByPhase(readAPhaseDTO));
                    boolean found_game = false;
                    int game_id = -1;
                    Iterator<ReadGameDTO> it_games = list_games.iterator();
            ReadGameDTO readGameDTO_it = null;
                    while (it_games.hasNext() && !found_game)
                    {
                        
                        readGameDTO_it = it_games.next();
                        System.out.println(readGameDTO_it.getHcTeamName() + readGameDTO_it.getVisTeamName());
                        if (readGameDTO_it.getHcTeamName().equalsIgnoreCase(teamHC) && readGameDTO_it.getVisTeamName().
                            equalsIgnoreCase(teamVis))
                        {
                            found_game = true;
                            game_id = readGameDTO_it.getId();
                        }
                    }
                    
            //Si se encuentra el juego, se llama al servicio de modifcar
             if (found_game == true && validate_runs() && validate_audience() && validate_date())
             {
                UpdateGameDTO updateGameDTO = new UpdateGameDTO(game_id, teamIdHC, teamIdVis, readAPhaseDTO.
                                                                getPhase_id(),
                                                                game_date, winner, audience,
                                                                runs_home_club, runs_visitant);
                ServicesLocator.GameServices.updateGame(updateGameDTO);
                UtilsConnector.viewMessagesUtils.showSuccessMessage(rootPane, "Juego modificado satisfactoriamente");
                ((SerieUI) this.getParent()
                ).update_tables();   
                this.dispose();
            }

   
        } catch (SQLException|ClassNotFoundException ex) {
            UtilsConnector.viewMessagesUtils.showConecctionErrorMessage(rootPane, ex);
        }        

    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jComboBoxVisitorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxVisitorActionPerformed
    {//GEN-HEADEREND:event_jComboBoxVisitorActionPerformed

    }//GEN-LAST:event_jComboBoxVisitorActionPerformed

    private void jComboBoxHomeClubActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxHomeClubActionPerformed
    {//GEN-HEADEREND:event_jComboBoxHomeClubActionPerformed
         
    }//GEN-LAST:event_jComboBoxHomeClubActionPerformed

    private void jComboBoxHomeClubKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jComboBoxHomeClubKeyReleased
    {//GEN-HEADEREND:event_jComboBoxHomeClubKeyReleased
        if (!jComboBoxHomeClub.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>"))
        {
        }
    }//GEN-LAST:event_jComboBoxHomeClubKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JComboBox<String> jComboBoxHomeClub;
    private javax.swing.JComboBox<String> jComboBoxVisitor;
    private com.toedter.calendar.JDateChooser jDate_Game;
    private javax.swing.JLabel jLabelAudience;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelHomeClub;
    private javax.swing.JLabel jLabelHomeClub1;
    private javax.swing.JLabel jLabelVisitor;
    private javax.swing.JLabel jLabelVisitor1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinnerAudience;
    private javax.swing.JSpinner jSpinnerRHC;
    private javax.swing.JSpinner jSpinnerRV;
    // End of variables declaration//GEN-END:variables

    public boolean validate_runs()
    {
        boolean result = true;
        int runs_hc = (int) this.jSpinnerRHC.getValue();
        int runs_v = (int) this.jSpinnerRV.getValue();

        if (runs_hc == runs_v)
        {
            UtilsConnector.viewMessagesUtils.showErrorMessage(rootPane,
                                                              "El juego no puede quedar con igualdad en el marcador");
            result = false;
        }
        return result;
    }
    
    public boolean validate_date(){
        boolean result = true;
        try
        {

            Date date = jDate_Game.getDate();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant iStart = date.toInstant();
            LocalDate game_date = iStart.atZone(defaultZoneId).toLocalDate();
            ReadPhaseDTO readPhaseDTO = ServicesLocator.PhaseServices.readAPhase(readAPhaseDTO);
            if (game_date.isBefore(readPhaseDTO.getStart_date()) || game_date.isAfter(game_date))
            {
                UtilsConnector.viewMessagesUtils.showErrorMessage(rootPane,
                                                                  "La fecha del juego se debe corresponder con las fechas de la fase correspondiente");
                result = false;
            } 
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            UtilsConnector.viewMessagesUtils.showConecctionErrorMessage(rootPane, ex);
        }
        return result;
    }

    public boolean validate_audience() throws SQLException, ClassNotFoundException
    {
        boolean result = true;
        //La audiencia debe ser inferior a la capacidad del estadio
        ReadStadiumDTO readStadiumDTO = ServicesLocator.StadiumServices.getStadiumByTeam(readATeamDTO);
        int capacity = readStadiumDTO.getCapacity();
        int audience = (int) this.jSpinnerAudience.getValue();
        if (audience > capacity)
        {
            UtilsConnector.viewMessagesUtils.showErrorMessage(rootPane,
                                                              "La audiencia del juego no puede ser superior a la capacidad del estadio");
            result = false;
        }

        return result;
    }
}
