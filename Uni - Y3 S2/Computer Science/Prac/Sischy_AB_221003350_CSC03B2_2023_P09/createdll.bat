@echo off
setlocal enabledelayedexpansion
set PROJNAME=%~1
set DLLNAME=%~2
set ERRMSG=

if "%PROJNAME%"=="" GOTO USAGE
if "%DLLNAME%"=="" GOTO USAGE
GOTO CLEAN

:USAGE
echo %PROJNAME% %DLLNAME%
echo "Usage: createdll [asmFileNameWithoutExtension] [dllFileNameWithoutExtension]"
goto END

:CLEAN
echo "~~~ Cleaning project ~~~"
DEL /S %DLLNAME%.dll %DLLNAME%.lib %DLLNAME%.exp %PROJNAME%.lst %PROJNAME%.obj
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Cleaning"
    GOTO ERROR
)

:ASSEMBLE
echo "~~~ Assembling project ~~~"
.\assembler\ml.exe /c /coff /Fl /Zi src\%PROJNAME%.asm
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Assembling"
    GOTO ERROR
)

:LINK
echo "~~~ Linking project ~~~"
.\assembler\link.exe /dll /subsystem:console /def:src\%DLLNAME%.def /out:bin\%DLLNAME%.dll %PROJNAME%.obj .\assembler\kernel32.lib .\assembler\io.lib
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Linking"
    GOTO ERROR
)

:RUN
echo "~~~ Running project ~~~"
bin\%DLLNAME%.exe
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Running"
    GOTO ERROR
)
GOTO END

:ERROR
echo "!!! An error has occured !!!"
echo %ERRMSG%
pause

:END
echo "~~~ End ~~~"
