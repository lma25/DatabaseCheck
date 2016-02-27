I. What does the code do?
a) Get number of students who registered a certain class. Two classes are same if and only if their names and professors are same.
b) Get number of students who take more than one class.

II. How does the code work?
Step1. Extract data from database file and store them in a list of tuples.
Step2a. By traverse the list of tuples once, get number of students who registered a class that matches the input.
Step2b. By traverse the list of tuples once, get number of students who take more than one class.

III. How does the code tested?
Using JUnit to create a temporary test database file and test the two functions a) and b).

IV. The structure of the code:
src
  --main
    --DataProcessor.java
    --DefaultValue.java
  --properties
    --ClassInfo.java
    --ClassName.java
    --Tuple.java
  --test
    --DataProcessorTest.java