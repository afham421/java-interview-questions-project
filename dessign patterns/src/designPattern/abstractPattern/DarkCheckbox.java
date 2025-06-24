package designPattern.abstractPattern;

// DarkCheckbox.java
public class DarkCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering Dark Checkbox");
    }
}
