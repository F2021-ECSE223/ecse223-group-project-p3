
package ca.mcgill.ecse.climbsafe.view;


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
        import javax.swing.border.EmptyBorder;
        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;



public class ClimbSafePage {
    private static JFrame mainFrame;
    private static JTabbedPane tabbedPane;
    private static String[] memberEmailList;
    private static String[] assignedMemberEmailList;
    private static String[] authCodeList;

        
    private static Boolean successAdd = false;
    private static Boolean successUpdate = false;
    private static Boolean successDelete = false;
    private static String updateErrorMsg = "";
    private static String addErrorMsg = "";
    private static String deleteErrorMsg = "";
    private static List<TOBookableItem> equipmentList = TOController.getEquipment();
    private static String[] equipmentListNames = new String[equipmentList.size()];
    private static List<TOBookableItem> bundleList = TOController.getEquipment();
    private static String[] bundleListNames = new String[equipmentList.size()];
    private static int[] memberEquipmentQuantityArray = new int[equipmentList.size()];
    private static  int[] memberEquipmentBundleQuantityArray = new int[bundleList.size()];

    private static void updateEquipmentList(){
        equipmentList = TOController.getEquipment();
        String[] tempList = new String[equipmentList.size()];
        for(int i = 0; i < equipmentList.size(); i++){
            tempList[i] = equipmentList.get(i).getName();
        }
        equipmentListNames = tempList;
        for (TOBookableItem e :
                equipmentList) {
            e.delete();
        }
    }
    private static void updateBundleList(){
        bundleList = TOController.getBundles();
        String[] tempList = new String[bundleList.size()];
        for(int i = 0; i < bundleList.size(); i++){
            tempList[i] = bundleList.get(i).getName();
        }
        bundleListNames = tempList;
        for (TOBookableItem e :
                bundleList) {
            e.delete();
        }
    }



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

        updateMembers();
        JPanel head = new JPanel();
        head.setOpaque(false);
        JLabel header = new JLabel("Neptan Mountain Climbing");
        header.setFont(new Font("Britannic Bold", Font.BOLD, 90));
        head.add(header);
        midPane.add(head);
        tabbedPane = new JTabbedPane();
        addNMCCard();
        addMemberCard();
        addGuideCard();
        addEquipmentCard();
        addBundleCard();
        addAssignmentCard();
        addTripCard();

