# Week 1 - Event-Driven Programming Fundamentals

| Folder | What it contains |
|---|---|
| activity1-spot-the-components | `ComponentsTable.md` - 8 real-world systems (source, event, listener, handler) |
| activity2-build-event-loop | `EventLoopReflection.md` - card handlers, blocked-loop answers, reflection |
| activity3-procedural-vs-event-driven | `procedural/ProceduralCalculator.java`, `eventdriven/EventDrivenCalculator.java`, `Comparison.md` |
| activity4-bug-fix-challenge | `Bug1_BlockingEDT` .. `Bug4_MemoryLeak` (fixed) + `BugFixes.md` |
| activity5-design-your-own-system | `SystemDesign.md` + `SmartHomePrototype.java` |
| activity6-self-assessment | `SelfAssessment.md` - 15 answers + one-page summary |

## Run (PowerShell, JDK 11+)
    cd activity3-procedural-vs-event-driven\procedural
    javac ProceduralCalculator.java
    java ProceduralCalculator

Or from this folder:  `.\run.ps1 activity4-bug-fix-challenge Bug1_BlockingEDT`
Check everything compiles:  `.\compile-all.ps1`

## Notes on changes from the guide
- **EventDrivenCalculator:** the guide's version crashes (NumberFormatException) if you
  press an operator or = after "Error" is shown. It now reads the display safely.
- **SmartHomePrototype:** in the guide the screen stays red after an alarm
  forever; disarming now clears it.
- **Bug3 fix:** besides `invokeLater`, the flag is `volatile`, a second Start click
  is ignored, the thread is a daemon, and there is a Stop button.
- The `.md` files hold DRAFT answers. Watch the videos, do the paper simulation,
  run both calculators, and rewrite the answers in your own words.
