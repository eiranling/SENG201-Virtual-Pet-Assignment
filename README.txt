============================================================================================================
-------------------------------------------RUNNING AND COMPILING--------------------------------------------
============================================================================================================
To run the program in the lab environment, either run the pre-compiled jar file xjm10-eli26-virtual-pet.jar by 
navigating to the same directory with the same jar file and running the following code

java -jar xjm10-eli26-virtual-pet.jar

If you wish to compile and run the jar file yourself, nagivate to the directory of the Game package in the 
source code directory and run then the following code into the terminal.

javac -cp ../ GameGUI.java
java -cp ../ Game/GameGUI



============================================================================================================
-------------------------------------------IMPORTS AND LIBRARIES--------------------------------------------
============================================================================================================
If you wish to run the game directly through Eclipse, create a new java project and import the entire
contents of the source code folder into the workspace. Then add the JUnit 4 Libraries to the project.

Libraries needed when running in eclipse:
- JUnit 4 Libraries*

============================================================================================================
--------------------------------------------------TESTING---------------------------------------------------
============================================================================================================
All the JUnit tests have been grouped into a single JUnit test class title AllTests.class. Running
this class as a JUnit test will run all the JUnit tests. This is located under the source code directory
in the JUnitTests package

Enjoy.
