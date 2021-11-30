package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.util.List;


public class ClimbSafeFeatureSet2Controller {

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
  public static void registerMember(String email, String password, String name,
                                    String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
                                    List<String> itemNames, List<Integer> itemQuantities) throws Exception {

    String error = "";

    if(nrWeeks <= 0 || nrWeeks > ClimbSafeApplication.getClimbSafe().getNrWeeks()) {
      error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
    }

    User user = User.getWithEmail(email);
    if (user instanceof Guide) error = "A guide with this email already exists";
    if (user instanceof Member) error = "A member with this email already exists";

    for (String itemName:itemNames){
      BookableItem bookableItem = BookableItem.getWithName(itemName);
      if(bookableItem==null) error = "Requested item not found";
    }


    if (email.equals("admin@nmc.nt")) error = "The email entered is not allowed for members";

    if (email.contains(" ")) error = "The email must not contain any spaces";

    if (email.indexOf("@") <= 0 ||
            email.indexOf("@") != email.lastIndexOf("@") ||
            email.indexOf("@") >= email.lastIndexOf(".") - 1 ||
            email.lastIndexOf(".") >= email.length() - 1) error = "Invalid email";

    if (email==null  || email.equals("")) error = "Email cannot be empty";
    if (password==null || password.equals("")) error = "The password cannot be empty";
    if (name==null || name.equals("")) error = "The name cannot be empty";
    if (emergencyContact==null || emergencyContact.equals("")) error = "The emergency contact cannot be empty";
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

      try {
        ClimbSafePersistence.save(c);
      }catch (Exception e){
        throw new InvalidInputException("Could not save");
      }
    }
    catch (RuntimeException e) {
      error = e.getMessage();
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
    if (newPassword==null || newPassword.equals("")) error = "The password cannot be empty";
    if (newName==null || newName.equals("")) error = "The name cannot be empty";
    if (newEmergencyContact==null || newEmergencyContact.equals("")) error = "The emergency contact cannot be empty";
    if(newNrWeeks <= 0 || newNrWeeks > ClimbSafeApplication.getClimbSafe().getNrWeeks()) {
      error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
    }
    for (String itemName:newItemNames){
      BookableItem bookableItem = BookableItem.getWithName(itemName);
      if(bookableItem==null) error = "Requested item not found";
    }

    User user = User.getWithEmail(email);
    if(user == null) {
      error = "Member not found";
    }
    ClimbSafe c = ClimbSafeApplication.getClimbSafe();
    if (error.length() > 0) {
      throw new InvalidInputException(error.trim());
    }
       try {
      Member member = (Member) User.getWithEmail(email);
      member.setName(newName);
      member.setPassword(newPassword);
      member.setEmergencyContact(newEmergencyContact);
      member.setNrWeeks(newNrWeeks);
      member.setGuideRequired(newGuideRequired);
      member.setHotelRequired(newHotelRequired);

      int size = member.getBookedItems().size();
      for (int i = 0; i< size; i++) {
       member.getBookedItem(0).delete();
         }

      for (int i = 0; i< newItemNames.size(); i++) {
        BookableItem item = BookableItem.getWithName(newItemNames.get(i));
        member.addBookedItem(member.addBookedItem(newItemQuantities.get(i), c, item));
      }
         try {
           ClimbSafePersistence.save(c);
         }catch (Exception e){
           throw new InvalidInputException(e.getMessage());
         }
    }
    catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }
}

