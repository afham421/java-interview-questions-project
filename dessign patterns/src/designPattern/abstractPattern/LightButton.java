package designPattern.abstractPattern;

// LightButton.java
public class LightButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Light Button");
    }
}
