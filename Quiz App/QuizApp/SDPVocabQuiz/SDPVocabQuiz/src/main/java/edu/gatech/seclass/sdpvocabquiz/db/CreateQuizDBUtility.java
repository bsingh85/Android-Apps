package edu.gatech.seclass.sdpvocabquiz.db;


import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import edu.gatech.seclass.sdpvocabquiz.vo.Word;

public class CreateQuizDBUtility {

    public static final String DATABASE_NAME = QuizDBHelper.DATABASE_NAME;
    public static final String TAG_NAME = "create_quiz_db_Logs";
    private static CreateQuizDBUtility createQuizDBUtility = new CreateQuizDBUtility();

    private CreateQuizDBUtility(){ }

    public static CreateQuizDBUtility getInstance(){
        return createQuizDBUtility;
    }

    public static String getFormattedWordList(Word[] wordList){
        StringBuilder s = new StringBuilder();
        if ( wordList == null ){
            return "";
        }
        for (Word word : wordList){
            s.append(word.getWord());
            s.append("=");
            s.append(word.getDefinition());
            s.append("|");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    public static Word[] parseWordList(String wordListString){
        if (StringUtils.isEmpty(wordListString))
            return null;
        String[] wordStrings = wordListString.split("\\|");
        List<Word> wordsList = new ArrayList<>();
        for (String wordString : wordStrings){
            String[] pieces = wordString.split("=");
            Word parsed = new Word();
            parsed.setWord(pieces[0]);
            parsed.setDefinition(pieces[1]);
            wordsList.add(parsed);
        }
        return wordsList.toArray(new Word[0]);
    }

    public static String getIncorrectOptionsList(String[] incorrectOptionList){

        // Only work with API 26
        //String.join("|", incorrectOptionList);

        // Only work with API 24
        //return incorrectOptionList.stream().collect(Collectors.joining("|"));

        if ( null == incorrectOptionList ){
            return StringUtils.EMPTY;
        }
        StringBuilder s = new StringBuilder();
        for (String incorrectOption : incorrectOptionList){
            s.append(incorrectOption);
            s.append("|");
        }
        if (s.length() > 1)
            return s.toString().substring(0, s.length() - 1);
        else
            return StringUtils.EMPTY;
    }
    public static String[] parseIncorrectOptionsList(String incorrectOptionsListString){
        return incorrectOptionsListString.split("\\|");
    }
}
