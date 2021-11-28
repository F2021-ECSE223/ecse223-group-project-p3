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
        mainFrame = new JFrame("ClimbSafe");
        mainFrame.setPreferredSize(new Dimension(1920, 1080));
        //mainFrame.setIconImage();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        addNMCCard();
        addMemberCard();
        addGuideCard();
        addEquipmentCard();
        addBundleCard();
        addAssignmentCard();
        addPayCard();
        addTripCard();
        
        mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public static void addNMCCard(){
        JPanel card1 = new JPanel() {};
        
        tabbedPane.addTab("Setup NMC", card1);
    }
    public static void addMemberCard(){
        JPanel card2 = new JPanel() {};
        
        tabbedPane.addTab("Register, Update and Delete Member", card2);
    }
    public static void addGuideCard(){
        JPanel card3 = new JPanel() {};
        
        tabbedPane.addTab("Register, Update and Delete Guide", card3);
    }
    public static void addEquipmentCard(){
        JPanel card4 = new JPanel() {};
        
        tabbedPane.addTab("Add, Update and Delete Equipment", card4);
    }
    public static void addBundleCard(){
        JPanel card5 = new JPanel() {};
        
        tabbedPane.addTab("Add, Update and Delete Equipment Bundle", card5);
    }
    public static void addAssignmentCard(){
        JPanel card6 = new JPanel() {};
        
        tabbedPane.addTab("Initiate and View Assignments", card6);
    }
    public static void addPayCard(){
        JPanel card7 = new JPanel() {};
        
        tabbedPane.addTab("Pay for Member's Trip", card7);
    }
    public static void addTripCard(){
        JPanel card8 = new JPanel() {};
        
        tabbedPane.addTab("Start trips, finish a trip and cancel a trip", card8);
    }
}
