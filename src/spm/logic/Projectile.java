/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.logic;

/**
 *
 * @author steven
 * 
 * Logic for the current state of 
 * any created particles and their
 * movement behaviour
 */
public class Projectile {
    
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double vyMax;
    private double t;
    private double a;
    private Confetti confetti;
    private boolean walls;
    
    public Projectile(double angle, double initialVelocity) {
        this.x = 0;
        this.y = 650;
        
        this.t = 0.1;         // Time interval between each increase of velocity
        this.a = -10;         // Gravitaional constant
        
        this.vx = initialVelocity*Math.cos(angle);    // Obtaining horizontal velocity component
        this.vy = initialVelocity*Math.sin(angle);    // Obtaining vertical velocity component
        vyMax = vy;          // Set max velocity to ensure no invalid behaviours
        
        this.walls = true;
    }
    
    public void move() {
        if (walls) {         // Check for any walls collision
            wall();
        } if (vy == 0) {     // If vertical velocity has reduced to 0 before reaching top
            vy *= -1;         // reverse velocity so acceleration increases it
        }
        
        moveX();
        moveY();
    }
    
    private void moveX() {
        x += vx * t;
    }
    
    private void moveY() {
        y -= vy * t;                  
        
        double vyBefore = vy;             
        vy += a * t;                  // Reduce vertical velocity by gravitational constant a (m/s^2) * time increment t (s)
        
        if (Math.abs(vy) > vyMax) {        // Limit velocity to initial velocity as maximum value
            vy = vyBefore;
        }
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setWalls(boolean onOff) {
        this.walls = onOff;                // Turn boundary conditions on or off
    }
    
    private void wall() {
        if (y < 0) {             // If projectile passes TOP border
            vy *= -1;            // reverse direction and ensure to start from boundary
            y = 0;               
        } else if (y > 650) {    // If projectile passes BOTTOM border...
            vy *= -1;
            y = 650;
        }
        
        if (x < 0) {             // If projectile passes LEFT border...
            vx *= -1;
            x = 0;
        } else if (x > 1030) {    // If projectile passes RIGHT border...
            vx *= -1;
            x = 1030;
        }
    }
    
    public void makeConfetti() {
        this.confetti = new Confetti(x, y, vx, vy);     // Creates new confetti ArrayList using current
        this.confetti.makeConfetti();                   // x & y positions and velocities
    }
    
    public Confetti getConfetti() {
        return confetti;
    }
}
