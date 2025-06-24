package designPattern.FactoryPattern;

// FactoryPatternDemo.java
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();  // Output: Drawing Circle

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();  // Output: Drawing Rectangle

        Shape shape3 = shapeFactory.getShape("TRIANGLE");
        if (shape3 == null) {
            System.out.println("Unknown shape requested.");
        }
    }
}

