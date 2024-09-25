import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete;

    // Data for deadline
    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime);
        this.complete = false;
    }

    // Name of deadline
    public String getName() {
        return name;
    }

    // Completes the deadline
    public void complete() {
        complete = true;
    }

    // Checks if completed
    public boolean isComplete() {
        return complete;
    }
}
