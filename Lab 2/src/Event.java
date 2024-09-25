import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;

    // Data of an Event
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Name of Event
    public abstract String getName();

    // Location of Event
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Date of the Event
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Update the name of the event
    public void setName(String name) {
        this.name = name;
    }

    // Compares dates
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
