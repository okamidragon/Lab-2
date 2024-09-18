import java.util.Date;

public class EventTester {

    public static void main(String[] args) {
        // Test the Deadline
        System.out.println("Testing Deadline:");
        Date deadlineDate = new Date(System.currentTimeMillis() + 3600000); // 1 hour from now
        Deadline deadline = new Deadline("Submit Report", deadlineDate);
        System.out.println(deadline.getName());
        System.out.println("Is Complete: " + deadline.isComplete());
        deadline.complete();
        System.out.println("Is Complete after completing: " + deadline.isComplete());

        // Test the Meeting
        System.out.println("\nTesting Meeting:");
        Date meetingStart = new Date(System.currentTimeMillis() + 7200000); // 2 hours from now
        Date meetingEnd = new Date(meetingStart.getTime() + 3600000); // 1 hour duration
        Meeting meeting = new Meeting("Team Sync", meetingStart, meetingEnd, "Conference Room A");
        System.out.println(meeting.getName());
        System.out.println("Duration (minutes): " + meeting.getDuration());
        System.out.println("Is Complete: " + meeting.isComplete());
        meeting.complete();
        System.out.println("Is Complete after completing: " + meeting.isComplete());

        // Test the Reminder
        System.out.println("\nTesting Reminder:");
        Reminder reminder = new Reminder("Prepare Presentation", deadlineDate);
        System.out.println(reminder.getName());
        System.out.println("Is Complete: " + reminder.isComplete());
        reminder.complete();
        System.out.println("Is Complete after completing: " + reminder.isComplete());

        // Test EventListPanel
        System.out.println("\nAdding Default Events:");
        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);
        // Displaying events in GUI is visual and can't be easily demonstrated in console
    }

    // Method to add default events to EventListPanel
    public static void addDefaultEvents(EventListPanel eventListPanel) {
        Date now = new Date();
        Deadline deadline = new Deadline("Submit Assignment", new Date(now.getTime() + 86400000)); // 1 day from now
        Meeting meeting = new Meeting("Project Meeting", now, new Date(now.getTime() + 3600000), "Meeting Room B"); // 1 hour duration
        Reminder reminder = new Reminder("Doctor Appointment", new Date(now.getTime() + 3600000)); // 1 hour from now

        eventListPanel.addEvent(deadline);
        eventListPanel.addEvent(meeting);
        eventListPanel.addEvent(reminder);
    }
}
