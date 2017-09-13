/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.steven.spmapp.logic;

/**
 *
 * @author steven
 */
public class ConfettiPiece {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double a;
    private double t;
    
    public ConfettiPiece(double angle, double initialVelocity, double x, double y) {
        this.x = x;
        this.y = y;
        
        this.a = -10;      // Gravitaional constant
        this.t = 0.1;      // Time interval between each change of velocity
        
        this.vx = initialVelocity * Math.cos(Math.toRadians(angle));   // Obtaining horizontal velocity component
        this.vy = initialVelocity * Math.sin(Math.toRadians(angle));   // Obtaining vertical velocity component
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
