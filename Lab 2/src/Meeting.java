import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    // Data for meeting
    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
        super(name, startDateTime);
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;
    }

    // Name of meeting
    public String getName() {
        return name;
    }

    // End date for meeting
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Location of meeting
    public String getLocation() {
        return location;
    }

    // Update the end time
    public void setEndTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    // Update the location
    public void setLocation(String location) {
        this.location = location;
    }

    // Determines how long the meeting is
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);
    }

    // Completes the meeting
    public void complete() {
        complete = true;
    }

    // Checks if complete
    public boolean isComplete() {
        return complete;
    }
}
