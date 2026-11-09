# Week 2 - The Observer Pattern

| Folder | What it contains | Main class |
|---|---|---|
| activity1-weather-station | Observer + Subject interfaces, WeatherData, 3 displays | WeatherStationMain |
| activity2-legacy-observer | Deprecated java.util.Observer/Observable + LegacyLimitations.md | LegacyMain |
| activity3-custom-observer | Tightly coupled system, then refactored to Observer | TightlyCoupledSystem, RefactoredMain |
| activity4-comparison | PropertyChangeListener demo + Comparison.md | PropertyChangeDemo |
| activity5-self-assessment | Self-assessment answers + summary | (written notes) |

## Run (PowerShell, JDK 11+)
    cd activity1-weather-station
    javac -encoding UTF-8 *.java
    java WeatherStationMain

Or from this folder:  `.\run.ps1 activity3-custom-observer RefactoredMain`
Check everything compiles:  `.\compile-all.ps1`

Activity 2 prints a note about "deprecated API". That is expected and is the
point of the activity.

## Notes on changes from the guide
- **StatisticsDisplay bug:** the guide starts the maximum at `Float.MIN_VALUE`,
  which is the smallest *positive* number, so it fails for negative temperatures.
  It now starts at `-Float.MAX_VALUE`.
- The degree sign is written as " C" so the output is not garbled on Windows consoles.
- `PropertyChangeDemo` also has `removePropertyChangeListener`.
- `Comparison.md` corrects the "JavaFX bindings are thread-safe" claim.
- The `.md` and notes files contain DRAFT answers. Rewrite them in your own words.
