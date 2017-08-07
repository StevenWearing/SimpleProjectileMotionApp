/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.gui;

import spm.logic.Projectile;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import spm.logic.ProjectileUpdater;

import java.util.Random;
/**
 *
 * @author steven
 */
public class ControlPanelListener implements ActionListener {
    
    private JButton fire;
    private JTextField velocity;
    private JTextField angle;
    private JRadioButton orange;
    private JRadioButton blue;
    private JRadioButton white;
    private JRadioButton wallsOn;
    private JRadioButton wallsOff;
    private ProjectileUpdater pU;
    private ControlPanel cpl;
    private JButton party;
    
    private Random rnd = new Random();
    
    public ControlPanelListener(ProjectileUpdater pU, ControlPanel cpl) {
        this.pU = pU;
        this.cpl = cpl;
        
        fire = cpl.getFire();
        velocity = cpl.getVelocity();
        angle = cpl.getAngle();
        orange = cpl.getO();
        blue = cpl.getB();
        white = cpl.getW();
        wallsOn = cpl.getOn();
        wallsOff = cpl.getOff();
        party = cpl.getParty();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fire) {
            if (velocity.getText().isEmpty() || angle.getText().isEmpty()) {
                return;
            }
            
            int a = Integer.parseInt(angle.getText());
            int v = Integer.parseInt(velocity.getText());
        
            if (a >= 0 && a <= 90 && v >= 0) {
                Projectile p = new Projectile(Math.toRadians(a), v);
                pU.addProjectile(p);
            }
        }
        
        if (orange.isSelected()) {
            pU.getUpdatable().backColour("orange");
        } else if (blue.isSelected()) {
            pU.getUpdatable().backColour("blue");
        } else if (white.isSelected()) {
            pU.getUpdatable().backColour("white");
        }
        
        if (wallsOn.isSelected()) {
            pU.setWalls(true);
        } else if (wallsOff.isSelected()) {
            pU.setWalls(false);
        }
        
        if (ae.getSource() == party) {
            pU.makeConfetti();
            pU.removeProjectiles();
            pU.getUpdatable().setParty(true);
        }
    }
}
