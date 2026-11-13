# Usage (from week08-javafx-bindings):  .\run.ps1 BidirectionalDemo
# Classes: PersonDemo, ListenerDemo, UnidirectionalDemo, BidirectionalDemo, ReactiveFormApp
param([string]$Class = "ReactiveFormApp")
mvn clean javafx:run "-Djavafx.mainClass=com.example.$Class"
