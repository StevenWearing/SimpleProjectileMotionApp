/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import spm.logic.ProjectileUpdater;
import java.awt.Dimension;
/**
 *
 * @author steven
 */
public class ControlPanel extends JPanel {
    
    private JTextField velocity;
    private JTextField angle;
    private JButton fire;
    private JRadioButton orange;
    private JRadioButton blue;
    private JRadioButton white;
    private JRadioButton on;
    private JRadioButton off;
    private ProjectileUpdater pU;
    private JButton party;
    private Color panelColor;
    
    public ControlPanel(ProjectileUpdater pU) {
        this.pU = pU;
        
        super.setBackground(new Color(220, 220, 220));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        setLayout(new BorderLayout());
        panelColor = new Color(200, 200, 200);
        
        // Adjustment panel borders
        JLabel borderRight = new JLabel(" ");
        JLabel borderLeft = new JLabel(" ");
        JLabel borderTop = new JLabel(" ");
        JLabel borderBottom = new JLabel(" ");
        borderLeft.setPreferredSize(new Dimension(5, 700));
        borderRight.setPreferredSize(new Dimension(5, 700));
        borderTop.setPreferredSize(new Dimension(200, 5));
        borderBottom.setPreferredSize(new Dimension(200, 5));
        
        JPanel p = controls();
        JPanel firePanel = firingPanel();
        
        add(borderRight, BorderLayout.EAST);
        add(borderLeft, BorderLayout.WEST);
        add(borderTop, BorderLayout.NORTH);
        add(firePanel, BorderLayout.SOUTH);
        add(p);
        
        addActionListeners();
    }
    
    private JPanel controls() {
        JPanel p = new JPanel(new GridLayout(6, 2));
        p.setBackground(panelColor);
        
        JLabel v = new JLabel("Intial velocity (m/s):");
        JPanel vPanel = velocity();
        
        JLabel theta = new JLabel("Angle fired at (degrees):");
        JPanel aPanel = angle();
        
        JLabel elastic = new JLabel("Elastic walls:");
        JPanel onOffPanel = elastic();
        
        JLabel colour = new JLabel("Background colour:");
        JPanel colourPanel = colourPanel();
        
        p.add(v);
        p.add(vPanel);
        p.add(theta);
        p.add(aPanel);
        p.add(elastic);
        p.add(onOffPanel);
        p.add(colour);
        p.add(colourPanel);
        p.add(new JLabel());
        p.add(new JLabel());
        
        return p;
    }
    
    public void addActionListeners() {
        ControlPanelListener cpl = new ControlPanelListener(pU, this);
        
        orange.addActionListener(cpl);
        blue.addActionListener(cpl);
        white.addActionListener(cpl);
        on.addActionListener(cpl);
        off.addActionListener(cpl);
        fire.addActionListener(cpl);
        party.addActionListener(cpl);
    }
    
    private JPanel elastic() {
        JPanel onOffPanel = new JPanel(new GridLayout(3, 3));
        onOffPanel.setBackground(panelColor);
        
        ButtonGroup onOffGroup = new ButtonGroup();
        on = new JRadioButton("On");
        on.setBackground(panelColor);
        on.setSelected(true);
        off = new JRadioButton("Off");
        off.setBackground(panelColor);
        onOffGroup.add(on);
        onOffGroup.add(off);
        
        for (int i = 0; i < 9; i++) {
            if (i == 3) {
                onOffPanel.add(on, BorderLayout.WEST);
            } else if (i == 4) {
                onOffPanel.add(off, BorderLayout.CENTER);
            } else {
                onOffPanel.add(new JLabel());
            }
        }
        
        return onOffPanel;
    }
    
    private JPanel velocity() {
        JPanel vPanel = new JPanel(new GridLayout(3, 3));
        vPanel.setBackground(panelColor);
        
        velocity = new JTextField();
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                vPanel.add(velocity);
            } else {
                vPanel.add(new JLabel());
            }
        }
        
        return vPanel;
    }
    
    private JPanel angle() {
        JPanel aPanel = new JPanel(new GridLayout(3, 3));
        aPanel.setBackground(panelColor);
        
        angle = new JTextField();
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                aPanel.add(angle);
            } else {
                aPanel.add(new JLabel());
            }
        }
        
        return aPanel;
    }
    
    private JPanel firingPanel() {
        JPanel firePanel = new JPanel();
        
        firePanel.setPreferredSize(new Dimension(100, 100));
        firePanel.setBackground(panelColor);
        fire = new JButton("FIRE");
        
        party = new JButton("PARTY");
        //pause = new JButton("PAUSE");
        //unpause = new JButton("UN-PAUSE");
        
        fire.setPreferredSize(new Dimension(200, 80));
        firePanel.add(fire, BorderLayout.CENTER);
        firePanel.add(party, BorderLayout.NORTH);
        JLabel border1 = new JLabel(" ");
        
        return firePanel;
    }
    
    private JPanel colourPanel() {
        JPanel colourPanel = new JPanel(new GridLayout(3, 2));
        
        colourPanel.setBackground(panelColor);
        ButtonGroup colourGroup = new ButtonGroup();
        orange = new JRadioButton("Orange");
        blue = new JRadioButton("Blue");
        white = new JRadioButton("White");
        orange.setBackground(panelColor);
        blue.setBackground(panelColor);
        blue.setSelected(true);
        white.setBackground(panelColor);
        colourGroup.add(orange);
        colourGroup.add(blue);
        colourGroup.add(white);
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                colourPanel.add(orange, BorderLayout.NORTH);
            } else if (i == 2) {
                colourPanel.add(blue);
            } else if (i == 4) {
                colourPanel.add(white, BorderLayout.SOUTH);
            } else {
                colourPanel.add(new JLabel());
            }
        }
        
        return colourPanel;
    }
    
    public JRadioButton getOn() {
        return on;
    }
    
    public JRadioButton getOff() {
        return off;
    }
    
    public JRadioButton getO() {
        return orange;
    }
    
    public JRadioButton getB() {
        return blue;
    }
    
    public JRadioButton getW() {
        return white;
    }
    
    public JButton getFire() {
        return fire;
    }
    
    public JTextField getVelocity() {
        return velocity;
    }
    
    public JTextField getAngle() {
        return angle;
    }
    
    public JButton getParty() {
        return party;
    }
}
