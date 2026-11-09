# Usage (from week06-javafx):  .\run.ps1 DashboardApp
# Classes: App, LayoutDemo, FXMLApp, DashboardApp
param([string]$Class = "App")
mvn clean javafx:run "-Djavafx.mainClass=com.example.$Class"
