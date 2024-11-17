import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;
    private EventCollection eventCollection;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        this.eventCollection = new EventCollection();  // Manage the collection of events

        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(0, 1));
        setModal(true);

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField locationField = new JTextField();

        String[] eventTypes = {"Deadline", "Meeting"};
        JComboBox<String> eventTypeDropdown = new JComboBox<>(eventTypes);

        add(new JLabel("Event Type:"));
        add(eventTypeDropdown);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Start Date (yyyy-MM-dd HH:mm):"));
        add(dateField);
        add(new JLabel("End Date (for Meeting, yyyy-MM-dd HH:mm):"));
        add(endDateField);
        add(new JLabel("Location (for Meeting):"));
        add(locationField);

        locationField.setEnabled(false);
        eventTypeDropdown.addActionListener(e -> {
            String selectedType = (String) eventTypeDropdown.getSelectedItem();
            if ("Meeting".equals(selectedType)) {
                locationField.setEnabled(true);
            } else {
                locationField.setEnabled(false);
                locationField.setText("");
            }
        });

        JButton addButton = new JButton("Add Event");
        add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                LocalDateTime dateTime = LocalDateTime.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(endDateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String location = locationField.getText();
                String selectedType = (String) eventTypeDropdown.getSelectedItem();

                if ("Meeting".equals(selectedType)) {
                    if (location == null || location.isEmpty()) {
                        JOptionPane.showMessageDialog(AddEventModal.this,
                                "Location is required for Meetings.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Meeting meeting = new Meeting(name, dateTime, endDateTime, location);
                    eventCollection.addEvent(meeting);
                } else if ("Deadline".equals(selectedType)) {
                    Deadline deadline = new Deadline(name, dateTime);
                    eventCollection.addEvent(deadline);
                }

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddEventModal.this,
                        "An error occurred while adding the event. Please check the input format.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
