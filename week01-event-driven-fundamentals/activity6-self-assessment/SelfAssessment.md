# Activity 6 - Final Self-Assessment (Week 1)

(Draft answers. Rewrite them in your own words and score yourself honestly.)

## Learning Outcome 1: Define EDP components
1. **Event:** something that happens that the program can react to. E.g. a button click, a timer expiring.
2. **Event source:** the object that generates the event. E.g. a JButton, a motion sensor.
3. **Event listener:** the object that waits for and receives events. E.g. ActionListener, KeyListener.
4. **Event handler:** the code that runs when the event occurs. E.g. the body of `actionPerformed()`, a lambda inside `addActionListener`.
5. **Diagram:**
```
 Event Source  --- generates --->  Event  --- delivered to --->  Listener  --- runs --->  Handler
 (JButton)                        (click)                    (ActionListener)          (actionPerformed)
```

## Learning Outcome 2: Compare paradigms
6. Procedural code runs in a fixed order decided by the program; event-driven code waits and reacts to events chosen by the user or system.
7. The user can interact in any order and recover from mistakes, and the GUI stays responsive.
8. For a simple script or batch job that runs once from start to finish (reading a file, converting data, a one-off calculation).

## Learning Outcome 3: Event loop and queue
9. The event loop is a loop that runs for the life of the program, taking the next event from the queue and dispatching it to the handler.
10. The event queue is a first-in-first-out line that stores events until the loop is ready for them.
11. If a handler blocks the loop, no other events are processed: the screen freezes and the user cannot interact.
12. The EDT (Event Dispatch Thread) is the single thread where Swing runs its event loop and where all GUI creation and updates must happen.

## Learning Outcome 4: Real-world examples
13. ATM, microwave oven, traffic light, video game, elevator (also smartphones, web browsers, smart homes).
14. ATM: source = keypad, event = button pressed, listener = ATM software, handler = verify PIN.
15. Because users expect to interact freely and get instant responses, and because systems must react to many unpredictable inputs (touch, network, sensors, timers).

**My score: ___ / 15**

---

# One-page summary (my own words)

**Key definitions.** An event is something that happens; an event source creates
it; a listener waits for it; a handler is the code that responds. An event-driven
program does not run a fixed script: it waits and reacts.

**Event loop and queue.** Events go into a first-in-first-out queue. The event
loop takes them out one at a time and calls the right handler. In Swing this
loop runs on the Event Dispatch Thread (EDT). Never block it: long work belongs
on a background thread, and GUI updates go back to the EDT with
`SwingUtilities.invokeLater`.

**Paradigms.** Procedural: the program controls the flow, one path. Event-driven:
the user controls the flow, many paths. Functional (e.g. streams): apply
functions to data, no side effects.

**Code that helped.** `btn.addActionListener(e -> {...})` to register a handler,
and the two bug fixes: background thread for slow work, `invokeLater` for GUI
updates.

**Diagram.**
`User action -> Event source -> Event -> Queue -> Event loop -> Handler -> GUI update`

**Questions I still have.** How do events work when several threads create
events at once? How does this connect to the Observer pattern in Week 2?
