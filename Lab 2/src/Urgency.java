import java.time.Duration;
import java.time.LocalDateTime;

public enum Urgency {
    DISTANT, IMMINENT, OVERDUE;

    // Example threshold
    private static Duration thresholdOfImminence = Duration.ofDays(1);

    // Sets the threshold of imminence
    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

    // Logic to determine how close an events date is to the current date,
    // Uses that logic to decide the urgency of the event
    public static Urgency getUrgency(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        if (time.isBefore(now)) {
            return OVERDUE;
        } else if (Duration.between(now, time).compareTo(thresholdOfImminence) < 0) {
            return IMMINENT;
        } else {
            return DISTANT;
        }
    }
}
