package edu.gatech.seclass.sdpvocabquiz.vo;

import java.util.Comparator;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;

public class QuizComparator implements Comparator<Quiz>
{
    @Override
    public int compare(Quiz o1, Quiz o2) {
        if (o1.practicedByCurrentUser) {
            if (!o2.practicedByCurrentUser) {
                return -1;
            }
            return o2.getMostRecentPractice().compareTo(o1.getMostRecentPractice()); //order by descending date time
        } else if (o2.practicedByCurrentUser) {
            return 1;
        } else if (o1.getQuizAuthor() == UserContext.getStudentId() && o2.getQuizAuthor() != UserContext.getStudentId()) {
            return 1;
        } else if (o1.getQuizAuthor() != UserContext.getStudentId() && o2.getQuizAuthor() == UserContext.getStudentId()) {
            return -1;
        } else {
            return o1.getQuizName().compareTo(o2.getQuizName()); //order alphabetically
        }
    }
}