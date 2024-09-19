import java.util.Date;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected Date dateTime;

    public Event(String name, Date dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public abstract String getName(); // Abstract method for subclasses to implement

    public Date getDateTime() {
        return dateTime; // Return the dateTime of the event
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime; // Set the dateTime
    }

    public void setName(String name) {
        this.name = name; // Set the event name
    }

    @Override
    public int compareTo(Event e) {
        return dateTime.compareTo(e.getDateTime()); // Compare by dateTime
    }
}
