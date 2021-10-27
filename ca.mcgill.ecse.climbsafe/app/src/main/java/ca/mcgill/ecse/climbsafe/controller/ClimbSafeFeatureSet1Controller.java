package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet1Controller {

  /**
   * @author Neel Faucher
   * @param startDate the method will check whether the startDate follows conventions of public class Date and is valid
   * @param nrWeeks the method will check whether the nrWeeks is greater or equal to 0
   * @param priceOfGuidePerWeek the method will check whether the priceOfGuidePerWeek is greater or equal to 0
   * @throws InvalidInputException will be thrown if inputs for above parameters are invalid
   * If the inputs for all the parameters are valid, NMC will be setup by setting the NrWeeks, StartDate,
   * and PriceOfGuidePerWeek to the respective inputs for corresponding parameters
   */

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek) throws InvalidInputException {
    if(startDate.getMonth() > 11 || startDate.getMonth() < 0) {
      throw new InvalidInputException("Invalid Date");
    }
    if (startDate.getDate() < 1 || startDate.getDay() > 31) {
      throw new InvalidInputException("Invalid Date");
    }
    if (priceOfGuidePerWeek < 0) {
      throw new InvalidInputException("The price of guide per week must be greater than or equal to zero");
    }
    if (nrWeeks < 0) {
      throw new InvalidInputException("The number of climbing weeks must be greater than or equal to zero");
    }
    ClimbSafeApplication.getClimbSafe().setNrWeeks(nrWeeks);
    ClimbSafeApplication.getClimbSafe().setStartDate(startDate);
    ClimbSafeApplication.getClimbSafe().setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

  /**
   * @author Neel Faucher
   * @param email is the input to the method, used to determine which guide the method must delete
   *        First a User object, user is created with the provided getWithEmail method.
   *        If the user is a guide, then the user will be deleted.
   */

  public static void deleteGuide(String email) {
      User user = User.getWithEmail(email);
      if (user instanceof Guide) {
        user.delete();
      }
  }

  /**
   * @author Neel Faucher
   * @param email is the input to the method, used to determine which member the method must delete
   *        First a User object, user is created with the provided getWithEmail method.
   *        If the user is a member, then the member will be deleted.
   */

  public static void deleteMember(String email) {
    User user = User.getWithEmail(email);
    if (user instanceof Member) {
      user.delete();
    }
  }

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {
  }

}
