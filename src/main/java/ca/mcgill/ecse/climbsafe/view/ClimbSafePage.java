package ca.mcgill.ecse.climbsafe.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class ClimbSafePage extends JFrame{
    NewEquipmentButton neb = null;
    ArrayList<EquipmentPanel> allEquipments = new ArrayList<>();
    public ClimbSafePage() throws IOException {
        JLabel title = new JLabel("Neptan Mountain Climbing");
        title.setBounds(650,50, 1920,30);
        JLabel equipmentTitle = new JLabel("Add/Update/Delete Equipment");
        equipmentTitle.setBounds(640,100, 1920,30);
        JPanel equipmentPanel = new JPanel();
        equipmentPanel.setLayout(null);
        equipmentPanel.setBounds(230, 150, 1000, 600);
        equipmentPanel.setBackground(Color.white);
        NewEquipmentButton.setPanel(equipmentPanel);
        EquipmentPanel.setPanel(equipmentPanel);
        NewEquipmentButton firstButton = new NewEquipmentButton();
        neb = firstButton;
        setSize(1920,1080);
        add(title);
        add(equipmentTitle);
        add(equipmentPanel);
        setLayout(null);
        setVisible(true);
        firstButton.addActionListener(this::addEquipment);
    }

    private void addEquipment(ActionEvent actionEvent) {
        EquipmentPanel ep = new EquipmentPanel();
        NewEquipmentButton b = new NewEquipmentButton();
        allEquipments.add(ep);
        neb.moveButton(neb);
        neb = b;
        b.addActionListener(this:: addEquipment);
        repaint();
        revalidate();
    }

}
