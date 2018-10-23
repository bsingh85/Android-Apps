package edu.gatech.seclass.sdpvocabquiz;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import edu.gatech.seclass.sdpvocabquiz.db.CreateQuizDBUtility;
import edu.gatech.seclass.sdpvocabquiz.vo.Word;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CreateQuizDBUtilityTest {
    @Test
    public void testCreateQuizDBUtilityGetInstance() {
        CreateQuizDBUtility cq = CreateQuizDBUtility.getInstance();
        Assert.assertTrue(cq != null );
    }

    @Test
    public void testGetFormattedOneWordList(){
        Word[] wordList = new Word[1];
        Word w1 = new Word();
        w1.setWord("Foo");
        w1.setDefinition("Bar");
        wordList[0] = w1;
        String s = CreateQuizDBUtility.getFormattedWordList(wordList);
        Assert.assertEquals(s, "Foo=Bar");
    }

    @Test
    public void testGetFormattedWordList(){
        Word[] wordList = new Word[2];
        Word w1 = new Word();
        w1.setWord("Foo");
        w1.setDefinition("Bar");
        Word w2 = new Word();
        w2.setWord("Bob");
        w2.setDefinition(("Cat"));
        wordList[0] = w1;
        wordList[1] = w2;
        String s = CreateQuizDBUtility.getFormattedWordList(wordList);
        Assert.assertEquals(s, "Foo=Bar|Bob=Cat");
    }

    @Test
    public void testGetFormattedEmptyWordList(){
        Word[] emptyWordList = null;
        Assert.assertEquals(CreateQuizDBUtility.getFormattedWordList(emptyWordList), StringUtils.EMPTY);
    }

    @Test
    public void testParseOneWordList(){
        Word[] wordList = CreateQuizDBUtility.parseWordList("Foo=Bar");
        Assert.assertEquals(wordList.length, 1);
        Assert.assertEquals(wordList[0].getWord(), "Foo");
        Assert.assertEquals(wordList[0].getDefinition(), "Bar");
    }

    @Test
    public void testParseTwoWordList(){
        Word[] wordList = CreateQuizDBUtility.parseWordList("Foo=Bar|Bob=Cat");
        Assert.assertEquals(wordList.length, 2);
        Assert.assertEquals(wordList[0].getWord(), "Foo");
        Assert.assertEquals(wordList[0].getDefinition(), "Bar");
        Assert.assertEquals(wordList[1].getWord(), "Bob");
        Assert.assertEquals(wordList[1].getDefinition(), "Cat");
    }

    @Test
    public void testParseEmptyWordList(){
        Word[] wordList = CreateQuizDBUtility.parseWordList(StringUtils.EMPTY);
        Assert.assertNull(wordList);
    }

    @Test
    public void testGetIncorrectOptionsList(){
        String[] incorrectOptionList = new String[]{"Foo","Bar","Bob","Cat"};
        Assert.assertEquals(CreateQuizDBUtility.getIncorrectOptionsList(incorrectOptionList),"Foo|Bar|Bob|Cat");
    }

    @Test
    public void testEmptyIncorectOptionsList(){
        String[] incorrectOptionList = null;
        Assert.assertEquals(CreateQuizDBUtility.getIncorrectOptionsList(incorrectOptionList), StringUtils.EMPTY);
    }

    @Test
    public void testParseIncorrectOptionsList(){
        String [] parseIncorrectOptionsList = CreateQuizDBUtility.parseIncorrectOptionsList("Foo|Bar|Bob|Cat");
        Assert.assertEquals( parseIncorrectOptionsList.length, 4);
        Assert.assertEquals(parseIncorrectOptionsList[0], "Foo");
        Assert.assertEquals(parseIncorrectOptionsList[1], "Bar");
        Assert.assertEquals(parseIncorrectOptionsList[2], "Bob");
        Assert.assertEquals(parseIncorrectOptionsList[3], "Cat");
    }

}