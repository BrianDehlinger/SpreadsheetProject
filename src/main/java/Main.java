import com.google.api.services.sheets.v4.Sheets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Sheets sheet;
        PersonMapHash peopleHash = new PersonMapHash(); // This is necessary to avoid infinite recursion utilizing the Comparator on the String key. It's worth the sacrirfice in space. HashMap is constant lookup time versus O(log n)
        TheTimeMap schedule = new TheTimeMap();
        DataInterface theDataInterface = new DataInterface();
        theDataInterface.getDataFromSpreadsheet(peopleHash, schedule);
        ArrayList<String> timeToRemove = new ArrayList();
        timeToRemove.add("1:00 WTC Mon");
        schedule.deleteDayTimes(timeToRemove);
        for (Map.Entry<String, Slot> entry : schedule.entrySet()){
            System.out.println("Time " + entry.getKey());
            System.out.println("People who are available to fill the time slot: " + entry.getValue().getPeopleAvailableNamems());
        }
        for (Map.Entry<String, Person> entry : peopleHash.entrySet()){
            System.out.println("Person " + entry.getKey());
            System.out.println("Time's person is available " + entry.getValue().getTimesFree());

        }
        Person john = new Person("John");
        Slot timeSlot = new Slot();
        timeSlot.addConflictMarker('D',1);
        Person mike = new Person("Mike");
        mike.addConflictMarker('D',1);
        timeSlot.addPersonToPeopleAvailable(mike);
        timeSlot.addPersonToPeopleAvailable(john);
        timeSlot.removeAllConflictMarkerRelatedToSlotFromAll('D', 1);
        System.out.println(timeSlot.checkForConflicts(john));

        Rule newRule = new Rule(2, 3);
        for (Map.Entry<String, Slot> entry : schedule.entrySet()){
            newRule.applyTimeDayRuleToSlot(entry.getValue(), "Thu", "9:30");
            System.out.println(entry.getValue().getDate() + " " + entry.getValue().getTime() + " : Min = " + entry.getValue().getMinimumRequired());
        }

        System.out.println("------------------------------------------------");

        //test methods in TheTimeMap

        System.out.println("\nTest displaySchedule:");
        schedule.displaySchedule();

        System.out.println("\nTest displayPeopleWorking:");
        schedule.displayPeopleWorking("9:30 Mon 7/2");

        System.out.println("\nTest displayPeopleAvailable:");
        schedule.displayPeopleAvailable("9:30 Mon 7/2");

        System.out.println("\nTest displayMinAndMax:");
        schedule.getMinAndMax("9:30 Mon 7/2");
    }
}
