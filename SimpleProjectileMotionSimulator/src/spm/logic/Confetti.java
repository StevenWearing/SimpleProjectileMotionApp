/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author steven
 */
public class Confetti {
    
    private List<ConfettiPiece> confetti;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private Random rnd;
    
    public Confetti(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        
        rnd = new Random();
        confetti = new ArrayList<ConfettiPiece>();
    }
    
    public void makeConfetti() {
        for (int i = 0; i < 50; i++) {
                confetti.add(new ConfettiPiece(rnd.nextInt(360),  30, x, y));
        }
    }
    
    public void move() {
        for (ConfettiPiece p : confetti) {
            p.move();
        }
    }
    
    public List<ConfettiPiece> getConfetti() {
        return confetti;
    }
    
    public double getVx() {
        return vx;
    }
    
    public double getVy() {
        return vy;
    }
}
