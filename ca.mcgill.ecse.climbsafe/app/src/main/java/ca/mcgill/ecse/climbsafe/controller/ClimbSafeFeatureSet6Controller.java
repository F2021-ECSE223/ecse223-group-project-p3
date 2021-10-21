package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;


import java.util.ArrayList;
import java.util.List;

public class ClimbSafeFeatureSet6Controller {



  public static void deleteEquipment(String name) throws InvalidInputException {
    if(name==null || name.equals("")) throw new InvalidInputException("Equipment name cannot be empty");
    List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();
    for (Equipment equipment: equipmentList){
      if(equipment.getName().equals(name)) equipment.delete();
    }
  }



  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) throws InvalidInputException {
    if(name==null || name.equals("")) throw new InvalidInputException("Equipment Bundle name cannot be empty");
    List<EquipmentBundle> bundleList = ClimbSafeApplication.getClimbSafe().getBundles();
    for(EquipmentBundle bundle: bundleList){
      if(bundle.getName().equals(name)) bundle.delete();
    }
  }

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
  private static int getTotalGuideCost(Assignment assignment){ //private helper method
    return assignment.getClimbSafe().getPriceOfGuidePerWeek()*assignment.getMember().getNrWeeks();
  }

  private static int getTotalEquipmentCost(Assignment assignment){ //private helper method
    int totalCost = 0;
    List<Equipment> availableEquipments = ClimbSafeApplication.getClimbSafe().getEquipment();
    List<BookedItem> bookedItems = assignment.getMember().getBookedItems();
    for(BookedItem bookedItem : bookedItems){
      String equipmentName = bookedItem.getItem().getName();
      for(Equipment equipment: availableEquipments){
       if(equipment.getName().equals(equipmentName)){
         totalCost+= equipment.getPricePerWeek()*assignment.getMember().getNrWeeks();
         break;
       }
      }
    }
    return totalCost;
  }
}

