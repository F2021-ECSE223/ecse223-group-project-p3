package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.climbsafe.model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.sql.Date;



public class P3StepDefinitions {
	
private String msg = "";
private ClimbSafe climbSafe;
	
/*
  @author Abhijeet Praveen

  Given that the following system exists, here the system is given through the dataTable
  Then the three elements of dataTable (Date, number of weeks, Guide price/week) are coded to be
  associated with the system
   */
  @Given("the following ClimbSafe system exists: \\(p3)")
  public void the_following_climb_safe_system_exists_p3(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<String> system = dataTable.asList();
    climbSafe = ClimbSafeApplication.getClimbSafe();
    climbSafe.setStartDate(Date.valueOf(system.get(0)));
    climbSafe.setNrWeeks(Integer.parseInt(system.get(1)));
    climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(system.get(2)));
  }
  //Author Edward Habelrih
  @Given("the following guides exist in the system: \\(p3)")
  public void the_following_guides_exist_in_the_system_p3(
      io.cucumber.datatable.DataTable dataTable) {
	  List<List<String>> guideList = dataTable.asList();
	  //traverse through list of guides
	  for(int i = 1; i < guideList.size(); i++) {
		//retrieve information
		String guideEmail = guideList.get(i).get(0);
		String guidePassword = guideList.get(i).get(1);
		String guideName = guideList.get(i).get(2);
		String guideEmergencyContact = guideList.get(i).get(3);
		//Create guide with given information
		Guide  guide = new Guide(guideEmail, guidePassword, guideName, guideEmergencyContact); //refer to instance of climbSafe application
		climbSafe.addGuide(guide);
	  }
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  
  @Given("the following members exist in the system: \\(p3)")
  public void the_following_members_exist_in_the_system_p3( io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
 // Neel
  public void the_following_members_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) {
  List<List<String>> memberList = dataTable.asLists(String.class);
  for(int i = 0; i < memberList.size() - 1; i++) {
	    	climbSafe.addMember(memberList.get(i).get(0),
	    						memberList.get(i).get(1), 
	    			            memberList.get(i).get(2),
	    			            memberList.get(i).get(3),
	    			            memberList.get(i).get(4),
	    			            memberList.get(i).get(5),
	    			            memberList.get(i).get(6))
	    }
	    throw new io.cucumber.java.PendingException();
	  }
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    //throw new io.cucumber.java.PendingException();
  }

  //author Sebastien
  @When("a new guide attempts to register with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_attempts_to_register_with_and_p3(String string, String string2,
      String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions

    if (string.equals("admin@nmc.nt")) msg = "Email cannot be admin@nmc.nt";


    for (Guide g: climbSafe.getGuides()){
      if (string.equals(g.getEmail())) {
        msg = "Email already linked to a guide account";
        break;
      }
    }

    for (Member m: climbSafe.getMembers()){
      if (string.equals(m.getEmail())) {
        msg = "Email already linked to a member account";
        break;
      }
    }

    if (string.contains(" ")) msg = "Email must not contain any spaces";

    if (string==null  || string.equals("")) msg = "Email cannot be empty";
    if (string2==null || string2.equals("")) msg = "Password cannot be empty";
    if (string3==null || string3.equals("")) msg = "Name cannot be empty";
    if (string4==null || string4.equals("")) msg = "Emergency contact cannot be empty";

    if      (string.indexOf("@") > 0 ||
            string.indexOf("@") == string.lastIndexOf("@") ||
            string.indexOf("@") < string.lastIndexOf(".") - 1 ||
            string.lastIndexOf(".") < string.length() - 1) msg = "Invalid email";

  }

  @Then("a new guide account shall exist with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_account_shall_exist_with_and_p3(String string, String string2,
      String string3, String string4) {
  	  Guide guide = guide.getWithEmail(string);
	  assertNotNull(guide);
	  assertEqual(string2, guide.getPassword());
	  assertEqual(string3, guide.getName());
	  assertEqual(string4, guide.getEmergencyContact());
  }

  @Then("the number of guides in the system is {int} \\(p3)")
  public void the_number_of_guides_in_the_system_is_p3(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(int1, climbSafe.numberOfGuides());
  }

  //author Sebastien
  @Then("the following {string} shall be raised \\(p3)")
  public void the_following_shall_be_raised_p3(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(string, msg);
  }
}
