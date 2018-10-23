package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;
import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;
import edu.gatech.seclass.sdpvocabquiz.vo.Quiz;

public class OperationsActivity extends AppCompatActivity {
    Button createquiz;
    QuizDBHelper quizHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        quizHelper = new QuizDBHelper(this);
        Intent i = getIntent();

        LinearLayout quizLayout = (LinearLayout)findViewById(R.id.quizLayout);

        Quiz[] allQuizzes = new Quiz[0];
        try {
            allQuizzes = quizHelper.getAllQuizzes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (allQuizzes.length > 0) {
            boolean isFirst = true;
            for (Quiz quiz : allQuizzes) {
                quizLayout.addView(CreateLayoutForQuiz(quiz,isFirst));
                isFirst = false;
            }
        } else {
            EditText noQuizzes = new EditText(this);
            noQuizzes.setText("[No Quizzes]");
            noQuizzes.setFocusable(false);
            quizLayout.addView(noQuizzes);
        }
        createquiz = (Button) findViewById(R.id.ops_button_create_quiz);
        createquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OperationsActivity.this, CreateQuizActivity.class);
                startActivity(i);
            }
        });
        Button logout = findViewById(R.id.ops_button_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserContext.logout();
                Intent i = new Intent(OperationsActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private View CreateLayoutForQuiz(final Quiz quiz, boolean isFirst){
        LinearLayout quizContainer = new LinearLayout(this);
        quizContainer.setOrientation(LinearLayout.VERTICAL);
        final RelativeLayout quizHeader = new RelativeLayout(this);
        quizHeader.setBackground(getDrawable(R.drawable.scroll_view_bg));

        final LinearLayout quizBody = new LinearLayout(this);
        quizBody.setOrientation(LinearLayout.HORIZONTAL);
        //if (!isFirst){
        quizBody.setVisibility(View.GONE);
        //}

        //populate header contents
        TextView quizTitleView = new TextView(this);
        SpannableString content1 = new SpannableString("QuizName: " + quiz.quizName + "\nDescription: " + quiz.quizDescription);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        quizTitleView.setText(content1);
        quizTitleView.setTextColor(Color.BLACK);
        quizTitleView.setTypeface(null, Typeface.BOLD);
        quizTitleView.setEllipsize(TextUtils.TruncateAt.END);
        quizTitleView.setPadding(5,5,5,5);

        RelativeLayout.LayoutParams alignLeftParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        alignLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        alignLeftParams.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        quizTitleView.setLayoutParams(alignLeftParams);

        quizHeader.addView(quizTitleView);

        Button actionButton = new Button(this);
        RelativeLayout.LayoutParams alignRightParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        alignRightParams.setMargins(10,20,10,20);
        alignRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        actionButton.setLayoutParams(alignRightParams);

        if (quiz.quizAuthor == UserContext.getStudentId()){
            actionButton.setWidth(40);
            actionButton.setText(" Remove ");
            actionButton.setBackgroundColor(getColor(R.color.colorFadRed));
            actionButton.setTextColor(Color.WHITE);
            actionButton.setTypeface(actionButton.getTypeface(), Typeface.BOLD);

            actionButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                long removed = quizHelper.removeQuiz(quiz.quizId);
                if (removed == 1) {
                    Toast.makeText(getApplicationContext(),quiz.getQuizName() + " was removed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(OperationsActivity.this, OperationsActivity.class);
                    startActivity(i);
                    return;
                }
                Toast.makeText(getApplicationContext(),removed + " quizzes were removed", Toast.LENGTH_LONG).show();

                }
            });
        } else{
            actionButton.setWidth(40);
            actionButton.setText(" Practice ");
            actionButton.setBackgroundColor(getColor(R.color.colorFadBlue));
            actionButton.setTextColor(Color.WHITE);
            actionButton.setTypeface(actionButton.getTypeface(), Typeface.BOLD);
            actionButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    UserContext.startQuiz(quiz);

                    Intent i = new Intent(OperationsActivity.this, PracticeQuizActivity.class);
                    startActivity(i);
                }
            });

        }
        quizHeader.addView(actionButton);
        //populate body contents
        if (quiz.practicedByCurrentUser) {
            //only show first and highest scores if they are relevant for the current user
            LinearLayout leftPanel = new LinearLayout(this);
            leftPanel.setOrientation(LinearLayout.VERTICAL);
            leftPanel.setPadding(5,5,15,5);

            LinearLayout firstScoreText = new LinearLayout(this);
            firstScoreText.setOrientation(LinearLayout.HORIZONTAL);

            TextView firstScore = new TextView(this);
            firstScore.setText("First Score: " );
            firstScore.setTextColor(getColor(R.color.colorFadBlue));
            firstScore.setTypeface(null, Typeface.BOLD);
            firstScoreText.addView(firstScore);

            TextView firstScoreValue = new TextView(this);
            firstScoreValue.setText(String.valueOf(Math.round(quiz.getFirstScore())) + '%');
            firstScoreValue.setTextColor(Color.BLACK);
            firstScoreText.addView(firstScoreValue);

            leftPanel.addView(firstScoreText);

            TextView firstScoreDateTime = new TextView(this);
            firstScoreDateTime.setText("on " + String.format("%1$tA %1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp",quiz.getFirstScoreDate()));
            firstScoreDateTime.setTextColor(Color.BLACK);
            leftPanel.addView(firstScoreDateTime);

            LinearLayout highestScoreText = new LinearLayout(this);
            highestScoreText.setOrientation(LinearLayout.HORIZONTAL);

            TextView highestScore = new TextView(this);
            highestScore.setTypeface(null, Typeface.BOLD);
            highestScore.setText("Highest Score: " );
            highestScore.setTextColor(getColor(R.color.colorFadBlue));
            highestScoreText.addView(highestScore);

            TextView highestScoreValue = new TextView(this);
            highestScoreValue.setText(String.valueOf(Math.round(quiz.getHighestScore())) + '%');
            highestScoreValue.setTextColor(Color.BLACK);
            highestScoreText.addView(highestScoreValue);

            leftPanel.addView(highestScoreText);

            TextView highestScoreDateTime = new TextView(this);
            highestScoreDateTime.setText("on " + String.format("%1$tA %1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp",quiz.getHighestScoreDate()));
            highestScoreDateTime.setTextColor(Color.BLACK);
            leftPanel.addView(highestScoreDateTime);

            quizBody.addView(leftPanel);
        }

        //always show first 3 100% users if they exist
        LinearLayout rightPanel = new LinearLayout(this);
        rightPanel.setOrientation(LinearLayout.VERTICAL);
        rightPanel.setPadding(110,2,2,2);

        TextView first3OneHundredsLabel = new TextView(this);
        SpannableString content = new SpannableString("First 3 100%");
        //content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        first3OneHundredsLabel.setText(content);
        first3OneHundredsLabel.setTextColor(getColor(R.color.colorFadBlue));
        first3OneHundredsLabel.setTypeface(null, Typeface.BOLD);

        rightPanel.addView(first3OneHundredsLabel);
        String firstUser = quiz.getFirstUser();
        if (firstUser == null){
            TextView noHundred = new TextView(this);
            noHundred.setText("[Nobody has scored 100% yet]");
            rightPanel.addView(noHundred);
        } else {
            TextView firstHundred = new TextView(this);
            firstHundred.setText(firstUser);
            firstHundred.setTextColor(Color.BLACK);
            rightPanel.addView(firstHundred);
            String secondUser = quiz.getSecondUser();
            if (secondUser != null){
                TextView secondHundred = new TextView(this);
                secondHundred.setText(secondUser);
                secondHundred.setTextColor(Color.BLACK);
                rightPanel.addView(secondHundred);
                String thirdUser = quiz.getThirdUser();
                if (thirdUser != null){
                    TextView thirdHundred = new TextView(this);
                    thirdHundred.setText(thirdUser);
                    thirdHundred.setTextColor(Color.BLACK);
                    rightPanel.addView(thirdHundred);
                }
            }
        }
        quizBody.addView(rightPanel);

        quizHeader.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int visibility;
                switch(quizBody.getVisibility()){
                    case View.VISIBLE:
                        visibility = View.GONE;
                        break;
                    case View.GONE:
                        visibility = View.VISIBLE;
                        break;
                    default: return; //todo: handle this case
                }
                quizBody.setVisibility(visibility);
            }
        });
        quizContainer.addView(quizHeader);
        quizContainer.addView(quizBody);
        return quizContainer;
    }
}
