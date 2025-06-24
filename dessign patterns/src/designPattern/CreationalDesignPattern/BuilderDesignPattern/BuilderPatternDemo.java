package designPattern.CreationalDesignPattern.BuilderDesignPattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        User user1 = new User.UserBuilder("Ali", 30)
                .email("ali@example.com")
                .address("Islamabad")
                .build();

        User user2 = new User.UserBuilder("Sara", 25)
                .build();

        System.out.println(user1);
        System.out.println(user2);
    }
}
