/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.logic;

/**
 *
 * @author steven
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
        this.x = 5;
        this.y = 650;
        
        this.t = 0.1;
        this.a = -10;
        
        this.vx = initialVelocity*Math.cos(angle);
        this.vy = initialVelocity*Math.sin(angle);
        vyMax = vy;
        
        this.walls = true;
    }
    
    public void move() {
        if (walls) {
            wall();
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
        vy += a * t;
        
        if (Math.abs(vy) > vyMax) {
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
        this.walls = onOff;
    }
    
    private void wall() {
        if (vy == 0 || y <= 0) { 
                vy *= -1;
            } else if (y > 650) {
                vy *= -1;
                y = 650;
            }
        
            if (x <= 0) { 
                vx *= -1;
                x = 1;
            } else if (x > 950) {
                vx *= -1;
                x = 950;
            }
    }
    
    public void makeConfetti() {
        this.confetti = new Confetti(x, y, vx, vy);
        this.confetti.makeConfetti();
    }
    
    public Confetti getConfetti() {
        return confetti;
    }
}
