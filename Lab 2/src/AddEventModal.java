import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(0, 1));
        setModal(true);

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField(); // Date input
        JTextField endDateField = new JTextField(); // End date for Meeting
        JTextField locationField = new JTextField(); // Location for Meeting

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

        JButton addButton = new JButton("Add Event");
        add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                LocalDateTime dateTime = LocalDateTime.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(endDateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String location = locationField.getText();
                String selectedType = (String) eventTypeDropdown.getSelectedItem();

                if ("Meeting".equals(selectedType) && location != null && !location.isEmpty()) {
                    Meeting meeting = new Meeting(name, dateTime, endDateTime, location);
                    eventListPanel.addEvent(meeting);
                } else {
                    Deadline deadline = new Deadline(name, dateTime);
                    eventListPanel.addEvent(deadline);
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
