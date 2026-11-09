# Usage (from week02-observer-pattern):  .\run.ps1 activity1-weather-station WeatherStationMain
param([string]$Folder, [string]$Class)
if (-not $Folder -or -not $Class) {
    Write-Host "Usage: .\run.ps1 <activity-folder> <MainClass>"
    exit 1
}
Push-Location $Folder
javac -encoding UTF-8 *.java
if ($LASTEXITCODE -eq 0) { java $Class }
Pop-Location
