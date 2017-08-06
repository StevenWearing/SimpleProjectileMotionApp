/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.logic;

import spm.gui.Updatable;
import spm.gui.Confetti;
import spm.gui.Projectile;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author steven
 */
public class ProjectileUpdater extends Timer implements ActionListener {
    
    private List<Projectile> projectiles;
    private Updatable updatable;
    private boolean walls;
    private boolean party;
    private boolean collisions;
    private List<Confetti> confettis;
    
    public ProjectileUpdater() {
        super(1000, null);
        addActionListener(this);
        setDelay(10);
        
        this.projectiles = new ArrayList<Projectile>();
        this.walls = true;
        this.party = false;
        this.collisions = false;
        this.confettis = new ArrayList<Confetti>();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (collisions) {
            collisionCheck();
        }
        
        if (projectiles.isEmpty()) {
            // adding "return;" gives a pause effect for confetti
        } else {
            for (Projectile p : projectiles) {
                p.move();
            }
            updatable.update();
        }
        
        if (confettis.isEmpty()) {
            return;
        } else {
            for (Confetti c : confettis) {
                c.move();
            }
            updatable.update();
        }
        
        if (!walls) {
            pastWallRemoval();
        }
    }
    
    
    
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }
    
    public List<Projectile> getProjectiles() {
        return projectiles;
    }
    
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
    
    public Updatable getUpdatable() {
        return updatable;
    }
    
    public void setWalls(boolean onOff) {
        for (Projectile p : projectiles) {
            p.setWalls(onOff);
        }
        
        this.walls = onOff;
    }
    
    private void pastWallRemoval() {
        Iterator<Projectile> iterator = projectiles.iterator();
        
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            if (p.getX() < -1 || p.getY() < -1 || p.getY() > 700 || p.getX() > 1100) {
                iterator.remove();
            }
        }
    }
    
    public void setParty(boolean onOff) {
        this.party = onOff;
    }
                
    public void makeConfetti() {
        for (Projectile p : projectiles) {
                p.makeConfetti();
                confettis.add(p.getConfetti());
            }
    }
    
    public List<Confetti> getConfetti() {
        return confettis;
    }
    
    public void removeProjectiles() {
        projectiles.clear();
    }
    
    public void setCollisions(boolean onOff) {
        this.collisions = onOff;
    }
    
    private void collisionCheck() {
        for (Projectile p : projectiles) {
            for (Projectile other : projectiles) {
                if (p == other) {
                    continue;
                }
                
                if (Math.abs(p.getX() - other.getX()) <= 5
                        && Math.abs(p.getY() - other.getY()) <= 5 
                        && p.getCollision() == false) {
                    
                    p.setCollision(other, true);
                }
                
                //if (Math.abs(p.getX() - other.getX()) == 0
                  //      && Math.abs(p.getY() - other.getY()) == 0
                    //    && p.getCollision() == false) {
                //    p.setCollision(other, false);
                //}
            }
        }
    }
}
