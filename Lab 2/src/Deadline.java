import java.util.Date;

public class Deadline extends Event implements Completable {
    private boolean complete = false;

    // Constructor
    public Deadline(String name, Date dateTime) {
        super(name, dateTime);
    }

    @Override
    public String getName() {
        return "Deadline: " + getBaseName() + " by " + getDateTime();
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
}
