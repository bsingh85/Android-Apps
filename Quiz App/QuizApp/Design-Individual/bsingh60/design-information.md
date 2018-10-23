## Assignment 5: Software Design

This is a design information document to concisely describe how each part of the requirement is realized either by showing directly in the UML class diagram named design.pdf or is not directly shown in the diagram but is taken into consideration.

## Requirements
##### Req.No.1: When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.
 ##### a. To register as a new student, the user must provide the following student information:
* i. A unique username
* ii. A major
* iii.  A seniority level (i.e., freshman, sophomore, junior, senior, or grad)
* iv.  An email address
##### b. The newly added student is immediately created in the system.
##### c. For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.
##### d. Also for simplicity, student and quiz information is local to a device.

> To realize this part of requirement we have ***Application*** class which contains list of registered students in **students** attribute and list of quizzes created so far in **quizzes** attribute. Requirement **1(1)** is taken care of by the method named **login()** in ***Student*** class. The Student class contains attributes **userName**,**major**, **seniorityLevel** and **email**. **login()** method will check if the student is registered or not. If the student is not registered, appropriate message like "Please Register." will be sent through the GUI class. The student will be allowed to login only if he is already registered in the system.
>
>Requirement **1(2)** is handled by the method named **register()** in ***Student*** class. The **seniorityLevel** attribute is populated by an enumeration named **SeniorityType**. **register()** method will enable students to register. The logic in **register()** method will check if the user who is trying to register is already registered in the system or not. If the user is already registered then an appropriate message like "You are already registered. Please login with your Username." will be sent through the GUI. If the student is not registered in the system, then he will be allowed to register by providing the required information. The newly registered student will be immediately created in the system.

##### Req.No.2: The application allows students to (1) add a quiz, (2) remove a quiz they created, (3) practice quizzes, and (4) view the list of quiz score statistics.

> The requirement **2(1)** is realized by the method **addQuiz()** in ***Quiz*** class.
> The requirement **2(2)** is realized by the method **removeQuiz()** in ***Quiz*** class.
> Requirement **2(3)** is realized by the method **practiceQuiz()** in ***Student*** class.
> Requirement **2(4)** is realized by the method **getStatisticsByStudent()** in ***ScoreStatistics*** class.

  ##### Req.No.3: To add a quiz, a student must enter the following quiz information: a.	Unique name b.	Short description c.	List of N words, where N is between 1 and 10,  together with their definitions  d.	List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.

> To realize this part of requirement, a ***Quiz*** class has been created with attributes **quizName** for storing the unique name of a quiz, attribute **description** to store short description of a quiz and attribute **words** to store list Of N words where N is between 1 and 10. The mapping from ***Quiz*** class to ***Word*** class ensures that N is between 1 and 10 for a quiz. The attribute **incorrectDefinitions** will contain N*3 incorrect definitions not tied to any particular word in the quiz.

 ##### Req.No.4.  To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.

> This requirement will be realized in the following way:
> **getRemovableQuizzes()** method from ***Student*** class ensures that a student get to see only those quizzes which he has created. Once he selects a quiz for removal from this list , **removeQuiz()** method from ***Quiz*** class will be called which will remove that particular quiz. Additionally, **removeScore()** method from ***ScoreStatistics*** class will be called with ***Quiz*** object as a parameter. **removeScore()** will iterate through the scores list and remove all the scores in the list where the **quizName** attribute of the ***Quiz*** object matches the **quizName** attribute on the individual ***Score*** objects.

##### Req.No.5. To practice a quiz, students must select it from the list of quizzes created by other students.

> To realize this part of the requirement, we have **getPracticableQuizzes()** method in ***Student** class which will return a list of quizzes created by students other than the logged in student. After the logged in student selects a quiz to practice, another method named **practiceQuiz()** in the ***Student*** class will be triggered which will enable logged in student to play the selected quiz.

### Note for requirement No. 6:
#### Since requirement No. 6 has many parts, I have separated them into smaller units followed by their realization explanation respectively.
##### Req.No.6.	When a student is practicing a quiz, the application must do the following: a:	Until all words in the quiz have been used in the current practice session:
##### No.6.a.i:	Display a random word in the quiz word list.

> **displayWord()** method in ***Quiz*** class will have the logic to randomly select a word from the **words** attribute of the ***Quiz*** class and display it and keep displaying a new word until all the words in the list has been displayed in the current practice session.

##### Req.No.6.a.ii:	Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz.

> To realize this requirement a method named **displayChoices()** in ***Quiz*** class has been created which will take care of the above logic.

 ##### Req.No.6.a.iii:	Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).

> This UML class diagram doesn't capture the part of the requirement which states "select a definition". This is because selection is a property of the GUI and will be realized by the GUI component.
> The GUI component will call **verifyChoice()** method after the user has selected one of the answer choices. **verifychoice()** method will return *"correct"* or *"incorrect"* depending on whether the selected choice is correct or not. **verifychoice()** method is in ***Quiz*** class.

  ##### Req.No.6.b:	After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.

>  To realize this part of the requirement a method named **calculateRunningPercentage()** has been created in ***Quiz*** class which will keep updating the attribute **runningPercentage** in ***Quiz*** class based on whether the selected answer choice is correct or not. Afterwards, **displayRunningPercentage()** method will display the running percentage after answer choice selection for each word. At the end of the quiz, the quiz score will be submitted by calling **addScore()** method of the ***Score*** class. Finally, **addScore()** method of the ***ScoreStatistics*** class will be triggered to add the ***Score*** object to the **scores** attribute of the ***ScoreStatistics*** class.

### Note for requirement No. 7:
#### Since requirement No. 7 has many parts, I have separated them into smaller units followed by their realization explantion respectively.

##### Req.No.7.The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first).
> This part of the requirement is realized by the method **getStatisticsByStudent()** in ***ScoreStatistics*** class. This method internally will call **orderByDate()** method in ***ScoreStatistics*** class which will order the scores list by **scoreDate** attribute(most recent first). In turn, **getStatisticsByStudent()** method will return ordered list of Score.

##### Clicking on a quiz must display
##### Req.No.7.1: the student’s first score and when it was achieved (date and time),
> "Clicking on a quiz" functionality is part of GUI component and is not captured in the UML class diagram. To get the student's first score and when it was achieved , GUI component will call **getFirstScore()** method from in ***ScoreStatistics*** class which will return ***Score*** object with required information. GUI component will extract the values of **score** and **scoreDate** attributes of the returned Score object.

##### Req.No.7.2: the student’s highest score and when it was achieved (date and time),

> This requirement is realized by method **getHighestScore()**. It will return a ***Score*** object which will have the information of highest score in **score** attribute and when it was achieved in **scoreDate** attribute.

##### Req.No.7.3: and the names of the first three students to score 100% on the quiz, ordered alphabetically.

 > This requirement is realized by method **getTop3StudentsWith100()**. This method will call **orderByScore()** to get this list of scores ordered by highest score first to lowest score last. Next, it will check the first three score objects in the list. If the **percentageCorrect** attribute of the ***Score*** object is 100, then it will add to the returned list of names. Before returning the list of names, it will sort the names in alphabetical order by using the method **orderAlphabetically()**.


##### Req.No.8: The user interface must be intuitive and responsive.
> This part of the requirement is not covered in the design because it is handled purely by GUI. A great algorithm and proper implementation will ensure intuitiveness.

##### Req.No.9: The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
>  This part of the requirement is also not covered in the design because it is handled purely by GUI. An efficient use of algorithm and classes and proper implementation will ensure required level of performance.
