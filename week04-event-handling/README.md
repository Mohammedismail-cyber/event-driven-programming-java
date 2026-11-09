# Week 4 - Making Swing Applications Interactive (Event Handling)

| Folder | What it contains | Main class(es) |
|---|---|---|
| activity1-actionlistener-basics | First interactive button | FirstButton |
| activity2-listener-techniques | 4 listener styles + getSource() | ListenerDemo, EventSourceDemo |
| activity3-item-and-document-listeners | Pizza order, char counter, live search | ItemListenerDemo, DocumentListenerDemo, LiveSearchDemo |
| activity4-counter-app | Counter with keyboard shortcuts (Up, Down, R) | CounterApp |
| activity5-interactive-form | Registration form with validation | RegistrationForm |
| activity6-complete-app | Reserved for your own extension | - |
| activity7-mouse-and-key | Mouse drawing and keyboard mover | DrawingApp, KeyboardMover |

## Run (PowerShell, JDK 11+)
    cd activity1-actionlistener-basics
    javac -encoding UTF-8 FirstButton.java
    java FirstButton

Or from this folder:  `.\run.ps1 activity4-counter-app CounterApp`
Check everything compiles:  `.\compile-all.ps1`

## Note on Activity 5
The guide builds the form with NetBeans drag-and-drop. `RegistrationForm.java`
is a hand-coded equivalent (same variable names and handlers) so it runs
anywhere. To practise Matisse, rebuild it in NetBeans and paste in the three
`...ActionPerformed` handlers.
