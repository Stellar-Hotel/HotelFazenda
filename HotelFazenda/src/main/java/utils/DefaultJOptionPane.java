package utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class DefaultJOptionPane {

	public static void test() {
		UIManager.put("OptionPane.background", new ColorUIResource(new Color(245, 245, 245)));

		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
		UIManager.put("OptionPane.messageForeground", new Color(50, 50, 50));
	}
}
