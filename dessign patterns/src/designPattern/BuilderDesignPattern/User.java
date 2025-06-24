package designPattern.BuilderDesignPattern;
public class User {
    private final String name;
    private final int age;
    private final String email;
    private final String address;

    // Private constructor
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }

    // Builder static inner class
    public static class UserBuilder {
        private final String name;
        private final int age;
        private String email;
        private String address;

        public UserBuilder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age +
                ", email='" + email + "', address='" + address + "'}";
    }
}
