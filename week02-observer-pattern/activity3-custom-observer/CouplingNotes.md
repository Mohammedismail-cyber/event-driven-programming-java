# Activity 3 - Coupling Notes

(Draft answers - rewrite in your own words.)

## Step 2: The coupling problem
1. **Adding a third display:** yes, I must open `DataModel.setValue()` and add
   another `new ...Display()` line. The model changes every time the UI changes.
2. **Deleting or renaming `LabelDisplay`:** `DataModel` no longer compiles,
   because it names the class directly.
3. **Testing `DataModel` alone:** hard. It always creates and calls the real
   display classes, so I cannot test it without them.

## Comparison

| Aspect | Tightly Coupled | Observer Pattern |
|---|---|---|
| Adding a new display | Modify `DataModel` | Just register a new observer (e.g. `TextLogger`) |
| Testing `DataModel` in isolation | Hard - it needs display classes | Easy - register a test observer or none |
| Removing a display | Modify `DataModel` | Just don't register it (or call `removeObserver`) |
| Who knows about whom | Model knows every display | Model only knows the `DataObserver` interface |
