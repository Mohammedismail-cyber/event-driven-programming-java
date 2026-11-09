# Usage (from week10-university-system):  .\run.ps1 -Password "your_mysql_password"
param([string]$Password = "")
if ($Password -ne "") { $env:UNI_DB_PASSWORD = $Password }
mvn clean javafx:run
