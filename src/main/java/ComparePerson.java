import java.util.*;
public class ComparePerson implements Comparator<Person> {

    public int compare(final Person personOne, final Person personTwo) {
        if (personOne == personTwo){
            return 0;
        }
        if (personOne.getName().equalsIgnoreCase(personTwo.getName())){
            return 0;
        }   
        int firstTest= ((personOne.getNumberScheduled()) * (personOne.getNumberInitiallyAvailable() + 1) ) - ((personTwo.getNumberScheduled()) * (personTwo.getNumberInitiallyAvailable() + 1));
        if ((firstTest != 0)) {
            //System.out.println("here"); //this results in some intense spam during testing
            return (int) Math.floor(firstTest);
        }
        int secondTest = personOne.getNumberInitiallyAvailable() - personTwo.getNumberInitiallyAvailable();
        if (secondTest != 0) {
            //System.out.println("Here");
            return secondTest;
        }
        else{
            //System.out.println("here3");
            return personOne.getName().compareToIgnoreCase(personTwo.getName());
        }
    }
}