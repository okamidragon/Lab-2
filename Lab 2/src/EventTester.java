    import java.time.Duration;
    import java.time.LocalDateTime;
    import java.util.Arrays;
    import java.util.Date;
    import java.util.stream.Stream;
    
    public class EventTester {
        static LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 12, 7, 17, 0);
        static String lastDeadlineName = "Last Deadline";
        static Deadline lastDeadline = new Deadline(lastDeadlineName, Date.from(deadlineDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
        static Deadline midDeadline = new Deadline("Mid Deadline", Date.from(deadlineDateTime.minusDays(10).atZone(java.time.ZoneId.systemDefault()).toInstant()));
        static Deadline firstDeadline = new Deadline("First Deadline", Date.from(deadlineDateTime.minusDays(20).atZone(java.time.ZoneId.systemDefault()).toInstant()));
    
        static LocalDateTime startDateTime = LocalDateTime.of(2024, 10, 7, 15, 0);
        static LocalDateTime endDateTime = LocalDateTime.of(2024, 10, 7, 16, 0);
        static String location = "MCS 321";
    
        static Meeting firstMeeting = new Meeting("First Meeting", Date.from(startDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()), Date.from(endDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()), location);
        static Meeting lastMeeting = new Meeting("Last Meeting", Date.from(startDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant()), Date.from(endDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant()), location);
        static Meeting midMeeting = new Meeting("Middle Meeting", Date.from(startDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant()), Date.from(endDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant()), location);
    
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
            System.out.println("Testing Getters..." + (testGetters() ? "passed" : "failed"));
            System.out.println("Testing Setters..." + (testSetters() ? "passed" : "failed"));
            System.out.println("Testing implements Comparable..." + (testComparingEvents() ? "passed" : "failed"));
            System.out.println("Testing get meeting duration..." + (testMeetingDuration() ? "passed" : "failed"));
            System.out.println("Testing implements Completable..." + (testCompletable() ? "passed" : "failed"));
        }
    
        public static boolean testGetters() {
            return (
                    lastDeadline.getName().equals(lastDeadlineName) &&
                            lastDeadline.getDateTime().equals(Date.from(deadlineDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())) &&
                            firstMeeting.getEndDateTime().equals(Date.from(endDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())) &&
                            firstMeeting.getLocation().equals(location)
            );
        }
    
        public static boolean testSetters() {
            // Test setters
            lastDeadline.setName("Final Deadline");
            lastDeadline.setDateTime(Date.from(deadlineDateTime.minusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant()));
            firstMeeting.setEndDateTime(Date.from(endDateTime.plusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant()));
            firstMeeting.setLocation("MCS 339");
    
            // Validate setters
            boolean passed = (
                    lastDeadline.getName().equals("Final Deadline") &&
                            lastDeadline.getDateTime().equals(Date.from(deadlineDateTime.minusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant())) &&
                            firstMeeting.getEndDateTime().equals(Date.from(endDateTime.plusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant())) &&
                            firstMeeting.getLocation().equals("MCS 339")
            );
    
            // Reset to original values
            lastDeadline.setName(lastDeadlineName);
            lastDeadline.setDateTime(Date.from(deadlineDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            firstMeeting.setEndDateTime(Date.from(endDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            firstMeeting.setLocation(location);
    
            return passed;
        }
    
        public static boolean testComparingEvents() {
            Arrays.sort(events);
            return (
                    events[0] == firstMeeting &&
                            events[1] == midMeeting &&
                            events[2] == lastMeeting &&
                            events[3] == firstDeadline &&
                            events[4] == midDeadline &&
                            events[5] == lastDeadline
            );
        }
    
        public static boolean testMeetingDuration() {
            // Duration of the meeting should be one hour
            Duration duration1 = Duration.ofMinutes(firstMeeting.getDuration());
            Duration duration2 = Duration.ofHours(1);
            return duration1.equals(duration2);
        }
    
        public static boolean testCompletable() {
            // Complete all the deadlines
            for (Deadline deadline : deadlines) {
                deadline.complete();
            }
            // Complete all the meetings
            for (Meeting meeting : meetings) {
                meeting.complete();
            }
            // Check that the deadlines are complete
            boolean deadlinesPass = Stream.of(deadlines).allMatch(Deadline::isComplete);
            // Check that the meetings are complete
            boolean meetingsPass = Stream.of(meetings).allMatch(Meeting::isComplete);
            // Return results
            return deadlinesPass && meetingsPass;
        }
    }
