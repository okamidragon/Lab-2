import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new GridLayout(0, 1));
        updateDisplay();

        // Add a button to complete the event if it implements Completable
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                updateDisplay();
            });
            add(completeButton);
        }
    }

    // Update the display to show event details
    private void updateDisplay() {
        removeAll();
        add(new JLabel("Name: " + event.getName()));
        add(new JLabel("Date: " + event.getDateTime()));

        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            add(new JLabel("End Time: " + meeting.getEndTime()));
            add(new JLabel("Location: " + meeting.getLocation()));
            add(new JLabel("Duration: " + meeting.getDuration() + " minutes"));
        }

        if (event instanceof Deadline) {
            Deadline deadline = (Deadline) event;
            add(new JLabel("Deadline by: " + deadline.getDateTime()));
        }

        if (event instanceof Reminder) {
            Reminder reminder = (Reminder) event;
            add(new JLabel("Reminder by: " + reminder.getDateTime()));
        }

        // Update background color based on urgency
        Urgency urgency = Urgency.getUrgency(event.getDateTime());
        switch (urgency) {
            case OVERDUE:
                setBackground(Color.RED);
                break;
            case IMMINENT:
                setBackground(Color.YELLOW);
                break;
            case DISTANT:
                setBackground(Color.GREEN);
                break;
        }
        revalidate();
        repaint();
    }
}
