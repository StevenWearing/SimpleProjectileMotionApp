/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.gui;

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
    private boolean transparant;
    private boolean collision;
    private Projectile other;
    
    private int color;
    
    public Projectile(double angle, double initialVelocity) {
        this.x = 5;
        this.y = 650;
        
        this.t = 0.1;
        this.a = -10;
        
        this.vx = initialVelocity*Math.cos(angle);
        this.vy = initialVelocity*Math.sin(angle);
        vyMax = vy;
        
        this.walls = true;
        this.transparant = true;
        this.collision = false;
    }
    
    public void color(int color) {
        this.color = color;
    }
    
    public int getColor() {
        return color;
    }
    
    public void move() {
        if (walls) {
            wall();
        }
        
        if (collision) {
            collision();
        }
        
        moveX(true);
        moveY(true);
    }
    
    public void reverseMove(boolean forward) {
        moveX(forward);
        moveY(forward);
    }
    
    private void moveX(boolean forward) {
        if (forward) {
            x += vx * t;
        } else {
            x -= vx * t;
        }
    }
    
    private void moveY(boolean forward) {
        if (forward) {
            y -= vy * t;
        
            double vyBefore = vy;
            vy += a * t;
            if (Math.abs(vy) > vyMax) {
                vy = vyBefore;
            }
        } else {
            y += vy * t;
        
            double vyBefore = vy;
            vy -= a * t;
            if (Math.abs(vy) > vyMax) {
                vy = vyBefore;
            }
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
        if (vy == 0) {// || y <= 0) { 
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
    
    public void setCollision(Projectile other, boolean onOff) {
        this.collision = onOff;
        this.other = other;
    }
    
    public boolean getCollision() {
        return collision;
    }
    
    public void collision() {
        //if (vx < 0 && vy < 0) {
                
          //  } else if (vx < 0 && vy > 0) {
                
            //} else if (vx > 0 && vy > 0 && other.vy > 0 && other.vx > 0 && other.vy > 0) {
                double xBefore = other.x;
                double yBefore = other.y;
                other.reverseMove(false);
                double xAfter = other.x;
                double yAfter = other.y;
                other.reverseMove(true);
                while (Math.abs(x - other.x) <= 5) { 
                    if (xAfter > xBefore && yAfter < yBefore) {//coming towards up and right
                        other.x -= 0.1 * other.vx;
                        other.y += 0.1 * other.vy;
                    } else if (xAfter < xBefore && yAfter > yBefore) { //coming towards down and right
                        other.x -= 0.1 * other.vx;
                        other.y -= 0.1 * other.vy;
                    } else if (xAfter > xBefore && yAfter > yBefore) { //coming towards down and left
                        other.x += 0.1 * other.vx;
                        other.y -= 0.1 * other.vy;
                    } else { // coming towards up and left
                        other.x += 0.1 * other.vx;
                        other.y += 0.1 * other.vy;
                    }
                }
                        
            
                double vxOld = vx;
                double vyOld = vy;
                vx = other.vx;
                other.vx = vxOld;
                
                vy = other.vy;
                other.vy = vyOld;
                
            //} else if (vx > 0 && vy < 0) {
                
            //}
            
            this.collision = false;
    }
    
    public void makeConfetti() {
        this.confetti = new Confetti(x, y, vx, vy);
        this.confetti.makeConfetti();
    }
    
    public Confetti getConfetti() {
        return confetti;
    }
}
