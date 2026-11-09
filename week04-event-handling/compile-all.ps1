# Compiles every activity to check for errors (no windows are opened)
Get-ChildItem -Directory -Filter "activity*" | ForEach-Object {
    if (Get-ChildItem $_.FullName -Filter *.java) {
        Write-Host "Compiling $($_.Name) ..."
        Push-Location $_.FullName
        javac -encoding UTF-8 *.java
        Pop-Location
    }
}
Write-Host "Done."
