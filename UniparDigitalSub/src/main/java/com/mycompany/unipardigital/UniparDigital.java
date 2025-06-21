package com.mycompany.unipardigital;

import javax.swing.JFrame;
import view.ViewPrincipal;


public class UniparDigital {

    public static void main(String[] args) {
        
        ViewPrincipal view = new ViewPrincipal();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
                
        
    }
}
