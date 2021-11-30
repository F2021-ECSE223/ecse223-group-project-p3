package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import java.awt.*;

public class NewEquipmentButton extends JButton{
    private static JPanel panel;
    public NewEquipmentButton(){
        setText("Add new equipment (+)");
        //int offset = EquipmentPanel.getOffset() * 60;
        setSize(panel.getWidth()-40, 50);
        //setLocation(20,5 + offset);
        panel.add(this);
    }
    public static void setPanel(JPanel newPanel){
        panel = newPanel;
    }

    public void moveButton(NewEquipmentButton b){
        panel.remove(b);
    }
}
