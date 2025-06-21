# PowerShell script to compile and run C++ code in one command
function Invoke-Cpp {
    param(
        [Parameter(Mandatory=$true)]
        [string]$SourceFile,
        
        [string]$OutputName = "output"
    )
    
    # Compile the code
    Write-Host "Compiling $SourceFile..." -ForegroundColor Yellow
    g++ -o $OutputName $SourceFile
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Compilation successful! Running..." -ForegroundColor Green
        & ".\$OutputName.exe"
    } else {
        Write-Host "Compilation failed!" -ForegroundColor Red
    }
}

# Example usage:
# Invoke-Cpp "Reverse-Words-In-String.cpp"
# Invoke-Cpp "myfile.cpp" "myprogram" 