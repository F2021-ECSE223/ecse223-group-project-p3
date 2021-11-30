package ca.mcgill.ecse.climbsafe.view;


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.Equipment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;


public class ClimbSafePage extends JFrame{

    String addErrorMsg = "";
    Boolean successAdd = false;
    Boolean successUpdate = false;
    String updateErrorMsg = "";
    Boolean successDelete = false;
    String deleteErrorMsg = "";

    ///TODO switch with transfer objects
    List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();
    String[] equipmentListNames = new String[equipmentList.size()];

    private void updateEquipmentList(){
        List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();
        String[] tempList = new String[equipmentList.size()];
        for(int i = 0; i < equipmentList.size(); i++){
            tempList[i] = equipmentList.get(i).getName();
        }
        equipmentListNames = tempList;
    }

    public ClimbSafePage() throws IOException {

        updateEquipmentList();

        JLabel title = new JLabel("Neptan Mountain Climbing");
        title.setBounds(650,0, 1920,30);

        JLabel equipmentTitle = new JLabel("Add/Update/Delete Equipment");
        equipmentTitle.setBounds(640,50, 1920,30);

        //panel to add new equipments to the climbsafe
        JPanel addEquipmentPanel = new JPanel();
        addEquipmentPanel.setLayout(null);
        addEquipmentPanel.setBounds(230, 150, 1000, 150);
        addEquipmentPanel.setBackground(Color.white);

        JLabel addEquipmentPanelTitle = new JLabel("Add a new equipment to the ClimbSafe");
        addEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        addEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentNameField = new JTextField();
        equipmentNameField.setBounds(50, 60, 150, 30);
        JLabel equipmentNameText = new JLabel("Equipment Name");
        equipmentNameText.setBounds(equipmentNameField.getX()+3, equipmentNameField.getY()-15, 150, 15);
        equipmentNameText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentWeightField = new JTextField();
        equipmentWeightField.setBounds(equipmentNameField.getX() + equipmentNameField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentWeightText = new JLabel("Equipment Weight");
        equipmentWeightText.setBounds(equipmentWeightField.getX()+3, equipmentWeightField.getY()-15, 150, 15);
        equipmentWeightText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentPriceField = new JTextField();
        equipmentPriceField.setBounds(equipmentWeightField.getX() + equipmentWeightField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceText = new JLabel("Equipment Price");
        equipmentPriceText.setBounds(equipmentPriceField.getX()+3, equipmentPriceField.getY()-15, 150, 15);
        equipmentPriceText.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel errorMsgLabel = new JLabel(addErrorMsg);
        errorMsgLabel.setBounds(5, 130, 350, 15);
        errorMsgLabel.setForeground(Color.red);

        JLabel successMsgAddLabel = new JLabel("Successfully added equipment to ClimbSafe.");
        successMsgAddLabel.setBounds(710, 130, 300, 15);
        successMsgAddLabel.setForeground(Color.green);
        successMsgAddLabel.setVisible(false);

        JButton submitButton = new JButton("Add");
        submitButton.setBounds(equipmentPriceField.getX() + 180, equipmentNameField.getY(), 100, 30);

        addEquipmentPanel.add(successMsgAddLabel);
        addEquipmentPanel.add(submitButton);
        addEquipmentPanel.add(errorMsgLabel);
        addEquipmentPanel.add(equipmentNameField);
        addEquipmentPanel.add(equipmentWeightField);
        addEquipmentPanel.add(equipmentPriceField);
        addEquipmentPanel.add(equipmentNameText);
        addEquipmentPanel.add(equipmentWeightText);
        addEquipmentPanel.add(equipmentPriceText);
        addEquipmentPanel.add(addEquipmentPanelTitle);

        //panel to update equipments in the climbsafe
        JPanel updateEquipmentPanel = new JPanel();
        updateEquipmentPanel.setLayout(null);
        updateEquipmentPanel.setBounds(230, 350, 1000, 150);
        updateEquipmentPanel.setBackground(Color.white);

        JLabel updateEquipmentPanelTitle = new JLabel("Update an equipment in the ClimbSafe");
        updateEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        updateEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        updateEquipmentPanel.add(updateEquipmentPanelTitle);

        JComboBox<String> equipmentUpdateDropdown = new JComboBox<String>(equipmentListNames);
        equipmentUpdateDropdown.setBounds(50, 60, 200, 30);
        JLabel equipmentUpdateText = new JLabel("Select an equipment to update");
        equipmentUpdateText.setBounds(equipmentUpdateDropdown.getX()+7, equipmentUpdateDropdown.getY()-15, 250, 15);
        equipmentUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentNameUpdateField = new JTextField();
        equipmentNameUpdateField.setBounds(equipmentUpdateDropdown.getX() + equipmentUpdateDropdown.getWidth() + 50, equipmentUpdateDropdown.getY(), 150, 30);
        JLabel equipmentNameUpdateText = new JLabel("Updated Equipment Name");
        equipmentNameUpdateText.setBounds(equipmentNameUpdateField.getX()+3, equipmentNameUpdateField.getY()-15, 200, 15);
        equipmentNameUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentWeightUpdateField = new JTextField();
        equipmentWeightUpdateField.setBounds(equipmentNameUpdateField.getX() + equipmentNameUpdateField.getWidth() + 50, equipmentNameUpdateField.getY(), 150, 30);
        JLabel equipmentWeightUpdateText = new JLabel("Updated Equipment Weight");
        equipmentWeightUpdateText.setBounds(equipmentWeightUpdateField.getX()+3, equipmentWeightUpdateField.getY()-15, 200, 15);
        equipmentWeightUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentPriceUpdateField = new JTextField();
        equipmentPriceUpdateField.setBounds(equipmentWeightUpdateField.getX() + equipmentWeightUpdateField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceUpdateText = new JLabel("Updated Equipment Price");
        equipmentPriceUpdateText.setBounds(equipmentPriceUpdateField.getX()+3, equipmentPriceUpdateField.getY()-15, 200, 15);
        equipmentPriceUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel successMsgUpdateLabel = new JLabel();
        successMsgUpdateLabel.setBounds(720, 130, 350, 15);
        successMsgUpdateLabel.setForeground(Color.green);
        successMsgUpdateLabel.setVisible(successUpdate);

        JLabel errorMsgUpdateLabel = new JLabel(updateErrorMsg);
        errorMsgUpdateLabel.setBounds(5, 130, 300, 15);
        errorMsgUpdateLabel.setForeground(Color.red);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(equipmentPriceUpdateField.getX() + 180, equipmentUpdateDropdown.getY(), 100, 30);

        updateEquipmentPanel.add(equipmentUpdateText);
        updateEquipmentPanel.add(equipmentUpdateDropdown);
        updateEquipmentPanel.add(successMsgUpdateLabel);
        updateEquipmentPanel.add(updateButton);
        updateEquipmentPanel.add(errorMsgUpdateLabel);
        updateEquipmentPanel.add(equipmentPriceUpdateField);
        updateEquipmentPanel.add(equipmentWeightUpdateText);
        updateEquipmentPanel.add(equipmentPriceUpdateText);
        updateEquipmentPanel.add(equipmentWeightUpdateField);
        updateEquipmentPanel.add(equipmentNameUpdateField);
        updateEquipmentPanel.add(equipmentNameUpdateText);



        //panel to delete equipments in the climbsafe
        JPanel deleteEquipmentPanel = new JPanel();
        deleteEquipmentPanel.setLayout(null);
        deleteEquipmentPanel.setBounds(230, 550, 1000, 150);
        deleteEquipmentPanel.setBackground(Color.white);
        JLabel deleteEquipmentPanelTitle = new JLabel("Delete an equipment from the ClimbSafe");
        deleteEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        deleteEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        deleteEquipmentPanel.add(deleteEquipmentPanelTitle);

        JComboBox<String> equipmentDeleteDropdown = new JComboBox<String>(equipmentListNames);
        equipmentDeleteDropdown.setBounds(50, 60, 250, 30);
        JLabel equipmentDeleteText = new JLabel("Select an equipment to delete");
        equipmentDeleteText.setBounds(equipmentDeleteDropdown.getX()+7, equipmentDeleteDropdown.getY()-15, 250, 15);
        equipmentDeleteText.setHorizontalAlignment(SwingConstants.LEFT);


        JLabel successMsgDeleteLabel = new JLabel();
        successMsgDeleteLabel.setBounds(720, 130, 300, 15);
        successMsgDeleteLabel.setForeground(Color.green);
        successMsgDeleteLabel.setVisible(successDelete);

        JLabel errorMsgDeleteLabel = new JLabel(deleteErrorMsg);
        errorMsgDeleteLabel.setBounds(5, 130, 400, 15);
        errorMsgDeleteLabel.setForeground(Color.red);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(equipmentDeleteDropdown.getX() + 270, equipmentDeleteDropdown.getY(), 100, 30);

        deleteEquipmentPanel.add(deleteButton);
        deleteEquipmentPanel.add(errorMsgDeleteLabel);
        deleteEquipmentPanel.add(successMsgDeleteLabel);
        deleteEquipmentPanel.add(equipmentDeleteDropdown);
        deleteEquipmentPanel.add(equipmentDeleteText);

        setSize(1920,1080);
        add(title);
        add(equipmentTitle);
        add(addEquipmentPanel);
        add(updateEquipmentPanel);
        add(deleteEquipmentPanel);
        setLayout(null);
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equipmentWeightField.getText().equals("") || equipmentPriceField.getText().equals("")){
                    addErrorMsg = equipmentWeightField.getText().equals("") ? "Weight field can not be empty" : "Price field can not be empty";
                    errorMsgLabel.setText(addErrorMsg);
                    addErrorMsg = "";
                    return;
                }
                try{

                    ClimbSafeFeatureSet4Controller.addEquipment(equipmentNameField.getText(), Integer.parseInt(equipmentWeightField.getText()), Integer.parseInt(equipmentPriceField.getText()));
                }
                catch (Exception a){
                    addErrorMsg = a.getMessage();
                    successAdd = false;
                }
                if(addErrorMsg.equals("")){
                    successAdd = true;
                    updateEquipmentList();
                    equipmentUpdateDropdown.addItem(equipmentListNames[equipmentListNames.length-1]);
                    equipmentDeleteDropdown.addItem(equipmentListNames[equipmentListNames.length-1]);
                    equipmentNameField.setText("");
                    equipmentWeightField.setText("");
                    equipmentPriceField.setText("");
                }
                //System.out.println(ClimbSafeApplication.getClimbSafe().getEquipment());
                successMsgAddLabel.setVisible(successAdd);
                errorMsgLabel.setText(addErrorMsg);
                addErrorMsg = "";
                revalidate();
                repaint();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldName = "";
                if(equipmentWeightUpdateField.getText().equals("") || equipmentPriceUpdateField.getText().equals("")){
                    updateErrorMsg = equipmentWeightUpdateField.getText().equals("") ? "Weight field can not be empty" : "Price field can not be empty";
                    errorMsgUpdateLabel.setText(updateErrorMsg);
                    updateErrorMsg = "";
                    return;
                }
                try{

                    ClimbSafeFeatureSet4Controller.updateEquipment(String.valueOf(equipmentUpdateDropdown.getSelectedItem()), equipmentNameUpdateField.getText(), Integer.parseInt(equipmentWeightUpdateField.getText()), Integer.parseInt(equipmentPriceUpdateField.getText()));
                }
                catch (Exception a){
                    updateErrorMsg = a.getMessage();
                    successUpdate = false;
                }
                if(updateErrorMsg.equals("")){
                    oldName = String.valueOf(equipmentUpdateDropdown.getSelectedItem());
                    successUpdate = true;
                    updateEquipmentList();
                    equipmentUpdateDropdown.removeAllItems();
                    equipmentDeleteDropdown.removeAllItems();
                    for (String s :
                            equipmentListNames) {
                        equipmentUpdateDropdown.addItem(s);
                        equipmentDeleteDropdown.addItem(s);
                    }
                    equipmentNameUpdateField.setText("");
                    equipmentWeightUpdateField.setText("");
                    equipmentPriceUpdateField.setText("");
                }
                //System.out.println(ClimbSafeApplication.getClimbSafe().getEquipment());
                successMsgUpdateLabel.setText(String.format("Successfully updated equipment %s.", oldName));
                successMsgUpdateLabel.setVisible(successUpdate);
                errorMsgUpdateLabel.setText(updateErrorMsg);
                updateErrorMsg = "";
                revalidate();
                repaint();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equipmentDeleteDropdown.getSelectedItem() == null){
                    deleteErrorMsg = "No more equipments to delete.";
                    errorMsgDeleteLabel.setText(deleteErrorMsg);
                    deleteErrorMsg = "";
                    return;
                }
                String oldName = "";
                try{
                    ClimbSafeFeatureSet6Controller.deleteEquipment(String.valueOf(equipmentDeleteDropdown.getSelectedItem()));
                }
                catch (Exception a){
                    deleteErrorMsg = a.getMessage();
                    successDelete = false;
                }
                if(deleteErrorMsg.equals("")){
                    oldName = String.valueOf(equipmentDeleteDropdown.getSelectedItem());
                    successDelete = true;
                    updateEquipmentList();
                    equipmentUpdateDropdown.removeAllItems();
                    equipmentDeleteDropdown.removeAllItems();
                    for (String s :
                            equipmentListNames) {
                        equipmentUpdateDropdown.addItem(s);
                        equipmentDeleteDropdown.addItem(s);
                    }
                }
                //System.out.println(ClimbSafeApplication.getClimbSafe().getEquipment());
                successMsgDeleteLabel.setText(String.format("Successfully deleted equipment %s.", oldName));
                successMsgDeleteLabel.setVisible(successDelete);
                errorMsgDeleteLabel.setText(deleteErrorMsg);
                deleteErrorMsg = "";
                revalidate();
                repaint();
            }
        });
    }



}
