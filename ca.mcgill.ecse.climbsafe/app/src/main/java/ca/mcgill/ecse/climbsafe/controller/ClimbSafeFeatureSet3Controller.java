package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet3Controller {
  /**
   * Controller method that creates a guide member in the climbsafe object in the application
   * Checks all the input parameters to guarantee it is all in the correct format and returns
   * an appropriate error code.
   * @author Sebastien
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @throws InvalidInputException
   */
  public static void registerGuide(String email, String password, String name,
                                   String emergencyContact) throws InvalidInputException{

    String error = "";

    if (email.equals("admin@nmc.nt")) error = "Email cannot be admin@nmc.nt";

    if (email.contains(" ")) error = "Email must not contain any spaces";

    if      (email.indexOf("@") <= 0 ||
            email.indexOf("@") != email.lastIndexOf("@") ||
            email.indexOf("@") >= email.lastIndexOf(".") - 1 ||
            email.lastIndexOf(".") >= email.length() - 1) error = "Invalid email";

    if (email==null  || email.equals("")) error = "Email cannot be empty";
    if (password==null || password.equals("")) error = "Password cannot be empty";
    if (name==null || name.equals("")) error = "Name cannot be empty";
    if (emergencyContact==null || emergencyContact.equals("")) error = "Emergency contact cannot be empty";

    if (error.length() > 0) {
      throw new InvalidInputException(error.trim());
    }

    ClimbSafe c = ClimbSafeApplication.getClimbSafe();

    try {
      c.addGuide(email,password,name,emergencyContact);
    }
    catch (RuntimeException e) {
      error = e.getMessage();
      if (error.equals("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {

        User u = Guide.getWithEmail(email);
        if (u instanceof Guide) error = "Email already linked to a guide account";
        else error = "Email already linked to a member account";
      }
      throw new InvalidInputException(error);
    }

  }

  /**
   * Controller method that updates a guide with a given email, updates password, name and emergency contact
   * If no guide exists with that email, creates one
   * If there is a member with that email, returns an error
   * @author Sebastien
   * @param email
   * @param newPassword
   * @param newName
   * @param newEmergencyContact
   * @throws InvalidInputException
   */
  public static void updateGuide(String email, String newPassword, String newName,
                                 String newEmergencyContact) throws InvalidInputException {
    String error = "";
    if (email==null || email.equals("")) error = "Email cannot be empty";
    if (newPassword==null || newPassword.equals("")) error = "Password cannot be empty";
    if (newName == null || newName.equals("")) error = "Name cannot be empty";
    if (newEmergencyContact==null || newEmergencyContact.equals("")) error = "Emergency contact cannot be empty";

    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }

    try{
      User u = Guide.getWithEmail(email);
      if (u==null){
        registerGuide(email, newPassword, newName, newEmergencyContact);
      }
      else if (u instanceof Guide ){
        Guide g = (Guide) Guide.getWithEmail(email);
        g.setPassword(newPassword);
        g.setName(newName);
        g.setEmergencyContact(newEmergencyContact);
      }
      else{
        error = "Email is already associated to an account that is not a guide";
        throw new InvalidInputException(error.trim());
      }

    }
    catch (RuntimeException e ){
      throw new InvalidInputException(e.getMessage());
    }



  }

  
}
