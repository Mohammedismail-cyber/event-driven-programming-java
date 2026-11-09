# Week 3 - Building GUI Applications with Swing

Project for the Week 3 self-paced guide (event-driven-programming-java).

## Structure
| Folder | What it contains | Main class(es) |
|---|---|---|
| activity1-first-window | First JFrame, EDT proof | FirstWindow |
| activity2-basic-components | 8 component types | ComponentShowcase |
| activity3-layout-managers | Layout demos | FlowLayoutDemo, BorderLayoutDemo, GridLayoutDemo, CombinedLayoutDemo, BoxLayoutDemo |
| activity4-edt-rule | Frozen vs responsive UI | FreezeDemo, FixedDemo (+ NOTES.md) |
| activity5-complete-mockup | Student Portal mockup | StudentPortal |

## Run (PowerShell, JDK 11+)
    cd activity1-first-window
    javac FirstWindow.java
    java FirstWindow

Or from this folder:  `.\run.ps1 activity5-complete-mockup StudentPortal`
Check everything compiles:  `.\compile-all.ps1`

## Open in NetBeans
File > New Project > Java with Existing Sources, choose an activity folder as the
source package folder. (Or open a .java file directly; each one has its own main.)

## Push to GitHub
    cd C:\Users\RUICT\Desktop\projects\event-driven-programming-java
    git add .
    git commit -m "Complete Week 3: Swing GUI Basics"
    git push origin main
