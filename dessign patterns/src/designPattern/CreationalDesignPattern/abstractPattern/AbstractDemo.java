package designPattern.CreationalDesignPattern.abstractPattern;

// Application.java
public class AbstractDemo {
    private final Button button;
    private final Checkbox checkbox;

    public AbstractDemo(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        // Dynamically choose factory
        GUIFactory factory = new LightFactory(); // try new DarkFactory() to switch theme
        AbstractDemo app = new AbstractDemo(factory);
        app.renderUI();
    }
}
