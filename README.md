# Soccer-Database-Project
This was a project to create a database using SQL / Java.  Our database was for a soccer championship.  Note that to run you gotta go to src>lib>src>com and follow the instructions in the readme there.  Had to zip some lib files up to meet space/file count requirements.  Otherwise, instructions to run are:

1) Make sure MySQL is setup
2) Start a database project or open the existing one
3) if anything is in it, drop the schema using the GUI
4) Open and run the SQL file 'create_db.sql' in the SQL folder
5) Open 'populate.sql' in the SQL folder BUT DO NOT RUN IT.  You need to change the directory strings to wherever you have the sample data.
6) Run the SQL file 'populate.sql' in the SQL folder.
7) Now open your prefered Java IDE and make a new project, likely as a console project.  Add our Java Code in Java Source Code to the project; both 
   the code in the main folder and in the data_object subfolder (you can just add the subfolder).
8) However you add JAR files in your IDE, add the ones in the lib folder.  These are necessary to connect to the mysql database.
9) You will need to change lines 13-15 of the Server.java file to whatever the user, url, and password is for your database.  Note that the URL should be fine if you stuck
   with the default config, but the user and password may need to be changed.
10) Run the main function in UI.  Many helpful usage messages should make it fairly self explanatory as you go; just type the inputs that it asks for, and if it's not right,
    it should keep bugging you for the right thing (e.g. entering "ASDF" instead of a "Y" where it expects it).
