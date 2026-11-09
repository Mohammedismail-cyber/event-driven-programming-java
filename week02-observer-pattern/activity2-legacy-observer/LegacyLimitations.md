# Limitations of java.util.Observer and java.util.Observable

(Draft answers - rewrite them in your own words before you submit.)

## Limitation 1: Single Inheritance
Explain: Why is it a problem that Observable is a CLASS, not an INTERFACE?

Your answer: Java allows a class to extend only ONE parent class. If my subject
already extends another class (for example a `Sensor` base class), it cannot also
extend `Observable`. An interface (like our own `Subject`) has no such problem,
because a class can implement many interfaces. Also, `setChanged()` is protected,
so I cannot use an Observable by composition (as a helper object) either.

## Limitation 2: Cannot Change the Notification Order
Explain: In what order are observers notified? Can you control this?

Your answer: The order is unspecified in the documentation (in practice the
JDK notifies them in reverse order of registration). I cannot control or rely on
it. With my own `ArrayList`-based subject I know the order: the order of
registration, and I could add priorities if I needed them.

## Limitation 3: setChanged() is Confusing
Explain: Why must you call setChanged() before notifyObservers()?

Your answer: `notifyObservers()` only does something if the "changed" flag is
true. It was meant to let me group several updates and notify once, but in
practice it is easy to forget: if I forget `setChanged()`, observers are
silently NOT notified and there is no error. The flag is also protected, so
code outside the subclass cannot set it.

## Limitation 4: Pull Model by Default
Explain: How does the observer get the data? Is this efficient?

Your answer: `notifyObservers()` with no argument sends nothing, so each observer
receives only the Observable reference and must call getters (`getTemperature()`)
and cast it. That is extra code, needs `instanceof` checks and casts, and is not
type-safe. A push model (data in the update method, as in Activity 1) is simpler
and type-safe, although it sends data an observer may not need.

## Limitation 5: Deprecated Since Java 9
Explain: Why did Java deprecate these classes?

Your answer: The design has several flaws: Observable is a class (not an
interface), the notification order is unspecified, `setChanged()` is easy to get
wrong, data is untyped (`Object arg`), and the API is not serializable or
properly thread-safe. Because it could not be fixed without breaking existing
code, it was deprecated in favour of `java.beans.PropertyChangeListener`,
`java.util.concurrent.Flow` and writing your own observer interfaces.
