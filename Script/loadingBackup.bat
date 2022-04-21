@ECHO OFF

CALL method.bat getPath
CALL method.bat getPassword


REM 將路徑移置 /mysql/bin
CD %path%


ECHO.
ECHO.
REM 設定資料庫資料
FOR /f "tokens=*" %%a IN (%~dp0backup\mysql_database.sql) DO (
    REM 創建資料庫
    IF [%password%] == [] (
        mysql -uroot -e "create database %%a"
    ) ELSE (
        mysql -uroot -p%password% -e "create database %%a"
    )

    REM 將備份檔案移置 /mysql/bin 中進行備份，並刪除備份檔案
    COPY %~dp0backup\%%a.sql .\%%a.sql
    IF [%password%] == [] (
        mysql -uroot %%a < %%a.sql
    ) ELSE (
        mysql -uroot -p%password% %%a < %%a.sql
    )
    DEL .\%%a.sql
    ECHO.
    ECHO %%a has already copied to database
    ECHO.
)



REM 設定使用者與權限
REM 將備份檔案移置 /mysql/bin 中進行備份，並刪除備份檔案
COPY %~dp0backup\mysql_privilege.sql .\mysql_privilege.sql
IF [%password%] == [] (
    for /f "tokens=*" %%a in (mysql_privilege.sql) do (
        mysql -uroot -e "%%a"
    )
) ELSE (
    for /f "tokens=*" %%a in (mysql_privilege.sql) do (
        mysql -uroot -p%password% -e "%%a"
    )
)
rem DEL .\mysql_privilege.sql
ECHO.
ECHO.
ECHO user and privilege has already set to database
ECHO.
ECHO.


PAUSE