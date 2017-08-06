/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import spm.logic.ProjectileUpdater;
import java.util.Random;
/**
 *
 * @author steven
 */
public class DrawingBoard extends JPanel implements Updatable{
    
    private String colour;
    private List<Projectile> projectiles;
    private List<Confetti> confettis;
    private ProjectileUpdater pU;
    private boolean party;
    private Random rnd;
    private Color blue;
    private Color orange;
    private Color white;
    private boolean cone;
    
    public DrawingBoard(ProjectileUpdater pU) {
            this.pU = pU;
            this.party = false;
            this.rnd = new Random();
            this.projectiles = pU.getProjectiles();
            this.blue = new Color(150, 150, 220);
            this.orange = new Color(240, 190, 0);
            this.white = new Color(230, 230, 230);
            this.cone = true;
            
            super.setBackground(blue);
    }
    
    @Override 
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        if (!projectiles.isEmpty()) {
            paintProjectiles(graphics);
        }
        
        if (party) {
            if (cone) {
                anglePaintParty(graphics);
            } else {
                paintParty(graphics);
            }
        }
        
        
    }
    
    private void paintProjectiles(Graphics graphics) {
        projectiles = pU.getProjectiles();
        
        for (Projectile p : projectiles) {
            if (p.getColor() == 0) {
                graphics.setColor(Color.GREEN);
            } else if (p.getColor() == 1) {
                graphics.setColor(Color.WHITE);
            } else if (p.getColor() == 2) {
                graphics.setColor(Color.PINK);
            } else {
                graphics.setColor(Color.BLACK);
            }
            //graphics.setColor(Color.BLACK);
            graphics.fillOval((int)p.getX(), (int)p.getY(), 10, 10);
        }
    }
    
    private void paintParty(Graphics graphics) {
        confettis = pU.getConfetti();
        
        for (Confetti c : confettis) {
            List<ConfettiPiece> cfpl = c.getConfetti();
            for (ConfettiPiece cfp : cfpl) {
                graphics.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
                graphics.fillRect((int)cfp.getX(), (int)cfp.getY(), 5, 5);
            }
        }
    }
    
    private void anglePaintParty(Graphics graphics) {
        confettis = pU.getConfetti();
        
        for (Confetti c : confettis) {
            c.setCone(true);
            List<ConfettiPiece> cfpl = c.getConfetti();
            for (ConfettiPiece cfp : cfpl) {
                graphics.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
                graphics.fillRect((int)cfp.getX(), (int)cfp.getY(), 5, 5);
            }
        }
    }
    
    @Override
    public void update() {
        repaint();
    }
    
    @Override
    public void backColour(String colour) {
        if (colour.equals("orange")) {
            super.setBackground(orange);
        } else if (colour.equals("blue")) {
            super.setBackground(blue);
        } else if (colour.equals("white")) {
            super.setBackground(white);
        }
    }
    
    @Override
    public void setParty(boolean onOff) {
        this.party = onOff;
    }
}
