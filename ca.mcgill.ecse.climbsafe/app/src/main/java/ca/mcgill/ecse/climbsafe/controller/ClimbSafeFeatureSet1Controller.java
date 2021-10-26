package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet1Controller {

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

  public static void deleteGuide(String email) {
      User user = User.getWithEmail(email);
      if (user instanceof Guide) {
        user.delete();
      }
  }

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
