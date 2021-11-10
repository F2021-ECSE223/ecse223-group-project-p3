package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

import java.util.List;

/**
 * 1. Initiate the assignment for all members
 * 2. Pay for a member’s trip
 * 3. Start all trips for a specific week
 * 4. Finish a member’s trip
 * 5. Cancel a member’s trip
 */


public class AssignmentController {
    public static void initiateAllAssignments(List<Member> allMembers) {

        List<Guide> allGuides = ClimbSafeApplication.getClimbSafe().getGuides();

        for (Guide guide : allGuides) {
            for (Member member : allMembers) {
                if (!member.hasAssignment()) {
                    int startWeek = 1;
                    if (guide.hasAssignments() && member.isGuideRequired()) {
                        int currentStart = 1;
                        for (Assignment a : guide.getAssignments()) {
                            if (a.getEndWeek() > currentStart) {
                                currentStart = a.getEndWeek();
                            }
                        }
                        startWeek = currentStart;
                    }
                    if (member.isGuideRequired()) {

                        if (ClimbSafeApplication.getClimbSafe().getNrWeeks() - startWeek >= member.getNrWeeks()) {
                            int endWeek = startWeek + member.getNrWeeks();
                            Assignment newA = new Assignment(startWeek, endWeek, member, ClimbSafeApplication.getClimbSafe());
                            newA.setGuide(guide);
                        }
                    } else if (!member.isGuideRequired()) {
                        int endWeek = member.getNrWeeks();
                        new Assignment(startWeek, endWeek, member, ClimbSafeApplication.getClimbSafe());
                    }
                }
            }
        }
    }

    public boolean payMemberTrip(Member payer, String authCode) {
        return true;
    }

    public static void startAllTrips(int weekNumber) throws InvalidInputException {
    }

    public static void finishMemberTrip(String email) throws InvalidInputException {
    }

    public boolean cancelMemberTrip(Member member) {
        return true;
    }
}
