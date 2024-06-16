function Get-Version {
    param (
        [string]$jarFile
    )

    $tempDir = [System.IO.Path]::GetTempPath()
    $manifestPath = Join-Path $tempDir "MANIFEST.MF"

    try {
        # Extract the MANIFEST.MF file from the JAR
        $zipArchive = [System.IO.Compression.ZipFile]::OpenRead($jarFile)
        $manifestEntry = $zipArchive.Entries | Where-Object { $_.FullName -eq "META-INF/MANIFEST.MF" }
        if ($manifestEntry) {
            $stream = $manifestEntry.Open()
            $reader = New-Object System.IO.StreamReader($stream)
            $content = $reader.ReadToEnd()
            $reader.Close()
            $stream.Close()
            $content | Set-Content -Path $manifestPath -Encoding UTF8
        } else {
            Write-Error "MANIFEST.MF not found in the JAR file."
            return
        }
        $zipArchive.Dispose()

        # Read and find the Implementation-Version line
        $manifestContent = Get-Content -Path $manifestPath
        $versionLine = $manifestContent | Select-String -Pattern '^Implementation-Version: (.*)$'
        $version = if ($versionLine) { $versionLine.Matches[0].Groups[1].Value } else { "Not Found" }

        # Output the Implementation-Version
        Write-Output "Implementation-Version: $version"
    }
    finally {
        # Clean up temporary file
        Remove-Item $manifestPath -ErrorAction SilentlyContinue
    }
}

# Call the function with the provided argument
Get-Version -jarFile .\JUnit\hamcrest-core-1.3.jar
Get-Version -jarFile .\JUnit\junit-4.13.2.jar


Write-Output "Compilo i file Java"
javac -cp ".;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar" myAdapter\*.java myTest\*.java

Write-Output "Eseguo i test"
java -cp ".;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar" myTest.TestRunner
