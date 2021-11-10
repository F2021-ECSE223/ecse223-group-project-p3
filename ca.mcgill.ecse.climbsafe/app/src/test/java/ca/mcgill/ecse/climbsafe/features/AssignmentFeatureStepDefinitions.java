package ca.mcgill.ecse.climbsafe.features;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.model.Member;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.util.List;
import java.util.Map;



public class AssignmentFeatureStepDefinitions {
  String error = "";
  private ClimbSafe climbSafe;

  /**
   * @author Neel Faucher
   * @param dataTable represents the table which contains input data provided in the feature file
   * Date, number per weeks, price of guide per week will be associated with ClimbSafe system by transforming dataTable into a list of maps
   */
  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    this.error = "";
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> map : rows) {
      climbSafe = ClimbSafeApplication.getClimbSafe();
      climbSafe.setStartDate(Date.valueOf(map.get("startDate")));
      climbSafe.setNrWeeks(parseInt(map.get("nrWeeks")));
      climbSafe.setPriceOfGuidePerWeek(parseInt(map.get("priceOfGuidePerWeek")));
    }
  }

 /** @author Neel Faucher
 * @param dataTable represents the table which contains input data provided in the feature file
 * name, price per week, weight will be associated with ClimbSafe system by transforming dataTable into a list of maps
 */
  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> cucumberData = dataTable.asMaps();

    for (Map<String, String> row : cucumberData) {
      climbSafe.addEquipment(row.get("name"), Integer.parseInt(row.get("weight")),
              Integer.parseInt(row.get("pricePerWeek")));
    }
  }

  /** @author Neel Faucher
   * @param dataTable represents the table which contains input data provided in the feature file
   * discount, items, quantities will be associated with ClimbSafe system by transforming dataTable into a list of maps
   */
  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> cucumberData = dataTable.asMaps();

    for (var equipmentBundle : cucumberData) {
      int discount = Integer.parseInt(equipmentBundle.get("discount"));
      String items = equipmentBundle.get("items");
      String quantities = equipmentBundle.get("quantities");
      String[] equipmentItems = items.split(",");
      String[] equipmentQuantities = quantities.split(",");

      var newBundle = new EquipmentBundle(equipmentBundle.get("name"), discount, climbSafe);

      for (int i = 0; i < equipmentItems.length; i++) {
        newBundle.addBundleItem(Integer.parseInt(equipmentQuantities[i]), climbSafe,
                (Equipment) BookableItem.getWithName(equipmentItems[i]));
      }
    }
  }

  /** @author Neel Faucher
   * @param dataTable represents the table which contains input data provided in the feature file
   * email, password, name, emergency contact will be associated with ClimbSafe system by transforming dataTable into a list of maps
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String emergencyContact = row.get("emergencyContact");
      climbSafe.addGuide(email, password, name, emergencyContact);
    }
  }

  /**
  * @author Sebastien Cantin
  * @param dataTable represents the table which contains input data provided in the feature file
  * email, password, name, emergency contact will be associated with ClimbSafe system by transforming dataTable into a list of maps
  */
  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> memberList = dataTable.asMaps();
      for (int i = 0; i < memberList.size(); i++) {
          climbSafe.addMember(memberList.get(i).get("email"), memberList.get(i).get("password"),
                  memberList.get(i).get("name"), memberList.get(i).get("emergencyContact"),
                  parseInt(memberList.get(i).get("nrWeeks")),
                  parseBoolean(memberList.get(i).get("guideRequired")),
                  parseBoolean(memberList.get(i).get("hotelRequired")));
      }
  }

  /**
  * @author Sebastien Cantin
  */
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    // Write code here that turns the phrase above into concrete actions
      try {
          AssignmentController.initiateAllAssignments(climbSafe.getMembers());
      }catch(Exception e){
          error = e.getMessage();
      }
  }

  /**
  * @author Sebastien Cantin
  * @param dataTable A dataTable containing the memberEmails, guideEmails, startWeeks and endWeeks
  *                  of all the assignments that ought to be in climbsafe
  */
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> assignmentList = dataTable.asMaps();
      for (var row : assignmentList){
          String memberEmail = row.get("memberEmail");
          Member m = (Member) Member.getWithEmail(memberEmail);
          Assignment a = m.getAssignment();
          String guideEmail = row.get("guideEmail");
          Integer startWeek = Integer.parseInt(row.get("startWeek"));
          Integer endWeek = Integer.parseInt(row.get("endWeek"));
          assertEquals(a.getGuide().getEmail(), guideEmail);
          assertEquals(a.getStartWeek(), startWeek);
          assertEquals(a.getEndWeek(), endWeek);
      }
  }

 /**
  * @author Sebastien Cantin
  * @param memberEmail the email of the member whose assignment's status will be checked
  * @param assignmentStatus the status the member's assignment status will be compared to
  */
  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String memberEmail, String assignmentStatus) {
    // Write code here that turns the phrase above into concrete actions
    Member m = (Member) Member.getWithEmail(memberEmail);
    Assignment a = m.getAssignment();
    assertEquals(a.getAssignmentStatusFullName(), assignmentStatus);
  }

  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Romen Poirier Taksev
   * @param string the email of the member
   * @param string2 the authorization code that the member provided
   */
  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String memberEmail,
                                                                     String authCode) {
    ((Member) Member.getWithEmail(memberEmail)).getAssignment().setAuthCode(authCode);
  }

  /**
   * @author Romen Poirier Taksev
   * @param string the email of the member
   */
  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String memberEmail) {
    assertNull(Member.getWithEmail(memberEmail));
  }

  /**
   * @author Romen Poirier Taksev
   * @param string the number of members in the system
   */
  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String numberOfMembers) {
    assertEquals(Integer.parseInt(numberOfMembers), climbSafe.getMembers().size());
  }

  /**
   * @author Romen Poirier Taksev
   * @param string error message
   */
  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String errorMessage) {
    assertEquals(error, errorMessage);
  }
  /**
   * @author Rooshnie Velautham
   * @param email this is the email of the member that the admin attempts to cancel the trip
   * This function will call the cancelMemberTrip from the model.
   */
 @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String email) {
    try{
      AssignmentController.cancelMemberTrip(email);
    }catch(InvalidInputException e){
      error = e.getMessage();
    }
  }
  /**
   * @author Rooshnie Velautham
   * @param email this is the email of the member that paid their trip
   * This function sets the status of the assignment to paid
   */
  @Given("the member with {email} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String email) {
    User user =  User.getWithEmail(email);
    if(user instanceof Member){
      Member member = (Member) user;
      member.getAssignment().pay();
    }

  }
   /**
   * @author Rooshnie Velautham
   * @param email this is the email of the member that paid their trip
   * @param percentage it is the percentage of the refund
   * This function checks if the right amount of the percentage has been given depending of their states
   */
  @Then("the member with email address {email} shall receive a refund of {amount} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String email,
      String amount) {
    User user =  User.getWithEmail(email);
    if(user instanceof Member){
      Member member = (Member) user;
      assertEquals(member.getRefund(),parseInt(amount));
    }

  }
  /**
   * @author Rooshnie Velautham
   * @param email this is the email of the member that wants to start the trip
   * This function puts the member in the start state like they have started their trip
   */
  @Given("the member with {email} has started their trip")
  public void the_member_with_has_started_their_trip(String email) {
    User user =  User.getWithEmail(email);
    if(user instanceof Member){
      Member member = (Member) user;
      Assignment a = member.getAssignment();
      a.setAuthCode("GG");
      a.pay();
      a.startWeek(a.getStartWeek());
   }
  }

  /**
   * @author Abhijeet Praveen
   * @param email represents the email of the member we intend to finish the trip for
   * The implementation for this step definition is done through calling the finishMemberTrip
   * from the controller API.
   */
  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String email) {
    try{
      AssignmentController.finishMemberTrip(email);
    }
    catch(InvalidInputException e){
      error = e.getMessage();
    }
  }

  /**
   * @author Abhijeet Praveen
   * @param email represents the email of the user we need to ban
   * The implementation for this step definition is done through calling the banMember() function in the
   * model for the application
   */
  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String email) {
    User user = User.getWithEmail(email);
    if(user instanceof Member){
      Member member = (Member) user;
      member.banMember();
    }
  }

  /**
   * @author Abhijeet Praveen
   * @param email represents the email of the user whose state we are interested in
   * @param status represents the ban status of the member
   * The implementation for this step definition is done through ensuring that the member with the given
   * email has the same status as the given status
   */
  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String email, String status) {
    User user = User.getWithEmail(email);
    if (user instanceof Member) {
      Member member = (Member) user;
      assertTrue(member.getBanStatus().name().equals(status));
    }
  }

  /**
   * @author Abhijeet Praveen
   * @param weekNumber represents the week that we want to start the trips for
   * The implementation of this step defintion is done through calling the startAllTrips method
   * from the controller API
   */
  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNumber) {
    try{
      AssignmentController.startAllTrips(parseInt(weekNumber));
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
  * @author Sebastien Cantin
  * @param memberEmail the email of the member whose assignment status will be checked
  */
  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
    Member m = (Member)Member.getWithEmail(memberEmail);
    Assignment a = m.getAssignment();
    a.cancel();  
  }

  /**
  * @author Sebastien Cantin
  * @param memberEmail the email of the member whose assignment status will be checked
  */
  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
      Member m = (Member)Member.getWithEmail(memberEmail);
      Assignment a = m.getAssignment();
      a.pay("beep boop");
      a.startWeek(a.getStartWeek());
      a.finishTrip();
  }
}
