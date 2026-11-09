# Compiles every folder that has Java files (no programs are run)
Get-ChildItem -Recurse -Directory | ForEach-Object {
    if (Get-ChildItem $_.FullName -Filter *.java) {
        Write-Host "Compiling $($_.FullName) ..."
        Push-Location $_.FullName
        javac -encoding UTF-8 *.java
        Pop-Location
    }
}
Write-Host "Done."
