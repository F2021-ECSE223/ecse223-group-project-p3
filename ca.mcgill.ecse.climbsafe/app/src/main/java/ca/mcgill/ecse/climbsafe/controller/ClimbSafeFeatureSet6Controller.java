package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;



import java.util.ArrayList;
import java.util.List;

public class ClimbSafeFeatureSet6Controller {


  /**
   * @author Abhijeet Praveen
   * @param name this is the input to the method which represents the name of the equipment we need to delete
   *
   *        We will find the equipment with the given name using the getWithName method from the BookableItem class,
   *        and use the delete method from the Equipment class to delete it.
   *        If the equipment specified is in a bundle then it cannot be deleted, and therefore the method
   *        will throw an InvalidInputException with the corresponding error message.
   */
  public static void deleteEquipment(String name) throws InvalidInputException {
    List<EquipmentBundle> equipmentBundles = ClimbSafeApplication.getClimbSafe().getBundles();
    for (EquipmentBundle equipmentBundle: equipmentBundles){
      for (BundleItem bundleItem: equipmentBundle.getBundleItems()){
        String equipmentName = bundleItem.getEquipment().getName();
        if (equipmentName.equals(name)) {
          throw new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted");
        }
      }

    }
    BookableItem bookableItem = BookableItem.getWithName(name);
    if(bookableItem instanceof Equipment){
      Equipment equipment = (Equipment) Equipment.getWithName(name);
      equipment.delete();
    }
  }


  /**
   * @author Abhijeet Praveen
   * @param name This is the input to the method which represents the name of the equipment bundle
   *        we need to delete.
   *
   *        We will find the equipmentBundle with the given name by using the getWithName method from the
   *        BookableItem class from the model, then we use the delete method from the EquipmentBundle
   *        class to delete it.
   */
  public static void deleteEquipmentBundle(String name) {
    BookableItem bookableItem = BookableItem.getWithName(name);
    if(bookableItem instanceof EquipmentBundle){
      EquipmentBundle equipmentBundle = (EquipmentBundle) EquipmentBundle.getWithName(name);
      equipmentBundle.delete();
    }
  }


  /**
   * @author Abhijeet Praveen
   *
   *         This function implements the ViewAssignments feature
   *         We use the TOAssignment class which has been created in this package using the Umple file
   *         We use getters from the corresponding classes to obtain the various inputs
   *         we will need for the constructor of our TOAssignment object.
   *         However, if a guide or hotel is not required in the assignment, we then pass
   *         null in the constructor for the TOAssignment object for guide's name, guide's email
   *         and hotel's name.
   *         This method uses helper methods that have been added to this Controller file.
   *
   * @return this method returns the list of assignments we have in our system
   */
  public static List<TOAssignment> getAssignments() {
    var assignments = new ArrayList<TOAssignment>();
    for (var assignment: ClimbSafeApplication.getClimbSafe().getAssignments()){
      String hotelName;
      String guideName;
      String guideEmail;
      try {
        hotelName = assignment.getHotel().getName();
      }
      catch(RuntimeException e) {
        hotelName = null;
      }
      try {
        guideName = assignment.getGuide().getName();
        guideEmail = assignment.getGuide().getEmail();
      }
      catch(RuntimeException e) {
        guideName = null;
        guideEmail = null;
      }
      assignments.add(new TOAssignment(assignment.getMember().getEmail(),assignment.getMember().getName(),
              guideEmail, guideName,hotelName,
              assignment.getStartWeek(), assignment.getEndWeek(),
              getTotalGuideCost(assignment),getTotalEquipmentCost(assignment)));
    }
    return assignments;
  }


  /**
   * @author Abhijeet Praveen
   *
   *         Using helper method to find the total guide cost for an assigment which is
   *         needed for our constructor for the TOAssignment object.
   *         The implementation is performed by obtaining the price of the guide per week
   *         and multiplying it by the number of weeks the member has booked.
   *         However, if no guide has been booked then the price returned will be 0.
   *
   * @param assignment corresponds to the assignment for which we are trying to obtain the total guide cost
   * @return integer corresponding to total cost for the guide
   */
  private static int getTotalGuideCost(Assignment assignment){
    if (assignment.getGuide()==null) return 0;
    return assignment.getClimbSafe().getPriceOfGuidePerWeek()*assignment.getMember().getNrWeeks();
  }


  /**
   * @author Abhijeet Praveen
   *         Using helper method to find the total equipment cost for an assignment which is needed for
   *         the TOAssignment object
   *
   *         Firstly, we will get the list of booked items for the member in the assignment.
   *         A bookedItem in a member's list can be either a bundle or an equipment.
   *         If it is a bundle, we get the list of equipments in that bundle,
   *         and the corresponding quantity of that equipment in the bundle.
   *         After that, for the total cost of the bundle, we get the price of that equipment per week,
   *         multiply it by the number of weeks the member
   *         has booked, as well multiplying the quantity of that equipment in the bundle, the quantity
   *         the member has booked and multiplying the corresponding discount if a guide has been booked.
   *
   *         If we see that the booked item is an equipment, we just get the equipment's price per week multiply
   *         it with the number of weeks the member has booked as well as the quantity the member has booked.
   *
   * @param assignment corresponds to the assignment we are trying to find the total equipment cost for
   * @return integer corresponding to total cost for the booked items for the member
   */
  private static int getTotalEquipmentCost(Assignment assignment){
    int totalCost = 0;
    List<BookedItem> bookedItems = assignment.getMember().getBookedItems();
    for(BookedItem bookedItem : bookedItems){
        if(bookedItem.getItem() instanceof EquipmentBundle) {
          EquipmentBundle equipmentBundle = (EquipmentBundle) bookedItem.getItem();
          List<BundleItem> equipmentList = equipmentBundle.getBundleItems();
          for (BundleItem bundleItem: equipmentList){
            Equipment equipment = bundleItem.getEquipment();
            int equipmentQuantity = bundleItem.getQuantity();
            if(assignment.getMember().getGuideRequired()) {
              double discount = (100-equipmentBundle.getDiscount())/((double) 100);
              totalCost += equipment.getPricePerWeek()*assignment.getMember().getNrWeeks()*equipmentQuantity*discount*bookedItem.getQuantity();
            }
            else {
              totalCost += equipment.getPricePerWeek()*assignment.getMember().getNrWeeks()*equipmentQuantity*bookedItem.getQuantity();
            }
          }
        }
        if(bookedItem.getItem() instanceof Equipment) {
          Equipment equipment = (Equipment) bookedItem.getItem();
          totalCost += equipment.getPricePerWeek()*assignment.getMember().getNrWeeks()* bookedItem.getQuantity();
        }
      if (assignment.getMember().getBookedItems().size()==0) totalCost = 0;
    }
    return totalCost;
  }
}
