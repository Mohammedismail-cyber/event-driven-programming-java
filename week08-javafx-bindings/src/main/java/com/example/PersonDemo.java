package com.example;

/**
 * Activity 1 (extra) - shows why the property getters matter.
 * Plain console program: properties work without opening a window.
 */
public class PersonDemo {

    public static void main(String[] args) {
        Person person = new Person();

        // The property getter lets us WATCH the model
        person.nameProperty().addListener((obs, oldValue, newValue) ->
                System.out.println("name: '" + oldValue + "' -> '" + newValue + "'"));
        person.ageProperty().addListener((obs, oldValue, newValue) ->
                System.out.println("age: " + oldValue + " -> " + newValue));

        person.setName("Alice");
        person.setAge(20);
        person.setAge(21);

        System.out.println("Final: " + person.getName() + ", " + person.getAge());
    }
}
