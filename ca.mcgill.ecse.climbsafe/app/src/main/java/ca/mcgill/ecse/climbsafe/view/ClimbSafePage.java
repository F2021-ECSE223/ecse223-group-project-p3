
package ca.mcgill.ecse.climbsafe.view;


        import ca.mcgill.ecse.climbsafe.controller.*;


        import java.awt.*;
        import java.awt.event.*;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Date;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import javax.imageio.ImageIO;
        import javax.swing.*;
        import javax.swing.border.EmptyBorder;
        import javax.swing.border.TitledBorder;
        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;



public class ClimbSafePage implements KeyListener {
    private static JFrame mainFrame;
    private static JTabbedPane tabbedPane;


    private static String[] equipmentNameArray;
    private static String[] equipmentBundleNameArray;
    private static int[] equipmentQuantityArray;
    private static  int[] equipmentBundleQuantityArray;
    private static String[] memberList;
    private static String[] guideList;

    private static String updateErrorMsg = "";
    private static String addErrorMsg = "";
    private static String deleteErrorMsg = "";
    private static JComboBox<String> equipmentVisualListF = null;
    private static JComboBox<String> equipmentBundleVisualListF = null;
    private static List<TOBookableItem> equipmentList = TOController.getEquipment();
    private static String[] equipmentListNames = new String[equipmentList.size()];
    private static List<TOBookableItem> bundleList = TOController.getEquipment();
    private static String[] bundleListNames = new String[equipmentList.size()];
    private static int[] memberEquipmentQuantityArray = new int[equipmentList.size()];
    private static  int[] memberEquipmentBundleQuantityArray = new int[bundleList.size()];
    private static List<TOAssignment> toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
    private static String[] memberEmailList = new String[toAssignmentList.size()];
    private static String[] memberNameList = new String[toAssignmentList.size()];
    private static String[] guideEmailList = new String[toAssignmentList.size()];
    private static String[] guideNameList = new String[toAssignmentList.size()];
    private static String[] hotelNameList = new String[toAssignmentList.size()];
    private static String[] startWeekList = new String[toAssignmentList.size()];
    private static String[] endWeekList = new String[toAssignmentList.size()];
    private static String[] guideCostList = new String[toAssignmentList.size()];
    private static String[] equipmentCostList = new String[toAssignmentList.size()];
    private static String[] statusList = new String[toAssignmentList.size()];
    private static String[] authCodeList = new String[toAssignmentList.size()];
    private static String[] refundList = new String[toAssignmentList.size()];
    private static String[] bannedStatusList = new String[toAssignmentList.size()];
    private static List<TOBookableItem> bundleListC = TOController.getBundles();
    private static String[] bundleListNamesC = new String[equipmentList.size()];
        
    private static final KeyListener ClimbSafePage = new ClimbSafePage();
    private static String cen = "";
    private static String cew = "";
    private static String cep = "";

        private static void updateEquipmentList(){
        equipmentList = TOController.getEquipment();
        String[] tempList = new String[equipmentList.size()];
        int[] tempList2 = new int[equipmentList.size()];
        for(int i = 0; i < equipmentList.size(); i++){
            tempList[i] = equipmentList.get(i).getName();
        }
        memberEquipmentQuantityArray = tempList2;
        equipmentListNames = tempList;
        for (TOBookableItem e :
                equipmentList) {
            e.delete();
        }
    }
    private static void updateBundleList(){
        bundleList = TOController.getBundles();
        String[] tempList = new String[bundleList.size()];
        int[] tempList2 = new int[bundleList.size()];
        for(int i = 0; i < bundleList.size(); i++){
            tempList[i] = bundleList.get(i).getName();
        }
        memberEquipmentBundleQuantityArray = tempList2;
        bundleListNames = tempList;
        for (TOBookableItem e :
                bundleList) {
            e.delete();
        }
    }




    public static void start(){
        mainFrame = new JFrame("ClimbSafe");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateMembers();

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

       private static void updateMembers() {
        var TOMembersList = TOController.getMembers();
        memberList = new String[TOMembersList.size()];
        for (int i = 0; i < TOMembersList.size(); i++) {
            memberList[i] = TOMembersList.get(i).getEmail();
        }
    }
        
        /**
     * @author Edward Habelrih
     * updates the guide display list in card3 for the guides
     */
    private static void updateGuideList(){
        var TOGuideList = TOController.getGuides();
        guideList = new String[TOGuideList.size()];
        for (int i = 0; i<TOGuideList.size();i++) {
            guideList[i] = TOGuideList.get(i).getEmail();
        }
    }
        
    /**
     * @author Abhijeet Praveen
     * updates the assignment display list in card6 for the assignments
     * when the assignments are initiated, they will then appear on the member list
     * any changes to the authcode or assignment status will also be taken care of.
     */
    private static void updateAssignment() {
        toAssignmentList = ClimbSafeFeatureSet6Controller.getAssignments();
        String[] tempMemberEmailList = new String[toAssignmentList.size()];
        String[] tempMemberNameList = new String[toAssignmentList.size()];
        String[] tempGuideEmailList = new String[toAssignmentList.size()];
        String[] tempGuideNameList = new String[toAssignmentList.size()];
        String[] tempHotelNameList = new String[toAssignmentList.size()];
        String[] tempStartWeekList = new String[toAssignmentList.size()];
        String[] tempEndWeekList = new String[toAssignmentList.size()];
        String[] tempGuideCostList = new String[toAssignmentList.size()];
        String[] tempEquipmentCostList = new String[toAssignmentList.size()];
        String[] tempStatusList = new String[toAssignmentList.size()];
        String[] tempAuthCodeList = new String[toAssignmentList.size()];
        String[] tempRefundList = new String[toAssignmentList.size()];
        String[] tempBannedStatusList = new String[toAssignmentList.size()];
        for (int i = 0; i < toAssignmentList.size(); i++) {
            tempMemberEmailList[i] = toAssignmentList.get(i).getMemberEmail();
            tempMemberNameList[i] = toAssignmentList.get(i).getMemberName();
            tempGuideEmailList[i] = toAssignmentList.get(i).getGuideEmail();
            tempGuideNameList[i] = toAssignmentList.get(i).getGuideName();
            tempHotelNameList[i] = toAssignmentList.get(i).getHotelName();
            tempStartWeekList[i] = String.valueOf(toAssignmentList.get(i).getStartWeek());
            tempEndWeekList[i] = String.valueOf(toAssignmentList.get(i).getEndWeek());
            tempGuideCostList[i] = String.valueOf(toAssignmentList.get(i).getTotalCostForGuide());
            tempEquipmentCostList[i] = String.valueOf(toAssignmentList.get(i).getTotalCostForEquipment());
            tempStatusList[i] = toAssignmentList.get(i).getStatus();
            tempAuthCodeList[i] = toAssignmentList.get(i).getAuthorizationCode();
            tempRefundList[i] = String.valueOf(toAssignmentList.get(i).getRefundedPercentageAmount());
            tempBannedStatusList[i] = toAssignmentList.get(i).getBannedStatus();
        }
        memberEmailList = tempMemberEmailList;
        memberNameList = tempMemberNameList;
        guideEmailList = tempGuideEmailList;
        guideNameList = tempGuideNameList;
        hotelNameList = tempHotelNameList;
        startWeekList = tempStartWeekList;
        endWeekList = tempEndWeekList;
        guideCostList = tempGuideCostList;
        equipmentCostList = tempEquipmentCostList;
        statusList = tempStatusList;
        authCodeList = tempAuthCodeList;
        refundList = tempRefundList;
        bannedStatusList = tempBannedStatusList;
        for (TOAssignment assignment: toAssignmentList) {
            assignment.delete();
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
       /**
        * @author Neel Faucher
        * Setup NMC page UI, with routing to Update Admin and Setup NMC controllers,
        * and checks to see whether the user's inputs are valid with appropriate popup messages
        */

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
                size.height = 75;
                size.width = 250;
                return size;
            }
        };

        JButton updateClimbSafe = new JButton("Update ClimbSafe") {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 75;
                size.width = 250;
                return size;
            }
        };

