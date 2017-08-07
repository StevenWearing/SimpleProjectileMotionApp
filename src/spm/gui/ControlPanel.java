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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import spm.logic.ProjectileUpdater;
import java.awt.Dimension;
import javax.swing.BorderFactory;
/**
 *
 * @author steven
 * 
 * The Control Panel, within which the user
 * specifies the initial attributes of any
 * to-be-created particles, along with the
 * current state of the environment moved within
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
        
        JPanel p = controls();
        p.setBorder(BorderFactory.createRaisedBevelBorder());
        
        add(p);
        
        addActionListeners();
    }
    
    private JPanel controls() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        p.setBackground(panelColor);
        p.setPreferredSize(new Dimension(250, 0));
        
        // Velocity
        JLabel v = new JLabel("Intial velocity:");
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 1;
        c.gridy = 0;
        p.add(v, c);
        JPanel vPanel = velocity();
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        p.add(vPanel, c);
        
        // Angle
        JLabel theta = new JLabel("Initial angle (degrees):");
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        p.add(theta, c);
        JPanel aPanel = angle();
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        p.add(aPanel, c);
        
        // Elastic Walls
        JLabel elastic = new JLabel("Elastic walls:");
        //c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        p.add(elastic, c);
        JPanel onOffPanel = elastic();
        onOffPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        p.add(onOffPanel, c);
        
        // Colour
        JLabel colour = new JLabel("Background colour:");
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        p.add(colour, c);
        JPanel colourPanel = colourPanel();
        colourPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        p.add(colourPanel, c);
        
        // Firing panel
        JPanel firePanel = firingPanel();
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.7;
        c.gridwidth = 6;
        c.gridx = 1;
        c.gridy = 5;
        p.add(firePanel, c);
        
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
    
    
    
    private JPanel velocity() {
        JPanel vPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        vPanel.setBackground(panelColor);
        
        velocity = new JTextField();
        velocity.setPreferredSize(new Dimension(60, 35));
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        vPanel.add(velocity, c);
        
        return vPanel;
    }
    
    private JPanel angle() {
        JPanel aPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        aPanel.setBackground(panelColor);
        
        angle = new JTextField();
        angle.setPreferredSize(new Dimension(60, 35));
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        aPanel.add(angle, c);
        
        return aPanel;
    }
    
    private JPanel elastic() {
        JPanel onOffPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        onOffPanel.setBackground(panelColor);
        
        
        on = new JRadioButton("On");
        on.setBackground(panelColor);
        on.setSelected(true);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        onOffPanel.add(on, c);
        
        off = new JRadioButton("Off");
        off.setBackground(panelColor);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        onOffPanel.add(off, c);

        
        ButtonGroup onOffGroup = new ButtonGroup();
        onOffGroup.add(on);
        onOffGroup.add(off);
        
        return onOffPanel;
    }
    
    private JPanel colourPanel() {
        JPanel colourPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        colourPanel.setBackground(panelColor);
        
        
        orange = new JRadioButton("Orange");
        orange.setBackground(panelColor);
        c.anchor = GridBagConstraints.LINE_START;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        colourPanel.add(orange, c);
        
        blue = new JRadioButton("Blue");
        blue.setBackground(panelColor);
        blue.setSelected(true);
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        colourPanel.add(blue, c);
        
        white = new JRadioButton("White");
        white.setBackground(panelColor);
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        colourPanel.add(white, c);
        
        
        ButtonGroup colourGroup = new ButtonGroup();
        colourGroup.add(orange);
        colourGroup.add(blue);
        colourGroup.add(white);
        
        return colourPanel;
    }
    
    private JPanel firingPanel() {
        JPanel firePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        firePanel.setBackground(panelColor);
        
        fire = new JButton("FIRE");
        fire.setPreferredSize(new Dimension(100, 70));
        fire.setBorder(BorderFactory.createRaisedBevelBorder());
        c.gridx = 1;
        c.gridy = 1;
        firePanel.add(fire, c);
        
        party = new JButton("PARTY");
        party.setForeground(Color.RED);
        party.setPreferredSize(new Dimension(50, 35));
        party.setBorder(BorderFactory.createRaisedBevelBorder());
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        firePanel.add(party, c);
        
        return firePanel;
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
