package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanel extends JPanel {
    private static int numberOfEquipment = 0;
    private static JPanel panel;

    public EquipmentPanel(){
        int offset = numberOfEquipment * 60;
        setSize(panel.getWidth()-40, 50);
        setLocation(20,5 + offset);
        setBackground(Color.GRAY);
        panel.add(this);
        numberOfEquipment++;
    }

    public static void setPanel(JPanel newPanel){
        panel = newPanel;
    }

    public static int getOffset(){
        return numberOfEquipment;
    }
}
