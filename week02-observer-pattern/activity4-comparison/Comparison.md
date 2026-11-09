# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
|---------|-----------------|--------------------|------------------------|-----------------|
| **Push or Pull?** | Both possible | Pull (by default) | Push (carries old + new value) | Push (listeners get old + new value) |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (extend the Observable class) | No | No |
| **Thread-safe?** | Depends on you | No | Listener list is managed safely by PropertyChangeSupport; your own data is not | No - JavaFX properties must be used on the FX Application Thread |
| **Auto-generates boilerplate?** | No | No | Yes (via PropertyChangeSupport) | Yes (properties, bindings, listeners built in) |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can be typed to send any data?** | Yes | No (`Object arg`) | Old/new values are `Object` | Yes (generic `ObservableValue<T>`) |
| **Recommended today?** | Yes | No | Only for legacy / Swing beans | Yes (in JavaFX) |

## Which One Should You Use?
- For a **plain Java project with custom needs** -> Custom Observer
- For a **JavaFX application** -> JavaFX Properties and Bindings
- For **legacy code using JavaBeans** -> PropertyChangeListener
- **Never** for new code -> java.util.Observer / Observable

## Corrections to the guide's table
The guide's table says JavaFX bindings are "thread-safe: Yes". That is not
accurate: JavaFX properties are NOT thread-safe, and UI-bound properties must
only be changed on the JavaFX Application Thread (use `Platform.runLater`).
This is the same rule you used with the EDT in Swing. The PropertyChangeListener
cell is also qualified, because `PropertyChangeSupport` protects its listener
list but not your own data.
