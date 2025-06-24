package designPattern.BehaviouralDesignPattern.ObserverDesignPattern;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer phone1 = new PhoneDisplay("Phone 1");
        Observer phone2 = new PhoneDisplay("Phone 2");

        station.attach(phone1);
        station.attach(phone2);

        station.setWeather("Sunny");
        station.setWeather("Rainy");

        station.detach(phone1);
        station.setWeather("Cloudy");
    }
}

