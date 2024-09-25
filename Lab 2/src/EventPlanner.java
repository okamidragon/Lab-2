import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventPlanner {
    private static final ArrayList<Event> events = new ArrayList<>();

    // Creates the GUI
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        EventListPanel eventListPanel = new EventListPanel(events);
        frame.add(eventListPanel);

        // Add default events
        addDefaultEvents(eventListPanel);

        frame.setVisible(true);
    }

    // Adds sample or default events
    static void addDefaultEvents(EventListPanel eventListPanel) {
        try {
            Event deadline = new Deadline("Sample Deadline", LocalDateTime.now().plusDays(1)); // 1 day later
            Event meeting = new Meeting("Sample Meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3), "Location A"); // 1 hour long
            eventListPanel.addEvent(deadline);
            eventListPanel.addEvent(meeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
