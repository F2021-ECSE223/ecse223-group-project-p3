package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;


import java.util.ArrayList;
import java.util.List;

public class ClimbSafeFeatureSet6Controller {


  /**
   * @author Abhijeet Praveen
   * @param name this is the input to the method which represents the name of the equipment we need to delete
   * This method will delete equipment specified by the given input (name)
   * We will find the equipment with the given name, and use the delete method from the Equipment class to delete it.
   * we will also throw an InvalidInputException if the input name is null or empty
   */
  public static void deleteEquipment(String name) throws InvalidInputException {
    if(name==null || name.equals("")) throw new InvalidInputException("Equipment name cannot be empty");
    List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();
    for (Equipment equipment: equipmentList){
      if(equipment.getName().equals(name)) equipment.delete();
    }
  }



  /**
   * @author Abhijeet Praveen
   * @param name this is the input to the method which represents the name of the equipment we need to delete
   * This method will delete equipmentBundle specified by the given input (name)
   * We will find the equipmentBundle with the given name, and use the delete method from the Equipment class to delete it.
   * we will also throw an InvalidInputException if the input name is null or empty
   */
  public static void deleteEquipmentBundle(String name) throws InvalidInputException {
    if(name==null || name.equals("")) throw new InvalidInputException("Equipment Bundle name cannot be empty");
    List<EquipmentBundle> bundleList = ClimbSafeApplication.getClimbSafe().getBundles();
    for(EquipmentBundle bundle: bundleList){
      if(bundle.getName().equals(name)) bundle.delete();
    }
  }

  /**
   * @author Abhijeet Praveen
   * This function implements the ViewAssignments feature
   * We use the TOAssignment which has been created in this package using the Umple file
   * We use getters from the corresponding classes to obtain the various inputs we will need for the constructor of
   * our TOAssignment object
   * Helper methods whcih have been implemented below are also used inside this method
   * @return then this method returns the list of assignments we have in our system
   */
  public static List<TOAssignment> getAssignments() {
    var assignments = new ArrayList<TOAssignment>();
    for (var assignment: ClimbSafeApplication.getClimbSafe().getAssignments()){
      assignments.add(new TOAssignment(assignment.getMember().getEmail(),assignment.getMember().getName(),
             assignment.getGuide().getEmail(), assignment.getGuide().getName(),assignment.getHotel().getName(),
              assignment.getStartWeek(), assignment.getEndWeek(),
              getTotalGuideCost(assignment),getTotalEquipmentCost(assignment)));
    }
    return assignments;
  }

  /**
   * @author Abhijeet Praveen
   * Using helper method to find the total guide cost for an assigment which is needed for our constructor for
   * the TOAssignment object.
   * The implementation is performed by obtaining the price of the guide per week and multiplying it by the number of weeks
   * the member has booked
   * @param assignment corresponds to the assignment we are trying to find the total cost of guide for
   * @return integer corresponding to total cost for the guide
   */
  private static int getTotalGuideCost(Assignment assignment){
    return assignment.getClimbSafe().getPriceOfGuidePerWeek()*assignment.getMember().getNrWeeks();
  }

  /**
   * @author Abhijeet Praveen
   * Using helper method to find the total equipment cost for an assignment which is needed for
   * the TOAssignment object
   * The implementation is done by firstly creating a list of equipments in the system
   * Then finding the booked items for the member in the assignment
   * Then for each booked item for our member we find its corresponding price/week and
   * then multplying it by the number of weeks the member has booked
   * @param assignment corresponds to the assignment we are trying to find the total cost of booked items for
   * @return integer corresponding to total cost for the booked items for the member
   */
  private static int getTotalEquipmentCost(Assignment assignment){
    int totalCost = 0;
    List<Equipment> availableEquipments = ClimbSafeApplication.getClimbSafe().getEquipment();
    List<BookedItem> bookedItems = assignment.getMember().getBookedItems();
    for(BookedItem bookedItem : bookedItems){
      String equipmentName = bookedItem.getItem().getName();
      for(Equipment equipment: availableEquipments){
       if(equipment.getName().equals(equipmentName)){
         totalCost+= equipment.getPricePerWeek()*assignment.getMember().getNrWeeks();
       }
      }
    }
    return totalCost;
  }
}
