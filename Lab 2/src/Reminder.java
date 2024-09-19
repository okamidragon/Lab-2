/*import java.util.Date;

public class Reminder extends Event implements Completable {
    private boolean complete = false;

    // Constructor
    public Reminder(String name, Date dateTime) {
        super(name, dateTime);
    }

    @Override
    public String getName() {
        // Provide a concrete implementation
        return "Reminder: " + super.getName() + " at " + getDateTime();
    }

    // Implement Completable interface methods
    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return this.complete;
    }

    @Override
    public String toString() {
        return getName() + " | Complete: " + isComplete();
    }
}*/
