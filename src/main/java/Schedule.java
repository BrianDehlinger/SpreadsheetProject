public interface Schedule {
    /**
     * display all of the time slots followed by all of the people working at the time and who is available at the time.
     */
    void displaySchedule();

    /**
     * display the people's names who are available for a given time and date
     * @param timeDate
     */
    void displayPeopleAvailable(String timeDate);

    /**
     * display the people's names who are working for a time and date.
     * @param timeDate
     */
    void displayPeopleWorking(String timeDate);

    /**
     * displays a times min and max number of people who need to/can work a time.
     * @param timeDate
     */
    void getMinAndMax(String timeDate);

    /*
    @param A string representing the time slot to return.
    @return A time slot object that corresponds to the single parameter
     */
    Slot getTimeSlot(String timeDate);

    /*
    @param A string representing the time slot to completely remove from the schedule
    @post The time slot corresponding to the date time string parameter will have been removed from the schedule
    @return The time slot removed from the schedule.
     */
    Slot removeTimeSlot(String timeDate);

    /*
    @param A string representing the time slot to set the min and max for.
    @param An integer specify the minimum required number of people that need to be working
    @param An integer specifying the maximum or ideal number of people working in the time slot.
    @post The time slot's min and max values will be modified to the values of the parameters
    @return True if the timeSlot was found and modified. False if the time slot was not found on the schedule.
     */
    boolean modifyTimeSlot(String timeDate, int min, int max);

    /*
    @pre A time slot object was instantiated.
    @post The schedule will be cleared of all times and will be empty.
     */
    void clear();

    /*
    @param The time and date identifier for the time slot.
    @param The actual slot object on the schedule
    @post The schedule will contain a time slot object associated with a given time and date string. The number of time slots will equal zero.
    @return True if the time slot was inserted into the schedule. False if the time slot was already on the schedule.
     */
    boolean putNewTimeSlotOnSchedule(String timeAndDate, Slot timeSlotObj);
}
