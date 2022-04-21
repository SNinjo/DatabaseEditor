@ECHO OFF

CALL method.bat getPath
CALL method.bat getPassword


MD backup

REM 將路徑移置 /mysql/bin
CD %path%

REM 取得用戶、權限 (除了系統使用者、Root)
IF [%password%] == [] (
    mysql -uroot -N -e "SELECT CONCAT('\'',user,'\'@\'',host,'\'') FROM mysql.user WHERE user != 'debian-sys-maint' AND user != 'mariadb.sys'" AND user != 'root' AND user != ''" > mysql_users.sql
) ELSE (
    mysql -uroot -p%password% -N -e "SELECT CONCAT('\'',user,'\'@\'',host,'\'') FROM mysql.user WHERE user != 'debian-sys-maint' AND user != 'root' AND user != 'pma' AND user != ''" > mysql_users.sql
)

REM 創建檔案
COPY NUL mysql_privilege.sql

REM 按照 user 清單輸出 grant 指令
ECHO.
ECHO.
FOR /f "tokens=*" %%a IN (mysql_users.sql) DO (
    IF [%password%] == [] (
        mysql -uroot -N -e "show grants for %%a" > temp.sql
    ) ELSE (
        mysql -uroot -p%password% -N -e "show grants for %%a" > temp.sql
    )
    REM /b 移除檔案結尾字元 ^Z (CTRL + Z)
    COPY /b mysql_privilege.sql + temp.sql mysql_privilege.sql
    ECHO %%a is backup
)
ECHO.
ECHO.


COPY .\mysql_privilege.sql %~dp0backup\mysql_privilege.sql
DEL .\mysql_privilege.sql
DEL .\mysql_users.sql
DEL .\temp.sql


PAUSE