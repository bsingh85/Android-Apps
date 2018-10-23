package edu.gatech.seclass.sdpvocabquiz.vo;

import java.util.Calendar;

public class QuizStats {

    private float score;
    private Calendar practiceDate;
    private String studentName;
    private int quizId;
    private int studentId;

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public float getScore() { return score; }

    public void setScore(float score) {
        this.score = score;
    }

    public Calendar getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(Calendar practiceDate) { this.practiceDate = practiceDate; }

    public int getStudentId() { return studentId; }

    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
}
