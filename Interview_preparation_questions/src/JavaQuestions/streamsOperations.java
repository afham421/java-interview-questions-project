package JavaQuestions;

import java.util.*;
import java.util.stream.Collectors;

public class streamsOperations {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User("Alice", "25", "USA"));
        users.add(new User("Bob", "30", "Canada"));
        users.add(new User("Charlie", "42", "France"));
        users.add(new User("David", "18", "UK"));
        users.add(new User("Emily", "55", "UK"));
        users.add(new User("Fiona", "22", "Australia"));
        users.add(new User("George", "38", "India"));
        users.add(new User("Helen", "19", "China"));
        users.add(new User("Isaac", "61", "Brazil"));
        users.add(new User("Jasmine", "27", "India"));

        Map<String, Integer> maxAgeByCountry = users.stream()
                .collect(Collectors.toMap(
                        User::getCountry,                                 // key = country
                        u -> Integer.parseInt(u.getAge()),                // value = age
                        Integer::max                                       // merge function = take max
                ));
        System.out.println(maxAgeByCountry);
//        maxAgeByCountry.forEach((country, age) ->
//                System.out.println("Country: " + country + ", Max Age: " + age)
//        );

        // 2. Get all users from USA
        System.out.println("\n=== Users from USA ===");
        users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase("UK"))
                .forEach(System.out::println);

        //3. get max age of country user
        System.out.println("\n=== Max Age User from UK ===");
        users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase("UK"))
                .max(Comparator.comparingInt(u -> Integer.parseInt(u.getAge())))
                .ifPresent(System.out::println);

        int maxAge = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase("UK"))
                .mapToInt(u -> Integer.parseInt(u.getAge()))
                .max()
                .orElse(0);

        System.out.println("Max Age in UK = " + maxAge);

// 4. only number then max number
        List<Integer> numbers = Arrays.asList(10, 25, 7, 42, 18);

// get max number
        int max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        System.out.println("Max = " + max);

         max = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();

        System.out.println("Max = " + max);

        // 3. Count of users per country
        System.out.println("\n=== User Count By Country ===");
        Map<String, Long> countByCountry = users.stream()
                .collect(Collectors.groupingBy(User::getCountry, Collectors.counting()));
        System.out.println(countByCountry);

        // 6. Group users by country
        System.out.println("\n=== Grouped Users By Country ===");
        Map<String, List<User>> usersByCountry = users.stream()
                .collect(Collectors.groupingBy(User::getCountry));
        System.out.println(usersByCountry);

        // 7. Average age per country
        System.out.println("\n=== Average Age By Country ===");
        Map<String, Double> avgAgeByCountry = users.stream()
                .collect(Collectors.groupingBy(
                        User::getCountry,
                        Collectors.averagingInt(u -> Integer.parseInt(u.getAge()))
                ));

        System.out.println(avgAgeByCountry);

//8. Users sorted by Age
        System.out.println("\n=== Users sorted by Age (Ascending) ===");
        users.stream()
                .sorted(Comparator.comparingInt(u -> Integer.parseInt(u.getAge())))
                .forEach(System.out::print);

        System.out.println("\n=== Users sorted by Age (Descending) ===");
        users.stream()
                .sorted(Comparator.comparingInt((User u) -> Integer.parseInt(u.getAge())).reversed())
                .forEach(System.out::print);


        // 10. Get names only of Indian users
        System.out.println("\n=== Names of Indian Users ===");
        List<String> indianNames = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase("India"))
                .map(User::getName)
                .collect(Collectors.toList());
        System.out.println(indianNames);


        // 11 .    filter > for filter and map > modify

        List<String> names = List.of("Ali", "Sara", "John");

        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);


    }
}

class User {
    private String name;
    private String age;
    private String country;

    public User(String name, String age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() { return name; }
    public String getAge() { return age; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return name + " (" + age + ", " + country + ")";
    }
}