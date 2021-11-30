package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import java.util.ArrayList;
import java.util.List;

public class TOController {
    public static List<TOBookableItem> getEquipment() {
        var bookableItems = new ArrayList<TOBookableItem>();
        for (var equipment: ClimbSafeApplication.getClimbSafe().getEquipment()){
            bookableItems.add(new TOBookableItem(equipment.getName(), 0, equipment.getWeight(), equipment.getPricePerWeek()));
        }
        return getEquipment();
    }
    public static List<TOBookableItem> getBundles() {
        var bookableItems = new ArrayList<TOBookableItem>();
        for (var bundle: ClimbSafeApplication.getClimbSafe().getBundles()){
            bookableItems.add(new TOBookableItem(bundle.getName(), bundle.getDiscount(), 0, 0));
        }
        return getBundles();
    }
}
