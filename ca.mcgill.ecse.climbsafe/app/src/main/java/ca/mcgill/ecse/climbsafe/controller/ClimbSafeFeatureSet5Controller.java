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
        if (discount < 0) error = "Discount must be at least 0";
        if (discount > 100) error = "Discount must be no more than 100";
        if (name.isEmpty()) error = "Equipment bundle name cannot be empty";

        for (String str : equipmentNames) {
            Equipment e = (Equipment) Equipment.getWithName(str);
            if (e == null) {
                error = "Equipment " + str + " does not exist";
                break;
            }
        }

        for (int i = 0; i < equipmentNames.size(); i++) {
            for (int j = 0; j < equipmentNames.size(); j++) {
                if (equipmentNames.get(i).equals(equipmentNames.get(j))) {
                    if (i != j) {
                        error = "Equipment bundle must contain at least two distinct types of equipment";
                        break;
                    }
                }
            }
        }


        BookableItem b = (BookableItem) BookableItem.getWithName(name);
        if (b != null) error = "A bookable item called " + name + " already exists";

        for (int i : equipmentQuantities) {
            if (i <= 0) {
                error = "Each bundle item must have quantity greater than or equal to 1";
                break;
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


    public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
                                             List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
        String error = "";
        ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
        List<Equipment> equipment = new ArrayList<Equipment>();
        List<BundleItem> bundleItems = new ArrayList<BundleItem>();
        EquipmentBundle bundle=null;

        for (String str : newEquipmentNames) {
            Equipment e = (Equipment) Equipment.getWithName(str);
            if (e == null) {
                error = "Equipment " + str + " does not exist";
                break;
            }
        }

        if (newEquipmentNames.size() <= 1)
            error = "Equipment bundle must contain at least two distinct types of equipment";

        for (int i = 0; i < newEquipmentNames.size(); i++) {
            for (int j = 0; j < newEquipmentNames.size(); j++) {
                if (newEquipmentNames.get(i).equals(newEquipmentNames.get(j))) {
                    if (i != j) {
                        error = "Equipment bundle must contain at least two distinct types of equipment";
                        break;
                    }
                }
            }
        }

        for (int i : newEquipmentQuantities) {
            if (i <= 0) {
                error = "Each bundle item must have quantity greater than or equal to 1";
                break;
            }
        }

        if (newDiscount < 0) error = "Discount must be at least 0";

        if (newDiscount > 100) error = "Discount must be no more than 100";

        if(!newName.equals(oldName)) {
            List<EquipmentBundle> equipmentBundles = climbSafe.getBundles();
            for (EquipmentBundle equipmentBundle1 : equipmentBundles) {
                if (equipmentBundle1.getName().equals(newName)) {
                    error = "A bookable item called " + newName + " already exists";
                }
            }
            List<Equipment> equipmentList = climbSafe.getEquipment();
            for (Equipment equipment1 : equipmentList) {
                if (equipment1.getName().equals(newName)) {
                    error = "A bookable item called " + newName + " already exists";
                }
            }
        }

        if (newName==null || newName.equals("")) error = "Equipment bundle name cannot be empty";

        try {
                bundle = (EquipmentBundle) EquipmentBundle.getWithName(oldName);
            }catch(RuntimeException e){

            }
        if (bundle == null) error = "Equipment bundle " + oldName + " does not exist";



        if (error.length() > 0) {
            throw new InvalidInputException(error.trim());
        }


        for (String str1 : newEquipmentNames) {
            for (Equipment e : climbSafe.getEquipment()) {
                if (str1.equals(e.getName())) {
                    equipment.add(e);
                }
            }
        }

            try {
                bundle.setDiscount(newDiscount);
                bundle.setName(newName);

                for (int i = 0; i < newEquipmentNames.size(); i++) {
                    bundleItems.add(new BundleItem(newEquipmentQuantities.get(i), climbSafe, bundle, equipment.get(i)));
                }
                for (BundleItem item : bundleItems) {
                    bundleItems.add(item);
                }

            } catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());
            }


        }
    }
