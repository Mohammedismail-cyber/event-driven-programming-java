# Compiles every activity that has Java files (no programs are run)
Get-ChildItem -Directory -Filter "activity*" | ForEach-Object {
    if (Get-ChildItem $_.FullName -Filter *.java) {
        Write-Host "Compiling $($_.Name) ..."
        Push-Location $_.FullName
        javac -encoding UTF-8 *.java
        Pop-Location
    }
}
Write-Host "Done."
