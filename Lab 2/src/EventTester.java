import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

// Logic provided by Dr. Baarsch
public class EventTester {

    static LocalDateTime deadline = LocalDateTime.of(2024, 12, 7, 17, 0);
    static String lastDeadlineName = "Last Deadline";
    static String lastDeadlineNameAlt = "Final Deadline";
    static Deadline lastDeadline = new Deadline(lastDeadlineName, deadline);
    static Deadline midDeadline = new Deadline("Mid Deadline", deadline.minusDays(10));
    static Deadline firstDeadline = new Deadline("First Deadline", deadline.minusDays(20));
    static final int INCREMENT = 1;

    static LocalDateTime start = LocalDateTime.of(2024, 10, 7, 15, 0);
    static LocalDateTime end = LocalDateTime.of(2024, 10, 7, 16, 0);
    static String location = "MCS 321";
    static String locationAlt = "MCS 339";

    static Meeting firstMeeting = new Meeting("First Meeting", start, end, location);
    static Meeting lastMeeting = new Meeting("Last Meeting", start.plusDays(4), end.plusDays(4), location);
    static Meeting midMeeting = new Meeting("Middle Meeting", start.plusDays(2), end.plusDays(2), location);

    static Event[] events = new Event[] {
            midDeadline,
            lastMeeting,
            lastDeadline,
            firstDeadline,
            firstMeeting,
            midMeeting,
    };

    static Deadline[] deadlines = new Deadline[] {
            firstDeadline,
            midDeadline,
            lastDeadline,
    };

    static Meeting[] meetings = new Meeting[] {
            firstMeeting,
            midMeeting,
            lastMeeting,
    };

    public static void main(String[] args) {
        System.out.println("Testing Getters..." + (testGetters()? "passed" : "failed"));
        System.out.println("Testing Setters..." + (testSetters()? "passed" : "failed"));
        System.out.println("Testing implements Comparable..." + (testComparingEvents()? "passed" : "failed"));
        System.out.println("Testing get meeting duration..." + (testMeetingDuration()? "passed" : "failed"));
        System.out.println("Testing implements Completable..." + (testCompletable()? "passed" : "failed"));
    }

    public static boolean testGetters() {
        return (
                lastDeadline.getName().equals(lastDeadlineName)
                        && lastDeadline.getDateTime().equals(deadline)
                        && firstMeeting.getEndDateTime().equals(end)
                        && firstMeeting.getLocation().equals(location)
        );
    }

    public static boolean testSetters() {
        lastDeadline.setName(lastDeadlineNameAlt);
        lastDeadline.setDateTime(deadline.minusDays(INCREMENT));
        firstMeeting.setEndTime(end.plusDays(INCREMENT));
        firstMeeting.setLocation(locationAlt);

        boolean passed = (
                lastDeadline.getName().equals(lastDeadlineNameAlt)
                        && lastDeadline.getDateTime().equals(deadline.minusDays(INCREMENT))
                        && firstMeeting.getEndDateTime().equals(end.plusDays(INCREMENT))
                        && firstMeeting.getLocation().equals(locationAlt)
        );

        lastDeadline.setName(lastDeadlineName);
        lastDeadline.setDateTime(deadline);
        firstMeeting.setEndTime(end);
        firstMeeting.setLocation(location);

        return passed;
    }

    public static boolean testComparingEvents() {
        Arrays.sort(events);
        return (
                events[0] == firstMeeting
                        && events[1] == midMeeting
                        && events[2] == lastMeeting
                        && events[3] == firstDeadline
                        && events[4] == midDeadline
                        && events[5] == lastDeadline
        );
    }

    public static boolean testMeetingDuration() {
        Duration duration1 =  firstMeeting.getDuration();
        Duration duration2 = Duration.ofHours(1);
        return duration1.equals(duration2);
    }

    public static boolean testCompletable() {
        for (Deadline deadline : deadlines) {
            deadline.complete();
        }
        for (Meeting meeting : meetings) {
            meeting.complete();
        }
        boolean deadlinesPass = Stream.of(deadlines)
                .allMatch(Deadline::isComplete);
        boolean meetingsPass = Stream.of(meetings)
                .allMatch(Meeting::isComplete);
        return deadlinesPass && meetingsPass;
    }
}
