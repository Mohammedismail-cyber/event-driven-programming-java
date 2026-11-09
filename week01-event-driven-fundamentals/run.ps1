# Usage (from week01-event-driven-fundamentals):
#   .\run.ps1 activity4-bug-fix-challenge Bug1_BlockingEDT
#   .\run.ps1 activity3-procedural-vs-event-driven\eventdriven EventDrivenCalculator
param([string]$Folder, [string]$Class)
if (-not $Folder -or -not $Class) {
    Write-Host "Usage: .\run.ps1 <folder> <MainClass>"
    exit 1
}
Push-Location $Folder
javac -encoding UTF-8 *.java
if ($LASTEXITCODE -eq 0) { java $Class }
Pop-Location
