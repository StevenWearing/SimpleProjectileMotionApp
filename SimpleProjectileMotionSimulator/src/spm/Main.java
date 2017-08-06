/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm;

import spm.gui.UserInterface;
import javax.swing.SwingUtilities;
import spm.logic.ProjectileUpdater;
/**
 *
 * @author steven
 */
public class Main {
    public static void main(String[] args) {
        ProjectileUpdater pU = new ProjectileUpdater();
        UserInterface ui = new UserInterface(pU);
        
        try {
            SwingUtilities.invokeAndWait(ui);
        } catch (Exception e) {
            
        }
        
        pU.setUpdatable(ui.getUpdatable());
        pU.start();
    }
    
}
