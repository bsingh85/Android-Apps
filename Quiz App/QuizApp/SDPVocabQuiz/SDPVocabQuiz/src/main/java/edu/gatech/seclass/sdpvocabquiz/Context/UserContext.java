package edu.gatech.seclass.sdpvocabquiz.Context;

import edu.gatech.seclass.sdpvocabquiz.vo.Quiz;

public class UserContext {

    private static UserContext _userContext = new UserContext();
    private UserContext() {}

    public static boolean initialize(int studentId, String studentUserName) {
        if (studentId <= 0) return false;
        StudentId = studentId;
        StudentUserName = studentUserName;
        return true;
    }

    public static void logout() {
        StudentId = 0;
        StudentUserName = null;
    }

    public static boolean startQuiz(Quiz quiz){
        if (quiz == null) return false;
        CurrentQuiz = quiz;
        return true;
    }

    public static void finishQuiz(){
        CurrentQuiz = null;
    }

    private static int StudentId = -1;
    private static String StudentUserName;

    private static Quiz CurrentQuiz = null;

    public static int getStudentId(){
        return StudentId;
    }

    public static String getStudentUserName(){
        return StudentUserName;
    }

    public static Quiz getCurrentQuiz(){
        return CurrentQuiz;
    }
}
