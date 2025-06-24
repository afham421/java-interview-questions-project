package designPattern.PrototypePattern;

public class PrototypePatternDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Car originalCar = new Car("Honda Civic", 220);
        Car clonedCar = originalCar.clone();

        System.out.println("Original: " + originalCar);
        System.out.println("Cloned  : " + clonedCar);
        System.out.println("Same object? " + (originalCar == clonedCar)); // false
    }
}
