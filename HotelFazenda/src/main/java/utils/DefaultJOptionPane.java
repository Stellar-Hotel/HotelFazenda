package utils;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class DefaultJOptionPane {

    public static void test() {
    	   UIManager.put("OptionPane.background", new ColorUIResource(new Color(245, 245, 245)));
        
           UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
           UIManager.put("OptionPane.messageForeground", new Color(50, 50, 50));
    }
}
