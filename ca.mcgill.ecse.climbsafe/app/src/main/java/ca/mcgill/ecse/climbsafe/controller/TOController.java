package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TOController {
    /**
     * @author Sebastien Cantin
     * @return A list of all equipments in the climbsafe as Transfer objects
     */
    public static List<TOBookableItem> getEquipment() {
        var bookableItems = new ArrayList<TOBookableItem>();
        for (var equipment: ClimbSafeApplication.getClimbSafe().getEquipment()){
            TOBookableItem item = TOBookableItem.getWithName(equipment.getName());
            bookableItems.add(Objects.requireNonNullElseGet(item, () -> new TOBookableItem(equipment.getName(), 0, equipment.getWeight(), equipment.getPricePerWeek())));
        }
        return bookableItems;
    }

    /**
     * @author Sebastien Cantin
     * @return A list of all bundles in the climbsafe as Transfer objects
     */
    public static List<TOBookableItem> getBundles() {
        var bookableItems = new ArrayList<TOBookableItem>();
        for (var bundle: ClimbSafeApplication.getClimbSafe().getBundles()){
            TOBookableItem item = TOBookableItem.getWithName(bundle.getName());
            bookableItems.add(Objects.requireNonNullElseGet(item, () -> new TOBookableItem(bundle.getName(), bundle.getDiscount(), 0, 0)));

        }
        return bookableItems;
    }

    /**
     * @author Sebastien Cantin
     * @return returns the climbsafe as a transfer object
     */
    public static TOClimbSafe getClimbSafe() {
        return new TOClimbSafe(ClimbSafeApplication.getClimbSafe().getNrWeeks());
    }

    /**
     * @author Sebastien Cantin
     * @param email the email of the user to be returned
     * @return either the member or guide as a transfer object
     */
    public static TONamedUser getUserWithEmail(String email){
        NamedUser user = (NamedUser) NamedUser.getWithEmail(email);
        if (user instanceof Member){
            Member member = (Member) user;
            return new TONamedUser(member.getEmail(), member.getPassword(), member.getName(), member.getEmergencyContact(), member.getHotelRequired(),member.getGuideRequired(),member.getNrWeeks(), "member");
        }
        else {
            Guide guide = (Guide) user;
            return new TONamedUser(guide.getEmail(), guide.getPassword(), guide.getName(), guide.getEmergencyContact(), false,false,0, "guide");
        }
    }

    /**
     * @author Sebastien Cantin
     * @return A list of all members in the climbsafe as Transfer objects
     */
    public static List<TONamedUser> getMembers(){
        var members = new ArrayList<TONamedUser>();
        for (var member: ClimbSafeApplication.getClimbSafe().getMembers())
            members.add(new TONamedUser(member.getEmail(), member.getPassword(), member.getName(), member.getEmergencyContact(), member.getHotelRequired(),member.getGuideRequired(),member.getNrWeeks(), "member"));

        return members;
    }

    /**
     * @author Sebastien Cantin
     * @return A list of all guides in the climbsafe as Transfer objects
     */
    public static List<TONamedUser> getGuides(){
        var guides = new ArrayList<TONamedUser>();
        for (var guide: ClimbSafeApplication.getClimbSafe().getGuides())
            guides.add(new TONamedUser(guide.getEmail(), guide.getPassword(), guide.getName(), guide.getEmergencyContact(), false,false,0, "guide"));

        return guides;
    }
    public static List<TOBookedItem> getItemsforMemberEmail(String aEmail){
        var itemList =  new ArrayList<TOBookedItem>();
        var member = (Member)Member.getWithEmail(aEmail);
        for (var item: member.getBookedItems()){
            if (item.getItem() instanceof Equipment) itemList.add(new TOBookedItem(item.getQuantity(), aEmail, new TOBookableItem(item.getItem().getName(),0,((Equipment) item.getItem()).getWeight(),((Equipment) item.getItem()).getPricePerWeek())));
            else itemList.add(new TOBookedItem(item.getQuantity(), aEmail, new TOBookableItem(item.getItem().getName(), ((EquipmentBundle)item.getItem()).getDiscount(),0,0)));
        }
        return itemList;
    }

}
