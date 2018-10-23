package edu.gatech.seclass.sdpvocabquiz.vo;

import java.util.Comparator;

public class QuizStatDateComparator implements Comparator<QuizStats>
{
    @Override
    public int compare(QuizStats o1, QuizStats o2) {
        return o1.getPracticeDate().compareTo(o2.getPracticeDate());
    }
}

