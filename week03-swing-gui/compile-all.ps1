# Compiles every activity to check for errors (no windows are opened)
Get-ChildItem -Directory -Filter "activity*" | ForEach-Object {
    Write-Host "Compiling $($_.Name) ..."
    Push-Location $_.FullName
    javac *.java
    Pop-Location
}
Write-Host "Done."
