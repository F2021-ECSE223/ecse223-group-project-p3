package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanel extends JPanel {
    private static int numberOfEquipment = 0;

    public EquipmentPanel(){
        int offset = numberOfEquipment * 60;
        setLocation(20,5 + offset);
        setBackground(Color.GRAY);
        this.setLayout(null);
        numberOfEquipment++;
    }

}
