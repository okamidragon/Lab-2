// Interface used to complete and determine if an Event completed.
interface Completable {
    void complete();
    boolean isComplete();
}
