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

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField(); // Date input
        JTextField endDateField = new JTextField(); // End date for Meeting
        JTextField locationField = new JTextField(); // Location for Meeting

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Date (yyyy-MM-dd HH:mm):"));
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
                try {
                    String name = nameField.getText();
                    Date dateTime = parseDate(dateField.getText());
                    Date endDateTime = parseDate(endDateField.getText());
                    String location = locationField.getText();

                    if (dateTime == null) {
                        JOptionPane.showMessageDialog(AddEventModel.this,
                                "Invalid date format. Please use yyyy-MM-dd HH:mm.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (endDateTime != null && location != null && !location.isEmpty()) {
                        Meeting meeting = new Meeting(name, dateTime, endDateTime, location);
                        eventListPanel.addEvent(meeting);
                    } else {
                        Deadline deadline = new Deadline(name, dateTime);
                        eventListPanel.addEvent(deadline);
                    }

                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddEventModel.this,
                            "An error occurred while adding the event.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
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
}
