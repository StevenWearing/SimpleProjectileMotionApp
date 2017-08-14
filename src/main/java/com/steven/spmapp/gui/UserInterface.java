/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steven.spmapp.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import com.steven.spmapp.logic.ProjectileUpdater;
/**
 *
 * @author steven
 */
public class UserInterface implements Runnable {
    
    private JFrame frame;
    private ProjectileUpdater pU;
    private DrawingBoard board;
    
    public UserInterface(ProjectileUpdater pU) {
        this.pU = pU;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Simple Projectile Motion Simulator");
        frame.setPreferredSize(new Dimension(1300, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        board = new DrawingBoard(pU);
        container.add(board);
        
        ControlPanel cPanel = new ControlPanel(pU);
        container.add(cPanel, BorderLayout.WEST);
    }
    
    public Updatable getUpdatable() {
        return board;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
