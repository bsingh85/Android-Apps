# Quiz Application Design Description
-------------------------------------

1)	When user starts application, **Application** class will be loaded in memory and it will display UI using **UserInterface** utility class. This application will present options to create student and login as existing student. In addition to this **Application** class will also manage to provide list of practicable and removable quizzes and play quiz for student. **Student** class will be created which has login(), register(), getPracticableQuizzes() and getRemovableQuizzes() method defined in it.

    a)	To register a student, **Student** class will be used. This class will have 4 properties and 4 methods as mentioned in diagram.

    b)	Newly created student will be persisted into database by DB utility classes.

    c)	There is no authentication method because we don’t need password authentication for this system.

    d)	Student and Quiz will be stored in local database by DB utility classes.

2)	Quiz CRUD operations will be managed by **Quiz** class and it’s methods

    a)	To add a quiz, Quiz class will be loaded and addQuiz() method will be called to persist data in database. This method will return newly created Quiz.

    b)	Student can remove a quiz by calling removeQuiz() method of Quiz class. This method will accept studentName as an argument and return deleted Quiz. removeQuiz() will verify quiz owner before deleting quiz.

    c)	Practice quizzes will be retrieved using **Student** class getPracticableQuizzes() method which will return list of quizzes.  

    d)	To view list of quiz stats, Application class will use **QuizStats** classes getStatsByStudent() or getStatsByQuiz() method. These methods will provide list

3)	To add a quiz, **Student** class will call addQuiz() method on **Quiz** class. This method will create the quiz and persist in database too. Student has to set quiz name, quiz description, N words and N * 3 incorrect definitions, name of the student in quiz owner field.

4)	To remove a quiz, **Student** class need to call removeQuiz() method on **Quiz** class. This method will check if the student is owner of the quiz or not, if student is owner of that quiz it will remove the quiz and call the removeQuizScoreStats() method of QuizStats class.

5)	To retrieve all the practicable quiz getPracticableQuizzes() method of **Student** class will be used.

6)	To practice a quiz, student will use **QuizSession** class practiceQuiz() method.

    a)	Until all words in quiz have been used in current practice session -

        i. Unique word will be populated using showUniqueWord() method on **Quiz** class. This will provide Word class object.

        ii.  Word class will have all the word related properties as shown in class diagram. When any quiz is created Word options will be prepared using populateOptions() method. This method will take care of adding one right definition and 3 random incorrect definitions.

        iii.  Word class will also have a method which will check if the inserted answer is correct or not. It will return true or false to UI. UI will take care of displaying correctness of the question.

    b)	After every quiz word response, calculateScore() method of **QuizSession** will be called to calculate the updated score for the quiz. UI will handle to display the updated score to the student. If the quiz is over than quiz score statistics will be mapped to database using **QuizStats** class. DB utility classes will take care of inserting this data into database.

7)	Quiz score statistics will be managed by **QuizStats** class. It contains all the properties related to quiz score.

    a) getStatsByStudent() method of **QuizStats** will accept student username as an argument and return the ordered list of QuizStats by descending order of participation date.

    b) getStatsByQuiz() method of **QuizStats** will accept quiz name as an input and return score list having all the students with their first and highest score, their respective date and name of first 3 students who got 100. UI classes will manage to display this information

8) UI utility classes will manage to display intuitive and responsive UI.

9) UI layer, Business layer and DB layer will be carefully written to provide proper response time from the application.
