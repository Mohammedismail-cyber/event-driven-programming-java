# Usage (from week03-swing-gui):  .\run.ps1 activity1-first-window FirstWindow
param([string]$Folder, [string]$Class)
if (-not $Folder -or -not $Class) {
    Write-Host "Usage: .\run.ps1 <activity-folder> <MainClass>"
    Write-Host "Example: .\run.ps1 activity3-layout-managers GridLayoutDemo"
    exit 1
}
Push-Location $Folder
javac *.java
if ($LASTEXITCODE -eq 0) { java $Class }
Pop-Location
