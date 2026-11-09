# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes when the button is clicked (cannot move or click anything for 5 seconds)
- **Cause:** `Thread.sleep(5000)` ran inside the button handler, which runs on the EDT, so the event loop was blocked
- **Fix:** Run the long operation in a background thread, update the UI on the EDT
- **Code Change:** Moved the sleep into `new Thread(() -> {...}).start()`; after it finishes, `SwingUtilities.invokeLater(...)` sets "Done!" and re-enables the button. Also set "Working..." and disabled the button while waiting.

## Bug 2: Missing Listener
- **Symptom:** Button click does nothing
- **Cause:** No ActionListener registered on the button
- **Fix:** Added an ActionListener to the increment button
- **Code Change:** Added `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`

## Bug 3: Wrong Thread
- **Symptom:** Erratic behavior, potential crashes or stale display
- **Cause:** `timeLabel.setText()` was called from a background thread, but Swing is not thread-safe
- **Fix:** Used `SwingUtilities.invokeLater()` for GUI updates
- **Code Change:** Wrapped the label update in `SwingUtilities.invokeLater(() -> timeLabel.setText(text))`. Also made `running` volatile, made the thread a daemon, ignored a second Start click, and added a Stop button.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time (buttons are gone from the screen but still held in memory)
- **Cause:** References were kept in the `createdButtons` list after removal from the UI
- **Fix:** Clear the list when removing buttons
- **Code Change:** Added `createdButtons.clear();` in the "Remove All" handler.
