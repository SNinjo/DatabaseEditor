# DatabaseEditor
developed some software to help engineer to manage and backup database (MySQL)


## DEMO
<div align="center"><img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/DatabaseEditor.gif" width="600" height="300"/></div>


## Operating Example (Java)
* input IP, Account, and Password  
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart01.png" width="600" height="300"/>
* you can directly input some instruction in textbox  
here is an exmaple output  
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart02.png" width="400" height="300"/>
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart03.png" width="400" height="300"/>
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart04.png" width="400" height="300"/>
* click "List Structure" button in "Database" menu  
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart05.png" width="400" height="300"/>
* double-click the database that you want to edit  
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart06.png" width="400" height="300"/>
* left-click to select the table, and right-click to open the function menu  
you can import (.csv -> table) or export (table -> .csv) tables  
<img src="https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart07.png" width="400" height="300"/>


## Operating Example (Script)
#### Warning! The actions of script is destructive instructions, watch out for it.  
click "backupDatabase.bat"  
![image](https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart2-01.png)  
backup your database to /backup (directory)  
![image](https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart2-02.png)  
click "loadingBackup.bat"  
download the record in /backup (directory) to your database  
![image](https://github.com/SNinjo/DatabaseEditor/blob/main/img/flowchart2-03.png)  


## Installation (Java)

### step 1
&emsp;&emsp;import this code to your IDE (Eclipse, Intellj, etc)
### step 2
&emsp;&emsp;open Main(package).Main(class)
### step 3
&emsp;&emsp;open your database
### step 4
&emsp;&emsp;run main function in Main.Main and then the GUI will open
### step 5
&emsp;&emsp;you can use DatabaseEditor (Java) now!
  
  
## Installation (Script)

### step 1
&emsp;&emsp;set the password of root user and the path of database (/bin directory) in config.txt
### step 2
&emsp;&emsp;you can use DatabaseEditor (Script) now!
