import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create EventCollection (Subject)
        EventCollection eventCollection = new EventCollection();

        // Create EventListPanel (Observer)
        EventListPanel eventListPanel = new EventListPanel(eventCollection);
        frame.add(eventListPanel);

        // Add some default events to the collection
        Event deadline = new Deadline("Sample Deadline", LocalDateTime.now().plusDays(1));
        Event meeting = new Meeting("Sample Meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3), "Location A");
        eventCollection.addEvent(deadline);
        eventCollection.addEvent(meeting);

        // Create the modal and pass the same event collection
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> new AddEventModal(eventListPanel, eventCollection).setVisible(true));
        frame.add(addEventButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
