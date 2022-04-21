@ECHO OFF

call method.bat getPath
call method.bat getPassword


MD backup

REM 將路徑移置 /mysql/bin
CD %path%

REM 取得資料庫列表 (除了系統、預設資料庫)
IF [%password%] == [] (
    mysql -uroot -N -e "show databases" > temp.sql
) ELSE (
    mysql -uroot -p%password% -N -e "show databases" > temp.sql
)
Setlocal EnableDelayedExpansion
COPY NUL mysql_database.sql
ECHO.
ECHO.
FOR /f "tokens=*" %%a IN (temp.sql) DO (
    SET isUserDatabase=true
    IF %%a == information_schema SET isUserDatabase=false
    IF %%a == mysql SET isUserDatabase=false
    IF %%a == performance_schema SET isUserDatabase=false
    IF !isUserDatabase! == true ECHO %%a>>mysql_database.sql
)
ECHO databases list has listed
ECHO.

REM 備份資料庫
FOR /f "tokens=*" %%a IN (mysql_database.sql) DO (
    IF [%password%] == [] (
        mysqldump -uroot %%a > %~dp0backup\%%a.sql
    ) ELSE (
        mysqldump -uroot -p%password% %%a > %~dp0backup\%%a.sql
    )
    ECHO %%a is backup
)
ECHO.
ECHO.


COPY .\mysql_database.sql %~dp0backup\mysql_database.sql
DEL .\mysql_database.sql
DEL .\temp.sql


PAUSE