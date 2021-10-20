package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import java.util.List;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
    if(weight > 0 && pricePerWeek >= 0){
      new Equipment(name, weight, pricePerWeek, ClimbSafeApplication.getClimbSafe());
    }else{
      String errorMsg = weight < 0 ? "Weight must be greater than 0.": "Price per week must be greater or equal to 0.";
      throw new InvalidInputException(errorMsg);
    }

  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
    List<Equipment> allEquipment = ClimbSafeApplication.getClimbSafe().getEquipment();
    for (Equipment e:allEquipment) {
      if(e.getName().equals(oldName)){
        e.setName(newName);
        if(newWeight > 0 && newPricePerWeek >= 0){
          e.setWeight(newWeight);
          e.setPricePerWeek(newPricePerWeek);
        }else{
          String errorMsg = newWeight < 0 ? "Weight must be greater than 0.": "Price per week must be greater or equal to 0.";
          throw new InvalidInputException(errorMsg);
        }
        break;
      }
    }
  }

}
