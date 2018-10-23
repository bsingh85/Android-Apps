# Design Information

## Design information for requirements:

1.	When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.
* a.	To register as a new student, the user must provide the following student information:
 * (a1).	A unique username
 * (a2).	A major
 * (a3).	A seniority level, i.e., freshman, sophomore, junior, senior, or grad.
 * (a4).	An email address
* b.	The newly added student is immediately created in the system.
* c.	For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.
* d.	Also for simplicity, student and quiz information is local to a device.

*To realize this requirement, I designed a class 'Application' with method of login(student) and registerStudent(student), depending on the input from the interface (login and register button handled by GUI), if student is a new user, class 'Application' uses class 'RegisterForm' to collect student information with a unique username for that student (if username exists, error is thrown in GUI) and saved to class 'Student'. Class 'Student' has attributes of 'userName','major','seniorityLevel', 'emailAddress' and 'quizOwned' (all in String type).*

2.	The application allows students to 
* (1) add a quiz, 
* (2) remove a quiz they created, 
* (3) practice quizzes, and 
* (4) view the list of quiz score statistics.

*To realize this requirement, class 'Student' has operations of 'addQuiz', 'removeQuiz', 'practiceQuiz' and 'viewQuizScore'. Class 'Student' has relationships (Added, Removed, Practiced) with class 'Quiz' and uses the method 'addQuiz' to add a quiz with a unique quiz name (if name already exists, error is thrown by GUI), method ’removeQuiz' to remove a quiz and method ’practiceQuiz' to practice a quiz. Class 'Student' also has a relationship with class 'QuizScore' and uses method 'removeQuiz' to delete quiz scores related to that quiz and use method 'viewQuizScore' to view the list of quiz score statistics.*

3.	To add a quiz, a student must enter the following quiz information:
* a.	Unique name
* b.	Short description
* c.	List of N words, where N is between 1 and 10,  together with their definitions 
* d.	List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.

*To realize this requirement, class 'Quiz' has attributes of 'name', 'description', 'quizOwner' (to record who create the quiz), 'numberOfWord' (N between 1 and 10 word in the quiz). Class 'Quiz' also has a relationship to class 'Student' to accept who create the quiz. Class 'Quiz' also has relationships with classes 'Word', "CorrectDefinition" and "IncorrectDefinition" to include N (1~10) words with correct definition and 3*N (3~30) incorrect definitions.*

4.	To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.

*To realize this requirement, I added the class 'Student' with list 'quizOwned' of items. Class 'Student' can select the quizzes they owned. Class 'Quiz' has a 'Removed' relationship to class 'Student' and uses it in method 'removeQuiz' to delete the quiz if the student is the 'quizOwner' of class 'Quiz'. Class 'QuizScore' also has a relationship with class 'Student' and use the method 'removeQuiz' to delete the quiz score statistics if student is 'quizOwner' of class 'QuizScore’.*

5.	To practice a quiz, students must select it from the list of quizzes created by other students.

*To realize this requirement, class 'Quiz' has an attribute of 'quizOwner', class 'Student' used method 'practiceQuiz' to only select those quizzes that student is not 'quizOwner' of those quizzes.*

6.	When a student is practicing a quiz, the application must do the following:
* a.	Until all words in the quiz have been used in the current practice session: 
 * (a1).	Display a random word in the quiz word list.
 * (a2).	Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz. 
 * (a3).	Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).
* b.	After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.

*To realize this requirement, an associate class 'PracticedSection' is created. It has attributes of 'score' (to record score) and 'counter' (to count how many word definitions are selected correctly). Class 'PracticedSection' uses class ‘Quiz’ in method 'selectWord' to randomly select words from that quiz, uses method 'selectDefinition' to select the correct definition together with the selected word and randomly select three other definitions. Class 'PracticedSection' has method 'acceptSelection' to accept the selection of the student and display "correct"/"incorrect" depending on whether answers are correct. If answers matched solutions, 'counter' is incremented till all words finished, then percentage of ’score' is computed and method 'displayScore' is called to display the percentage of words they correctly defined. Then it uses method 'saveQuizScore' to save the quiz score to class 'QuizScore’.*

7.	The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first). Clicking on a quiz must display
* (1) the student’s first score and when it was achieved (date and time), 
* (2) the student’s highest score and when it was achieved (date and time), and 
* (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.

*To realize this requirement, class 'Student' has a relationship with class 'QuizScore', and uses method 'viewQuizScore' to display all quizzes ordered based on when they were last played by the student. Class 'QuizScore' has attributes of 'quizTaken', 'quizTakenDate’, ’quizTakeTime’, ‘quizTakenStudent’ and ‘quizOwner’ to keep track what quizzes the student have taken and when the practice happened (Date and Time) and who owned the quizzes. To display the student’s first score and when it was achieved, class ‘QuizScore’ uses method ‘displayFirstScore’ to find and display the first score for that student and that quiz and quiz date/time. To display the student’s highest score and when it was achieved, class ‘QuizScore’ uses method ‘displayHighestScore’ to find and display the highest score for that quiz and that student (if many records found, the latest highest score is displayed). To list three students to score 100% on the quiz, the class ‘QuizScore’ use method ‘displayFullScoreStudent’ to find all the students who scored full points for that quiz and display three students’ names ordered alphabetically.* 
   
8.	The user interface must be intuitive and responsive.

*This is not represented in my design, and it will be entirely handled by GUI implementation.*

9.	The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

*This is not represented in my design, and it should be handled in the implementation stage of application.*