        midPane.add(tabbedPane);
        mainFrame.getContentPane().add(midPane, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * @author Sebastien Cantin
     * @param num number associated to the photo that will be chosen
     * @return the url of the photo to be used as a background
     */
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


    /**
     * @author Sebastien Cantin
     * updates all the important things to pay, the array with all users that have an assignment
     * and their authcodes
     */
    private static void updatePay() {
        List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
        assignedMemberEmailList = new String[toAssignmentList.size()];
        authCodeList = new String[toAssignmentList.size()];
        for (int i = 0; i < toAssignmentList.size(); i++) {
            assignedMemberEmailList[i] = toAssignmentList.get(i).getMemberEmail();
            authCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
        }
        if (toAssignmentList.size() == 0) {
            assignedMemberEmailList = new String[1];
            assignedMemberEmailList[0] = "Placeholder";
            authCodeList = new String[1];
            authCodeList[0] = "Placeholder";
        }
    }

    private static void updateMembers() {
        List<TONamedUser> memberList = TOController.getMembers();
        memberEmailList = new String[memberList.size()];
        for (int i = 0; i < memberList.size(); i++) {
            memberEmailList[i] = memberList.get(i).getEmail();
        }
        if (memberList.size() == 0) {
            memberEmailList = new String[1];
            memberEmailList[0] = "Placeholder";
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


    /**
     * @author Sebastien Cantin
     * fills in the tab in the UI responsible for registering, updating and deleting member accounts
     */
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
        JPanel names = new JPanel();
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


        names.setOpaque(false);
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
        guideNeeded.setOpaque(false);
        rightColumn.setOpaque(false);
        bottomButtons.setOpaque(false);
        row1.setOpaque(false);
        row2.setOpaque(false);
        row3.setOpaque(false);
        row4.setOpaque(false);

        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        JLabel emailsLabel = new JLabel("Select Member: ", SwingConstants.LEFT);
        JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        JLabel nameLabel = new JLabel("Name:",SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:",SwingConstants.RIGHT);
        JLabel lengthLabel = new JLabel("Length of Stay:", SwingConstants.RIGHT);
        JLabel nrWeeks = new JLabel("1 week(s)");
        JLabel stayHotelLabel = new JLabel("Stay at Hotel:",SwingConstants.RIGHT);
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

        JToggleButton stayHotel = new JToggleButton("<html><span>&#10007;</span></html>"){
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

        JComboBox members = new JComboBox(memberEmailList);
        members.setPreferredSize(new Dimension(members.getPreferredSize().width +50,members.getPreferredSize().height));
        updateEquipmentList();
        JComboBox<String> equipmentVisualList = new JComboBox<>(equipmentListNames);
        equipmentVisualList.setPreferredSize(new Dimension(equipmentVisualList.getPreferredSize().width+50, equipmentVisualList.getPreferredSize().height));
        updateBundleList();
        JComboBox<String> equipmentBundleVisualList = new JComboBox<>(bundleListNames);
        equipmentBundleVisualList.setPreferredSize(new Dimension(equipmentBundleVisualList.getPreferredSize().width+50, equipmentBundleVisualList.getPreferredSize().height));


        card2.add(memberPart);
        card2.add(bottomButtons);



        memberPart.add(names);
        memberPart.add(leftColumn);
        memberPart.add(rightColumn);


        names.add(emailsLabel);
        names.add(members);
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
        col6.add(stayHotelLabel);
        col6.add(before);
        before.add(stayHotel);
        col7.add(guideLabel);
        col7.add(guideNeeded);
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
        stayHotelLabel.setPreferredSize(new Dimension(150, 35));
        before.setPreferredSize(new Dimension(150,35));
        col7.setPreferredSize(new Dimension(320,35));
        guideLabel.setPreferredSize(new Dimension(150, 35));
        guideNeeded.setPreferredSize(new Dimension(150,35));
        equipmentLabel.setPreferredSize(new Dimension(150,20));
        contentLabel1.setPreferredSize(new Dimension(150,20));
        equipmentBundleLabel.setPreferredSize(new Dimension(150,20));
        contentLabel2.setPreferredSize(new Dimension(150,20));

        weekUp.setHorizontalAlignment(SwingConstants.CENTER);




        members.addActionListener(new ActionListener() {//TODO: finish this
            @Override
            public void actionPerformed(ActionEvent e) {
                TONamedUser member = TOController.getUserWithEmail((String) members.getSelectedItem());
                email.setText(member.getEmail());
                name.setText(member.getName());
                password.setText(member.getPassword());
                emergencyContact.setText(member.getEmergencyContact());
                nrWeeks.setText(String.valueOf(member.getNrWeeks()));
                guide.setSelected(member.getGuideRequired());
                if (member.getGuideRequired())guide.setText("<html><span>&#10003;</span></html>");
                else guide.setText("<html><span>&#10007;</span></html>");
                stayHotel.setSelected(member.getHotelRequired());
                if (member.getHotelRequired())stayHotel.setText("<html><span>&#10003;</span></html>");
                else stayHotel.setText("<html><span>&#10007;</span></html>");
                for (var bookedItem: TOController.getItemsforMemberEmail(member.getEmail())){
                    if (bookedItem.getItem().getWeight()==0){ //equipment bundle
                        for (int i = 0; i< bundleListNames.length; i++){
                            if (bundleListNames[i]==bookedItem.getItem().getName()) memberEquipmentBundleQuantityArray[i]= bookedItem.getQuantity();
                        }

                    }else{  //equipment
                        for (int i = 0; i< equipmentListNames.length; i++){
                            if (equipmentListNames[i]==bookedItem.getItem().getName()) memberEquipmentQuantityArray[i]= bookedItem.getQuantity();
                        }
                    }
                }
            }
        });
        weekUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = nrWeeks.getText().split(" ");
                int num = Integer.parseInt(txt[0]);
                num += 1;
                int len = TOController.getClimbSafe().getNrWeeks();
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
                if (num<1) num = 1;
                nrWeeks.setText(num+" week(s)");
            }
        });
        stayHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stayHotel.isSelected())stayHotel.setText("<html><span>&#10003;</span></html>");
                else stayHotel.setText("<html><span>&#10007;</span></html>");
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
                equipmentQuantity.setText(String.valueOf(memberEquipmentQuantityArray[equipmentVisualList.getSelectedIndex()]));
            }
        });
        equipmentUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentQuantity.getText());
                num += 1;
                equipmentQuantity.setText(String.valueOf(num));
                memberEquipmentQuantityArray[equipmentVisualList.getSelectedIndex()] = num;
            }
        });
        equipmentDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentQuantity.getText());
                num -= 1;
                if (num<0) num = 0;
                equipmentQuantity.setText(String.valueOf(num));
                memberEquipmentQuantityArray[equipmentVisualList.getSelectedIndex()] = num;
            }
        });
        equipmentBundleVisualList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipmentBundleVisualList.getSelectedIndex() !=-1)
                equipmentBundleQuantity.setText(String.valueOf(memberEquipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()]));
            }
        });
        equipmentBundleUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentBundleQuantity.getText());
                num += 1;
                equipmentBundleQuantity.setText(String.valueOf(num));
                memberEquipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()] = num;
            }
        });
        equipmentBundleDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(equipmentBundleQuantity.getText());
                num -= 1;
                if (num<0) num = 0;
                equipmentBundleQuantity.setText(String.valueOf(num));
                memberEquipmentBundleQuantityArray[equipmentBundleVisualList.getSelectedIndex()] = num;
            }
        });
        registerMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    List<String> itemNames = new ArrayList<>();
                    itemNames.addAll(Arrays.asList(equipmentListNames));
                    itemNames.addAll(Arrays.asList(bundleListNames));
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : memberEquipmentQuantityArray) itemQuantities.add(i);
                    for (int i : memberEquipmentBundleQuantityArray) itemQuantities.add(i);
                    ClimbSafeFeatureSet2Controller.registerMember(email.getText(), String.valueOf(password.getPassword()), name.getText(),
                            emergencyContact.getText(),Integer.parseInt(nrWeeks.getText().split(" ")[0]),guide.isSelected(), stayHotel.isSelected(), itemNames, itemQuantities);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        updateMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> itemNames = new ArrayList<>();
                    itemNames.addAll(Arrays.asList(equipmentListNames));
                    itemNames.addAll(Arrays.asList(bundleListNames));
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : memberEquipmentQuantityArray) itemQuantities.add(i);
                    for (int i : memberEquipmentBundleQuantityArray) itemQuantities.add(i);
                    ClimbSafeFeatureSet2Controller.updateMember(email.getText(), password.getText(), name.getText(),
                            emergencyContact.getText(),Integer.parseInt(nrWeeks.getText().split(" ")[0]),guide.isSelected(), stayHotel.isSelected(), itemNames, itemQuantities);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        deleteMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                updateEquipmentList();
                equipmentVisualList.removeAllItems();
                for (String s : equipmentListNames) equipmentVisualList.addItem(s);
                updateBundleList();
                equipmentBundleVisualList.removeAllItems();
                for (String s : bundleListNames) equipmentBundleVisualList.addItem(s);
            }
        });

        tabbedPane.addTab("Member", card2);
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



        tabbedPane.addTab("Guide", card3);
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
            
        JPanel addEquipmentPanel = new JPanel();
        addEquipmentPanel.setLayout(null);
        addEquipmentPanel.setBounds(230, 50, 1000, 150);
        addEquipmentPanel.setBackground(Color.lightGray);
        //addEquipmentPanel.setOpaque(false);

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
        errorMsgLabel.setBackground(Color.gray);

        JLabel successMsgAddLabel = new JLabel("Successfully added equipment to ClimbSafe.");
        successMsgAddLabel.setBounds(710, 130, 300, 15);
        successMsgAddLabel.setForeground(Color.green);
        successMsgAddLabel.setBackground(Color.gray);
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
        updateEquipmentPanel.setBounds(230, addEquipmentPanel.getY() + 200, 1000, 150);
        updateEquipmentPanel.setBackground(Color.lightGray);

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

        JLabel test = new JLabel();
        test.setBounds(equipmentNameUpdateField.getX() + 5, equipmentUpdateDropdown.getY() + 20, 150, 30);
        test.setForeground(Color.white);
        updateEquipmentPanel.add(test);


        JLabel equipmentNameUpdateText = new JLabel("Updated Equipment Name");
        equipmentNameUpdateText.setBounds(equipmentNameUpdateField.getX()+3, equipmentNameUpdateField.getY()-15, 200, 15);
        equipmentNameUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField equipmentWeightUpdateField = new JTextField();
        equipmentWeightUpdateField.setBounds(equipmentNameUpdateField.getX() + equipmentNameUpdateField.getWidth() + 50, equipmentNameUpdateField.getY(), 150, 30);
        JLabel equipmentWeightUpdateText = new JLabel("Updated Equipment Weight");
        equipmentWeightUpdateText.setBounds(equipmentWeightUpdateField.getX()+3, equipmentWeightUpdateField.getY()-15, 200, 15);
        equipmentWeightUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel test1 = new JLabel();
        test1.setBounds(equipmentWeightUpdateField.getX() + 5, equipmentUpdateDropdown.getY() + 20, 150, 30);
        test1.setForeground(Color.white);
        updateEquipmentPanel.add(test1);

        JTextField equipmentPriceUpdateField = new JTextField();
        equipmentPriceUpdateField.setBounds(equipmentWeightUpdateField.getX() + equipmentWeightUpdateField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceUpdateText = new JLabel("Updated Equipment Price");
        equipmentPriceUpdateText.setBounds(equipmentPriceUpdateField.getX()+3, equipmentPriceUpdateField.getY()-15, 200, 15);
        equipmentPriceUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel test2 = new JLabel();
        test2.setBounds(equipmentPriceUpdateField.getX() + 5, equipmentUpdateDropdown.getY() + 20, 150, 30);
        test2.setForeground(Color.white);
        updateEquipmentPanel.add(test2);

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
        deleteEquipmentPanel.setBounds(230, updateEquipmentPanel.getY() + 200, 1000, 150);
        deleteEquipmentPanel.setBackground(Color.lightGray);
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

        JLabel allEquipmentDisplayTitle = new JLabel("All Equipments");
        allEquipmentDisplayTitle.setBounds(10,20, 150,20);
        JList<String> allEquipmentsDisplay = new JList<>();
        allEquipmentsDisplay.setListData(equipmentListNames);
        allEquipmentsDisplay.setBounds(10,40,150,200);

        card4.add(allEquipmentDisplayTitle);
        card4.add(allEquipmentsDisplay);
        card4.add(addEquipmentPanel);
        card4.add(updateEquipmentPanel);
        card4.add(deleteEquipmentPanel);
        card4.setLayout(null);
        card4.setVisible(true);

        allEquipmentsDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedEq = allEquipmentsDisplay.getSelectedIndex();
                equipmentDeleteDropdown.setSelectedIndex(selectedEq);
                equipmentUpdateDropdown.setSelectedIndex(selectedEq);
            }
        });

        equipmentUpdateDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TOBookableItem eq = null;
                String eqName = (String) equipmentUpdateDropdown.getSelectedItem();
                for (TOBookableItem tob:
                     equipmentList) {
                    if(tob.getName().equals(eqName)){
                        eq = tob;
                    }
                }
                if(eq != null){
                    test.setText("Current Name: " + eqName);
                    test1.setText("Current weight: " + eq.getWeight());
                    test2.setText("Current Price: " + eq.getPricePerWeek());
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                successMsgUpdateLabel.setVisible(false);
                successMsgDeleteLabel.setVisible(false);
                successMsgAddLabel.setVisible(false);
                errorMsgDeleteLabel.setText("");
                errorMsgUpdateLabel.setText("");
                errorMsgLabel.setText("");
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
                    allEquipmentsDisplay.setListData(equipmentListNames);
                    equipmentNameField.setText("");
                    equipmentWeightField.setText("");
                    equipmentPriceField.setText("");
                }

                successMsgAddLabel.setVisible(successAdd);
                errorMsgLabel.setText(addErrorMsg);
                addErrorMsg = "";
                card4.revalidate();
                card4.repaint();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                successMsgUpdateLabel.setVisible(false);
                successMsgDeleteLabel.setVisible(false);
                successMsgAddLabel.setVisible(false);
                errorMsgDeleteLabel.setText("");
                errorMsgUpdateLabel.setText("");
                errorMsgLabel.setText("");
                if(equipmentUpdateDropdown.getSelectedItem() == null){
                    updateErrorMsg = "No equipments to update.";
                    errorMsgDeleteLabel.setText(updateErrorMsg);
                    updateErrorMsg = "";
                    return;
                }
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
                    allEquipmentsDisplay.setListData(equipmentListNames);
                    equipmentNameUpdateField.setText("");
                    equipmentWeightUpdateField.setText("");
                    equipmentPriceUpdateField.setText("");
                }

                successMsgUpdateLabel.setText(String.format("Successfully updated equipment %s.", oldName));
                successMsgUpdateLabel.setVisible(successUpdate);
                errorMsgUpdateLabel.setText(updateErrorMsg);
                updateErrorMsg = "";
                card4.revalidate();
                card4.repaint();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                successMsgUpdateLabel.setVisible(false);
                successMsgDeleteLabel.setVisible(false);
                successMsgAddLabel.setVisible(false);
                errorMsgDeleteLabel.setText("");
                errorMsgUpdateLabel.setText("");
                errorMsgLabel.setText("");
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
                    allEquipmentsDisplay.setListData(equipmentListNames);
                }

                successMsgDeleteLabel.setText(String.format("Successfully deleted equipment %s.", oldName));
                successMsgDeleteLabel.setVisible(successDelete);
                errorMsgDeleteLabel.setText(deleteErrorMsg);
                deleteErrorMsg = "";
                card4.revalidate();
                card4.repaint();
            }
        });


        tabbedPane.addTab("Equipment", card4);
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

        tabbedPane.addTab("Equipment Bundles", card5);
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

        tabbedPane.addTab("Assignments", card6);
    }




    /**
     * @author Sebastien Cantin
     * Fills in the tab in the UI associated to starting, finishing and cancelling trips
     */
    private static void addTripCard() {
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

        JPanel start = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                size.height -=300;
                return size;
            }
        };
        JPanel middle = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                size.height -=300;
                return size;
            }
        };
        JPanel finishCancel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;

                return size;
            }
        };

        JLabel weekLabel = new JLabel("Start all trips for a week:");
        JLabel weekNumber = new JLabel("Week number: 1");
        JLabel members= new JLabel("Member Names:");
        JLabel code = new JLabel("Authorization Code:");

        JTextField authCode = new JTextField(authCodeList[0]);
        JComboBox<String> memberNameVisualList = new JComboBox<>(assignedMemberEmailList);

        JButton weekDown = new JButton("<html>-</html>");
        JButton weekUp = new JButton("<html>+</html>");
        JButton startWeek = new JButton("Start Week");
        JButton pay = new JButton("Pay Member Trip");
        JButton finish = new JButton("Finish Member's Trip");
        JButton cancel = new JButton("Cancel Member's Trip");
        JButton refresh = new JButton("Refresh Member Names");


        start.setOpaque(false);
        middle.setOpaque(false);
        finishCancel.setOpaque(false);

        memberNameVisualList.setPreferredSize(new Dimension(memberNameVisualList.getPreferredSize().width+50, memberNameVisualList.getPreferredSize().height));
        authCode.setPreferredSize(new Dimension(authCode.getPreferredSize().width+100, authCode.getPreferredSize().height));

        weekLabel.setBorder(new EmptyBorder(0,10,0,10));

        code.setBorder(new EmptyBorder(0,25,0,0));


        start.add(weekLabel);
        start.add(weekDown);
        start.add(weekNumber);
        start.add(weekUp);
        start.add(startWeek);
        middle.add(members);
        middle.add(memberNameVisualList);
        middle.add(code);
        middle.add(authCode);
        finishCancel.add(pay);
        finishCancel.add(finish);
        finishCancel.add(cancel);
        finishCancel.add(refresh);
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
        weekUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] txt = weekNumber.getText().split(" ");
                int num = Integer.parseInt(txt[2]);
                num += 1;
                int len = TOController.getClimbSafe().getNrWeeks();
                System.out.println(len);
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
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (assignedMemberEmailList.length>0 && authCode != null && memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()) != "Placeholder")
                        AssignmentController.payMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()), authCode.getText());
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
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePay();
                authCode.setText(authCodeList[0]);
            }
        });

        tabbedPane.addTab("Trip Management", card8);

        }
}
