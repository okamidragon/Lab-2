import java.util.ArrayList;
import java.util.List;

public class EventCollection implements Subject {
    private List<Event> events = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addEvent(Event event) {
        events.add(event);
        notifyObservers();  // Notify observers when the list is updated
    }

    public List<Event> getEvents() {
        return events;
    }
}
