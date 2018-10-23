package edu.gatech.seclass.sdpvocabquiz.vo;

import java.util.Comparator;

public class QuizStatScoreComparator implements Comparator<QuizStats>
{
    @Override
    public int compare(QuizStats o1, QuizStats o2) {
        return new Float(o2.getScore()).compareTo(new Float(o1.getScore()));
    }
}
