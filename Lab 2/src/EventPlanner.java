import javax.swing.*;

public class EventPlanner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            EventListPanel eventListPanel = new EventListPanel();
            frame.add(eventListPanel);

            // Optionally add some default events
            EventTester.addDefaultEvents(eventListPanel);

            frame.setVisible(true);
        });
    }
}
