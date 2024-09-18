import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public enum Urgency {
    DISTANT, IMMINENT, OVERDUE;

    private static Duration thresholdOfImminence = Duration.ofDays(1); // Default threshold

    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

    public static Urgency getUrgency(Date time) {
        Instant now = Instant.now();
        Instant eventTime = time.toInstant();
        Duration duration = Duration.between(now, eventTime);

        if (duration.isNegative()) {
            return OVERDUE;
        } else if (duration.compareTo(thresholdOfImminence) < 0) {
            return IMMINENT;
        } else {
            return DISTANT;
        }
    }
}
