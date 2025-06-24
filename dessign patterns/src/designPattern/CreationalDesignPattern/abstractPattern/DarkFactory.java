package designPattern.CreationalDesignPattern.abstractPattern;

// DarkFactory.java
public class DarkFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}
