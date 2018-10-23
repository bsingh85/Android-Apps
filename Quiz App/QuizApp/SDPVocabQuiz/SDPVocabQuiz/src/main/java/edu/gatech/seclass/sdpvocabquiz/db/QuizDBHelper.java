package edu.gatech.seclass.sdpvocabquiz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.ArrayMap;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;
import edu.gatech.seclass.sdpvocabquiz.vo.Quiz;
import edu.gatech.seclass.sdpvocabquiz.vo.QuizComparator;
import edu.gatech.seclass.sdpvocabquiz.vo.QuizStats;

public class QuizDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quiz.db";
    public static final String TAG_NAME = "DB_Logs";

    public static final int S_USERNAME_MAX_LENGTH = 25;

    // Student Table
    public static final String TABLE_STUDENT = "student";
    public static final String S_ID = "s_id";
    public static final String S_USERNAME = "s_username";
    public static final String S_MAJOR = "s_major";
    public static final String S_SEN_LEVEL = "s_sen_level";
    public static final String S_EMAIL = "s_email";
    public static final String QUERY_STUDENT = "create table " + TABLE_STUDENT + "("
            + S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + S_USERNAME + " TEXT NOT NULL UNIQUE, "
            + S_MAJOR + " TEXT NOT NULL, "
            + S_SEN_LEVEL + " TEXT NOT NULL, "
            + S_EMAIL + " TEXT NOT NULL, "
            + " CHECK ("+ S_SEN_LEVEL +" IN (\"Freshman\", \"Sophomore\", \"Junior\", \"Senior\", \"Grad\"))"
            +")";


    // Quiz Table
    public static final String TABLE_QUIZ = "quiz";
    public static final String Q_ID = "q_id";
    public static final String Q_QUIZNAME = "q_name";
    public static final String Q_AUTHOR_ID = "q_author_id";
    public static final String Q_DESC = "q_description";
    // This field will store all the words and it's definition in below format
    // word1=definition1|word2=definition2|word3=definition3|...|word10=definition10
    public static final String Q_WORDS = "q_words";
    // This field will store all incorrect options in pipe separate format
    // incorrect1|incorrect2|incorrect3|...|incorrect30
    public static final String Q_INCORRECT_OPTIONS = "q_incorrect_options";
    public static final String QUERY_QUIZ = "create table " + TABLE_QUIZ + "("
            + Q_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + Q_QUIZNAME + " TEXT NOT NULL UNIQUE, "
            + Q_AUTHOR_ID + " INTEGER NOT NULL , "
            + Q_DESC + " TEXT NOT NULL, "
            + Q_WORDS + " TEXT NOT NULL, "
            + Q_INCORRECT_OPTIONS + " TEXT NOT NULL, "
            + " FOREIGN KEY("+ Q_AUTHOR_ID +") REFERENCES "+ TABLE_STUDENT +"("+S_ID+") ON DELETE CASCADE"
            +")";


    // Quiz Stats Table
    public static final String TABLE_QUIZ_STATS = "quiz_stats";
    public static final String QS_ID = "qs_id";
    public static final String QS_S_ID = "s_id";
    public static final String QS_Q_ID = "q_id";
    public static final String QS_PRACTICE_DATE = "qs_practice_date";
    public static final String QS_PRACTICE_DATE_LOCAL = "qs_practice_date_local";
    public static final String QS_SCORE = "qs_score";
    public static final String QUERY_QUIZ_STATS = "create table " + TABLE_QUIZ_STATS + "("
            + QS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + QS_S_ID + " INTEGER NOT NULL, "
            + QS_Q_ID + " INTEGER NOT NULL , "
            + QS_PRACTICE_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + QS_SCORE + " REAL NOT NULL, "
            + " FOREIGN KEY("+ QS_S_ID +") REFERENCES "+ TABLE_STUDENT +"("+S_ID+"), "
            + " FOREIGN KEY("+ QS_Q_ID +") REFERENCES "+ TABLE_QUIZ +"("+Q_ID+") ON DELETE CASCADE"
            +")";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }


    public void onCreateNoTestData(SQLiteDatabase db){
        Log.d(TAG_NAME, "onCreate: " + QUERY_STUDENT);
        Log.d(TAG_NAME, "onCreate: " + QUERY_QUIZ);
        db.execSQL(QUERY_STUDENT);
        db.execSQL(QUERY_QUIZ);
        db.execSQL(QUERY_QUIZ_STATS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateNoTestData(db);
        insertTestData(db);

        // Test insert statement - Remove this from final code
        /* ContentValues contentValues = new ContentValues();
        contentValues.put(S_USERNAME, "rajan.jethva");
        contentValues.put(S_MAJOR, "M.S. Computer Science");
        contentValues.put(S_SEN_LEVEL, "Grad");
        contentValues.put(S_EMAIL, "rajan.jethva@gmail.com");
        long result = db.insert(TABLE_STUDENT, null, contentValues);
        Log.d(TAG_NAME, "InsertDB: " + result); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ_STATS);
        onCreate(db);
        insertTestData(db);
    }

    public void emptyDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete FROM " + TABLE_STUDENT);
        db.execSQL("Delete FROM " + TABLE_QUIZ);
        db.execSQL("Delete FROM " + TABLE_QUIZ_STATS);
        db.close();
    }

    public boolean insert(String username, String major, String sen_level, String email) {
        if (username == null
                || username.isEmpty()
                || username.length() > S_USERNAME_MAX_LENGTH
                || containsNonPrintableChar(username)) return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_USERNAME, username);
        contentValues.put(S_MAJOR, major);
        contentValues.put(S_SEN_LEVEL, sen_level);
        contentValues.put(S_EMAIL, email);
        long ins = db.insert(TABLE_STUDENT, null, contentValues);
        db.close();
        if(ins==-1) 
            return false;
        else 
            return true;
    }

    public int chkusername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + S_ID + " FROM " + TABLE_STUDENT + " WHERE " + S_USERNAME + "=?", new String[]{username});
        int userId;
        if (cursor.moveToFirst())
            userId = cursor.getInt(cursor.getColumnIndex(S_ID));
        else
            userId = -1;
        cursor.close();
        db.close();
        return userId;
    }

    public long insertQuiz(Quiz q){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Q_QUIZNAME, q.getQuizName());
        contentValues.put(Q_AUTHOR_ID, q.getQuizAuthor());
        contentValues.put(Q_DESC, q.getQuizDescription());
        contentValues.put(Q_WORDS,
                CreateQuizDBUtility.getInstance().getFormattedWordList(q.getWordList()));
        contentValues.put(Q_INCORRECT_OPTIONS,
                CreateQuizDBUtility.getInstance().getIncorrectOptionsList(q.getIncorrectOptions()));
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.insert(TABLE_QUIZ, null, contentValues);
        db.close();
        return inserted;
    }

    public boolean validUniqueThing(String fieldName, String thing){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] params = new String[]{thing};
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_QUIZ + " WHERE " + fieldName + " = ?" ,
                params);
        boolean valid = res.getCount() == 0;
        res.close();
        db.close();
        return valid;
    }


    public int removeQuiz(int quizId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        return db.delete(QuizDBHelper.TABLE_QUIZ, Q_ID + " = ?", new String[]{Integer.toString(quizId)});
    }

    public Quiz[] getAllQuizzes() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUIZ +
                " JOIN " + TABLE_STUDENT + " ON " + TABLE_QUIZ + "." + Q_AUTHOR_ID + " = " + TABLE_STUDENT + "." + S_ID
                , new String[0]);

        List<Quiz> quizzes = new ArrayList<Quiz>();
        if (cursor.moveToFirst()){

            quizzes.add(getNextQuiz(cursor));
            while(cursor.moveToNext()){
                quizzes.add(getNextQuiz(cursor));
            }
        }
        cursor.close();
        db.close();

        QuizStats[] stats = getAllQuizStats();

        ArrayMap<Integer,List<QuizStats>> quizStats = new ArrayMap<Integer, List<QuizStats>>();
        ArrayMap<Integer,List<QuizStats>> personalQuizStats = new ArrayMap<Integer, List<QuizStats>>();
        for(Quiz quiz : quizzes){
            quizStats.put(quiz.getQuizId(),new ArrayList<QuizStats>());
            personalQuizStats.put(quiz.getQuizId(),new ArrayList<QuizStats>());
        }
        for(QuizStats stat : stats){
            quizStats.get(stat.getQuizId()).add(stat);
            if (stat.getStudentId() == UserContext.getStudentId()){
                personalQuizStats.get(stat.getQuizId()).add(stat);
            }
        }
        for(Quiz quiz : quizzes){
            quiz.setQuizStatistics(quizStats.get(quiz.getQuizId()));
            quiz.setUserQuizStatistics(personalQuizStats.get(quiz.getQuizId()));
        }

        Collections.sort(quizzes, new QuizComparator()); //order correctly for display

        return quizzes.toArray(new Quiz[0]);
    }

    public QuizStats[] getAllQuizStats() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT qs.*, datetime(qs."+ QS_PRACTICE_DATE + ", 'localtime') as " + QS_PRACTICE_DATE_LOCAL + ", s." + S_USERNAME + " FROM " + TABLE_QUIZ_STATS +
                        " qs JOIN " + TABLE_STUDENT + " s ON qs." + QS_S_ID + " = s." + S_ID
                , new String[0]);
        List<QuizStats> quizStats = new ArrayList<QuizStats>();
        if (cursor.moveToFirst()){

            quizStats.add(getNextQuizStat(cursor));
            while(cursor.moveToNext()){
                quizStats.add(getNextQuizStat(cursor));
            }
        }
        cursor.close();
        db.close();
        return quizStats.toArray(new QuizStats[0]);
    }

    public long saveScore(int userId, int quizId, float score){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QS_S_ID, userId);
        contentValues.put(QS_Q_ID, quizId);
        contentValues.put(QS_SCORE, score);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_QUIZ_STATS, null, contentValues);
    }

    private static Quiz getNextQuiz(Cursor cursor){
        Quiz quiz = new Quiz();
        quiz.setQuizId(cursor.getInt(cursor.getColumnIndex(Q_ID)));
        quiz.setQuizName(cursor.getString(cursor.getColumnIndex(Q_QUIZNAME)));
        quiz.setQuizAuthor(cursor.getInt(cursor.getColumnIndex(S_ID)));
        quiz.setQuizDescription(cursor.getString(cursor.getColumnIndex(Q_DESC)));
        quiz.setWordList(CreateQuizDBUtility.getInstance().parseWordList(cursor.getString(cursor.getColumnIndex(Q_WORDS))));
        quiz.setIncorrectOptions(CreateQuizDBUtility.getInstance().parseIncorrectOptionsList(cursor.getString(cursor.getColumnIndex(Q_INCORRECT_OPTIONS))));
        return quiz;
    }
    private static QuizStats getNextQuizStat(Cursor cursor) throws ParseException {
        QuizStats quizStat = new QuizStats();
        quizStat.setQuizId(cursor.getInt(cursor.getColumnIndex(QS_Q_ID)));
        quizStat.setStudentId(cursor.getInt(cursor.getColumnIndex(QS_S_ID)));
        quizStat.setStudentName(cursor.getString(cursor.getColumnIndex(S_USERNAME)));
        quizStat.setScore(cursor.getFloat(cursor.getColumnIndex(QS_SCORE)));
        String stringDateTime = cursor.getString(cursor.getColumnIndex(QS_PRACTICE_DATE_LOCAL));
        Calendar t = new GregorianCalendar();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = isoFormat.parse(stringDateTime);
        t.setTime(dt);
        quizStat.setPracticeDate(t);
        return quizStat;
    }

    public void insertTestData(SQLiteDatabase db){
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Bharati','MS in Finance','Senior','bharati.link@gmail.com');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Hao','Oceanography','Junior','haoluogit@gmail.com');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Nathan','Computer Engineering','Sophomore','nathan@gmail.com');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Rajan','Computer Science','Freshman','rajan.jethva@gmail.com');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Alex','Computer Science','Grad','Alex.Orso@gatech.edu');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Will','Mathematics','Grad','Will.question@gatech.edu');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Erin','Physics','Grad','Erin.p@gmail.com');");
        db.execSQL("INSERT INTO student (s_username,s_major,s_sen_level,s_email) VALUES ('Q','MD in Ortho','Junior','q.wong@gatech.edu');");
        db.execSQL("INSERT INTO quiz (q_name,q_description,q_author_id,q_words,q_incorrect_options) VALUES ('ThreeQuiz','Thee Word Quiz','4','affable=friendly|articulate=well-spoken|parsimonious=stingy','cheap|sticky|reckless|curious|strict|smooth|quarrelsome|resentful|wasteful');");
        db.execSQL("INSERT INTO quiz (q_name,q_description,q_author_id,q_words,q_incorrect_options) VALUES ('FiveQuiz','Five Word Quiz','1','propinquity=closeness|pecuniary=financial|arbitrary=random|ambience=atmosphere|paucity=shortage','miracle|framework|longing|rewarding|unkind|envaious|imaginative|unresolvable|feeble|blame|publicity|formality|lonlieness|change|journey');");
        db.execSQL("INSERT INTO quiz (q_name,q_description,q_author_id,q_words,q_incorrect_options) VALUES ('OneQuiz','One Word Quiz','2','archaic=old','filmsy|imported|unknown');");
        db.execSQL("INSERT INTO quiz (q_name,q_description,q_author_id,q_words,q_incorrect_options) VALUES ('TwoQuiz','Two Word Quiz','3','ambivalence=uncertainity|emulate=copy','gossip|emergency|amazement|extend|hamper|yearning');");
        db.execSQL("INSERT INTO quiz (q_name,q_description,q_author_id,q_words,q_incorrect_options) VALUES ('VocabQuiz','Vocab Quiz','1','Indivious=calumnious|indefatigable=tireless|phlegmatic=stoic|probity=virtuousness|expiate=to make amend for|supine=lying on the back or face upward|meretricious=tawdrily and falsely attractive|imbroglio=embroilment|bellicose=warlike|picayune=triviality','overcritical|nagging|justifiable|enervated|proper|slovenly|impulsive|higgledy-piggledy|hot-blooded|emergence|misanthropy|mendacity|to renounce|to remove or return|to lose health|easily annoyed|placating behavior|exciting and adventurous|valuable and satisfying|filled with delight and wonder|of falsified origin|inhibition|frolic|nonchalance|constructive|alluring|repellent|adventurous|long-winded|glided');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('2','1','2007-01-01 10:00:00','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','3','2017-03-01 23:00:01','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('1','1','2018-05-01 01:00:02','33');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('2','2','2018-07-01 17:00:03','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('1','4','2018-10-01 03:00:04','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','5','2018-09-01 12:00:05','80');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','4','2018-07-01 11:00:06','50');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','1','2018-09-01 18:00:07','66');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('2','5','2018-02-01 13:00:08','70');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','2','2018-02-01 10:00:09','80');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('6','1','2018-03-01 10:00:10','66');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('5','3','2018-04-01 09:00:11','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('4','2','2018-04-01 10:00:12','20');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','5','2018-07-01 08:00:13','60');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('2','1','2018-01-01 10:00:14','66');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('1','3','2018-01-01 07:00:15','0');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','2','2018-01-01 10:00:16','40');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','1','2017-09-01 10:00:17','33');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('5','2','2018-01-01 10:00:18','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('4','4','2018-01-01 15:17:19','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','1','2016-11-01 10:00:20','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','3','2018-01-01 10:00:21','0');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','1','2017-12-01 14:11:22','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('4','4','2018-01-01 10:00:23','50');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('4','5','2018-09-01 16:17:24','50');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('5','5','2018-01-01 10:00:25','40');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('6','5','2018-04-01 22:49:26','30');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','5','2018-01-01 21:23:27','20');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','5','2017-11-01 23:11:28','10');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','5','2017-12-01 07:59:29','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','5','2017-12-09 08:30:30','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','3','2017-12-09 08:30:30','70');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','3','2018-01-01 15:17:19','80');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('3','3','2018-09-27 14:19:57','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('1','3','2018-07-25 18:19:03','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('4','3','2018-08-20 12:26:57','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('6','3','2018-04-14 19:02:34','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('7','3','2018-03-22 01:20:01','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','3','2018-02-27 12:15:32','100');");
        db.execSQL("INSERT INTO quiz_stats (s_id,q_id,qs_practice_date,qs_score) VALUES ('8','3','2018-04-16 03:00:00','100');");
    }

    public static boolean containsNonPrintableChar(String str){
        for (char c : str.toCharArray()){
            int cInt = (int)c;
            if (cInt < 23 || cInt > 126) return true;
        }
        return false;
    }
}
