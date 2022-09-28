package Zadanie1;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ObjectContainer<Person> peopleFromWarsaw = new ObjectContainer<>(p -> p.getCity().equals("Warsaw"));
        peopleFromWarsaw.add(new Person("Jan", "Warsaw", 30));
        peopleFromWarsaw.add(new Person("Janek", "Warsaw", 60));
        peopleFromWarsaw.add(new Person("Weronika","Warsaw", 20));
//        peopleFromWarsaw.add(new Person("Waldek", "Monaco", 34));

        peopleFromWarsaw.print();
        System.out.println("\n");

        peopleFromWarsaw.removeIf(p -> p.getAge() > 50);


        peopleFromWarsaw.print();
        System.out.println("\n");

        List<Person> females = peopleFromWarsaw.getWithFilter(p -> p.getName().endsWith("a"));
        females.forEach(System.out::println);
        System.out.println("\n");

        peopleFromWarsaw.storeToFile("youngPeopleFromWarsaw.txt", p -> p.getAge() < 30, p -> p.getName()+";"+p.getAge()+";"+p.getCity());

    }
}
