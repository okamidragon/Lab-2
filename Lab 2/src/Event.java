import java.util.Date;

public abstract class Event implements Comparable<Event> {
    private String name;
    private Date dateTime;

    // Constructor
    public Event(String name, Date dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getName();

    // Getters and Setters
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Protected getter for name
    protected String getBaseName() {
        return name;
    }

    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
