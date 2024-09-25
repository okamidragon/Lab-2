import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        completeButton = new JButton("Complete");
        completeButton.setVisible(event instanceof Completable);

        // Set up the display
        updateDisplay();
        updateUrgency();

        completeButton.addActionListener(e -> {
            if (event instanceof Completable) {
                ((Completable) event).complete();
                updateDisplay();
            }
        });

        add(completeButton, BorderLayout.SOUTH);
    }

    // Logic to flag an event as completed
    private void updateDisplay() {
        String displayText = String.format("Name: %s \nDate: %s \nComplete: %s",
                event.getName(), event.getDateTime(), (event instanceof Completable && ((Completable) event).isComplete()));
        removeAll();
        add(new JLabel(displayText), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void updateUrgency() {
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
    }
}
