package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;


public class ClimbSafePage extends JFrame{
    JFrame f;
    public ClimbSafePage() {
        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        add(b);//adding button on frame
        setSize(400,500);
        setLayout(null);
        setVisible(true);
        b.addActionListener(this::jeetSux);
    }

    private void jeetSux(ActionEvent actionEvent) {
        JLabel a = new JLabel("Jeet Sux");
        a.setBounds(50,50, 100,30);
        add(a);
        repaint();
        revalidate();
    }

}
