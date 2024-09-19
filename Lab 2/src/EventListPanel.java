import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    private final ArrayList<Event> events; // List of events to display
    private final JPanel displayPanel; // Panel to hold the event displays

    public EventListPanel(ArrayList<Event> events) {
        this.events = events;
        setLayout(new BorderLayout());
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            new AddEventModel(this).setVisible(true);
        });

        add(addEventButton, BorderLayout.SOUTH);
        updateEventList(); // Initial population of events
    }

    public void addEvent(Event event) {
        events.add(event);
        updateEventList(); // Refresh the displayed list of events
    }

    public void updateEventList() {
        displayPanel.removeAll(); // Clear previous event panels
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        displayPanel.revalidate();
        displayPanel.repaint(); // Refresh the display
    }
}
