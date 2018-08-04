import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;

import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Sheets sheet;
        PersonMapHash peopleHash = new PersonMapHash(); // This is necessary to avoid infinite recursion utilizing the Comparator on the String key. It's worth the sacrifice in space. HashMap is constant lookup time versus O(log n)
        TheTimeMap schedule = new TheTimeMap();
        DataInterface theDataInterface = new DataInterface();
        theDataInterface.getDataFromSpreadsheet(peopleHash, schedule);
        ArrayList<String> timeToRemove = new ArrayList();
        final String inputValueOption = "RAW";
        /*

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the ID of the spreadsheet to read from:");
        theDataInterface.setReadSheetID(input.nextLine());

        System.out.println("Enter the ID of the spreadsheet to write to:");
        theDataInterface.setWriteSheetID(input.nextLine());
        */

        for (Map.Entry<String, Slot> entry : schedule.entrySet()) {
            ArrayList<Person> guides = entry.getValue().getPeopleAvailable();
            while(entry.getValue().getNumberOfPeopleWorking() < entry.getValue().getMax()){
                Collections.sort(guides, new ComparePerson());
                Person current = guides.get(0);
                if(entry.getValue().getPeopleWorkingNames().contains(current)){
                    guides.remove(current);
                }
                else{
                    entry.getValue().addPersontoPeopleWorking(current);
                    current.incrementNumberScheduled();
                }
            }
        }


        for(Map.Entry<String, Slot> entry : schedule.entrySet()){
            System.out.println(entry.getValue().getPeopleWorkingNames());
        }

        /*
        final String range = "A1:E1";
        Object [] v = new Object[] {"", "Wed", "Thu", "Fri", "Sat"};
        final List<List<Object>> values = Arrays.asList(Arrays.asList(v));
        theDataInterface.updateValues("1hRLbsjpvW20V1b_QytLXNQ7TqQshh7eHdbP5ZD5NrPw", range, inputValueOption, values);
        */
    }
}