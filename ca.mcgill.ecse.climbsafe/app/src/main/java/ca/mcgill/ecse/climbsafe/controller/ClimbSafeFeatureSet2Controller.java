



package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import java.util.List;
/**
  * Controller method that creates a member in the climbsafe object in the application
  * Checks all the input parameters to guarantee it is all in the correct format and returns
  * an appropriate error code.
  * @author Edward Habelrih
  * @param email
  * @param password
  * @param name
  * @param emergencyContact
  * @param nrWeeks
  * @param guideRequired
  * @param hotelRequired
  * @param itemNames
  * @paramitemQuantities
  * @throws InvalidInputException
  */

public class ClimbSafeFeatureSet2Controller {

  public static void registerMember(String email, String password, String name,
                                    String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
                                    List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {

    String error = "";

    if (email.equals("admin@nmc.nt")) error = "Email cannot be admin@nmc.nt";

    if (email.contains(" ")) error = "Email must not contain any spaces";

    if (email==null  || email.equals("")) error = "Email cannot be empty";
    if (password==null || password.equals("")) error = "Password cannot be empty";
    if (name==null || name.equals("")) error = "Name cannot be empty";
    if (emergencyContact==null || emergencyContact.equals("")) error = "Emergency contact cannot be empty";
    if(itemNames == null) error = "Item names list cannot be null";
    //iterate over item names to make sure there are no empty strings
    for(String itemName : itemNames){
      if(itemName.equals(""))
        error = "Item has a blank name";
    }

    if(itemQuantities == null) error = "Item quantities cannot be null";
    //iterate over item quantities to make sure none is empty
    for(Integer number: itemQuantities){
      if(number == null)
        error = "Item quantity cannot be null and must be specified";
    }


    if (email.indexOf("@") <= 0 ||
            email.indexOf("@") != email.lastIndexOf("@") ||
            email.indexOf("@") >= email.lastIndexOf(".") - 1 ||
            email.lastIndexOf(".") >= email.length() - 1) error = "Invalid email";

    if (error.length() > 0) {
      throw new InvalidInputException(error.trim());
    }

    ClimbSafe c = ClimbSafeApplication.getClimbSafe();

    try {
      Member m = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, c);
      for (int i = 0; i< itemNames.size(); i++) {
        BookableItem item = BookableItem.getWithName(itemNames.get(i));
        m.addBookedItem(itemQuantities.get(i), c, item);
      }
    }
    catch (RuntimeException e) {
      error = e.getMessage();
      if (error.equals("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {

        User u = Member.getWithEmail(email);
        if(u instanceof Member) error = "Email already linked to a member account";
        else error = "Email already linked to a guide account";
      }
      throw new InvalidInputException(error);
    }

  }

  /**
   * Controller method that updates a member with a given email, updates password, name, emergency contact,
   * the number of weeks, if a new hotel is required, if a new guide is required, a new list of item names and item quantities.
   * If no member exists with that email, creates one
   * If there is a guide with that email, returns an error
   * @author Edward Habelrih
   * @param email
   * @param newPassword
   * @param newName
   * @param newEmergencyContact
   * @param newNrWeeks
   * @param newGuideRequired
   * @param newHotelRequired
   * @param newItemNames
   * @param newItemQuantities
   * @throws InvalidInputException
   */

  public static void updateMember(String email, String newPassword, String newName,
                                  String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
                                  boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
          throws InvalidInputException {
    String error = "";

    if (email.equals("admin@nmc.nt")) error = "Email cannot be admin@nmc.nt";

    if (email.contains(" ")) error = "Email must not contain any spaces";

    if (email==null  || email.equals("")) error = "Email cannot be empty";
    if (newPassword==null || newPassword.equals("")) error = "Password cannot be empty";
    if (newName==null || newName.equals("")) error = "Name cannot be empty";
    if (newEmergencyContact==null || newEmergencyContact.equals("")) error = "Emergency contact cannot be empty";
    if(newItemNames == null) error = "Item names list cannot be null";
    //iterate over item names to make sure none is an empty string
    for(String itemName : newItemNames){
      if(itemName.equals(""))
        error = "Item has a blank name";
    }

    if(newItemQuantities == null) error = "Item quantities cannot be null";
    //iterate over item quantities to make sure none is empty
    for(Integer number: newItemQuantities){
      if(number == null)
        error = "Item quantity cannot be null and must be specified";
    }


    if (email.indexOf("@") <= 0 ||
            email.indexOf("@") != email.lastIndexOf("@") ||
            email.indexOf("@") >= email.lastIndexOf(".") - 1 ||
            email.lastIndexOf(".") >= email.length() - 1) error = "Invalid email";

    if (error.length() > 0) {
      throw new InvalidInputException(error.trim());
    }

    try{
      User u = Member.getWithEmail(email);
      if (u==null){
        registerMember(email, newPassword, newName, newEmergencyContact, newNrWeeks, newGuideRequired, newHotelRequired, newItemNames, newItemQuantities);
      }
      else if (u instanceof Member ){
        Member m = (Member) Member.getWithEmail(email);
        m.setPassword(newPassword);
        m.setName(newName);
        m.setEmergencyContact(newEmergencyContact);
        m.setGuideRequired(newGuideRequired);
        m.setHotelRequired(newHotelRequired);
        m.getBookedItems().clear(); //clear the list to then replace it
        for (int i = 0; i< newItemNames.size(); i++) {
          BookableItem item = BookableItem.getWithName(newItemNames.get(i));
          m.addBookedItem(newItemQuantities.get(i), ClimbSafeApplication.getClimbSafe(), item);
        }


      }
      else{
        error = "Email is already associated to an account that is not a member";
        throw new InvalidInputException(error.trim());
      }

    }
    catch (RuntimeException e ){
      throw new InvalidInputException(e.getMessage());
    }

  }

}


