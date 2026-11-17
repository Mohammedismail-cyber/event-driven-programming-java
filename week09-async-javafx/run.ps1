# Usage (from week09-async-javafx):  .\run.ps1 FreezeDemo
# Classes: FreezeDemo, TaskDemo, ProgressDemo, ServiceDemo
param([string]$Class = "ServiceDemo")
mvn clean javafx:run "-Djavafx.mainClass=com.example.$Class"
