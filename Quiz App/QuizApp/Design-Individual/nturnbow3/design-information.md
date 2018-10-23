```
1. When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.
    a. To register as a new student, the user must provide the following student information:
        i. A unique username
        ii. A major
        iii. A seniority level (i.e., freshman, sophomore, junior, senior, or grad)
        iv. An email address 
    b.	The newly added student is immediately created in the system.
    c.	For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.
    d.	Also for simplicity, student and quiz information is local to a device.
```

**To realize requirement 1, I created a User class with the static operation “register”, which takes as input the information outlined in (a) and outputs an instance of the User class. This class also contains the static operation “login” which accepts a username and returns an instance of the class “StudentLoginSession”. I introduced StudentLoginSession as a class to represent the time that a particular user spends interacting with the application following a successful login (and terminating when the application closes or the user logs out).**

```
2. The application allows students to (1) add a quiz, (2) remove a quiz they created, (3) practice quizzes, and (4) view the list of quiz score statistics.
```

**To realize requirement 2 points (1), (2), and (4); I added the following methods to the StudentLoginSession class: “createQuiz”, “removeQuiz”, and “listScoreStatistics”. I also introduced the class “Quiz” to represent a quiz created by a logged in user. The Quiz class has 3 fields and is an aggregation of Word objects with their correct definitions as well as a collection of incorrect definitions. “createQuiz” takes as input the items outlined in requirement 3 a-c, as well as sufficient data to populate the related aggregate items. Additionally, Quiz has the property “createdByUsername” which is meant to be obtained from the user’s “StudentLoginSession” and which will provide data to “removeQuiz” so that a quiz will not be removed unless it was created by the same user that is related to the current instance of StudentLoginSession (fulfilling 2). A constraint is added between the “removeQuiz” and “createQuiz” in order to illustrate that “removeQuiz” is dependent on a prior “createQuiz” having executed, each with the same username.**

**To realize requirement 2 point (3), I added a new class “PracticeSession” to represent an aggregation of time periods during a “StudentLoginSession” during which a student is practicing a single quiz. The relationship between a PracticeSession and a Quiz is one of dependency because a PracticeSession must use the information encoded within a Quiz object (including its associations) to provide the user experience.**

```
3. To add a quiz, a student must enter the following quiz information:
    a. Unique name
    b. Short description
    c. List of N words, where N is between 1 and 10, together with their definitions 
    d. List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.
```

**To realize requirement 3 (a) and (b), the public Quiz class properties “name” and “description” were added. (c) is satisfied by the addition of a new class “Word” which contains the “wordText” and “correctDefinition” string fields. Words participate in an association relationship with a parent Quiz instance and cannot exist except in association with a Quiz, with a multiplicity between 1 and 10. (d) is satisfied by the addition of an “IncorrectDefinition” class which obtains through inheritance (Generalization) a pair of common property with the “Word” class, but which engages in different relationships. The complex relationship “N words where 1 < N < 10, and 3*N incorrect definitions” is captured by the multiplicities of the Word and IncorrectDefinition class relationships with regard to the Quiz class. The arguments to the createQuiz method also reflect each of these requirements so that the proper data with the proper relationships may be provided.**

```
4. To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.
```

**The constraint “{remove.username == create.username}” was added in an attempt to capture this requirement, that a student cannot remove a quiz that was created by a different user. This constraint was added with an arrow at one end in an attempt to show that the constraint is only binding on “remove” and not “create”, but data populated from the “create” relationship is a prerequisite to the “remove” relationship. In other words, a Quiz cannot be removed before it is created, and it can’t be removed by a user other than the user who created it.
The second requirement is captured by an association relationship between Quiz and PracticeSession (which holds the “percentCorrectStatistic” representing the “score statistics” for a particular quiz administration). The association implies that a quiz cannot be removed without also removing its associated statistics.**

```
5. To practice a quiz, students must select it from the list of quizzes created by other students.
```

**This requirement is expressed by the constraint “{PracticeSession-Quiz.createdByUsername != PracticeSession-StudentLoginSession.username}” between the StudentLoginSession/PracticeSession aggregation and the Quiz/PracticeSession association. This is meant to express that a StudentLoginSession cannot initiate a PracticeSession against a quiz which was created by the current user.** 

```
6. When a student is practicing a quiz, the application must do the following:
    a. Until all words in the quiz have been used in the current practice session: 
```

**The requirement that all words belonging to a quiz must be used in each PracticeSession is an implicit derivation from the note off of the PracticeSession class which states “The unordered set {PracticeSession-PracticeQuestion->Word} must be identical to the unordered set {PracticeSession-Quiz-Word}”. In other words, for every word in the Quiz there must be a corresponding practiceQuestion in the associated PracticeSession.**

```
        i. Display a random word in the quiz word list.
```

**Random selection from the quiz-word relationship and displaying to the user are implementation details of the user interface**

```
        ii. Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz.
```

**This requirement is captured by the dependency relationships originating in the PracticeQuestion class (“incorrect” and “correct”). The “correct” dependency terminates in the Word class and has a multiplicity of 1 on the terminal end, signifying that there should be one word and one correct definition per randomly selected question displayed to the user. The “incorrect” dependency terminates in the abstract class Definition which is inherited by both Word (with the correct definition) and IncorrectDefinition. This dependency terminates with a multiplicity of “0..3” signifying that between 0 and 3 definitions will be selected from the superset of correct and incorrect answers. The constraint between correct and incorrect stating “{Incorrect cannot contain correct}” indicates that the correct definition for a word cannot also be included in the set of incorrect definitions for the same question.**

```
        iii. Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).
```

**This will be an implementation detail of the user interface**

```
    iv. After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.
```

**This is largely a UI implementation detail. I would expect that the UI, equipped with the list of correct and incorrect definitions would handle determining which definition the user indicated and would condense this information down into a Boolean “correct” or “incorrect” and maintain an integer count of how many “corrects”. With that, calculation of the statistic as corrects/total questions could either be handled by the UI and simply set on the PracticeSession class, or that calculation could be performed by the PracticeSession class itself provided with the “corrects” count (and information it already has about its relationship to PracticeQuestions). I provided a “calculateStatistic” method on PracticeSession assuming the latter case.**

```
7. The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first). Clicking on a quiz must display (1) the student’s first score and when it was achieved (date and time), (2) the student’s highest score and when it was achieved (date and time), and (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.
```

**Each of these requirements require timestamp information regarding each user’s administration of their quizzes, so to enable the UI to provide this ordering and these calculations, a public “DateTimeStamp” field has been provided on the PracticeSession class. All other information required can be derived from the relationships defined between these various classes.**

```
8. The user interface must be intuitive and responsive.
9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
```

**8 and 9 are to be handled as implementation details of the user interface**


