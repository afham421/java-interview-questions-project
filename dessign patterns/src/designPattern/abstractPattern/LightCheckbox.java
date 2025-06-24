package designPattern.abstractPattern;

// LightCheckbox.java
public class LightCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering Light Checkbox");
    }
}
