import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventModel extends JDialog {
    private EventListPanel eventListPanel;

    public AddEventModel(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(0, 1));
        setModal(true);
        setLocationRelativeTo(null); // Center the dialog

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField(); // Date input
        JTextField endDateField = new JTextField(); // End date for Meeting
        JTextField locationField = new JTextField(); // Location for Meeting

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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String startDateStr = dateField.getText().trim();
                String endDateStr = endDateField.getText().trim();
                String location = locationField.getText().trim();

                try {
                    Date startDateTime = parseDate(startDateStr);
                    Date endDateTime = !endDateStr.isEmpty() ? parseDate(endDateStr) : null;

                    if (startDateTime == null) {
                        showError("Invalid start date format. Please use yyyy-MM-dd HH:mm.");
                        return;
                    }

                    if (endDateTime != null && (location.isEmpty() || endDateTime.before(startDateTime))) {
                        showError("Invalid end date or location. Please ensure location is provided and end date is after start date.");
                        return;
                    }

                    if (!location.isEmpty() && endDateTime != null) {
                        Meeting meeting = new Meeting(name, startDateTime, endDateTime, location);
                        eventListPanel.addEvent(meeting);
                    } else {
                        Deadline deadline = new Deadline(name, startDateTime);
                        eventListPanel.addEvent(deadline);
                    }

                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showError("An error occurred while adding the event.");
                }
            }
        });
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
