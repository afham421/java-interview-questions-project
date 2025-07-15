package JavaQuestions.DesignPattern.BehaviouralDesignPattern.ObserverDesignPattern;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
