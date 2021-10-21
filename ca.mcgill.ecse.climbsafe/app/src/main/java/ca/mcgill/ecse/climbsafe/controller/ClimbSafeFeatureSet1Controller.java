package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek) throws InvalidInputException {
    if(nrWeeks >= 0 && priceOfGuidePerWeek >= 0) {
      ClimbSafeApplication.getClimbSafe().setNrWeeks(nrWeeks);
      ClimbSafeApplication.getClimbSafe().setStartDate(startDate);
      ClimbSafeApplication.getClimbSafe().setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    }
    else {
      throw new InvalidInputException("The value of both nrWeeks and priceOfGuidePerWeek need to be greater or equal to 0");
    }
  }

  public static void deleteMember(String email) throws InvalidInputException {
    List<Member> members = ClimbSafeApplication.getClimbSafe().getMembers();
    for (Member member: members) {
     if (email.equals("") || email == null) {
       throw new InvalidInputException("Email can't be empty!");
     }
     else if (member.getEmail().equals(email)) {
       deleteMember(email);
      }
    }
  }

  public static void deleteGuide(String email) throws InvalidInputException {
    List<Guide> guides = ClimbSafeApplication.getClimbSafe().getGuides();
    for (Guide guide: guides) {
      if (email.equals("") || email == null) {
        throw new InvalidInputException("Email can't be empty!");
      }
      else if (guide.getEmail().equals(email)) {
        deleteMember(email);
      }
    }
  }
}