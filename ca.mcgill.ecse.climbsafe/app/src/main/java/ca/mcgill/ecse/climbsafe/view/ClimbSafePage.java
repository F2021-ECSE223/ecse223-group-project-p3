package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;



public class ClimbSafePage {
    private static JFrame mainFrame;
    private static JTabbedPane tabbedPane;
    

    public static void start(){
        //UIManager.put("swing.boldMetal", Boolean.FALSE); //for some reason this is key
        mainFrame = new JFrame("ClimbSafe");
        mainFrame.setPreferredSize(new Dimension(1920, 1080));
        //mainFrame.setIconImage();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        addAdminCard();
        
        mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public static void addAdminCard(){
        JPanel card1 = new JPanel() {};
        
        tabbedPane.addTab("Admin Page", card1);
    }
}
