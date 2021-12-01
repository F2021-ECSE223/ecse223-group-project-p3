
package ca.mcgill.ecse.climbsafe.view;

        import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
        import ca.mcgill.ecse.climbsafe.controller.*;


        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import javax.imageio.ImageIO;
        import javax.swing.*;



public class ClimbSafePage {
    private static JFrame mainFrame;
    private static JTabbedPane tabbedPane;
    private static String[] memberNameList;
    private static String[] authCodeList;
    private static String[] equipmentNameArray;
    private static String[] equipmentBundleNameArray;
    private static int[] equipmentQuantityArray;
    private static  int[] equipmentBundleQuantityArray;



    public static void start(){
        mainFrame = new JFrame("ClimbSafe");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel midPane = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 1080;
                size.width +=755;
                return size;
            }
        };
        midPane.setLayout(new BoxLayout(midPane, BoxLayout.Y_AXIS)); //sets so that elements are added vertically to panel
        midPane.setBackground(new Color(207,226,255));

        JLabel header = new JLabel("Neptan Mountain Climbing");
        header.setFont(new Font("Britannic Bold", Font.BOLD, 90));
        header.setPreferredSize(new Dimension(400,200));
        midPane.add(header);
        tabbedPane = new JTabbedPane();
        addNMCCard();
        addMemberCard();
        addGuideCard();
        addEquipmentCard();
        addBundleCard();
        addAssignmentCard();
        addPayCard();
        addTripCard();

        midPane.add(tabbedPane);
        mainFrame.getContentPane().add(midPane, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static java.net.URL getPhoto(int num)  {
        java.net.URL imageURL = ClimbSafePage.class.getResource("ClimbSafePage.class");
        String imageString = (imageURL.getFile());
        imageString = imageString.substring(0,imageString.length()-73);
        imageString += "src/main/java/ca/mcgill/ecse/climbsafe/view/images/";
        if (num == 0) imageString+= "Hikers-Background.png";
        else imageString+="Mountain-Background.png";
        imageString = "file:"+imageString;
        try {
            imageURL = new URL(imageString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return imageURL;
    }
    private static void updateEquipmentNames() {
        List<TOBookableItem> equipmentList = TOController.getEquipment();
        if (equipmentList == null || equipmentList.size() == 0) {
            equipmentNameArray = new String[1];
            equipmentNameArray[0] = "Placeholder";
            equipmentQuantityArray = new int[1];
            equipmentQuantityArray[0] = 0;
        }
        else{
            equipmentNameArray = new String[equipmentList.size()];
            for (int i = 0; i < equipmentList.size(); i++) {
                equipmentNameArray[i] = equipmentList.get(i).getName();
            }
        }
    }
    private static void updateBundlesNames() {
        List<TOBookableItem> equipmentBundleList = TOController.getBundles();
        if (equipmentBundleList==null || equipmentBundleList.size() == 0){
            equipmentBundleNameArray = new String[1];
            equipmentBundleNameArray[0] = "Placeholder";
            equipmentBundleQuantityArray = new int[1];
            equipmentBundleQuantityArray[0] = 0;
        }
        else {
            equipmentBundleNameArray = new String[equipmentBundleList.size()];
            for (int i = 0; i < equipmentBundleList.size(); i++) {
                equipmentBundleNameArray[i] = equipmentBundleList.get(i).getName();
            }
        }
    }
    private static void updatePay() {
        List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
        memberNameList = new String[toAssignmentList.size()];
        authCodeList = new String[toAssignmentList.size()];
        for (int i = 0; i < toAssignmentList.size(); i++) {
            memberNameList[i] = toAssignmentList.get(i).getMemberEmail();
            authCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
        }
        if (toAssignmentList.size() == 0) {
            memberNameList = new String[1];
            memberNameList[0] = "Placeholder";
            authCodeList = new String[1];
            authCodeList[0] = "Placeholder";
        }
    }

    public static void addNMCCard(){
        java.net.URL imageURL = getPhoto(0);
        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card1 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        //TODO: add elements to card1 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Setup NMC", card1);
    }

    public static void addMemberCard(){

        //Create the "cards".
        java.net.URL imageURL = getPhoto(0);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card2 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };




        JPanel memberPart = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        JPanel leftColumn = new JPanel(){};
        JPanel col1 = new JPanel(){};
        JPanel col2 = new JPanel(){};
        JPanel col3 = new JPanel(){};
        JPanel col4 = new JPanel(){};
        JPanel col5 = new JPanel(){};
        JPanel weeks = new JPanel();
        JPanel col6 = new JPanel(){};
        JPanel before = new JPanel();
        JPanel col7 = new JPanel(){};
        JPanel after = new JPanel();
        JPanel col8 = new JPanel(){};
        JPanel guideNeeded = new JPanel();
        JPanel rightColumn = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        JPanel bottomButtons = new JPanel();
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();


        memberPart.setOpaque(false);
        leftColumn.setOpaque(false);
        col1.setOpaque(false);
        col2.setOpaque(false);
        col3.setOpaque(false);
        col4.setOpaque(false);
        col5.setOpaque(false);
        weeks.setOpaque(false);
        col6.setOpaque(false);
        before.setOpaque(false);
        col7.setOpaque(false);
        after.setOpaque(false);
        col8.setOpaque(false);
        guideNeeded.setOpaque(false);
        rightColumn.setOpaque(false);
        bottomButtons.setOpaque(false);
        row1.setOpaque(false);
        row2.setOpaque(false);
        row3.setOpaque(false);
        row4.setOpaque(false);

        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        JLabel nameLabel = new JLabel("Name:",SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:",SwingConstants.RIGHT);
        JLabel lengthLabel = new JLabel("Length of Stay:", SwingConstants.RIGHT);
        JLabel nrWeeks = new JLabel("1 week(s)");
        JLabel stayBeforeLabel = new JLabel("Stay Before:",SwingConstants.RIGHT);
        JLabel beforeText = new JLabel("+ 3 days");
        JLabel stayAfterLabel = new JLabel("Stay After:",SwingConstants.RIGHT);
        JLabel afterText = new JLabel("+ 3 days");
        JLabel guideLabel = new JLabel("Add Guide:",SwingConstants.RIGHT);
        JLabel equipmentLabel = new JLabel("Equipment:",SwingConstants.RIGHT);
        JLabel contentLabel1 = new JLabel("Content:",SwingConstants.RIGHT);
        JLabel equipmentQuantity = new JLabel("0");
        JLabel equipmentBundleLabel = new JLabel("Equipment Bundle:",SwingConstants.RIGHT);
        JLabel contentLabel2 = new JLabel("Content:",SwingConstants.RIGHT);
        JLabel equipmentBundleQuantity = new JLabel("0");


        JTextField email = new JTextField("johndoe@email.com");
        JTextField name = new JTextField("John Doe");
        JTextField emergencyContact = new JTextField("(514)-XXX-XXXX");

        JPasswordField password = new JPasswordField("Password");

        JToggleButton stayBefore = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JToggleButton stayAfter = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JToggleButton guide = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};

        JButton weekDown = new JButton("<html>-</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton weekUp = new JButton("<html>+</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton equipmentUp = new JButton("<html>+</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton equipmentDown = new JButton("<html>-</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton equipmentBundleUp = new JButton("<html>+</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton equipmentBundleDown = new JButton("<html>-</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        JButton registerMember = new JButton("Register Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        JButton updateMember = new JButton("Update Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        JButton deleteMember = new JButton("Delete Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        JButton refreshMember = new JButton("Refresh Bookable Item Lists"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };

        updateEquipmentNames();
        JComboBox<String> equipmentVisualList = new JComboBox<>(equipmentNameArray);
        updateBundlesNames();
        JComboBox<String> equipmentBundleVisualList = new JComboBox<>(equipmentBundleNameArray);



        card2.add(memberPart);
        card2.add(bottomButtons);

        memberPart.add(leftColumn);
        memberPart.add(rightColumn);

        leftColumn.add(col1);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col2);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col3);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col4);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col5);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col6);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col7);
        leftColumn.add(new JLabel(" "));
        leftColumn.add(col8);
        leftColumn.add(new JLabel(" "));

        rightColumn.add(row1);
        rightColumn.add(new JLabel(" "));
        rightColumn.add(row2);
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(new JLabel(" "));
        rightColumn.add(row3);
        rightColumn.add(new JLabel(" "));
        rightColumn.add(row4);

        col1.add(emailLabel);
        col1.add(email);
        col2.add(nameLabel);
        col2.add(name);
        col3.add(passwordLabel);
        col3.add(password);
        col4.add(emergencyContactLabel);
        col4.add(emergencyContact);
        col5.add(lengthLabel);
        col5.add(weeks);
        weeks.add(weekDown);
        weeks.add(nrWeeks);
        weeks.add(weekUp);
        col6.add(stayBeforeLabel);
        col6.add(before);
        before.add(stayBefore);
        before.add(beforeText);
        col7.add(stayAfterLabel);
        col7.add(after);
        after.add(stayAfter);
        after.add(afterText);
        col8.add(guideLabel);
        col8.add(guideNeeded);
        guideNeeded.add(guide);
        row1.add(equipmentLabel);
        row1.add(equipmentVisualList);
        row2.add(contentLabel1);
        row2.add(equipmentDown);
        row2.add(equipmentQuantity);
        row2.add(equipmentUp);
        row3.add(equipmentBundleLabel);
        row3.add(equipmentBundleVisualList);
        row4.add(contentLabel2);
        row4.add(equipmentBundleDown);
        row4.add(equipmentBundleQuantity);
        row4.add(equipmentBundleUp);
        bottomButtons.add(registerMember);
        bottomButtons.add(updateMember);
        bottomButtons.add(deleteMember);
        bottomButtons.add(refreshMember);



        col1.setPreferredSize(new Dimension(300,35));
        emailLabel.setPreferredSize(new Dimension(150, 35));
        email.setPreferredSize(new Dimension(150,25));
        col2.setPreferredSize(new Dimension(300,35));
        nameLabel.setPreferredSize(new Dimension(150, 35));
        name.setPreferredSize(new Dimension(150,25));
        col3.setPreferredSize(new Dimension(300,35));
        passwordLabel.setPreferredSize(new Dimension(150, 35));
        password.setPreferredSize(new Dimension(150,25));
        col4.setPreferredSize(new Dimension(300,35));
        emergencyContactLabel.setPreferredSize(new Dimension(150, 35));
        emergencyContact.setPreferredSize(new Dimension(150,25));
        col5.setPreferredSize(new Dimension(300,35));
        lengthLabel.setPreferredSize(new Dimension(150, 35));
        weeks.setPreferredSize(new Dimension(150,35));
        col6.setPreferredSize(new Dimension(299,35));
        stayBeforeLabel.setPreferredSize(new Dimension(150, 35));
        before.setPreferredSize(new Dimension(150,35));
        col7.setPreferredSize(new Dimension(299,35));
        stayAfterLabel.setPreferredSize(new Dimension(150, 35));
        after.setPreferredSize(new Dimension(150,35));
        col8.setPreferredSize(new Dimension(350,35));
        guideLabel.setPreferredSize(new Dimension(150, 35));
        guideNeeded.setPreferredSize(new Dimension(150,35));
        equipmentLabel.setPreferredSize(new Dimension(150,20));
        contentLabel1.setPreferredSize(new Dimension(150,20));
        equipmentBundleLabel.setPreferredSize(new Dimension(150,20));
        contentLabel2.setPreferredSize(new Dimension(150,20));

        weekUp.setHorizontalAlignment(SwingConstants.CENTER);




        weekUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = nrWeeks.getText().split(" ");
                int num = Integer.parseInt(txt[0]);
                num += 1;
                //TODO Create climbsafe TO?
                int len = ClimbSafeApplication.getClimbSafe().getNrWeeks();
                if (num > len) num = len;
                nrWeeks.setText(num+" week(s)");
            }
        });
        weekDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = nrWeeks.getText().split(" ");
                int num = Integer.parseInt(txt[0]);
                num -= 1;
                if (num<0) num = 0;
                nrWeeks.setText(num+" week(s)");
            }
        });
        stayBefore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stayBefore.isSelected())stayBefore.setText("<html><span>&#10003;</span></html>");
                else stayBefore.setText("<html><span>&#10007;</span></html>");
            }
        });

        stayAfter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stayAfter.isSelected())stayAfter.setText("<html><span>&#10003;</span></html>");
                else stayAfter.setText("<html><span>&#10007;</span></html>");
            }
        });
        guide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guide.isSelected())guide.setText("<html><span>&#10003;</span></html>");
                else guide.setText("<html><span>&#10007;</span></html>");
            }
        });
        equipmentVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipmentVisualList.getSelectedIndex() !=-1)
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
        equipmentBundleVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipmentBundleVisualList.getSelectedIndex() !=-1)
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
        registerMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hotel = stayBefore.isSelected() || stayAfter.isSelected();
                try {
                    List<String> itemNames = new ArrayList<>();
                    itemNames.addAll(Arrays.asList(equipmentNameArray));
                    itemNames.addAll(Arrays.asList(equipmentBundleNameArray));
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : equipmentQuantityArray) itemQuantities.add(i);
                    for (int i : equipmentBundleQuantityArray) itemQuantities.add(i);
                    ClimbSafeFeatureSet2Controller.registerMember(email.getText(), String.valueOf(password.getPassword()), name.getText(),
                            emergencyContact.getText(),Integer.parseInt(nrWeeks.getText().split(" ")[0]),guide.isSelected(), hotel, itemNames, itemQuantities);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        updateMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hotel = stayBefore.isSelected() || stayAfter.isSelected();
                try {
                    List<String> itemNames = new ArrayList<>();
                    itemNames.addAll(Arrays.asList(equipmentNameArray));
                    itemNames.addAll(Arrays.asList(equipmentBundleNameArray));
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
        refreshMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEquipmentNames();
                equipmentVisualList.removeAllItems();
                for (String s : equipmentNameArray) equipmentVisualList.addItem(s);
                updateBundlesNames();
                equipmentBundleVisualList.removeAllItems();
                for (String s : equipmentBundleNameArray) equipmentBundleVisualList.addItem(s);
            }
        });

        tabbedPane.addTab("Register, Update and Delete Member", card2);
    }



    public static void addGuideCard(){
        java.net.URL imageURL = getPhoto(0);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card3 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        //TODO: add elements to card3 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Register, Update and Delete Guide", card3);
    }



    public static void addEquipmentCard(){
        java.net.URL imageURL = getPhoto(0);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card4 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        //TODO: add elements to card4 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Add, Update and Delete Equipment", card4);
    }



    public static void addBundleCard(){
        java.net.URL imageURL = getPhoto(1);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card5 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        //TODO: add elements to card5 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Add, Update and Delete Equipment Bundle", card5);
    }



    public static void addAssignmentCard(){
        java.net.URL imageURL = getPhoto(1);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card6 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        //TODO: add elements to card6 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Initiate and View Assignments", card6);
    }



    public static void addPayCard(){
        updatePay();
        java.net.URL imageURL = getPhoto(1);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card7 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        card7.setLayout(new BoxLayout(card7, BoxLayout.Y_AXIS));

        JPanel fields = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,5)){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        JPanel buttons = new JPanel();
        fields.setOpaque(false);
        buttons.setOpaque(false);

        JLabel members= new JLabel("Member Names:");
        JLabel code = new JLabel("Authorization Code:");
        JButton pay = new JButton("Pay Member Trip");
        JButton refresh = new JButton("Refresh Member Names");
        JComboBox<String> memberNameVisualList = new JComboBox<>(memberNameList);
        JTextField authCode = new JTextField(authCodeList[0]);
        fields.add(members);
        fields.add(memberNameVisualList);
        fields.add(code);
        fields.add(authCode);
        buttons.add(pay);
        buttons.add(refresh);
        card7.add(fields);
        card7.add(buttons);

        authCode.setPreferredSize(new Dimension(authCode.getPreferredSize().width+50, authCode.getPreferredSize().height));

        memberNameVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authCode.setText(authCodeList[memberNameVisualList.getSelectedIndex()]);
            }
        });
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameList.length>0 && authCode != null && memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()) != "Placeholder")
                    AssignmentController.payMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()), authCode.getText());
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePay();
                authCode.setText(authCodeList[0]);
            }
        });



        tabbedPane.addTab("Pay for Member's Trip", card7);
    }





    java.net.URL imageURL = getPhoto(1);

        BufferedImage hikerBackground = null;
        try {
            if (imageURL != null)
            hikerBackground = ImageIO.read(imageURL);
        } catch (IOException ex) {
            Logger.getLogger(ClimbSafePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage finalBackground = hikerBackground;
        JPanel card8 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 800;
                return size;
            }
        };
        card8.setLayout(new BoxLayout(card8, BoxLayout.Y_AXIS));

        JPanel start = new JPanel();
        JPanel middle = new JPanel();
        JPanel finishCancel = new JPanel();

        JLabel weekNumber = new JLabel("Week number: 1");
        JComboBox<String> memberNameVisualList = new JComboBox<>(memberNameList);

        JButton weekDown = new JButton("<html>-</html>");
        JButton weekUp = new JButton("<html>+</html>");
        JButton startWeek = new JButton("Start Week");
        JButton finish = new JButton("Finish Member's Trip");
        JButton cancel = new JButton("Cancel Member's Trip");


        start.setOpaque(false);
        middle.setOpaque(false);
        finishCancel.setOpaque(false);

        start.add(weekDown);
        start.add(weekNumber);
        start.add(weekUp);
        start.add(startWeek);
        middle.add(memberNameVisualList);
        finishCancel.add(finish);
        finishCancel.add(cancel);
        card8.add(start);
        card8.add(middle);
        card8.add(finishCancel);


        weekDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = weekNumber.getText().split(" ");
                int num = Integer.parseInt(txt[2]);
                num -= 1;
                if (num < 1) num = 1;
                weekNumber.setText("Week Number: "+num);
            }
        });
        weekDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = weekNumber.getText().split(" ");
                int num = Integer.parseInt(txt[2]);
                num += 1;
                //TODO Create climbsafe TO?
                int len = ClimbSafeApplication.getClimbSafe().getNrWeeks();
                if (num > len) num = len;
                weekNumber.setText("Week Number: "+num);
            }
        });
        startWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AssignmentController.startAllTrips(Integer.parseInt(weekNumber.getText().split(" ")[2]));
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }
            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex())!="Placeholder")
                    AssignmentController.finishMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()));
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex())!="Placeholder")
                    AssignmentController.cancelMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()));
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }
            }
        });

        tabbedPane.addTab("Start trips, finish a trip and cancel a trip", card8);


}
