
package ca.mcgill.ecse.climbsafe.view;

        import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
        import ca.mcgill.ecse.climbsafe.controller.*;
        import ca.mcgill.ecse.climbsafe.model.Equipment;
        import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.nio.file.Path;
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
    //private static BasicBack


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

        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        JPanel memberPart = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        memberPart.setOpaque(false);
        card2.add(memberPart);
        JPanel leftColumn = new JPanel(){};
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setOpaque(false);
        memberPart.add(leftColumn);


        JPanel col1 = new JPanel(){};
        col1.setPreferredSize(new Dimension(300,35));
        col1.setOpaque(false);
        JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        emailLabel.setPreferredSize(new Dimension(150, 35));
        col1.add(emailLabel);
        JTextField email = new JTextField("johndoe@email.com");
        email.setPreferredSize(new Dimension(150,25));
        col1.add(email);
        leftColumn.add(col1);
        leftColumn.add(new JLabel(" "));

        JPanel col2 = new JPanel(){};
        col2.setPreferredSize(new Dimension(300,35));
        col2.setOpaque(false);
        JLabel nameLabel = new JLabel("Name:",SwingConstants.RIGHT);
        nameLabel.setPreferredSize(new Dimension(150, 35));
        col2.add(nameLabel);
        JTextField name = new JTextField("John Doe");
        name.setPreferredSize(new Dimension(150,25));
        col2.add(name);
        leftColumn.add(col2);
        leftColumn.add(new JLabel(" "));

        JPanel col3 = new JPanel(){};
        col3.setPreferredSize(new Dimension(300,35));
        col3.setOpaque(false);
        JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
        passwordLabel.setPreferredSize(new Dimension(150, 35));
        col3.add(passwordLabel);
        JPasswordField password = new JPasswordField("Password");
        password.setPreferredSize(new Dimension(150,25));
        col3.add(password);
        leftColumn.add(col3);
        leftColumn.add(new JLabel(" "));

        JPanel col4 = new JPanel(){};
        col4.setPreferredSize(new Dimension(300,35));
        col4.setOpaque(false);
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:",SwingConstants.RIGHT);
        emergencyContactLabel.setPreferredSize(new Dimension(150, 35));
        col4.add(emergencyContactLabel);
        JTextField emergencyContact = new JTextField("(514)-XXX-XXXX");
        emergencyContact.setPreferredSize(new Dimension(150,25));
        col4.add(emergencyContact);
        leftColumn.add(col4);
        leftColumn.add(new JLabel(" "));

        JPanel col5 = new JPanel(){};
        col5.setPreferredSize(new Dimension(300,35));
        col5.setOpaque(false);
        JLabel lengthLabel = new JLabel("Length of Stay:", SwingConstants.RIGHT);
        lengthLabel.setPreferredSize(new Dimension(150, 35));
        col5.add(lengthLabel);
        JPanel weeks = new JPanel();
        weeks.setPreferredSize(new Dimension(150,35));
        weeks.setOpaque(false);
        JLabel nrWeeks = new JLabel("1 week(s)");
        JButton weekUp = new JButton("<html>+</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
        }};
        weekUp.setHorizontalAlignment(SwingConstants.CENTER);

        JButton weekDown = new JButton("<html>-</html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};

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
        leftColumn.add(col5);
        leftColumn.add(new JLabel(" "));

        JPanel col6 = new JPanel(){};
        col6.setPreferredSize(new Dimension(299,35));
        col6.setOpaque(false);
        JLabel stayBeforeLabel = new JLabel("Stay Before:",SwingConstants.RIGHT);
        stayBeforeLabel.setPreferredSize(new Dimension(150, 35));
        col6.add(stayBeforeLabel);
        JPanel before = new JPanel();
        before.setPreferredSize(new Dimension(150,35));
        JToggleButton stayBefore = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        stayBefore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stayBefore.isSelected())stayBefore.setText("<html><span>&#10003;</span></html>");
                else stayBefore.setText("<html><span>&#10007;</span></html>");
            }
        });
        before.add(stayBefore);
        JLabel beforeText = new JLabel("+ 3 days");
        before.add(beforeText);
        before.setOpaque(false);
        col6.add(before);
        leftColumn.add(col6);
        leftColumn.add(new JLabel(" "));

        JPanel col7 = new JPanel(){};
        col7.setPreferredSize(new Dimension(299,35));
        col7.setOpaque(false);
        JLabel stayAfterLabel = new JLabel("Stay After:",SwingConstants.RIGHT);
        stayAfterLabel.setPreferredSize(new Dimension(150, 35));
        col7.add(stayAfterLabel);
        JPanel after = new JPanel();
        after.setPreferredSize(new Dimension(150,35));
        JToggleButton stayAfter = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        stayAfter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stayAfter.isSelected())stayAfter.setText("<html><span>&#10003;</span></html>");
                else stayAfter.setText("<html><span>&#10007;</span></html>");
            }
        });
        after.add(stayAfter);
        JLabel afterText = new JLabel("+ 3 days");
        after.setOpaque(false);
        after.add(afterText);
        col7.add(after);
        leftColumn.add(col7);
        leftColumn.add(new JLabel(" "));

        JPanel col8 = new JPanel(){};
        col8.setPreferredSize(new Dimension(350,35));
        col8.setOpaque(false);
        JLabel guideLabel = new JLabel("Add Guide:",SwingConstants.RIGHT);
        guideLabel.setPreferredSize(new Dimension(150, 35));
        col8.add(guideLabel);
        JPanel guideNeeded = new JPanel();
        guideNeeded.setPreferredSize(new Dimension(150,35));
        JToggleButton guide = new JToggleButton("<html><span>&#10007;</span></html>"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height -= 5;
                return size;
            }};
        guide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guide.isSelected())guide.setText("<html><span>&#10003;</span></html>");
                else guide.setText("<html><span>&#10007;</span></html>");
            }
        });
        guideNeeded.add(guide);
        guideNeeded.setOpaque(false);
        col8.add(guideNeeded);
        leftColumn.add(col8);
        leftColumn.add(new JLabel(" "));



        JPanel rightColumn = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        rightColumn.setOpaque(false);
        memberPart.add(rightColumn);

        JPanel row1 = new JPanel();
        row1.setOpaque(false);
        JLabel equipmentLabel = new JLabel("Equipment:",SwingConstants.RIGHT);
        equipmentLabel.setPreferredSize(new Dimension(150,20));

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
        row2.setOpaque(false);
        rightColumn.add(row2);
        JLabel contentLabel1 = new JLabel("Content:",SwingConstants.RIGHT);
        contentLabel1.setPreferredSize(new Dimension(150,20));
        row2.add(contentLabel1);

        JLabel equipmentQuantity = new JLabel("0");
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
        row3.setOpaque(false);
        JLabel equipmentBundleLabel = new JLabel("Equipment Bundle:",SwingConstants.RIGHT);
        equipmentBundleLabel.setPreferredSize(new Dimension(150,20));

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
        row4.setOpaque(false);
        rightColumn.add(row4);
        JLabel contentLabel2 = new JLabel("Content:",SwingConstants.RIGHT);
        contentLabel2.setPreferredSize(new Dimension(150,20));
        row4.add(contentLabel2);

        JLabel equipmentBundleQuantity = new JLabel("0");
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
        JButton registerMember = new JButton("Register Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        bottomButtons.add(registerMember);
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

        JButton updateMember = new JButton("Update Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        bottomButtons.setOpaque(false);
        bottomButtons.add(updateMember);
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
        JButton deleteMember = new JButton("Delete Member"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
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
        List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
        memberNameList = new String[toAssignmentList.size()];
        authCodeList = new String[toAssignmentList.size()];
        for (int i= 0;i<toAssignmentList.size();i++){
            memberNameList[i] = toAssignmentList.get(i).getMemberEmail();
            authCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
        }
        if (memberNameList.length==0){
            memberNameList = new String[1];
            memberNameList[0] = "Placeholder";
            authCodeList = new String[1];
            authCodeList[0] = "Placeholder";
        }
        JComboBox<String> memberNameVisualList = new JComboBox<>(memberNameList);
        JTextField authCode = new JTextField(authCodeList[0]);
        memberNameVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authCode.setText(authCodeList[memberNameVisualList.getSelectedIndex()]);
            }
        });
        authCode.setPreferredSize(new Dimension(authCode.getPreferredSize().width+50, authCode.getPreferredSize().height));
        fields.add(members);
        fields.add(memberNameVisualList);
        fields.add(code);
        fields.add(authCode);

        JButton pay = new JButton("Pay Member Trip");
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (toAssignmentList.size()>0 && authCode != null)
                    AssignmentController.payMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()), authCode.getText());
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton refresh = new JButton("Refresh Member Names");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
                memberNameList = new String[toAssignmentList.size()];
                authCodeList = new String[toAssignmentList.size()];
                for (int i= 0;i<toAssignmentList.size();i++){
                    memberNameList[i] = toAssignmentList.get(i).getMemberEmail();
                    authCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
                }
                if (memberNameList.length==0){
                    memberNameList = new String[1];
                    memberNameList[0] = "Placeholder";
                    authCodeList = new String[1];
                    authCodeList[0] = "Placeholder";
                }
                authCode.setText(authCodeList[0]);
            }
        });
        buttons.add(pay);
        buttons.add(refresh);

        card7.add(fields);card7.add(buttons);


        tabbedPane.addTab("Pay for Member's Trip", card7);
    }





    public static void addTripCard(){
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
        //TODO: add elements to card8 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

        tabbedPane.addTab("Start trips, finish a trip and cancel a trip", card8);
    }


}
