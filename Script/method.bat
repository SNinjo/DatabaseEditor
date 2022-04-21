IF %1 == getPath (GOTO getPath)
IF %1 == getPassword (GOTO getPassword)
GOTO Exit

:getPath
REM /f tokens用來指定你要截取第幾個或者哪幾個匹配項存放到變數中
FOR /f "tokens=1,2" %%a IN (config.txt) DO (
    IF %%a == path: SET path=%%b
)
GOTO Exit

:getPassword
FOR /f "tokens=1,2" %%a IN (config.txt) DO (
    IF %%a == password: SET password=%%b
)
GOTO Exit

:Exit