# Activity 2 - Build the Event Loop

(Do the paper simulation first. These are draft answers: rewrite them in your own words.)

## Handlers for the 10 cards
| # | Event card | Handler |
|---|---|---|
| 1 | Button A Clicked | Button A's ActionListener shows a message |
| 2 | Button B Clicked | Button B's ActionListener runs its action |
| 3 | Text Field Updated: 'Hello' | DocumentListener updates the preview |
| 4 | Timer Fired | Timer handler refreshes the clock |
| 5 | Window Resized | Layout manager re-arranges components |
| 6 | Checkbox Selected | ItemListener updates the option |
| 7 | Menu Item Clicked | Menu item's ActionListener runs |
| 8 | Key Pressed: 'A' | KeyListener inserts the character |
| 9 | Mouse Moved | MouseMotionListener updates the coordinates |
| 10 | Button A Clicked | Button A's ActionListener runs again |

## Round 3 questions (blocked event loop)
1. **What happened to the other events?** They just waited in the queue. Nothing
   was lost, but none of them was processed until the slow handler finished.
2. **What would a user see?** A frozen application: buttons do not respond, the
   window cannot be moved or redrawn, and the system may show "Not Responding".
3. **How to fix it?** Do the long work on a background thread and send only the
   short result back to the event loop (in Swing, with `SwingUtilities.invokeLater`).

## Reflection
The event queue is a waiting line that stores events in the order they happen, so
none is lost while the program is busy. The event loop is a loop that runs for the
whole life of the program: it takes the next event from the queue, passes it to
the right handler, then goes back for the next one. Because events are handled one
at a time, a handler that takes a long time makes every other event wait. If the
loop is blocked, the screen cannot repaint and the user cannot click anything, so
the application looks frozen. That is why handlers must be short and long
operations belong on background threads. In Java Swing the event loop runs on the
Event Dispatch Thread (EDT), which is why we never sleep or do heavy work on it.
