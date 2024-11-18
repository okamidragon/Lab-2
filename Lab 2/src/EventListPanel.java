import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class EventListPanel extends JPanel implements Observer {
    private EventCollection eventCollection;
    private JPanel displayPanel;

    // Filters and sorting flags
    private boolean hideCompleted = false;
    private boolean hideDeadlines = false;
    private boolean sortByName = false;
    private boolean sortByDate = false;
    private boolean sortAscending = true;

    public EventListPanel(EventCollection eventCollection) {
        this.eventCollection = eventCollection;
        eventCollection.addObserver(this);

        setLayout(new BorderLayout());

        // Control panel for sorting/filtering
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Add Event Button
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            // Open the modal with the correct event collection
            new AddEventModal(this, eventCollection).setVisible(true);
        });

        // Sorting by Name or Date
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date"});
        sortComboBox.addActionListener(e -> {
            sortByName = sortComboBox.getSelectedIndex() == 0;
            sortByDate = !sortByName;
            updateEventList();
        });
        controlPanel.add(sortComboBox);

        // Ascending/Descending Sorting
        JCheckBox ascendingCheckBox = new JCheckBox("Ascending", true);
        ascendingCheckBox.addActionListener(e -> {
            sortAscending = ascendingCheckBox.isSelected();
            updateEventList();
        });
        controlPanel.add(ascendingCheckBox);

        // Hide Completed Events Checkbox
        JCheckBox hideCompletedCheckBox = new JCheckBox("Hide Completed Events");
        hideCompletedCheckBox.addActionListener(e -> {
            hideCompleted = hideCompletedCheckBox.isSelected();
            updateEventList();
        });
        controlPanel.add(hideCompletedCheckBox);

        // Hide Deadlines Checkbox
        JCheckBox hideDeadlinesCheckBox = new JCheckBox("Hide Deadlines");
        hideDeadlinesCheckBox.addActionListener(e -> {
            hideDeadlines = hideDeadlinesCheckBox.isSelected();
            updateEventList();
        });
        controlPanel.add(hideDeadlinesCheckBox);

        add(controlPanel, BorderLayout.NORTH);

        // Display panel for showing events
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the list with current events
        updateEventList();
    }

    // Update the display when notified
    @Override
    public void update() {
        updateEventList();
    }

    private void updateEventList() {
        displayPanel.removeAll();
        List<Event> events = eventCollection.getEvents();

        // Apply filters
        if (hideCompleted) {
            events = events.stream().filter(event -> !(event instanceof Completable) || !((Completable) event).isComplete()).collect(Collectors.toList());
        }

        if (hideDeadlines) {
            events = events.stream().filter(event -> !(event instanceof Deadline)).collect(Collectors.toList());
        }

        // Sort events
        if (sortByName) {
            events.sort(Comparator.comparing(Event::getName));
        } else if (sortByDate) {
            events.sort(Comparator.comparing(Event::getDateTime));
        }

        // Apply ascending/descending sorting
        if (!sortAscending) {
            Collections.reverse(events);
        }

        // Display events
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }
}
