import java.util.Date;

public class Meeting extends Event implements Completable {
    private boolean complete = false;
    private Date endDateTime;
    private String location;

    // Constructor
    public Meeting(String name, Date dateTime, Date endDateTime, String location) {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
    }

    @Override
    public String getName() {
        return "Meeting: " + getBaseName() + " at " + getDateTime() + " until " + getEndTime() + " in " + getLocation();
    }

    // Implement Completable interface methods
    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return this.complete;
    }

    // Getter and Setter for endDateTime
    public Date getEndTime() {
        return endDateTime;
    }

    public void setEndTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Method to calculate duration in minutes
    public int getDuration() {
        if (endDateTime != null && getDateTime() != null) {
            long durationMillis = endDateTime.getTime() - getDateTime().getTime();
            return (int) (durationMillis / (1000 * 60)); // Convert milliseconds to minutes
        }
        return 0;
    }

    @Override
    public String toString() {
        return getName() + " | Duration: " + getDuration() + " minutes | Complete: " + isComplete();
    }
}
