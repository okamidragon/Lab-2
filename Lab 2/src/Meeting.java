import java.util.Date;

public class Meeting extends Event implements Completable {
    private Date endDateTime; // The end time of the meeting
    private String location; // Location of the meeting
    private boolean complete = false;

    // Constructor
    public Meeting(String name, Date startDateTime, Date endDateTime, String location) {
        super(name, startDateTime); // Call the superclass constructor
        this.endDateTime = endDateTime; // Set the end dateTime
        this.location = location; // Set the location of the meeting
    }

    @Override
    public String getName() {
        return name; // Return the name of the meeting
    }

    public Date getEndDateTime() {
        return endDateTime; // Return the end time of the meeting
    }

    public int getDuration() {
        return (int) ((endDateTime.getTime() - dateTime.getTime()) / 1000 / 60); // Duration in minutes
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime; // Set the end time of the meeting
    }

    public String getLocation() {
        return location; // Return the location of the meeting
    }

    public void setLocation(String location) {
        this.location = location; // Set the location of the meeting
    }

    @Override
    public void complete() {
        this.complete = true; // Set completion status
    }

    @Override
    public boolean isComplete() {
        return this.complete; // Return completion status
    }
}
