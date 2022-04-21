@ECHO OFF

CALL method.bat getPath


IF EXIST .\data\ (
    ECHO please delete or rename data [folder]
    ECHO.
    GOTO Exit
)

REM 將路徑移置 /mysql/bin
CD %path%

MOVE ..\data %~dp0

REM 初始化資料庫 (需刪除 /mysql/data/)
mysql_install_db


:Exit
PAUSE