        JTextField climbingSeasonStart = new JTextField("yyyy-mm-dd");
        climbingSeasonStart.setPreferredSize(dim);
        JTextField numberWeeks = new JTextField("number");
        numberWeeks.setPreferredSize(dim);
        JTextField weeklyCost = new JTextField();
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

       updateClimbSafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean error = false;
                boolean errorDate = false;
                boolean errorPrice = false;
                boolean errorNoWeeks = false;
                String startDate;
                Date startDate2 = null;
                int costCS = 0;
                int noWeeks = 0;

                try{
                    startDate = climbingSeasonStart.getText();
                    startDate2 = java.sql.Date.valueOf(startDate);
                }
                catch (Exception ex){
                    errorDate = true;
                    new Popup("Please respect the format for date", card1, 1);
                }
                try{
                    costCS = Integer.parseInt(weeklyCost.getText());
                }
                catch (Exception ex){
                    errorPrice = true;
                    new Popup("Please respect format for the price per week", card1, 1);
                }

                try {
                    noWeeks = Integer.parseInt(numberWeeks.getText());
                }
                catch (Exception exc) {
                    errorNoWeeks = true;
                    new Popup("Please respect format for number of weeks", card1, 1);
                }
                
                if(!errorDate && !errorPrice && !errorNoWeeks){
                    try {
                        ClimbSafeFeatureSet1Controller.setup((java.sql.Date) startDate2, noWeeks, costCS);
                    } catch (Exception ex) {
                        error = true;
                        new Popup(ex.getMessage(), card1, 1);
                    }
                    if(!error){
                        new Popup("Successfully updated ClimbSafe", card1, 0);
                    }
                }
            }
        });

       updateAdmin.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Boolean error = false;
               Boolean errorEmail = false;
               Boolean errorPW = false;
               String email = "";
               String pw = "";

               try{
                   email = adminEmail.getText();
               }
               catch (Exception ex){
                   errorEmail = true;
                   new Popup("Please respect email format", card1, 1);
               }
               try {
                   pw = String.valueOf(adminPW.getPassword());
               }
               catch (Exception ex){
                   errorPW = true;
                   new Popup("Please respect password format", card1, 1);
               }
               if(!errorEmail && !errorPW){
                   try {
                       ClimbSafeFeatureSet1Controller.updateAdmin(email, pw);
                   } catch (Exception ex) {
                       error = true;
                       new Popup(ex.getMessage(), card1, 1);
                   }
                   if(!error){
                       new Popup("Successfully updated admin", card1, 0);
                   }
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

        updateMembers();

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

        updateEquipmentList();
        updateBundleList();
        JComboBox members = new JComboBox(memberList);
        members.setPreferredSize(new Dimension(members.getPreferredSize().width +50,members.getPreferredSize().height));
        JComboBox<String> equipmentVisualList = new JComboBox<>(equipmentListNames);
        equipmentVisualList.setPreferredSize(new Dimension(equipmentVisualList.getPreferredSize().width+50, equipmentVisualList.getPreferredSize().height));
        JComboBox<String> equipmentBundleVisualList = new JComboBox<>(bundleListNames);
        equipmentBundleVisualList.setPreferredSize(new Dimension(equipmentBundleVisualList.getPreferredSize().width+50, equipmentBundleVisualList.getPreferredSize().height));



        card2.add(memberPart);
        card2.add(bottomButtons);

        memberPart.add(members);
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

        members.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TONamedUser member = TOController.getUserWithEmail((String) members.getSelectedItem());
                if(member == null) return;
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
                    new Popup("Member registered successfully",card2,0);
                    updateMembers();
                    System.out.println("never Fail");
                    members.removeAllItems();
                    for (var m : memberList){
                        System.out.println(m);
                        members.addItem(m);
                    }
                } catch (Exception ex) {
                    new Popup(ex.getMessage(),card2,1);
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
                    new Popup("Member updated successfully",card2,0);
                } catch (Exception ex) {
                    new Popup(ex.getMessage(),card2,1);
                }
            }
        });
        deleteMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClimbSafeFeatureSet1Controller.deleteMember(email.getText());
                    for (var member: TOController.getMembers())System.out.println(member.getEmail());
                    new Popup("Member deleted successfully",card2,0);
                    updateMembers();
                    members.removeAllItems();
                    for (var m : memberList){
                        members.addItem(m);
                        System.out.println(m);
                    }
                } catch (Exception ex) {
                    new Popup(ex.getMessage(),card2,0);
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


 /**
     * @author Edward Habelrih
     * Register/Update/Delete Guide UI
     */
    public static void addGuideCard(){
            updateGuideList();
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
            card3.setLayout(new BoxLayout(card3, BoxLayout.Y_AXIS));

            //PANELS

            //Main panels
            JPanel listandfields = new JPanel();
            JPanel guideInfo = new JPanel();
            guideInfo.setLayout(new BoxLayout(guideInfo, BoxLayout.Y_AXIS));
            JPanel list = new JPanel(); //JCombo box with the list of members
            JPanel buttons = new JPanel();




            //Sub panels
            JPanel emails = new JPanel();
            JPanel names = new JPanel();
            JPanel passwords = new JPanel();
            JPanel emergencyContacts = new JPanel();
            JPanel space = new JPanel();
            JPanel space2 = new JPanel();
            JPanel space3 = new JPanel();
            JPanel space4 = new JPanel();
            JPanel space5 = new JPanel();
            JPanel space6 = new JPanel();
            JPanel space7 = new JPanel();
            JPanel space8 = new JPanel();
            JPanel space9 = new JPanel();
            //Labels + Text fields

            JLabel email = new JLabel("Email: ", SwingConstants.RIGHT);
            JLabel name = new JLabel    ("Name: ", SwingConstants.RIGHT);
            JLabel emergencyContact = new JLabel("Emergency contact: ", SwingConstants.RIGHT);
            JLabel password = new JLabel("Password: ", SwingConstants.RIGHT);
            JLabel empty = new JLabel("");

            JTextField emailEntry = new JTextField("johndoe@gmail.com");
            JTextField nameEntry = new JTextField("John Doe");
            JPasswordField passwordEntry = new JPasswordField("Password: ");
            JTextField emergencyContactEntry = new JTextField("(xxx) xxx-xxxx");

            //Jcombo Box
            JComboBox guides = new JComboBox(guideList);
            guides.setPreferredSize(new Dimension(guides.getPreferredSize().width+50, guides.getPreferredSize().height));

            //Resize Text fields
            Dimension textDimensions = new Dimension(600, 25);
            emailEntry.setPreferredSize(textDimensions);
            nameEntry.setPreferredSize(textDimensions);
            passwordEntry.setPreferredSize(textDimensions);
            emergencyContactEntry.setPreferredSize(textDimensions);

            //Resize Labels
            email.setPreferredSize(emergencyContact.getPreferredSize());
            name.setPreferredSize(emergencyContact.getPreferredSize());
            password.setPreferredSize(emergencyContact.getPreferredSize());
            emergencyContact.setPreferredSize(emergencyContact.getPreferredSize());

            //BUTTONS
            JButton registerGuide = new JButton("Register Guide"){
                public Dimension getPreferredSize() {
                    Dimension size = super.getPreferredSize();
                    size.height += 30;
                    size.width += 50;
                    return size;
                }
            };
            JButton updateGuide = new JButton("Update Guide"){
                public Dimension getPreferredSize() {
                    Dimension size = super.getPreferredSize();
                    size.height += 30;
                    size.width += 50;
                    return size;
                }
            };
            JButton deleteGuide = new JButton("Delete Guide"){
                public Dimension getPreferredSize() {
                    Dimension size = super.getPreferredSize();
                    size.height += 30;
                    size.width += 50;
                    return size;
                }
            };

            //ADD LABELS TO CORRESPONDING PANELS
            emails.add(email);
            emails.add(emailEntry);
            names.add(name);
            names.add(nameEntry);
            passwords.add(password);
            passwords.add(passwordEntry);
            emergencyContacts.add(emergencyContact);
            emergencyContacts.add(emergencyContactEntry);

            //spaces
            space.add(empty);
            space2.add(empty);
            space3.add(empty);
            space4.add(empty);
            space5.add(empty);
            space6.add(empty);
            space7.add(empty);
            space8.add(empty);
            space9.add(empty);
            //Add sub-panels to main panels
            guideInfo.add(space9);
            guideInfo.add(space8);
            guideInfo.add(space7);
            guideInfo.add(space6);
            guideInfo.add(space5);
            guideInfo.add(emails);
            guideInfo.add(space);
            guideInfo.add(names);
            guideInfo.add(space2);
            guideInfo.add(passwords);
            guideInfo.add(space3);
            guideInfo.add(emergencyContacts);
            guideInfo.add(space4);

            buttons.add(registerGuide);
            buttons.add(updateGuide);
            buttons.add(deleteGuide);


            list.add(guides);

            listandfields.add(list);
            listandfields.add(guideInfo);
            //SET OPACITY FALSE
            //Main panels
            guideInfo.setOpaque(false);
            buttons.setOpaque(false);
            list.setOpaque(false);
            listandfields.setOpaque(false);
            //Sub panels
            emails.setOpaque(false);
            names.setOpaque(false);
            passwords.setOpaque(false);
            emergencyContacts.setOpaque(false);
            space.setOpaque(false);
            space2.setOpaque(false);
            space3.setOpaque(false);
            space4.setOpaque(false);
            space5.setOpaque(false);
            space6.setOpaque(false);
            space7.setOpaque(false);
            space8.setOpaque(false);
            space9.setOpaque(false);
            //ADD TO CARD
            card3.add(listandfields);
            card3.add(buttons);

            tabbedPane.addTab("Guide", card3);

            //ACTION LISTENERS
            deleteGuide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ClimbSafeFeatureSet1Controller.deleteGuide(emailEntry.getText());

                    } catch (Exception ex) {
                        new Popup(ex.getMessage(), card3, 1);
                    }
                    new Popup("Guide deleted successfully!", card3, 0);
                    updateGuideList();
                    if(guides!=null){
                        guides.removeAllItems();
                        for(String guide: guideList)
                            guides.addItem(guide);
                    }
                }
            });

            updateGuide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ClimbSafeFeatureSet3Controller.updateGuide(emailEntry.getText(), passwordEntry.getText(), nameEntry.getText(), emergencyContactEntry.getText());
                        new Popup("Guide updated successfully!", card3, 0);
                    } catch (Exception ex) {
                        new Popup(ex.getMessage(),card3,1);
                    }
                    updateGuideList();
                    if(guides!=null){
                        guides.removeAllItems();
                        for(String guide: guideList)
                            guides.addItem(guide);
                    }

                }
            });

            registerGuide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ClimbSafeFeatureSet3Controller.registerGuide(emailEntry.getText(), String.valueOf(passwordEntry.getPassword()), nameEntry.getText(), emergencyContactEntry.getText());
                        new Popup("Guide registered successfully!", card3, 0);
                    } catch (Exception ex) {
                        new Popup(ex.getMessage(),card3,1);
                    }
                    updateGuideList();
                    if(guides!=null){
                        guides.removeAllItems();
                        for(String guide: guideList)
                            guides.addItem(guide);
                    }
                }
            });

            guides.addActionListener(new ActionListener() {//TODO: finish this
                @Override
                public void actionPerformed(ActionEvent e) {
                    TONamedUser guide = TOController.getUserWithEmail((String) guides.getSelectedItem());
                    if(guide != null){
                        emailEntry.setText(guide.getEmail());
                        nameEntry.setText(guide.getName());
                        passwordEntry.setText(guide.getPassword());
                        emergencyContactEntry.setText(guide.getEmergencyContact());
                    }
                }
            });



        }




    /**
     * @author Romen Poirier Taksev
     */
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

        updateEquipmentList();

        //Panel for adding equipment
        JPanel addEquipmentPanel = new JPanel();
        addEquipmentPanel.setLayout(null);
        addEquipmentPanel.setBounds(230, 50, 1000, 150);
        addEquipmentPanel.setBackground(Color.lightGray);
        addEquipmentPanel.setOpaque(false);

        //title of add equipment panel
        JLabel addEquipmentPanelTitle = new JLabel("Add a new equipment to the ClimbSafe");
        addEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        addEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);

        //text field and title of text field to set name of equipment
        JTextField equipmentNameField = new JTextField();
        equipmentNameField.setBounds(50, 60, 150, 30);
        JLabel equipmentNameText = new JLabel("Equipment Name");
        equipmentNameText.setBounds(equipmentNameField.getX()+3, equipmentNameField.getY()-15, 150, 15);
        equipmentNameText.setHorizontalAlignment(SwingConstants.LEFT);

        //text field and title of text field to set weight of equipment
        JTextField equipmentWeightField = new JTextField();
        equipmentWeightField.setBounds(equipmentNameField.getX() + equipmentNameField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentWeightText = new JLabel("Equipment Weight");
        equipmentWeightText.setBounds(equipmentWeightField.getX()+3, equipmentWeightField.getY()-15, 150, 15);
        equipmentWeightText.setHorizontalAlignment(SwingConstants.LEFT);

        //text field and title of text field to set price of equipment
        JTextField equipmentPriceField = new JTextField();
        equipmentPriceField.setBounds(equipmentWeightField.getX() + equipmentWeightField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceText = new JLabel("Equipment Price");
        equipmentPriceText.setBounds(equipmentPriceField.getX()+3, equipmentPriceField.getY()-15, 150, 15);
        equipmentPriceText.setHorizontalAlignment(SwingConstants.LEFT);

        //button to add equipment
        JButton submitButton = new JButton("Add");
        submitButton.setBounds(equipmentPriceField.getX() + 180, equipmentNameField.getY(), 100, 30);

        //add all of previous to add equipment panel
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

        //title of update equipment panel
        JLabel updateEquipmentPanelTitle = new JLabel("Update an equipment in the ClimbSafe");
        updateEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        updateEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        updateEquipmentPanel.add(updateEquipmentPanelTitle);

        //text field and title of text field to update name of equipment
        JTextField equipmentNameUpdateField = new JTextField();
        equipmentNameUpdateField.setBounds(50, 60, 150, 30);
        JLabel equipmentNameUpdateText = new JLabel("Updated Equipment Name");
        equipmentNameUpdateText.setBounds(equipmentNameUpdateField.getX()+3, equipmentNameUpdateField.getY()-15, 200, 15);
        equipmentNameUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        //text field and title of text field to update weight of equipment
        JTextField equipmentWeightUpdateField = new JTextField();
        equipmentWeightUpdateField.setBounds(equipmentNameUpdateField.getX() + equipmentNameUpdateField.getWidth() + 50, equipmentNameUpdateField.getY(), 150, 30);
        JLabel equipmentWeightUpdateText = new JLabel("Updated Equipment Weight");
        equipmentWeightUpdateText.setBounds(equipmentWeightUpdateField.getX()+3, equipmentWeightUpdateField.getY()-15, 200, 15);
        equipmentWeightUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        //text field and title of text field to update price of equipment
        JTextField equipmentPriceUpdateField = new JTextField();
        equipmentPriceUpdateField.setBounds(equipmentWeightUpdateField.getX() + equipmentWeightUpdateField.getWidth() + 50, equipmentNameField.getY(), 150, 30);
        JLabel equipmentPriceUpdateText = new JLabel("Updated Equipment Price");
        equipmentPriceUpdateText.setBounds(equipmentPriceUpdateField.getX()+3, equipmentPriceUpdateField.getY()-15, 200, 15);
        equipmentPriceUpdateText.setHorizontalAlignment(SwingConstants.LEFT);

        //button to submit changes
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(equipmentPriceUpdateField.getX() + 180, equipmentNameUpdateField.getY(), 100, 30);

        //add all of above to update equipment panel
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

        //title of delete equipment panel
        JLabel deleteEquipmentPanelTitle = new JLabel("Delete an equipment from the ClimbSafe");
        deleteEquipmentPanelTitle.setBounds(5,5,addEquipmentPanel.getWidth(),15);
        deleteEquipmentPanelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        deleteEquipmentPanel.add(deleteEquipmentPanelTitle);

        //text space to see which equipment will be deleted
        JTextField equipmentDeleteText = new JTextField("Select an equipment to delete");
        equipmentDeleteText.setBounds(50, 70, 250, 30);
        equipmentDeleteText.setEditable(false);

        //button to delete currently selected equipment
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(equipmentDeleteText.getX() + 270, equipmentDeleteText.getY(), 100, 31);

        //add all of above to delete equipment panel
        deleteEquipmentPanel.add(deleteButton);
        deleteEquipmentPanel.add(equipmentDeleteText);

        //List to display all equipments currently in the climbsafe
        JList<String> allEquipmentsDisplay = new JList<>();
        allEquipmentsDisplay.setListData(equipmentListNames);
        allEquipmentsDisplay.setBounds(65,addEquipmentPanel.getY(),150,300);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)allEquipmentsDisplay.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        allEquipmentsDisplay.setBorder(new TitledBorder("All Equipments"));

        //all everything to card 4
        card4.add(allEquipmentsDisplay);
        card4.add(addEquipmentPanel);
        card4.add(updateEquipmentPanel);
        card4.add(deleteEquipmentPanel);
        card4.setLayout(null);
        card4.setVisible(true);

        //set listeners and conditions for autofill
        equipmentNameUpdateField.setName("equipmentNameUpdateField");
        equipmentPriceUpdateField.setName("equipmentPriceUpdateField");
        equipmentWeightUpdateField.setName("equipmentWeightUpdateField");

        //update current equipment name, weight and price
        TOBookableItem eq = null;
        String eqName = allEquipmentsDisplay.getSelectedValue();
        for (TOBookableItem tob:
                equipmentList) {
            if(tob.getName().equals(eqName)){
                eq = tob;
            }
        }
        if(eq != null){
            equipmentNameUpdateField.setText(eqName);
            equipmentWeightUpdateField.setText(String.valueOf(eq.getWeight()));
            equipmentPriceUpdateField.setText(String.valueOf(eq.getPricePerWeek()));
        }

        card4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                allEquipmentsDisplay.clearSelection();
                equipmentDeleteText.setText("");
                equipmentNameUpdateField.setText("");
                equipmentWeightUpdateField.setText("");
                equipmentPriceUpdateField.setText("");
                allEquipmentsDisplay.repaint();
                allEquipmentsDisplay.revalidate();
            }
        });

        /**
         * @author Romen Poirier Taksev
         * checks which element is currently selected in the list, and updates current equipment name, price and weight and delete text.
         */
        allEquipmentsDisplay.addListSelectionListener(e -> {
            TOBookableItem eq1 = null;
            String eqName1 = allEquipmentsDisplay.getSelectedValue();
            for (TOBookableItem tob:
                    equipmentList) {
                if(tob.getName().equals(eqName1)){
                    eq1 = tob;
                }
            }
            if(eq1 != null){
                equipmentNameUpdateField.setText(eqName1);
                equipmentWeightUpdateField.setText(String.valueOf(eq1.getWeight()));
                equipmentPriceUpdateField.setText(String.valueOf(eq1.getPricePerWeek()));
                equipmentDeleteText.setText("Delete " + eqName1 + "?");
            }
        });

        /**
         * @author Romen Poirier Taksev
         * when submit button is pressed, try to add equipment to climbsafe. Otheriwse throw error
         */
        submitButton.addActionListener(e -> {

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
                new Popup(String.format("Successfully added %s to ClimbSafe.", equipmentNameField.getText()), card4, 0);
                updateEquipmentList();
                allEquipmentsDisplay.setListData(equipmentListNames);
                if(equipmentVisualListF != null){ equipmentVisualListF.addItem(equipmentNameField.getText());}
            }
            else{
                new Popup(addErrorMsg, card4, 1);
            }
            equipmentNameField.setText("");
            equipmentWeightField.setText("");
            equipmentPriceField.setText("");
            equipmentNameUpdateField.setText("");
            equipmentPriceUpdateField.setText("");
            equipmentWeightUpdateField.setText("");
            equipmentDeleteText.setText("");
            addErrorMsg = "";
            card4.revalidate();
            card4.repaint();
        });

        /**
         * @author Romen Poirier Taksev
         * when update button is pressed, try to update equipment in the climbsafe of the name selected in the List. Throw an error if it doesn't work.
         */
        updateButton.addActionListener(e -> {
            if(allEquipmentsDisplay.getSelectedValue() == null){
                updateErrorMsg = equipmentList.size() != 0 ? "No equipment currently selected." :"No equipments to update.";
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
                if(equipmentVisualListF != null){
                    equipmentVisualListF.removeAllItems();
                    for (String s :
                            equipmentListNames) {
                        equipmentVisualListF.addItem(s);
                    }
                }
            }
            else{
                new Popup(updateErrorMsg, card4, 1);
            }
            equipmentNameField.setText("");
            equipmentWeightField.setText("");
            equipmentPriceField.setText("");
            equipmentNameUpdateField.setText("");
            equipmentPriceUpdateField.setText("");
            equipmentWeightUpdateField.setText("");
            equipmentDeleteText.setText("");
            updateErrorMsg = "";
            card4.revalidate();
            card4.repaint();
        });

        /**
         * @author Romen Poirier Taksev
         * when delete button is pressed, try to delete equipment in the climbsafe of the name selected in the List. Throw an error if it doesn't work.
         */
        deleteButton.addActionListener(e -> {

            if(allEquipmentsDisplay.getSelectedValue() == null){

                deleteErrorMsg = equipmentList.size() != 0 ? "No equipment currently selected." :"No more equipments to delete.";
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
                if(equipmentVisualListF != null){
                    equipmentVisualListF.removeAllItems();
                    for (String s :
                            equipmentListNames) {
                        equipmentVisualListF.addItem(s);
                    }
                }
            }
            else{
                new Popup(deleteErrorMsg, card4, 1);
            }
            equipmentNameField.setText("");
            equipmentWeightField.setText("");
            equipmentPriceField.setText("");
            equipmentNameUpdateField.setText("");
            equipmentPriceUpdateField.setText("");
            equipmentWeightUpdateField.setText("");
            equipmentDeleteText.setText("");
            deleteErrorMsg = "";
            card4.revalidate();
            card4.repaint();
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

        Dimension dim = new Dimension(130, 20);

        JPanel start = new JPanel();
        JPanel bundle = new JPanel();
        JPanel name = new JPanel();
        JPanel equipment = new JPanel();
        JPanel price = new JPanel();
        JPanel rightTable = new JPanel();
        JPanel bundleInput = new JPanel();
        JPanel buttons = new JPanel();

        card5.setLayout(new BoxLayout(card5, BoxLayout.Y_AXIS));
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
        bundleInput.setLayout(new BoxLayout(bundleInput, BoxLayout.X_AXIS));

        JComboBox<String> bundleList = new JComboBox<>(bundleListNames);
        JComboBox<String> equipmentList = new JComboBox<>(equipmentListNames);
        JLabel bundleNameLbl = new JLabel("Bundle Name:", SwingConstants.RIGHT);
        JLabel nameLbl  = new JLabel("Name:", SwingConstants.RIGHT);
        JLabel equipmentNameLbl = new JLabel("Equipment:", SwingConstants.RIGHT);
        JLabel pricelbl = new JLabel("Discount:", SwingConstants.RIGHT);
        JTextField nameTxt = new JTextField("");
        nameTxt.setPreferredSize(dim);

        JTextField priceTxt = new JTextField("0");
        priceTxt.setPreferredSize(dim);
        JLabel quantityNumber = new JLabel("1", SwingConstants.CENTER);

        JTable table = new JTable(equipmentListNames.length+1, 2);
        table.setEnabled(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.setValueAt("Equipment",0,0);
        table.setValueAt("Quantity",0,1);

            for (int i = 1; i < equipmentListNames.length+1; i++) {
                table.setValueAt(equipmentListNames[i-1],i,0);
             }
            for (int i = 1; i < equipmentListNames.length+1; i++) {
                 table.setValueAt(0,i,1);
             }


        JButton quantityDown = new JButton("<html>-</html>");
        JButton quantityUp = new JButton("<html>+</html>");
        JButton save = new JButton("Save"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 1;
                size.width += 10;
                return size;
            }};
        JButton register = new JButton("Add Bundle"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        JButton update = new JButton("Update Bundle"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };
        JButton cancel = new JButton("Delete Bundle"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 50;
                size.width += 50;
                return size;
            }
        };

        start.setOpaque(false);
        rightTable.setOpaque(false);
        buttons.setOpaque(false);
        bundle.setOpaque(false);
        name.setOpaque(false);
        equipment.setOpaque(false);
        price.setOpaque(false);
        bundleInput.setOpaque(false);

        bundle.add(bundleNameLbl);
        bundle.add(bundleList);
        name.add(nameLbl);
        name.add(nameTxt);

        equipment.add(equipmentNameLbl);
        equipment.add(equipmentList);
        equipment.add(quantityDown);
        equipment.add(quantityNumber);
        equipment.add(quantityUp);
        equipment.add(save);
        price.add(pricelbl);
        price.add(priceTxt);

        rightTable.add(table,SwingConstants.CENTER);
        start.add(bundle);
        start.add(name);
        start.add(equipment);
        start.add(price);

        bundleInput.add(start);
        bundleInput.add(rightTable);

        buttons.add(register);
        buttons.add(update);
        buttons.add(cancel);

        card5.add(bundleInput);
        card5.add(buttons);

        int[] equipmentBundleQuantityArray= new int[equipmentListNames.length];
        String[] equipmentArray= new String[equipmentListNames.length];
        quantityUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(quantityNumber.getText());
                num += 1;
                quantityNumber.setText(String.valueOf(num));
                equipmentBundleQuantityArray[equipmentList.getSelectedIndex()] = num;
            }
        });
        quantityDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(quantityNumber.getText());
                num -= 1;
                if (num<0) num = 0;
                quantityNumber.setText(String.valueOf(num));
                equipmentBundleQuantityArray[equipmentList.getSelectedIndex()] = num;
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(quantityNumber.getText());
                quantityNumber.setText(String.valueOf(num));
                equipmentBundleQuantityArray[equipmentList.getSelectedIndex()] = num;
              table.setValueAt(equipmentBundleQuantityArray[equipmentList.getSelectedIndex()],equipmentList.getSelectedIndex()+1,1);

            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<String> itemNames = new ArrayList<>();
                    List<Integer> itemQuantity = new ArrayList<>();

                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    if(Integer.parseInt(String.valueOf(table.getValueAt(i,1)))!=0){
                        itemNames.add(String.valueOf(table.getValueAt(i,0)));
                        itemQuantity.add(Integer.parseInt(String.valueOf(table.getValueAt(i,1))));
                    }
                }
                    ClimbSafeFeatureSet5Controller.addEquipmentBundle(nameTxt.getText(),Integer.parseInt(priceTxt.getText()),itemNames,itemQuantity);
                    new Popup("Equipment Bundle has been added",card5,0);
                }catch(Exception ex){
                    new Popup(ex.getMessage(),card5,1);
                }
                updateBundleNameList();
                bundleList.removeAllItems();
                equipmentBundleVisualListF.addItem(s);
                for (String s : equipmentBundleNameArray) bundleList.addItem(s);
                for (String s : equipmentBundleNameArray) equipmentBundleVisualListF.addItem(s);
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(equipmentListNames[i-1],i,0);
                }
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(0,i,1);
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<String> itemNames = new ArrayList<>();
                    List<Integer> itemQuantity = new ArrayList<>();

                    for (int i = 1; i < equipmentListNames.length+1; i++) {
                        if(Integer.parseInt(String.valueOf(table.getValueAt(i,1)))!=0){
                            itemNames.add(String.valueOf(table.getValueAt(i,0)));
                            itemQuantity.add(Integer.parseInt(String.valueOf(table.getValueAt(i,1))));
                        }
                    }
                    ClimbSafeFeatureSet5Controller.updateEquipmentBundle((String)bundleList.getSelectedItem(),nameTxt.getText(),Integer.parseInt(priceTxt.getText()),itemNames,itemQuantity);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                updateBundleNameList();
                bundleList.removeAllItems();
                for (String s : equipmentBundleNameArray) bundleList.addItem(s);
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(equipmentListNames[i-1],i,0);
                }
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(0,i,1);
                }

            }
        });
            cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   ClimbSafeFeatureSet6Controller.deleteEquipmentBundle((String)bundleList.getSelectedItem());
                   new Popup("Equipment Bundle has been deleted",card5,0);
               }catch (Exception ex){
                   new Popup(ex.getMessage(),card5,1);
               }
                updateBundleNameList();
                bundleList.removeAllItems();
                for (String s : equipmentBundleNameArray) bundleList.addItem(s);
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(equipmentListNames[i-1],i,0);
                }
                for (int i = 1; i < equipmentListNames.length+1; i++) {
                    table.setValueAt(0,i,1);
                }

            }
        });
        bundleList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> itemNames = new ArrayList<>();
                List<Integer> itemQuantity = new ArrayList<>();
                TOBookableItem eq = null;
                String eqName = (String) bundleList.getSelectedItem();
                for (TOBookableItem tob: bundleListC) {
                    if(tob.getName().equals(eqName)){
                        eq = tob;
                    }
                }
                if(eq != null){
                    nameTxt.setText(eqName);
                    priceTxt.setText(String.valueOf(eq.getDiscount()));
                }
            }
        });
        tabbedPane.addTab("Bundles", card5);
    }

        public static void updateBundleNameList(){
        List<TOBookableItem> equipmentBundleList = TOController.getBundles();
        if (equipmentBundleList==null){
            equipmentBundleNameArray = new String[1];
            equipmentBundleNameArray[0] = "Placeholder";
        }
        else {
            equipmentBundleNameArray = new String[equipmentBundleList.size()];
            for (int i = 0; i < equipmentBundleList.size(); i++) {
                equipmentBundleNameArray[i] = equipmentBundleList.get(i).getName();
            }
        }
    }

    /**
     * @author Abhijeet Praveen
     * added the UI page (tab) for view assignments
     * Using JList to view all Members
     * Based on the member selected
     * the info on the right side
     * will be filled with the info of the member
     * the right side is filled with JLabels and JTextFields
     * corresponding to the info of the member.
     */
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


        card6.setLayout(new BoxLayout(card6,BoxLayout.Y_AXIS));


        updateAssignment();

        JPanel viewAssignments = new JPanel();
        viewAssignments.setOpaque(false);

        JList<String> displayList = new JList<>(memberEmailList);
        displayList.setPreferredSize(new Dimension(displayList.getPreferredSize().width +100,displayList.getPreferredSize().height+50));
        displayList.setBorder(new TitledBorder("Member"));


        viewAssignments.add(displayList);

        JPanel allInfoOnRightSide = new JPanel();
        allInfoOnRightSide.setOpaque(false);
        allInfoOnRightSide.setLayout(new BoxLayout(allInfoOnRightSide,BoxLayout.Y_AXIS));
        viewAssignments.add(allInfoOnRightSide);


        JPanel panelforMemberEmail = new JPanel();
        panelforMemberEmail.setOpaque(false);
        JPanel panelforMemberName = new JPanel();
        panelforMemberName.setOpaque(false);
        JPanel panelforGuideEmail = new JPanel();
        panelforGuideEmail.setOpaque(false);
        JPanel panelforGuideName = new JPanel();
        panelforGuideName.setOpaque(false);
        JPanel panelforHotelName = new JPanel();
        panelforHotelName.setOpaque(false);
        JPanel panelforStartWeek = new JPanel();
        panelforStartWeek.setOpaque(false);
        JPanel panelforEndWeek = new JPanel();
        panelforEndWeek.setOpaque(false);
        JPanel panelforGuideCost = new JPanel();
        panelforGuideCost.setOpaque(false);
        JPanel panelforEquipmentCost = new JPanel();
        panelforEquipmentCost.setOpaque(false);
        JPanel panelforAssignmentStatus = new JPanel();
        panelforAssignmentStatus.setOpaque(false);
        JPanel panelforBanStatus = new JPanel();
        panelforBanStatus.setOpaque(false);
        JPanel panelforAuthCode = new JPanel();
        panelforAuthCode.setOpaque(false);
        JPanel panelforRefund = new JPanel();
        panelforRefund.setOpaque(false);



        allInfoOnRightSide.add(panelforMemberEmail);
        allInfoOnRightSide.add(panelforMemberName);
        allInfoOnRightSide.add(panelforGuideEmail);
        allInfoOnRightSide.add(panelforGuideName);
        allInfoOnRightSide.add(panelforHotelName);
        allInfoOnRightSide.add(panelforStartWeek);
        allInfoOnRightSide.add(panelforEndWeek);
        allInfoOnRightSide.add(panelforGuideCost);
        allInfoOnRightSide.add(panelforEquipmentCost);
        allInfoOnRightSide.add(panelforAssignmentStatus);
        allInfoOnRightSide.add(panelforBanStatus);
        allInfoOnRightSide.add(panelforAuthCode);
        allInfoOnRightSide.add(panelforRefund);

        JLabel labelMemberEmail = new JLabel("Member Email: ");
        labelMemberEmail.setPreferredSize(new Dimension(150,30));
        JTextField memberEmail = new JTextField();
        memberEmail.setEditable(false);
        memberEmail.setPreferredSize(new Dimension(150,21));
        panelforMemberEmail.add(labelMemberEmail);
        panelforMemberEmail.add(memberEmail);

        JLabel labelMemberName = new JLabel("Member Name: ");
        labelMemberName.setPreferredSize(new Dimension(150,30));
        JTextField memberName = new JTextField();
        memberName.setEditable(false);
        memberName.setPreferredSize(new Dimension(150,21));
        panelforMemberName.add(labelMemberName);
        panelforMemberName.add(memberName);

        JLabel labelGuideEmail = new JLabel("Guide Email: ");
        labelGuideEmail.setPreferredSize(new Dimension(150,30));
        JTextField guideEmail = new JTextField();
        guideEmail.setEditable(false);
        guideEmail.setPreferredSize(new Dimension(150,21));
        panelforGuideEmail.add(labelGuideEmail);
        panelforGuideEmail.add(guideEmail);

        JLabel labelGuideName = new JLabel("Guide Name: ");
        labelGuideName.setPreferredSize(new Dimension(150,30));
        JTextField guideName = new JTextField();
        guideName.setEditable(false);
        guideName.setPreferredSize(new Dimension(150,21));
        panelforGuideName.add(labelGuideName);
        panelforGuideName.add(guideName);

        JLabel labelHotelName = new JLabel("Hotel Name: ");
        labelHotelName.setPreferredSize(new Dimension(150,30));
        JTextField hotelName = new JTextField();
        hotelName.setEditable(false);
        hotelName.setPreferredSize(new Dimension(150,21));
        panelforHotelName.add(labelHotelName);
        panelforHotelName.add(hotelName);

        JLabel labelStartWeek = new JLabel("Start Week: ");
        labelStartWeek.setPreferredSize(new Dimension(150,30));
        JTextField startWeek = new JTextField();
        startWeek.setEditable(false);
        startWeek.setPreferredSize(new Dimension(150,21));
        panelforStartWeek.add(labelStartWeek);
        panelforStartWeek.add(startWeek);

        JLabel labelEndWeek = new JLabel("End Week: ");
        labelEndWeek.setPreferredSize(new Dimension(150,30));
        JTextField endWeek = new JTextField();
        endWeek.setEditable(false);
        endWeek.setPreferredSize(new Dimension(150,21));
        panelforEndWeek.add(labelEndWeek);
        panelforEndWeek.add(endWeek);

        JLabel labelGuideCost = new JLabel("Guide Cost: ");
        labelGuideCost.setPreferredSize(new Dimension(150,30));
        JTextField guideCost = new JTextField();
        guideCost.setEditable(false);
        guideCost.setPreferredSize(new Dimension(150,21));
        panelforGuideCost.add(labelGuideCost);
        panelforGuideCost.add(guideCost);

        JLabel labelEquipmentCost = new JLabel("Equipment Cost: ");
        labelEquipmentCost.setPreferredSize(new Dimension(150,30));
        JTextField equipmentCost = new JTextField();
        equipmentCost.setEditable(false);
        equipmentCost.setPreferredSize(new Dimension(150,21));
        panelforEquipmentCost.add(labelEquipmentCost);
        panelforEquipmentCost.add(equipmentCost);

        JLabel labelAssignmentStatus = new JLabel("Assignment Status: ");
        labelAssignmentStatus.setPreferredSize(new Dimension(150,30));
        JTextField assignmentStatus = new JTextField();
        assignmentStatus.setEditable(false);
        assignmentStatus.setPreferredSize(new Dimension(150,21));
        panelforAssignmentStatus.add(labelAssignmentStatus);
        panelforAssignmentStatus.add(assignmentStatus);

        JLabel labelBanStatus = new JLabel("Ban Status: ");
        labelBanStatus.setPreferredSize(new Dimension(150,30));
        JTextField banStatus = new JTextField();
        banStatus.setEditable(false);
        banStatus.setPreferredSize(new Dimension(150,21));
        panelforBanStatus.add(labelBanStatus);
        panelforBanStatus.add(banStatus);

        JLabel labelAuthCode = new JLabel("Authentication Code: ");
        labelAuthCode.setPreferredSize(new Dimension(150,30));
        JTextField authCode = new JTextField();
        authCode.setEditable(false);
        authCode.setPreferredSize(new Dimension(150,21));
        panelforAuthCode.add(labelAuthCode);
        panelforAuthCode.add(authCode);

        JLabel labelRefund = new JLabel("Refund: ");
        labelRefund.setPreferredSize(new Dimension(150,30));
        JTextField refund = new JTextField();
        refund.setEditable(false);
        refund.setPreferredSize(new Dimension(150,21));
        panelforRefund.add(labelRefund);
        panelforRefund.add(refund);


        displayList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                for (int i = 0; i < memberEmailList.length; i++) {
                    if (displayList.getSelectedValue().equals(memberEmailList[i])) {
                        memberEmail.setText(memberEmailList[i]);
                        memberName.setText(memberNameList[i]);
                        guideEmail.setText(guideEmailList[i]);
                        guideName.setText(guideNameList[i]);
                        hotelName.setText(hotelNameList[i]);
                        startWeek.setText(startWeekList[i]);
                        endWeek.setText(endWeekList[i]);
                        guideCost.setText(guideCostList[i]);
                        equipmentCost.setText(equipmentCostList[i]);
                        assignmentStatus.setText(statusList[i]);
                        authCode.setText(authCodeList[i]);
                        refund.setText(refundList[i]);
                        banStatus.setText(bannedStatusList[i]);
                    }
                }
            }
        });



        JButton initiateButton = new JButton("Initiate Assignments"){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height += 5;
                size.width += 5;
                return size;
            }
        };
        initiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AssignmentController.initiateAllAssignments();
                    updateAssignment();
                    displayList.setListData(memberEmailList);
                    displayList.setPreferredSize(new Dimension(displayList.getPreferredSize().width +120,displayList.getPreferredSize().height+80));
                    new Popup("Assignments Initiated Successfully",card6,0);
                } catch (Exception ex) {
                    new Popup(ex.getMessage(), card6,1);
                }
            }
        });


        JPanel panelforInitiateButton = new JPanel();
        panelforInitiateButton.setOpaque(false);
        panelforInitiateButton.add(initiateButton);

        card6.add(viewAssignments);
        card6.add(panelforInitiateButton);



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
        JComboBox<String> memberNameVisualList = new JComboBox<>(memberNameList);

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
                    if (memberNameList.length>0 && authCode != null && memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()) != "Placeholder")
                        AssignmentController.payMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()), authCode.getText());
                    new Popup("Succesfully payed for trip", card8, 0);
                } catch (InvalidInputException ex) {
                    new Popup(ex.getMessage(),card8,1);
                }
            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex())!="Placeholder")
                    AssignmentController.finishMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()));
                    new Popup("Succesfully finished trip", card8,0);
                } catch (InvalidInputException ex) {
                    new Popup(ex.getMessage(),card8,1);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex())!="Placeholder")
                    AssignmentController.cancelMemberTrip(memberNameVisualList.getItemAt(memberNameVisualList.getSelectedIndex()));
                    new Popup("Trip Cancelled Succesfully", card8,0);
                } catch (InvalidInputException ex) {
                    new Popup(ex.getMessage(),card8,1);
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
        
        @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 9){
            autofill(e.getSource());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @author Romen Poirier Taksev
     * @param caller
     */
    public void autofill(Object caller){
        JTextField field = (JTextField) caller;
        String name = field.getName();
        switch (name) {
            case "equipmentNameUpdateField" -> field.setText(cen);
            case "equipmentWeightUpdateField" -> field.setText(cew);
            case "equipmentPriceUpdateField" -> field.setText(cep);
        }
    }
}
