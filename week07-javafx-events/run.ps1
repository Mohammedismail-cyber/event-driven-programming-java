# Usage (from week07-javafx-events):  .\run.ps1 DragDropDemo
# Classes: EventPropagationDemo, MouseKeyDemo, DragDropDemo, CustomEventDemo
param([string]$Class = "EventPropagationDemo")
mvn clean javafx:run "-Djavafx.mainClass=com.example.$Class"
