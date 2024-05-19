@echo off

set myDirectory="Users\redth\OneDrive\Desktop\Work_Test\lib\new"
if not exist "C:\%myDirectory%" mkdir C:\%myDirectory%
echo %myDirectory%
pause

cd ..
cd AZCopy


Pause

azcopy sync 'https://mystorageaccount.blob.core.windows.net/mycontainer' 'C:\%myDirectory%' --recursive

Pause
