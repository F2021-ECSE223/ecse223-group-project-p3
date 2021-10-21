package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

import java.util.ArrayList;
import java.util.List;

public class ClimbSafeFeatureSet5Controller {

    public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
                                          List<Integer> equipmentQuantities) throws InvalidInputException {
        ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
        List<Equipment> equipment = new ArrayList<Equipment>();
        List<BundleItem> bundleItems = new ArrayList<BundleItem>();
        String error = "";

        if (equipmentQuantities.size() <= 1)
            error = "Equipment bundle must contain at least two distinct types of equipment";
        if (equipmentNames.size() <= 1)
            error = "Equipment bundle must contain at least two distinct types of equipment";
        if (discount < 0) error = "The discount must be at least 0";
        if (discount > 100) error = "Discount must be no more than 100";
        if (name == null) error = "Equipment bundle name cannot be empty";

        for (String str : equipmentNames) {
            for (Equipment e : climbSafe.getEquipment()) {
                if (!(str.equals(e.getName()))) {
                    error = "Equipment " + str + " does not exist ";
                    break;
                }

            }
            for (EquipmentBundle bundle : climbSafe.getBundles()) {
                if (name.equals(bundle.getName())) {
                    error = "A bookable item called small bundle already exists";
                    break;


                }
                for (int i : equipmentQuantities) {
                    if (i <= 0) {
                        error = "Each bundle item must have quantity greater than or equal to 1";
                        break;
                    }
                }

            }

            if (error.length() > 0) {
                throw new InvalidInputException(error.trim());
            }
            for (String str1 : equipmentNames) {
                for (Equipment e : climbSafe.getEquipment()) {
                    if (str1.equals(e.getName())) {
                        equipment.add(e);
                    }

                }
                try {
                    EquipmentBundle bundle = climbSafe.addBundle(name, discount);
                    for (int i = 0; i < equipment.size(); i++) {
                        bundleItems.add(new BundleItem(equipmentQuantities.get(i), climbSafe, bundle, equipment.get(i)));
                    }
                    for (BundleItem item : bundleItems) {
                        bundleItems.add(item);
                    }
                } catch (RuntimeException e) {
                    throw new InvalidInputException(e.getMessage());
                }


            }
        }
    }

    public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
                                             List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
            throws InvalidInputException {
        String error = "";
        ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
        EquipmentBundle bundle=null;
        List<Equipment> equipment = new ArrayList<Equipment>();
        List<BundleItem> bundleItems = new ArrayList<BundleItem>();

        if (newEquipmentQuantities.size() <= 1)
            error = "Equipment bundle must contain at least two distinct types of equipment";
        if (newEquipmentNames.size() <= 1)
            error = "Equipment bundle must contain at least two distinct types of equipment";
        if (newDiscount < 0) error = "The discount must be at least 0";
        if (newDiscount > 100) error = "Discount must be no more than 100";
        if (newName == null) error = "Equipment bundle name cannot be empty";

        for (String str : newEquipmentNames) {
            for (Equipment e : climbSafe.getEquipment()) {
                if (!(str.equals(e.getName()))) {
                    error = "Equipment " + str + " does not exist ";
                    break;
                }
            }
        }

        for (EquipmentBundle b : climbSafe.getBundles()) {
            if (newName.equals(b.getName())) {
                bundle=b;
            }
        }

        if (bundle==null) error = "Equipment bundle rope does not exist";

         try{
             bundle.setDiscount(newDiscount);
             bundle.setName(newName);
             for (int i = 0; i < equipment.size(); i++) {
                 bundleItems.add(new BundleItem(newEquipmentQuantities.get(i), climbSafe, bundle, equipment.get(i)));
             }
             for (BundleItem item : bundleItems) {
                 bundleItems.add(item);
             }
         }catch(RuntimeException e){
             throw new InvalidInputException(e.getMessage());
         }


    }
}
