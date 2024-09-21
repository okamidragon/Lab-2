import java.time.Duration;
import java.time.LocalDateTime;

public enum Urgency {
    DISTANT, IMMINENT, OVERDUE;

    private static Duration thresholdOfImminence = Duration.ofDays(1); // Example threshold

    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

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
