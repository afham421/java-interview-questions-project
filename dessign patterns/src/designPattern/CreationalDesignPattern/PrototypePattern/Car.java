package designPattern.CreationalDesignPattern.PrototypePattern;

public class Car implements Cloneable {
    private String model;
    private int topSpeed;

    public Car(String model, int topSpeed) {
        this.model = model;
        this.topSpeed = topSpeed;
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
//        return new Car(model, topSpeed); //Deep copy
        return (Car) super.clone(); // shallow copy
    }


    @Override
    public String toString() {
        return "Car{model='" + model + "', topSpeed=" + topSpeed + "}";
    }
}
