import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class EventPlanner {
    private static final ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        EventListPanel eventListPanel = new EventListPanel(events);
        addDefaultEvents(eventListPanel); // Adding default events
        frame.add(eventListPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel eventListPanel) {
        // Creating sample events
        Date now = new Date();
        events.add(new Deadline("Project Deadline", now));
        events.add(new Meeting("Team Meeting", now, new Date(now.getTime() + 60 * 60 * 1000), "Conference Room"));

        eventListPanel.updateEventList(); // Refresh the list
    }
}
