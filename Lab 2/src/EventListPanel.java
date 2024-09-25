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
    private JCheckBox hideDeadlines; // To hide deadlines
    private JCheckBox hideMeetings; // To hide meetings

    public EventListPanel(ArrayList<Event> events) {
        this.events = events;
        setLayout(new BorderLayout());

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Sort dropdown
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Reverse Name", "Sort by Date"});
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);

        // Filter checkbox to hide completed tasks
        filterCompleted = new JCheckBox("Hide Completed");
        filterCompleted.addActionListener(e -> updateEventList());
        controlPanel.add(filterCompleted);

        // Checkbox to hide deadlines
        hideDeadlines = new JCheckBox("Hide Deadlines");
        hideDeadlines.addActionListener(e -> updateEventList());
        controlPanel.add(hideDeadlines);

        // Checkbox to hide meetings
        hideMeetings = new JCheckBox("Hide Meetings");
        hideMeetings.addActionListener(e -> updateEventList());
        controlPanel.add(hideMeetings);

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

    // Logic to hide events based off of filters
    private void updateEventList() {
        displayPanel.removeAll();
        for (Event event : events) {
            boolean shouldDisplay = true;

            // Check if we need to hide completed tasks
            if (filterCompleted.isSelected() && event instanceof Completable && ((Completable) event).isComplete()) {
                shouldDisplay = false;
            }

            // Check if we need to hide deadlines
            if (hideDeadlines.isSelected() && event instanceof Deadline) {
                shouldDisplay = false;
            }

            // Check if we need to hide meetings
            if (hideMeetings.isSelected() && event instanceof Meeting) {
                shouldDisplay = false;
            }

            // If the event passes all filters, add it to the display
            if (shouldDisplay) {
                EventPanel eventPanel = new EventPanel(event);
                displayPanel.add(eventPanel);
            }
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // Checkboxes to sort the panel
    private void sortEvents() {
        int selectedIndex = sortDropDown.getSelectedIndex();
        if (selectedIndex == 0) {
            Collections.sort(events, Comparator.comparing(Event::getName));
        } else if (selectedIndex == 1) {
            Collections.sort(events, Comparator.comparing(Event::getName).reversed());
        } else {
            Collections.sort(events, Comparator.comparing(Event::getDateTime));
        }
        updateEventList();
    }
}
