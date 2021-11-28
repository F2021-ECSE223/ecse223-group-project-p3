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
        JPanel card2 = new JPanel();
        card2.setPreferredSize(new Dimension(1920,800));
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        JPanel memberPart = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        card2.add(memberPart);
        JPanel leftColumn = new JPanel(){};
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        memberPart.add(leftColumn);


        JPanel col1 = new JPanel(){};
        JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        emailLabel.setPreferredSize(new Dimension(150, 18));
        col1.add(emailLabel);
        JTextField email = new JTextField("johndoe@email.com");
        email.setPreferredSize(new Dimension(150,18));
        col1.add(email);
        leftColumn.add(col1);
        leftColumn.add(new JLabel(" "));

        JPanel col2 = new JPanel(){};
        JLabel nameLabel = new JLabel("Name:",SwingConstants.RIGHT);
        nameLabel.setPreferredSize(new Dimension(150, 18));
        col2.add(nameLabel);
        JTextField name = new JTextField("John Doe");
        name.setPreferredSize(new Dimension(150,18));
        col2.add(name);
        leftColumn.add(col2);
        leftColumn.add(new JLabel(" "));

        JPanel col3 = new JPanel(){};
        JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
        passwordLabel.setPreferredSize(new Dimension(150, 18));
        col3.add(passwordLabel);
        JPasswordField password = new JPasswordField("Password");
        password.setPreferredSize(new Dimension(150,18));
        col3.add(password);
        leftColumn.add(col3);
        leftColumn.add(new JLabel(" "));

        JPanel col4 = new JPanel(){};
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:",SwingConstants.RIGHT);
        emergencyContactLabel.setPreferredSize(new Dimension(150, 18));
        col4.add(emergencyContactLabel);
        JTextField emergencyContact = new JTextField("(514)-XXX-XXXX");
        emergencyContact.setPreferredSize(new Dimension(150,18));
        col4.add(emergencyContact);
        leftColumn.add(col4);
        leftColumn.add(new JLabel(" "));

        JPanel col5 = new JPanel(){};
        JLabel lengthLabel = new JLabel("Length of Stay:", SwingConstants.RIGHT);
        lengthLabel.setPreferredSize(new Dimension(150, 18));
        col5.add(lengthLabel);
        JPanel weeks = new JPanel();
        weeks.setPreferredSize(new Dimension(150,18));
        JLabel nrWeeks = new JLabel("1 week(s)");
        JButton weekUp = new JButton();
        JButton weekDown = new JButton();
        weeks.add(weekDown);weeks.add(nrWeeks);weeks.add(weekUp);
        col5.add(weeks);
        weekUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = nrWeeks.getText().split(" ");
                int num = Integer.parseInt(txt[0]);
                num += 1;
                //TODO Create climbsafe TO?
                int len = ClimbSafeApplication.getClimbSafe().getNrWeeks();
                if (num > len) num = len;
                nrWeeks.setText(String.valueOf(num)+" week(s)");
            }
        });
        weekDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = nrWeeks.getText().split(" ");
                int num = Integer.parseInt(txt[0]);
                num -= 1;
                if (num<0) num = 0;
                nrWeeks.setText(String.valueOf(num)+" week(s)");
            }
        });
        leftColumn.add(col5);
        leftColumn.add(new JLabel(" "));

        JPanel col6 = new JPanel(){};
        JLabel stayBeforeLabel = new JLabel("Stay Before:",SwingConstants.RIGHT);
        stayBeforeLabel.setPreferredSize(new Dimension(150, 18));
        col6.add(stayBeforeLabel);
        JPanel before = new JPanel();
        before.setPreferredSize(new Dimension(150,18));
        JToggleButton stayBefore = new JToggleButton();
        before.add(stayBefore);
        JLabel beforeText = new JLabel("+ 3 days");
        before.add(beforeText);
        col6.add(before);
        leftColumn.add(col6);
        leftColumn.add(new JLabel(" "));

        JPanel col7 = new JPanel(){};
        JLabel stayAfterLabel = new JLabel("Stay After:",SwingConstants.RIGHT);
        stayAfterLabel.setPreferredSize(new Dimension(150, 18));
        col7.add(stayAfterLabel);
        JPanel after = new JPanel();
        after.setPreferredSize(new Dimension(150,18));
        JToggleButton stayAfter = new JToggleButton();
        after.add(stayAfter);
        JLabel afterText = new JLabel("+ 3 days");
        after.add(afterText);
        col7.add(after);
        leftColumn.add(col7);
        leftColumn.add(new JLabel(" "));

        JPanel col8 = new JPanel(){};
        JLabel guideLabel = new JLabel("Add Guide:",SwingConstants.RIGHT);
        guideLabel.setPreferredSize(new Dimension(150, 18));
        col8.add(guideLabel);
        JPanel guideNeeded = new JPanel();
        guideNeeded.setPreferredSize(new Dimension(150,18));
        JToggleButton guide = new JToggleButton();
        guideNeeded.add(guide);
        col8.add(guideNeeded);
        leftColumn.add(col8);
        leftColumn.add(new JLabel(" "));



        JPanel rightColumn = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };;
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        memberPart.add(rightColumn);

        JPanel row1 = new JPanel();
        JLabel equipmentLabel = new JLabel("Equipment:",SwingConstants.RIGHT);
        equipmentLabel.setPreferredSize(new Dimension(150,18));

        List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();
        String[] equipmentNameArray = new String[equipmentList.size()];
        int[] equipmentQuantityArray = new int[equipmentList.size()];
        for (int i= 0;i<equipmentList.size();i++){
            equipmentNameArray[i] = equipmentList.get(i).getName();
            equipmentQuantityArray[i] = 0;
        }
        JComboBox<String> equipmentVisualList = new JComboBox<>(equipmentNameArray);

        row1.add(equipmentLabel);
        row1.add(equipmentVisualList);
        rightColumn.add(row1);
        rightColumn.add(new JLabel(" "));

        JPanel row2 = new JPanel();
        rightColumn.add(row2);
        JLabel contentLabel1 = new JLabel("Content:",SwingConstants.RIGHT);
        contentLabel1.setPreferredSize(new Dimension(150,18));
        row2.add(contentLabel1);

        JLabel equipmentQuantity = new JLabel("0");
        JButton equipmentUp = new JButton();
        JButton equipmentDown = new JButton();
        row2.add(equipmentDown);row2.add(equipmentQuantity);row2.add(equipmentUp);

        equipmentVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipmentQuantity.setText(String.valueOf(equipmentQuantityArray[equipmentVisualList.getSelectedIndex()]));
            }
        });
        equipmentUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentQuantity.getText());
                num += 1;
                equipmentQuantity.setText(String.valueOf(num));
                equipmentQuantityArray[equipmentVisualList.getSelectedIndex()] = num;
            }
        });
        equipmentDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentQuantity.getText());
                num -= 1;
                if (num<0) num = 0;
                equipmentQuantity.setText(String.valueOf(num));
                equipmentQuantityArray[equipmentVisualList.getSelectedIndex()] = num;
            }
        });

        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));

        JPanel row3 = new JPanel();
        JLabel equipmentBundleLabel = new JLabel("Equipment Bundle:",SwingConstants.RIGHT);
        equipmentBundleLabel.setPreferredSize(new Dimension(150,18));

        List<EquipmentBundle> equipmentBundleList = ClimbSafeApplication.getClimbSafe().getBundles();
        String[] equipmentBundleNameArray = new String[equipmentBundleList.size()];
        int[] equipmentBundleQuantityArray = new int[equipmentBundleList.size()];
        for (int i= 0;i<equipmentBundleList.size();i++){
            equipmentBundleNameArray[i] = equipmentBundleList.get(i).getName();
            equipmentBundleQuantityArray[i] = 0;
        }
        JComboBox<String> equipmentBundleVisualList = new JComboBox<>(equipmentBundleNameArray);

        row3.add(equipmentBundleLabel);
        row3.add(equipmentBundleVisualList);
        rightColumn.add(row3);
        rightColumn.add(new JLabel(" "));

        JPanel row4 = new JPanel();
        rightColumn.add(row4);
        JLabel contentLabel2 = new JLabel("Content:",SwingConstants.RIGHT);
        contentLabel2.setPreferredSize(new Dimension(150,18));
        row4.add(contentLabel2);

        JLabel equipmentBundleQuantity = new JLabel("0");
        JButton equipmentBundleUp = new JButton();
        JButton equipmentBundleDown = new JButton();
        row4.add(equipmentBundleDown);row4.add(equipmentBundleQuantity);row4.add(equipmentBundleUp);

        equipmentBundleVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipmentBundleQuantity.setText(String.valueOf(equipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()]));
            }
        });
        equipmentBundleUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentBundleQuantity.getText());
                num += 1;
                equipmentBundleQuantity.setText(String.valueOf(num));
                equipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()] = num;
            }
        });
        equipmentBundleDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentBundleQuantity.getText());
                num -= 1;
                if (num<0) num = 0;
                equipmentBundleQuantity.setText(String.valueOf(num));
                equipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()] = num;
            }
        });

        JPanel bottomButtons = new JPanel();
        card2.add(bottomButtons);
        JButton registerMember = new JButton("Register Member");
        bottomButtons.add(registerMember);
        registerMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hotel = stayBefore.isSelected() || stayAfter.isSelected();
                try {
                    List<String> itemNames = new ArrayList<>();
                    for (String s : equipmentNameArray) itemNames.add(s);
                    for (String s : equipmentBundleNameArray) itemNames.add(s);
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : equipmentQuantityArray) itemQuantities.add(i);
                    for (int i : equipmentBundleQuantityArray) itemQuantities.add(i);
                    ClimbSafeFeatureSet2Controller.registerMember(email.getText(), password.getText(), name.getText(),
                            emergencyContact.getText(),Integer.parseInt(nrWeeks.getText().split(" ")[0]),guide.isSelected(), hotel, itemNames, itemQuantities);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        JButton updateMember = new JButton("Update Member");
        bottomButtons.add(updateMember);
        updateMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hotel = stayBefore.isSelected() || stayAfter.isSelected();
                try {
                    List<String> itemNames = new ArrayList<>();
                    for (String s : equipmentNameArray) itemNames.add(s);
                    for (String s : equipmentBundleNameArray) itemNames.add(s);
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : equipmentQuantityArray) itemQuantities.add(i);
                    for (int i : equipmentBundleQuantityArray) itemQuantities.add(i);
                    ClimbSafeFeatureSet2Controller.updateMember(email.getText(), password.getText(), name.getText(),
                            emergencyContact.getText(),Integer.parseInt(nrWeeks.getText().split(" ")[0]),guide.isSelected(), hotel, itemNames, itemQuantities);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        JButton deleteMember = new JButton("Delete Member");
        bottomButtons.add(deleteMember);
        deleteMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hotel = stayBefore.isSelected() || stayAfter.isSelected();
                try {
                    ClimbSafeFeatureSet1Controller.deleteMember(email.getText());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
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
