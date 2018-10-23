package edu.gatech.seclass.sdpvocabquiz.vo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Quiz {

    public int quizId;
    public String quizName;
    public Word[] wordList;
    public String quizDescription;
    public String[] incorrectOptions;
    public int quizAuthor;
    public Calendar createdDate;
    public float firstScore;
    public Calendar firstScoreDate;
    public float highestScore;
    public Calendar highestScoreDate;
    public Calendar mostRecentPractice;
    public String firstUser;
    public String secondUser;
    public String thirdUser;

    public boolean practicedByCurrentUser;

    public int getQuizId(){
        return quizId;
    }

    public void setQuizId(int quizId){
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Word[] getWordList() {
        return wordList;
    }

    public void setWordList(Word[] wordList) {
        this.wordList = wordList;
    }

    public String[] getIncorrectOptions() {
        return incorrectOptions;
    }

    public void setIncorrectOptions(String[] incorrectOptions) {
        this.incorrectOptions = incorrectOptions;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public int getQuizAuthor() {
        return quizAuthor;
    }

    public void setQuizAuthor(int quizAuthor) {
        this.quizAuthor = quizAuthor;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserQuizStatistics(List<QuizStats> quizStats){
        if (quizStats.size() <= 0) return;
        practicedByCurrentUser = true;
        Collections.sort(quizStats,new QuizStatDateComparator());
        firstScore = quizStats.get(0).getScore();
        firstScoreDate = quizStats.get(0).getPracticeDate();
        mostRecentPractice = quizStats.get(quizStats.size() - 1).getPracticeDate();
        Collections.sort(quizStats,new QuizStatScoreComparator());
        highestScore = quizStats.get(0).getScore();
        highestScoreDate = quizStats.get(0).getPracticeDate();
    }

    public void setQuizStatistics(List<QuizStats> quizStats){
        if (quizStats.size() <= 0) return;
        Collections.sort(quizStats,new QuizStatDateComparator());

        List<String> hundredUsers = new ArrayList<>();
        for (QuizStats stat : quizStats){
            if (stat.getScore() < 100) continue;
            if (hundredUsers.contains(stat.getStudentName())) continue;

            hundredUsers.add(stat.getStudentName());
            if (hundredUsers.size() == 3) break;
        }

        Collections.sort(hundredUsers);
        if (hundredUsers.size() > 0){
            firstUser = hundredUsers.get(0);
            if (hundredUsers.size() > 1){
                secondUser = hundredUsers.get(1);
                if (hundredUsers.size() > 2){
                    thirdUser = hundredUsers.get(2);
                }
            }
        }
    }

    public float getFirstScore() {return firstScore;}
    public Calendar getFirstScoreDate() {return firstScoreDate;}

    public float getHighestScore() {return highestScore;}
    public Calendar getHighestScoreDate() {return highestScoreDate;}

    public Calendar getMostRecentPractice() {return mostRecentPractice;}

    public String getFirstUser() {return firstUser;}
    public void setFirstUser(String firstUser) {this.firstUser = firstUser;}

    public String getSecondUser() {return secondUser;}
    public void setSecondUser(String secondUser) {this.secondUser = secondUser;}

    public String getThirdUser() {return thirdUser;}
    public void setThirdUser(String thirdUser) {this.thirdUser = thirdUser;}
}
