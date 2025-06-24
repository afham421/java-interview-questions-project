package designPattern.BehaviouralDesignPattern.ObserverDesignPattern;

public class PhoneDisplay implements Observer {
    private String name;

    public PhoneDisplay(String name) {
        this.name = name;
    }

    public void update(String weather) {
        System.out.println(name + " received weather update: " + weather);
    }
}
