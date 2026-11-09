# Activity 4 - My Observations

## FreezeDemo (BAD)
- Clicked "Freeze the UI": the window could not be moved or resized for 5 seconds.
- The label only changed to "Done!" after the sleep ended (the "Working..." text never painted).
- Reason: Thread.sleep(5000) ran on AWT-EventQueue-0 (the EDT), blocking the event loop.

## FixedDemo (GOOD)
- Clicked "Do Long Operation": label showed "Working..." and the button was disabled.
- The window stayed movable and resizable the whole time.
- Reason: the sleep ran on a background thread (Thread-0); the GUI update was
  sent back to the EDT with SwingUtilities.invokeLater().

## Two Golden Rules
1. All GUI creation and updates must happen on the EDT.
2. Never perform long operations on the EDT.
