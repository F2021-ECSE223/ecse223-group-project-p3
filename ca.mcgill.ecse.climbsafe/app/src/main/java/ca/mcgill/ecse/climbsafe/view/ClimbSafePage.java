
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
    private static String[] authCodeList;
    private static String[] equipmentNameArray;
    private static String[] equipmentBundleNameArray;
    private static int[] equipmentQuantityArray;
    private static  int[] equipmentBundleQuantityArray;

    private static String updateErrorMsg = "";
    private static String addErrorMsg = "";
    private static String deleteErrorMsg = "";
    private static List<TOBookableItem> equipmentList = TOController.getEquipment();
    private static String[] equipmentListNames = new String[equipmentList.size()];

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
     * updates the array holding all the equipment names
     */
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
            equipmentQuantityArray = new int[equipmentList.size()];
            for (int i = 0; i < equipmentList.size(); i++) {
                equipmentNameArray[i] = equipmentList.get(i).getName();
            }
        }
    }

    /**
     * @author Sebastien Cantin
     * updates the array holding all the bundle names
     */
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
            equipmentBundleQuantityArray = new int[equipmentBundleList.size()];
            for (int i = 0; i < equipmentBundleList.size(); i++) {
                equipmentBundleNameArray[i] = equipmentBundleList.get(i).getName();
            }
        }
    }

    /**
     * @author Sebastien Cantin
     * updates all the important things to pay, the array with all users that have an assignment
     * and their authcodes
     */
    private static void updatePay() {
        List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
        memberEmailList = new String[toAssignmentList.size()];
        authCodeList = new String[toAssignmentList.size()];
        for (int i = 0; i < toAssignmentList.size(); i++) {
            memberEmailList[i] = toAssignmentList.get(i).getMemberEmail();
            authCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
        }
        if (toAssignmentList.size() == 0) {
            memberEmailList = new String[1];
            memberEmailList[0] = "Placeholder";
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
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));

        Dimension dim = new Dimension(217, 20);
        Dimension dim2 = new Dimension(217, 20);

        JPanel start = new JPanel();
        JPanel start1 = new JPanel();
        JPanel start2 = new JPanel();
        JPanel start3 = new JPanel();
        JPanel start4 = new JPanel();
        JPanel start5 = new JPanel();
        JPanel start6 = new JPanel();
        JPanel end = new JPanel();

        JLabel text1 = new JLabel("Climbing Season Start Date", SwingConstants.RIGHT);
        text1.setPreferredSize(dim2);
        JLabel text2 = new JLabel("Number of Weeks", SwingConstants.RIGHT);
        text2.setPreferredSize(dim2);
        JLabel text3 = new JLabel("Price of Guide per Week (Shillings)", SwingConstants.RIGHT);
        text3.setPreferredSize(dim2);
        JLabel text4 = new JLabel("Set Admin Email", SwingConstants.RIGHT);
        text4.setPreferredSize(dim2);
        JLabel text5 = new JLabel("Set Admin Password", SwingConstants.RIGHT);
        text5.setPreferredSize(dim2);

        JButton updateAdmin = new JButton("Update Admin") {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };

        JButton updateClimbSafe = new JButton("Update ClimbSafe") {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };

        JTextField climbingSeasonStart = new JTextField("dd/mm/yyyy");
        climbingSeasonStart.setPreferredSize(dim);
        JTextField numberWeeks = new JTextField("number");
        numberWeeks.setPreferredSize(dim);
        JTextField weeklyCost = new JTextField("$");
        weeklyCost.setPreferredSize(dim);
        JTextField adminEmail = new JTextField("email");
        adminEmail.setPreferredSize(dim);
        JPasswordField adminPW = new JPasswordField("password");
        adminPW.setPreferredSize(dim);

        start1.add(text4);
        start1.add(adminEmail);
        start2.add(text5);
        start2.add(adminPW);
        start3.add(updateAdmin);
        start4.add(text1);
        start4.add(climbingSeasonStart);
        start5.add(text2);
        start5.add(numberWeeks);
        start6.add(text3);
        start6.add(weeklyCost);
        end.add(updateClimbSafe);

        start.setOpaque(false);
        start1.setOpaque(false);
        start2.setOpaque(false);
        start3.setOpaque(false);
        start4.setOpaque(false);
        start5.setOpaque(false);
        start6.setOpaque(false);
        end.setOpaque(false);

        card1.add(start1);
        card1.add(start2);
        card1.add(start3);
        card1.add(start4);
        card1.add(start5);
        card1.add(start6);
        card1.add(end);

       updateAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = adminEmail.getText();
                String pw = String.valueOf(adminPW.getPassword());
                int cost = Integer.parseInt(weeklyCost.getText());

                String startDate = climbingSeasonStart.getText();
                Date startDate2 = java.sql.Date.valueOf(startDate);

                int noWeeks = Integer.parseInt(numberWeeks.getText());
                // (Date startDate, int nrWeeks, int priceOfGuidePerWeek) throws InvalidInputException {
                try {
                    ClimbSafeFeatureSet1Controller.setup(startDate2, noWeeks, cost);
                    ClimbSafeFeatureSet1Controller.updateAdmin(email, pw);
                } catch (InvalidInputException ex) {
                    ex.printStackTrace();
                }


            }
        });
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
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        JLabel nameLabel = new JLabel("Name:",SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:",SwingConstants.RIGHT);
        JLabel lengthLabel = new JLabel("Length of Stay:", SwingConstants.RIGHT);
        JLabel nrWeeks = new JLabel("1 week(s)");
        JLabel stayHotelLabel = new JLabel("Stay at Hotel:",SwingConstants.RIGHT);
        JLabel hotelText = new JLabel("+ 3 days");
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
        before.add(hotelText);
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

                try {
                    List<String> itemNames = new ArrayList<>();
                    itemNames.addAll(Arrays.asList(equipmentNameArray));
                    itemNames.addAll(Arrays.asList(equipmentBundleNameArray));
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : equipmentQuantityArray) itemQuantities.add(i);
                    for (int i : equipmentBundleQuantityArray) itemQuantities.add(i);
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
                    itemNames.addAll(Arrays.asList(equipmentNameArray));
                    itemNames.addAll(Arrays.asList(equipmentBundleNameArray));
                    List<Integer> itemQuantities = new ArrayList<>();
                    for (int i : equipmentQuantityArray) itemQuantities.add(i);
                    for (int i : equipmentBundleQuantityArray) itemQuantities.add(i);
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
                updateEquipmentNames();
                equipmentVisualList.removeAllItems();
                for (String s : equipmentNameArray) equipmentVisualList.addItem(s);
                updateBundlesNames();
                equipmentBundleVisualList.removeAllItems();
                for (String s : equipmentBundleNameArray) equipmentBundleVisualList.addItem(s);
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
        //TODO: add elements to card3 to create the page
        //If you create any JPanels, be sure to use panelName.setOpaque(false)

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

        updateEquipmentList();

        JPanel addEquipmentPanel = new JPanel();
        addEquipmentPanel.setLayout(null);
        addEquipmentPanel.setBounds(230, 50, 1000, 150);
        addEquipmentPanel.setBackground(Color.lightGray);
        addEquipmentPanel.setOpaque(false);

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

        JButton submitButton = new JButton("Add");
        submitButton.setBounds(equipmentPriceField.getX() + 180, equipmentNameField.getY(), 100, 30);

        addEquipmentPanel.add(submitButton);
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
        updateEquipmentPanel.setOpaque(false);

        JLabel updateEquipmentPanelTitle = new JLabel("Update an equipment in the ClimbSafe");
        updateEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        updateEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        updateEquipmentPanel.add(updateEquipmentPanelTitle);

        JTextField equipmentNameUpdateField = new JTextField();
        equipmentNameUpdateField.setBounds(50, 60, 150, 30);

        JLabel test = new JLabel();
        test.setBounds(equipmentNameUpdateField.getX() + 5, equipmentNameUpdateField.getY() + 20, 150, 30);
        test.setForeground(Color.decode("#ff8c00"));
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
        test1.setBounds(equipmentWeightUpdateField.getX() + 5, equipmentNameUpdateField.getY() + 20, 150, 30);
        test1.setForeground(Color.decode("#ff8c00"));
        updateEquipmentPanel.add(test1);

        JTextField equipmentPriceUpdateField = new JTextField();
        equipmentPriceUpdateField.setBounds(equipmentWeightUpdateField.getX() + equipmentWeightUpdateField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceUpdateText = new JLabel("Updated Equipment Price");
        equipmentPriceUpdateText.setBounds(equipmentPriceUpdateField.getX()+3, equipmentPriceUpdateField.getY()-15, 200, 15);
        equipmentPriceUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel test2 = new JLabel();
        test2.setBounds(equipmentPriceUpdateField.getX() + 5, equipmentNameUpdateField.getY() + 20, 150, 30);
        test2.setForeground(Color.decode("#ff8c00"));
        updateEquipmentPanel.add(test2);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(equipmentPriceUpdateField.getX() + 180, equipmentNameUpdateField.getY(), 100, 30);

        updateEquipmentPanel.add(updateButton);
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
        deleteEquipmentPanel.setOpaque(false);

        JLabel deleteEquipmentPanelTitle = new JLabel("Delete an equipment from the ClimbSafe");
        deleteEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        deleteEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        deleteEquipmentPanel.add(deleteEquipmentPanelTitle);

        JTextField equipmentDeleteText = new JTextField("Select an equipment to delete");
        equipmentDeleteText.setBounds(50, 70, 250, 30);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(equipmentDeleteText.getX() + 270, equipmentDeleteText.getY(), 100, 31);

        deleteEquipmentPanel.add(deleteButton);
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

        TOBookableItem eq = null;
        String eqName = allEquipmentsDisplay.getSelectedValue();
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

        allEquipmentsDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                TOBookableItem eq = null;
                String eqName = allEquipmentsDisplay.getSelectedValue();
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
                    equipmentDeleteText.setText("Delete " + eqName + "?");
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(equipmentWeightField.getText().equals("") || equipmentPriceField.getText().equals("")){
                    addErrorMsg = equipmentWeightField.getText().equals("") ? "Weight field can not be empty" : "Price field can not be empty";
                    new Popup(addErrorMsg, card4, 1);
                    addErrorMsg = "";
                    return;
                }
                try{

                    ClimbSafeFeatureSet4Controller.addEquipment(equipmentNameField.getText(), Integer.parseInt(equipmentWeightField.getText()), Integer.parseInt(equipmentPriceField.getText()));
                }
                catch (Exception a){
                    addErrorMsg = a.getMessage();
                }
                if(addErrorMsg.equals("")){
                    new Popup("Successfully added equipment to ClimbSafe.", card4, 0);
                    updateEquipmentList();
                    allEquipmentsDisplay.setListData(equipmentListNames);
                    equipmentNameField.setText("");
                    equipmentWeightField.setText("");
                    equipmentPriceField.setText("");
                }
                new Popup(addErrorMsg, card4, 1);
                addErrorMsg = "";
                card4.revalidate();
                card4.repaint();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(allEquipmentsDisplay.getSelectedValue() == null){
                    updateErrorMsg = "No equipments to update.";
                    new Popup(updateErrorMsg, card4, 1);
                    updateErrorMsg = "";
                    return;
                }
                if(equipmentWeightUpdateField.getText().equals("") || equipmentPriceUpdateField.getText().equals("")){
                    updateErrorMsg = equipmentWeightUpdateField.getText().equals("") ? "Weight field can not be empty" : "Price field can not be empty";
                    new Popup(updateErrorMsg, card4, 1);
                    updateErrorMsg = "";
                    return;
                }
                try{

                    ClimbSafeFeatureSet4Controller.updateEquipment(String.valueOf(allEquipmentsDisplay.getSelectedValue()), equipmentNameUpdateField.getText(), Integer.parseInt(equipmentWeightUpdateField.getText()), Integer.parseInt(equipmentPriceUpdateField.getText()));
                }
                catch (Exception a){
                    updateErrorMsg = a.getMessage();
                }
                if(updateErrorMsg.equals("")){
                    String oldName = String.valueOf(allEquipmentsDisplay.getSelectedValue());
                    new Popup(String.format("Successfully updated equipment %s.", oldName), card4, 0);
                    updateEquipmentList();
                    allEquipmentsDisplay.setListData(equipmentListNames);
                    equipmentNameUpdateField.setText("");
                    equipmentWeightUpdateField.setText("");
                    equipmentPriceUpdateField.setText("");
                }
                new Popup(updateErrorMsg, card4, 1);
                updateErrorMsg = "";
                card4.revalidate();
                card4.repaint();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(allEquipmentsDisplay.getSelectedValue() == null){
                    deleteErrorMsg = "No more equipments to delete.";
                    new Popup(deleteErrorMsg, card4, 1);
                    deleteErrorMsg = "";
                    return;
                }
                try{
                    ClimbSafeFeatureSet6Controller.deleteEquipment(String.valueOf(allEquipmentsDisplay.getSelectedValue()));
                }
                catch (Exception a){
                    deleteErrorMsg = a.getMessage();
                }
                if(deleteErrorMsg.equals("")){
                    String oldName = String.valueOf(allEquipmentsDisplay.getSelectedValue());
                    new Popup(String.format("Successfully deleted equipment %s.", oldName), card4, 0);
                    updateEquipmentList();
                    allEquipmentsDisplay.setListData(equipmentListNames);
                }
                new Popup(deleteErrorMsg, card4, 1);
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
        JComboBox<String> memberNameVisualList = new JComboBox<>(memberEmailList);

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
        authCode.setPreferredSize(new Dimension(authCode.getPreferredSize().width+50, authCode.getPreferredSize().height));

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
                    if (memberEmailList.length>0 && authCode != null && memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()) != "Placeholder")
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
