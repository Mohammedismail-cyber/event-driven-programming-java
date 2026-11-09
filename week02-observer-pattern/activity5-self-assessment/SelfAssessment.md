# Activity 5 - Final Self-Assessment (Week 2)

(Draft answers. Rewrite them in your own words, and score yourself honestly.)

## Learning Outcome 1: Design and implement the Observer pattern
1. **UML diagram** (text version):

```
  <<interface>> Subject              <<interface>> Observer
  + registerObserver(o)  ---------->  + update(...)
  + removeObserver(o)      1 .. *            ^
  + notifyObservers()                        |
          ^                      CurrentConditionsDisplay
          |                      StatisticsDisplay
     WeatherData                 ForecastDisplay
   (ConcreteSubject)             (ConcreteObservers)
   - observers : List<Observer>
   - temperature, humidity, pressure
```

2. **Subject interface from memory:**
```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```
3. **Why a list of observers?** The subject does not know in advance who is
   interested or how many. The list lets it call every interested observer
   when its state changes, and lets observers join or leave at runtime.

## Learning Outcome 2: Evaluate legacy java.util.Observer
4. Three problems: it is a class (single inheritance), notification order is
   unspecified, `setChanged()` is easy to forget (silent failure). Also: untyped
   `Object` argument, not thread-safe.
5. It was deprecated (Java 9) because the design cannot be fixed without
   breaking existing code, and better options exist.
6. **Pull model:** the subject only says "something changed"; each observer
   must call getters to fetch the data. It needs casts and `instanceof`
   checks and extra method calls, so it is more code and less type-safe.

## Learning Outcome 3: Create a custom event notification system
7. A Subject interface needs: register/add observer, remove observer, notify observers.
8. It loops over its list of observers and calls each one's update method.
9. **Avoiding memory leaks:** call `removeObserver()` when an observer is no
   longer needed (otherwise the subject's list keeps it alive). Weak
   references are another option.

## Learning Outcome 4: Compare mechanisms
10. Custom Observer, `java.util.Observer/Observable`, `PropertyChangeListener`, JavaFX properties/bindings.
11. JavaFX properties and bindings.
12. `java.util.Observer` / `Observable`.

**My score: ___ / 12**

---

# One-page summary of the Observer pattern (my own words)

The Observer pattern lets one object (the Subject) tell many other objects
(Observers) when its state changes, without knowing anything about them except
that they implement an interface. Observers register with the subject, and the
subject loops through its list and calls each one's update method. This is
loose coupling: I can add, remove or replace displays without touching the
subject. It is the foundation of event-driven programming: a button is a
subject, an ActionListener is an observer, and the click is the event. In Java
I can write my own interfaces (best for plain projects), use
PropertyChangeListener (JavaBeans/Swing) or JavaFX properties (JavaFX apps),
and I should avoid the deprecated java.util.Observable.

# Final reflection (5-7 sentences)

The Observer pattern is a design where a subject keeps a list of observers and
notifies them automatically when something changes. It matters because it
removes tight coupling, so parts of a program can change independently. It is
the core idea of event-driven programming: events are the notifications, and
listeners are the observers. In Week 1 I learned the vocabulary (event, source,
listener, handler), and this week I built the machinery behind it myself. For
my own plain Java project I would choose a custom observer interface because it
gives full control and no inheritance limits. For a JavaFX project I would use
properties and bindings, because they already give push notifications with
old and new values.
