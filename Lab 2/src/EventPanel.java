import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private final Event event;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new GridLayout(1, 1)); // Adjust layout as needed
        JLabel nameLabel = new JLabel(event.getName());
        JLabel dateLabel = new JLabel(event.getDateTime().toString());
        add(nameLabel);
        add(dateLabel);
    }
}
