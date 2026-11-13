package com.example;

/**
 * Activity 2 - InvalidationListener vs ChangeListener.
 *
 * A plain console program: properties do not need a JavaFX window, so there is
 * no Application.launch() here (the guide's version never opened a window and
 * so never exited by itself).
 */
public class ListenerDemo {

    public static void main(String[] args) {
        Person person = new Person();

        // 1. InvalidationListener: "something changed" (no values)
        person.nameProperty().addListener(observable -> {
            System.out.println("1. [InvalidationListener] Name was changed!");
        });

        // 2. ChangeListener: gives the old and the new value
        person.nameProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("2. [ChangeListener] Name changed from '"
                    + oldValue + "' to '" + newValue + "'");
        });

        System.out.println("--- Setting name to Alice ---");
        person.setName("Alice");

        System.out.println("\n--- Setting name to Bob ---");
        person.setName("Bob");

        System.out.println("\n--- Setting name to Bob (same value) ---");
        person.setName("Bob"); // neither listener fires
    }
}
