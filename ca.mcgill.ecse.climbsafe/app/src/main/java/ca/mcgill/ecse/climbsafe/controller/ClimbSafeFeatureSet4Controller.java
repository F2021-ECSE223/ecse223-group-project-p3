package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

import java.util.List;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
    if(name.equals("")){throw new InvalidInputException("The name must not be empty");}
    if(weight <= 0){throw new InvalidInputException("The weight must be greater than 0");}
    if(pricePerWeek < 0){throw new InvalidInputException("The price per week must be greater than or equal to 0");}
    List<Equipment> allEquipment = ClimbSafeApplication.getClimbSafe().getEquipment();
    List<EquipmentBundle> allBundles = ClimbSafeApplication.getClimbSafe().getBundles();
    for(Equipment e: allEquipment){
      if(e.getName().equals(name)){
        throw new InvalidInputException("The piece of equipment already exists");
      }
    }
    for(EquipmentBundle b: allBundles){
      if(b.getName().equals(name)){
        throw new InvalidInputException("The equipment bundle already exists");
      }
    }
    new Equipment(name, weight, pricePerWeek, ClimbSafeApplication.getClimbSafe());
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
    if(newName.equals("")){throw new InvalidInputException("The name must not be empty");}
    if(newWeight <= 0){throw new InvalidInputException("The weight must be greater than 0");}
    if(newPricePerWeek < 0){throw new InvalidInputException("The price per week must be greater than or equal to 0");}
    List<Equipment> allEquipment = ClimbSafeApplication.getClimbSafe().getEquipment();
    List<EquipmentBundle> allBundles = ClimbSafeApplication.getClimbSafe().getBundles();

    Equipment updatedEquipment = null;
    if(newName.equals(oldName)){
      for (Equipment e:allEquipment) {
        if(e.getName().equals(oldName) ){
          updatedEquipment = e;
        }
      }
    }
    else{
      Equipment oldEquipment = null;
      for (Equipment e:allEquipment) {
        if (e.getName().equals(oldName)) {
          oldEquipment = e;
          break;
        }
      }
      for (Equipment e:allEquipment) {
        if(e.getName().equals(newName) && e != oldEquipment){
          throw new InvalidInputException("The piece of equipment already exists");
        }
      }
      updatedEquipment = oldEquipment;
    }
    for(EquipmentBundle b: allBundles){
      if(b.getName().equals(newName)){
        throw new InvalidInputException("An equipment bundle with the same name already exists");
      }
    }
    if(updatedEquipment != null){
      updatedEquipment.setName(newName);
      updatedEquipment.setWeight(newWeight);
      updatedEquipment.setPricePerWeek(newPricePerWeek);
    }else{
      throw new InvalidInputException("The piece of equipment does not exist");
    }
  }

}
