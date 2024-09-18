import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventListPanel extends JPanel {
    private List<Event> events;
    private JPanel controlPanel;
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterDisplay;
    private JButton addEventButton;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Initialize controlPanel and add components
        controlPanel = new JPanel();
        add(controlPanel, BorderLayout.NORTH);

        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date"});
        controlPanel.add(sortDropDown);

        filterDisplay = new JCheckBox("Filter Complete Events");
        controlPanel.add(filterDisplay);

        addEventButton = new JButton("Add Event");
        controlPanel.add(addEventButton);

        // Initialize displayPanel to show events
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add event listeners for sorting, filtering, and adding events
        sortDropDown.addActionListener(e -> sortEvents());
        filterDisplay.addActionListener(e -> filterEvents());
        addEventButton.addActionListener(e -> openAddEventDialog());
    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    private void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        revalidate();
        repaint();
    }

    private void sortEvents() {
        // Implement sorting logic here
        updateDisplay();
    }

    private void filterEvents() {
        // Implement filtering logic here
        updateDisplay();
    }

    private void openAddEventDialog() {
        new AddEventModel(this).setVisible(true);
    }
}
