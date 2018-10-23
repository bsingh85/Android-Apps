package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;
import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;
import edu.gatech.seclass.sdpvocabquiz.vo.Quiz;
import edu.gatech.seclass.sdpvocabquiz.vo.Word;

public class PracticeQuizActivity extends AppCompatActivity {

    QuizDBHelper quizHelper;
    int wordIndex = 0;
    int correctIndex = 0;
    int correctCount = 0;
    TextView percentCorrectView;
    ProgressBar progressBar;
    TextView wordView;
    Button option0Definition;
    Button option1Definition;
    Button option2Definition;
    Button option3Definition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_quiz);

        quizHelper = new QuizDBHelper(this);

        percentCorrectView = findViewById(R.id.percentCorrect);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setMax(UserContext.getCurrentQuiz().wordList.length);

        wordView = findViewById(R.id.word);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(v.getTag().toString());
                Toast toast;
                if (index == correctIndex){
                    correctCount++;
                    toast = Toast.makeText(PracticeQuizActivity.this,"correct!",Toast.LENGTH_SHORT);
                } else if (index < correctCount) {
                    toast = Toast.makeText(PracticeQuizActivity.this,"incorrect :(",Toast.LENGTH_SHORT);
                } else{
                    return;
                }
                toast.show();
                wordIndex++;
                Clear();
                Render();
            }
        };
        option0Definition = findViewById(R.id.definition0);
        option1Definition = findViewById(R.id.definition1);
        option2Definition = findViewById(R.id.definition2);
        option3Definition = findViewById(R.id.definition3);

        option0Definition.setOnClickListener(listener);
        option1Definition.setOnClickListener(listener);
        option2Definition.setOnClickListener(listener);
        option3Definition.setOnClickListener(listener);

        final Button cancel = findViewById(R.id.p_q_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserContext.finishQuiz();
                Intent i = new Intent(PracticeQuizActivity.this, OperationsActivity.class);
                startActivity(i);
            }
        });
        Render();
    }

    private void Clear(){
        wordView.setText("");
        option0Definition.setText("");
        option1Definition.setText("");
        option2Definition.setText("");
        option3Definition.setText("");
    }

    private void Render(){
        float percentCorrect;
        if (wordIndex == 0){
            percentCorrect = 0;
        }else{
            percentCorrect = ((float)correctCount/(float)wordIndex)*100;
        }
        percentCorrectView.setText("(" +correctCount+ "/" + wordIndex+ " correct) " + String.valueOf(Math.round(percentCorrect) + "%"));
        progressBar.setProgress(wordIndex);

        //todo: make word list randomly ordered
        if (wordIndex < UserContext.getCurrentQuiz().wordList.length){
            Word nextWord = UserContext.getCurrentQuiz().wordList[wordIndex];
            wordView.setText(nextWord.getWord());
            correctIndex = (int)Math.round(Math.random() * 3);
            setDefinition(correctIndex, nextWord.getDefinition());

            ArrayList<String> availableIncorrectChoices = new ArrayList<>(Arrays.asList(UserContext.getCurrentQuiz().getIncorrectOptions()));
            for(int i = 0; i < UserContext.getCurrentQuiz().wordList.length; i++){
                if (i == wordIndex) continue;
                availableIncorrectChoices.add(UserContext.getCurrentQuiz().wordList[i].getDefinition());
            }
            for (int i = 0; i < 4; i++){
                if (i == correctIndex) continue;
                int random = (int)Math.round(Math.random() * (availableIncorrectChoices.size() - 1));
                String choice = availableIncorrectChoices.get(random);
                setDefinition(i, choice);
                availableIncorrectChoices.remove(choice);
            }
        } else {
            option0Definition.setOnClickListener(null);
            option1Definition.setOnClickListener(null);
            option2Definition.setOnClickListener(null);
            option3Definition.setOnClickListener(null);
            //todo: save score
            long result = quizHelper.saveScore(UserContext.getStudentId(),UserContext.getCurrentQuiz().getQuizId(),percentCorrect);
            UserContext.finishQuiz();
            Toast.makeText(PracticeQuizActivity.this,"You got " + correctCount + " correct out of " + wordIndex + ": " + Math.round(percentCorrect) + "%",Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(PracticeQuizActivity.this, OperationsActivity.class);
                    startActivity(i);
                }
            }, 5000);


        }
    }

    private void setDefinition(int index, String value){
        switch(index){
            case 0:
                option0Definition.setText(value);
                break;
            case 1:
                option1Definition.setText(value);
                break;
            case 2:
                option2Definition.setText(value);
                break;
            case 3:
                option3Definition.setText(value);
                break;
        }
    }
}
