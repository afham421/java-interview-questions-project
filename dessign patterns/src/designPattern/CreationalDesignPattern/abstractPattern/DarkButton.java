package designPattern.CreationalDesignPattern.abstractPattern;

// DarkButton.java
public class DarkButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Dark Button");
    }
}
