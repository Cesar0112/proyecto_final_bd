/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu.edu.cujae.structbd.utils;

import cu.edu.cujae.structbd.visual.batter.BatterUI;

import cu.edu.cujae.structbd.visual.coach.Coach_UI;
import cu.edu.cujae.structbd.visual.game.GameUI;
import cu.edu.cujae.structbd.visual.phase.PhaseUI;

import cu.edu.cujae.structbd.visual.pitcher.PitcherUI;

import cu.edu.cujae.structbd.visual.player.Player_UI;
import cu.edu.cujae.structbd.visual.position.PositionUI;
import cu.edu.cujae.structbd.visual.stadium.StadiumUI;
import cu.edu.cujae.structbd.visual.team.TeamUI;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author Hector Angel Gomez
 */
public class ViewUtils {
    private final HashMap<String, JFrame> views = new HashMap<>();
    
    public ViewUtils()
    {
        
        try{
            this.views.put("Stadium", new StadiumUI());
            this.views.put("Position", new PositionUI());
            this.views.put("Pitcher", new PitcherUI());
            this.views.put("Phase", new PhaseUI());
            this.views.put("Game", new GameUI());
            this.views.put("Coach", new Coach_UI());
            this.views.put("Team", new TeamUI());
            this.views.put("Batter", new BatterUI());
            this.views.put("Player", new Player_UI());
        }
        catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex);
            System.out.println("Error por falta de Backup");
        }catch (Exception ex){
            System.out.println("Error por falta de Backup");
        }
    }
    
    public HashMap<String, JFrame> getViews(){
        return this.views;
    }
}
