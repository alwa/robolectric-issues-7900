@echo off
if "%~1"=="" (
    echo "Syntax: gradlewu <NEW_VERSION>"
) else (
    call gradlew wrapper --gradle-version "%~1" --distribution-type bin
    call powershell -Command "(Get-Content gradle/wrapper/gradle-wrapper.properties) -replace 'services.gradle.org/distributions/', 'artifacts.assaabloy.net/nexus/repository/gradle-binaries-raw/' | Out-File -encoding ASCII gradle/wrapper/gradle-wrapper.properties"
)
