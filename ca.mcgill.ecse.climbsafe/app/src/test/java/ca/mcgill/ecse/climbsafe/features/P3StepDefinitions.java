package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse.climbsafe.model.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;


public class P3StepDefinitions {

  private String msg = "";
  private ClimbSafe climbSafe;

  /**
   *
   * @author Abhijeet Praveen
   * Implenting Given that the following system exists
   * @param dataTable this is the input to the method, which represents the application
   * Then the three elements of dataTable (Date, number of weeks, Guide price/week) will be
   * associated with the ClimbSafe system by transforming dataTable to a list of strings
   *
   */
  @Given("the following ClimbSafe system exists: \\(p3)")
  public void the_following_climb_safe_system_exists_p3(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    List<Map<String, String>> system = dataTable.asMaps();
    climbSafe = ClimbSafeApplication.getClimbSafe();
    climbSafe.setStartDate(Date.valueOf(system.get(0).get("startDate")));
    climbSafe.setNrWeeks(parseInt(system.get(0).get("nrWeeks")));
    climbSafe.setPriceOfGuidePerWeek(parseInt(system.get(0).get("priceOfGuidePerWeek")));
  }


  /**
   * @author Edward Habelrih
   *Given that the following guide exists, the guides are given through the dataTable
   *The relevant data of @param dataTable regarding each guide (email, password, name and emergency contact) are coded to be
   *associated with the system.
   */
  @Given("the following guides exist in the system: \\(p3)")
  public void the_following_guides_exist_in_the_system_p3(
          io.cucumber.datatable.DataTable dataTable) {
    List<List<String>> guideList = dataTable.asLists();
    //traverse through list of guides
    for(int i = 1; i < guideList.size(); i++) {
      //retrieve information
      String guideEmail = guideList.get(i).get(0);
      String guidePassword = guideList.get(i).get(1);
      String guideName = guideList.get(i).get(2);
      String guideEmergencyContact = guideList.get(i).get(3);
      //Create guide with given information
      Guide  guide = new Guide(guideEmail, guidePassword, guideName, guideEmergencyContact, climbSafe); //refer to instance of climbSafe application
      climbSafe.addGuide(guide);
    }

  }

  /**
   * @author Neel Faucher
   * Given that the following members exist in the system, members are given through the dataTable
   * @param dataTable is the input for the method
   * There are seven elements of dataTable (email, password, name, emergencycontact, nrWeeks, guideRequired, hotelRequired) coded to be
   * associated with the system.
   */

  @Given("the following members exist in the system: \\(p3)")
  public void the_following_members_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> memberList = dataTable.asMaps();
    for(int i = 0; i < memberList.size() - 1; i++) {
      climbSafe.addMember(memberList.get(i).get("email"),
              memberList.get(i).get("password"),
              memberList.get(i).get("name"),
              memberList.get(i).get("emergencyContact"),
              parseInt(memberList.get(i).get("nrWeeks")),
              parseBoolean(memberList.get(i).get("guideRequired")),
              parseBoolean(memberList.get(i).get("hotelRequired"))) ;
    }
  }

  /**
   * When a guide tries to register, this code sends the inputs to the controller that check it is all correct
   * and creates a guide if it is, otherwise returns the error and adds it to msg
   * @author Sebastien
   * @param email  is the entered email of the guide to be checked
   * @param password is the entered password of the guide to be checked
   * @param name is the entered name of the guide to be checked
   * @param emergencyContact is the entered emergencyNumber to be checked
   */
  @When("a new guide attempts to register with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_attempts_to_register_with_and_p3(String email, String password,
                                                           String name, String emergencyContact) {
    // Write code here that turns the phrase above into concrete actions

    try {
      ClimbSafeFeatureSet3Controller.registerGuide(email, password, name, emergencyContact);
    } catch (InvalidInputException e) {
      msg = e.getMessage();
    }

  }
  /**
   * @Author: Rooshnie Velautham
   * This function verifies if the object Guide has been created properly if it is not the case it will return an error
   * @param  email this is the email of the guide
   * @param  password this is the password that the guide should use
   * @param  name this is the name of the guide
   * @param  emergencyContact this is the guide's emergency contact
   *
   */
  @Then("a new guide account shall exist with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_account_shall_exist_with_and_p3(String email, String password,
                                                          String name, String emergencyContact) {
    Guide guide=null;
    for (Guide g: climbSafe.getGuides()){
      if (String.equals(g.getEmail())) {
        guide = g;
        break;
      }
    }
    assertNotNull(guide);
    assertEquals(email, guide.getEmail());
    assertEquals(password, guide.getPassword());
    assertEquals(name, guide.getName());
    assertEquals(emergencyContact, guide.getEmergencyContact());
  }

/**
   * @author Romen Poirier Taksev
   * @param int1, the number of guides supposed to be in the system.
   * This method checks whether the number of guides in the system is equal to the number it is expected to be.
   */
  @Then("the number of guides in the system is {int} \\(p3)")
  public void the_number_of_guides_in_the_system_is_p3(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(int1, climbSafe.numberOfGuides());
  }

  /**
   * If the guide created had any errors when registering
   * this program checks that the expected error message is the same
   * as the given one
   * @author Sebastien
   * @param string is the expected error code
   */
  @Then("the following {string} shall be raised \\(p3)")
  public void the_following_shall_be_raised_p3(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(string, msg);
  }

  /**
   * This method deletes the climbSafe object at the end of each test so
   * that it is reset when doing the next test
   */
  @After
  public void teardown(){
    climbSafe.delete();
  }
}