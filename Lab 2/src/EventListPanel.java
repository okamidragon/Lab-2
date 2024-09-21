import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private final ArrayList<Event> events; // List of events to display
    private final JPanel displayPanel; // Panel to hold the EventPanels
    private JComboBox<String> sortDropDown; // For sorting events
    private JCheckBox filterCompleted; // To filter completed tasks

    public EventListPanel(ArrayList<Event> events) {
        this.events = events;
        setLayout(new BorderLayout());

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Sort dropdown
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date"});
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);

        // Filter checkbox
        filterCompleted = new JCheckBox("Hide Completed");
        filterCompleted.addActionListener(e -> updateEventList());
        controlPanel.add(filterCompleted);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> new AddEventModal(this).setVisible(true));
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);

        // Display panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addEvent(Event event) {
        events.add(event);
        updateEventList();
    }

    private void updateEventList() {
        displayPanel.removeAll();
        for (Event event : events) {
            if (!filterCompleted.isSelected() || !(event instanceof Completable && ((Completable) event).isComplete())) {
                EventPanel eventPanel = new EventPanel(event);
                displayPanel.add(eventPanel);
            }
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void sortEvents() {
        if (sortDropDown.getSelectedIndex() == 0) {
            Collections.sort(events, Comparator.comparing(Event::getName));
        } else {
            Collections.sort(events, Comparator.comparing(Event::getDateTime));
        }
        updateEventList();
    }
}
