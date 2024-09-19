import java.util.Date;

public class Deadline extends Event implements Completable {
    private boolean complete = false;

    public Deadline(String name, Date dateTime) {
        super(name, dateTime);
    }

    @Override
    public String getName() {
        return name; // Return the name of the deadline
    }

    @Override
    public void complete() {
        this.complete = true; // Set completion status
    }

    @Override
    public boolean isComplete() {
        return this.complete; // Return completion status
    }
}
