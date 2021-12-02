package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;

public class Popup {
    Popup(String message, JPanel panel, int messageType){
        switch (messageType) {
            case 0 -> {
                JOptionPane.showMessageDialog(panel, message, "Success",JOptionPane.INFORMATION_MESSAGE);
            }
            case 1 -> {
                JOptionPane.showMessageDialog(panel, message, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
