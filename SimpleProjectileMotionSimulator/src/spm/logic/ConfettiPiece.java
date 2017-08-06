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
public class ConfettiPiece {
    private double x;
    private double y;
    private double angle;
    private double velocity;
    private double vx;
    private double vy;
    private double a;
    private double t;
    
    public ConfettiPiece(double angle, double velocity, double x, double y) {
        this.angle = angle;
        this.velocity = velocity;
        this.x = x;
        this.y = y;
        
        this.a = -10;
        this.t = 0.1;
        
        this.vx = velocity * Math.cos(Math.toRadians(angle));
        this.vy = velocity * Math.sin(Math.toRadians(angle));
    }
    
    public void move() {
        moveY();
        moveX();
    }
    
    private void moveX() {
        x += vx * t;
    }
    
    private void moveY() {
        y -= vy * t;
        vy += a * t;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
}
