package edu.gatech.seclass.sdpvocabquiz;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class DatabaseTests {
    private QuizDBHelper db;
    private Context context;

    private final String username = "TestUser";
    private final String major = "CS";
    private final String senLevel = "Freshman";
    private final String email = "test@email.com";

    @Before
    public void createDb() {
       context = InstrumentationRegistry.getTargetContext();
       db = new QuizDBHelper(context);
       db.emptyDatabase();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void Failure_To_Register_Username_Already_Exists(){
        assertTrue(db.insert(username,major,senLevel,email));
        assertFalse(db.insert(username,major,senLevel,email));
    }

    @Test
    public void Failure_To_Register_Null_Username(){
        assertFalse(db.insert(null,major,senLevel,email));
    }

    @Test
    public void Failure_To_Register_Empty_Username(){
        assertFalse(db.insert("",major,senLevel,email));
    }

    @Test
    public void Failure_To_Register_Username_Too_Long(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < QuizDBHelper.S_USERNAME_MAX_LENGTH; i++){
            sb.append('a');
        }
        sb.append('a');
        assertFalse(db.insert(sb.toString(),major,senLevel,email));
    }

    @Test
    public void Failure_To_Register_Username_Non_Printable_Chars(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 20; i++){
            sb.append((char)i);
        }
        assertFalse(db.insert(sb.toString(),major,senLevel,email));
    }
}
