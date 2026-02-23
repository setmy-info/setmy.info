@echo off
set SCRIPT_DIR=%~dp0
java -cp "%SCRIPT_DIR%..\lib\*" info.setmy.stealer.cli.Application %*
