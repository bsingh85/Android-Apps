package edu.gatech.seclass.sdpvocabquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;
import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;
import edu.gatech.seclass.sdpvocabquiz.vo.Quiz;
import edu.gatech.seclass.sdpvocabquiz.vo.Word;

public class CreateQuizActivity extends AppCompatActivity {

    List<EditText> wordTextBoxes;
    List<EditText> definitionTextBoxes;
    List<EditText> optionTextBoxes;
    List<Button> addWordButtons;
    List<Word> words;
    String[] transientWords = new String[10];
    String[] transientDefinitions = new String[10];
    List<String> incorrectDefinitions;
    EditText quizName;
    EditText quizDescription;
    EditText word1, word2, word3, word4, word5, word6, word7, word8, word9, word10;
    EditText definition1, definition2, definition3, definition4,
                definition5, definition6, definition7, definition8,
                definition9, definition10;
    EditText option1, option2, option3, option4, option5, option6, option7, option8, option9,
             option10, option11, option12, option13, option14, option15, option16, option17,
             option18, option19, option20, option21, option22, option23, option24, option25,
             option26, option27, option28, option29, option30;
    Button addWordButton1, addWordButton2, addWordButton3, addWordButton4,
            addWordButton5, addWordButton6, addWordButton7, addWordButton8,
            addWordButton9;

    Button cancelQuizButton;
    Button resetQuizButton;
    Button createQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        //Get screen snapshot
        wordTextBoxes = new ArrayList<>();
        definitionTextBoxes = new ArrayList<>();
        addWordButtons = new ArrayList<>();
        optionTextBoxes = new ArrayList<>();
        incorrectDefinitions = new ArrayList<>();
        words = new ArrayList<>();
        quizName = findViewById(R.id.c_q_textbox_quiz_name);
        quizDescription = findViewById(R.id.c_q_textbox_quiz_description);
        word1 = findViewById(R.id.word1);
        wordTextBoxes.add(word1);
        word2 = findViewById(R.id.word2);
        wordTextBoxes.add(word2);
        word3 = findViewById(R.id.word3);
        wordTextBoxes.add(word3);
        word4 = findViewById(R.id.word4);
        wordTextBoxes.add(word4);
        word5 = findViewById(R.id.word5);
        wordTextBoxes.add(word5);
        word6 = findViewById(R.id.word6);
        wordTextBoxes.add(word6);
        word7 = findViewById(R.id.word7);
        wordTextBoxes.add(word7);
        word8 = findViewById(R.id.word8);
        wordTextBoxes.add(word8);
        word9 = findViewById(R.id.word9);
        wordTextBoxes.add(word9);
        word10 = findViewById(R.id.word10);
        wordTextBoxes.add(word10);
        definition1 = findViewById(R.id.definition1);
        definitionTextBoxes.add(definition1);
        definition2 = findViewById(R.id.definition2);
        definitionTextBoxes.add(definition2);
        definition3 = findViewById(R.id.definition3);
        definitionTextBoxes.add(definition3);
        definition4 = findViewById(R.id.definition4);
        definitionTextBoxes.add(definition4);
        definition5 = findViewById(R.id.definition5);
        definitionTextBoxes.add(definition5);
        definition6 = findViewById(R.id.definition6);
        definitionTextBoxes.add(definition6);
        definition7 = findViewById(R.id.definition7);
        definitionTextBoxes.add(definition7);
        definition8 = findViewById(R.id.definition8);
        definitionTextBoxes.add(definition8);
        definition9 = findViewById(R.id.definition9);
        definitionTextBoxes.add(definition9);
        definition10 = findViewById(R.id.definition10);
        definitionTextBoxes.add(definition10);
        addWordButton1 = findViewById(R.id.word_button1);
        addWordButtons.add(addWordButton1);
        addWordButton2 = findViewById(R.id.word_button2);
        addWordButtons.add(addWordButton2);
        addWordButton3 = findViewById(R.id.word_button3);
        addWordButtons.add(addWordButton3);
        addWordButton4 = findViewById(R.id.word_button4);
        addWordButtons.add(addWordButton4);
        addWordButton5 = findViewById(R.id.word_button5);
        addWordButtons.add(addWordButton5);
        addWordButton6 = findViewById(R.id.word_button6);
        addWordButtons.add(addWordButton6);
        addWordButton7 = findViewById(R.id.word_button7);
        addWordButtons.add(addWordButton7);
        addWordButton8 = findViewById(R.id.word_button8);
        addWordButtons.add(addWordButton8);
        addWordButton9 = findViewById(R.id.word_button9);
        addWordButtons.add(addWordButton9);
        option1 = findViewById(R.id.option1);
        optionTextBoxes.add(option1);
        option2 = findViewById(R.id.option2);
        optionTextBoxes.add(option2);
        option3 = findViewById(R.id.option3);
        optionTextBoxes.add(option3);
        option4 = findViewById(R.id.option4);
        optionTextBoxes.add(option4);
        option5 = findViewById(R.id.option5);
        optionTextBoxes.add(option5);
        option6 = findViewById(R.id.option6);
        optionTextBoxes.add(option6);
        option7 = findViewById(R.id.option7);
        optionTextBoxes.add(option7);
        option8 = findViewById(R.id.option8);
        optionTextBoxes.add(option8);
        option9 = findViewById(R.id.option9);
        optionTextBoxes.add(option9);
        option10 = findViewById(R.id.option10);
        optionTextBoxes.add(option10);
        option11 = findViewById(R.id.option11);
        optionTextBoxes.add(option11);
        option12 = findViewById(R.id.option12);
        optionTextBoxes.add(option12);
        option13 = findViewById(R.id.option13);
        optionTextBoxes.add(option13);
        option14 = findViewById(R.id.option14);
        optionTextBoxes.add(option14);
        option15 = findViewById(R.id.option15);
        optionTextBoxes.add(option15);
        option16 = findViewById(R.id.option16);
        optionTextBoxes.add(option16);
        option17 = findViewById(R.id.option17);
        optionTextBoxes.add(option17);
        option18 = findViewById(R.id.option18);
        optionTextBoxes.add(option18);
        option19 = findViewById(R.id.option19);
        optionTextBoxes.add(option19);
        option20 = findViewById(R.id.option20);
        optionTextBoxes.add(option20);
        option21 = findViewById(R.id.option21);
        optionTextBoxes.add(option21);
        option22 = findViewById(R.id.option22);
        optionTextBoxes.add(option22);
        option23 = findViewById(R.id.option23);
        optionTextBoxes.add(option23);
        option24 = findViewById(R.id.option24);
        optionTextBoxes.add(option24);
        option25 = findViewById(R.id.option25);
        optionTextBoxes.add(option25);
        option26 = findViewById(R.id.option26);
        optionTextBoxes.add(option26);
        option27 = findViewById(R.id.option27);
        optionTextBoxes.add(option27);
        option28 = findViewById(R.id.option28);
        optionTextBoxes.add(option28);
        option29 = findViewById(R.id.option29);
        optionTextBoxes.add(option29);
        option30 = findViewById(R.id.option30);
        optionTextBoxes.add(option30);
        cancelQuizButton = findViewById(R.id.c_q_cancel);
        resetQuizButton = findViewById(R.id.c_q_button_reset);
        createQuizButton = findViewById(R.id.c_q_button_create);


        //Process Quiz name
        quizName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( !hasFocus ){
                    isQuizNameValid();
                }
            }
        });

        //Process Quiz name
        quizDescription.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( !hasFocus ){
                    isQuizDescriptionValid();
                }
            }
        });

        //Process all word text box for empty
        for (int i = 0; i < 10; i++) {
            final int count = i;
            ((EditText)wordTextBoxes.toArray()[i])
                    .setOnFocusChangeListener(new View.OnFocusChangeListener(){
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    EditText textBox = (EditText)wordTextBoxes.toArray()[count];
                    String s =  textBox.getText().toString();
                    if ( !hasFocus && ( isEmptyWord(s)
                            || !isTransientWordUnique(transientWords,s, count)
                            || StringUtils.isNumeric(s))){
                        textBox.setError(getString(R.string.c_q_error_invalid_word));
                        //manageFocus(textBox);
                    } else {
                        transientWords[count] = s;
                    }
                    hideKeyboard(v);
                }
            });
        }

        //Process all definition text box for empty
        for (int i = 0; i < 10; i++) {
            final int count = i;
            ((EditText)definitionTextBoxes.toArray()[i])
                .setOnFocusChangeListener(new View.OnFocusChangeListener(){
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if ( !hasFocus ){
                            EditText textBox = (EditText)definitionTextBoxes.toArray()[count];
                            String s = textBox.getText().toString();
                            if ( isEmptyWord(s)
                                    || !isTransientWordUnique(transientDefinitions,s,count)
                                    || StringUtils.isNumeric(textBox.getText().toString())) {
                                textBox.setError(getString(R.string.c_q_error_invalid_definition));
                                //manageFocus(textBox);
                            } else {
                                transientDefinitions[count] = s;
                            }
                        }
                        hideKeyboard(v);
                    }
                });
        }

        //Process all "Add Word" buttons
        for (int i = 0; i < 9; i++) {
            final int count = i;
            ((Button)addWordButtons.toArray()[i])
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        EditText editTextWord = wordTextBoxes.get(count);
                        EditText editTextDefinition = definitionTextBoxes.get(count);
                        boolean proceed = true;
                        if ( isEmptyWord(editTextWord.getText().toString()) ) {
                            editTextWord.setError(getString(R.string.c_q_error_invalid_word));
                            //manageFocus(editTextWord);
                            proceed = false;
                        }
                        if ( proceed && !isWordUnique(words, editTextWord.getText().toString())){
                            editTextWord.setError(getString(R.string.c_q_error_unique_word));
                            //manageFocus(editTextWord);
                            proceed = false;
                        }
                        if ( proceed && isEmptyWord(editTextDefinition.getText().toString())) {
                            editTextDefinition.setError(getString(R.string.c_q_error_invalid_definition));
                            //manageFocus(editTextDefinition);
                            proceed = false;
                        }
                        if ( proceed ) {
                            int next = count + 1;
                            //Enable next set of textbox and buttons
                            EditText nextWord = wordTextBoxes.get(next);
                            nextWord.setVisibility(View.VISIBLE);
                            nextWord.setEnabled(true);

                            EditText nextDefinition = definitionTextBoxes.get(next);
                            nextDefinition.setVisibility(View.VISIBLE);
                            nextDefinition.setEnabled(true);

                            if ( next < 9 ){
                                Button nextAddWordButton = addWordButtons.get(next);
                                nextAddWordButton.setVisibility(View.VISIBLE);
                                nextAddWordButton.setEnabled(true);
                            }

                            v.setEnabled(false);
                            v.setVisibility(View.INVISIBLE);

                            EditText nextOption1 = optionTextBoxes.get(next * 3);
                            nextOption1.setEnabled(true);
                            nextOption1.setVisibility(View.VISIBLE);

                            EditText nextOption2 = optionTextBoxes.get(next * 3 + 1);
                            nextOption2.setEnabled(true);
                            nextOption2.setVisibility(View.VISIBLE);

                            EditText nextOption3 = optionTextBoxes.get(next * 3 + 2);
                            nextOption3.setEnabled(true);
                            nextOption3.setVisibility(View.VISIBLE);


                            hideKeyboard(v);
                        }
                    }
                });
        }

        //Save Quiz
        createQuizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean proceed = true;
                if ( !isQuizNameValid() ) proceed = false;
                if ( proceed && ! isQuizDescriptionValid() ) proceed = false;
                if ( proceed && ! isWordNamesValid() ) proceed = false;
                if ( proceed && ! isDefinitionsValid() ) proceed = false;
                if ( proceed ){

                    //iterate over all textbboxes and create words list
                    for( int i = 0; i<10; i++ ){
                        EditText wordTextBox = wordTextBoxes.get(i);
                        EditText definitionTextBox = definitionTextBoxes.get(i);
                        EditText option1TextBox = optionTextBoxes.get(i*3);
                        EditText option2TextBox = optionTextBoxes.get(i*3 + 1);
                        EditText option3TextBox = optionTextBoxes.get(i*3 + 2);
                        if ( wordTextBox.isEnabled() ){
                            Word w = new Word();
                            w.setWord(wordTextBox.getText().toString().trim());
                            w.setDefinition(definitionTextBox.getText().toString().trim());
                            words.add(w);
                        }
                        if ( option1TextBox.isEnabled() ){
                            if ( !isOptionValid(option1TextBox.getText().toString().trim())) {
                                option1TextBox.setError("Invalid option");
                                proceed = false;
                                break;
                            } else {
                                incorrectDefinitions.add(option1TextBox.getText().toString().trim());
                            }
                        }
                        if ( option2TextBox.isEnabled() ){
                            if ( !isOptionValid(option2TextBox.getText().toString().trim())) {
                                option2TextBox.setError("Invalid option");
                                proceed = false;
                                break;
                            } else {
                                incorrectDefinitions.add(option2TextBox.getText().toString().trim());
                            }
                        }
                        if ( option3TextBox.isEnabled() ){
                            if ( !isOptionValid(option3TextBox.getText().toString().trim())) {
                                option3TextBox.setError("Invalid option");
                                proceed = false;
                                break;
                            } else {
                                incorrectDefinitions.add(option3TextBox.getText().toString().trim());
                            }
                        }
                    }
                    if ( proceed ){
                        Quiz q = new Quiz();
                        q.setQuizName(quizName.getText().toString());
                        q.setQuizDescription(quizDescription.getText().toString());
                        q.setQuizAuthor(UserContext.getStudentId());
                        q.setWordList(words.toArray(new Word[words.size()]));
                        q.setIncorrectOptions(incorrectDefinitions.toArray(new String[incorrectDefinitions.size()]));
                        long results = saveQuiz(q);
                        if ( results != -1) {
                            Toast.makeText(getApplicationContext(), q.getQuizName() + " is created",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), OperationsActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Oops! Looks like something went wrong. Please try again.",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        incorrectDefinitions = new ArrayList<>();
                        words = new ArrayList<>();
                    }
                }
            }
        });

        resetQuizButton.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {
                   reset();
               }
        });
        cancelQuizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reset();
                startActivity(new Intent(getApplicationContext(), OperationsActivity.class));
            }
        });
    }

    /**
     * This method will be used to check if quiz name is valid
     * @return boolean
     */
    public boolean isQuizNameValid(){
        if (StringUtils.isEmpty(quizName.getText().toString().trim())){
            quizName.setError(getString(R.string.c_q_error_invalid_quiz_name));
            //manageFocus(quizName);
            return false;
        }
        else if (!new QuizDBHelper(this).validUniqueThing(
                QuizDBHelper.Q_QUIZNAME, quizName.getText().toString().trim())){
                quizName.setError(getString(R.string.c_q_error_quiz_name));
                //manageFocus(quizName);
                return false;
        }
        return true;
    }

    /**
     * This method will verify if the quiz description is not empty
     * This method will be used on every button click
     * @return boolean
     */
    public boolean isQuizDescriptionValid(){
        if (StringUtils.isEmpty(quizDescription.getText().toString().trim())
                || StringUtils.isNumeric(quizDescription.getText().toString().trim())){
            quizDescription.setError(getString(R.string.c_q_error_quiz_description));
            //manageFocus(quizDescription);
            return false;
        }
        enableDefaultUI();
        return true;
    }

    private void enableDefaultUI(){

        word1.setEnabled(true);
        word1.setVisibility(View.VISIBLE);
        definition1.setEnabled(true);
        definition1.setVisibility(View.VISIBLE);
        addWordButton1.setEnabled(true);
        addWordButton1.setVisibility(View.VISIBLE);
        createQuizButton.setEnabled(true);
        option1.setEnabled(true);
        option1.setVisibility(View.VISIBLE);
        option2.setEnabled(true);
        option2.setVisibility(View.VISIBLE);
        option3.setEnabled(true);
        option3.setVisibility(View.VISIBLE);
    }

    /**
     * This method will verify if the word textbox is empty or not
     * @param word String
     * @return boolean
     */
    public boolean isEmptyWord(String word) {
        if ( StringUtils.isEmpty(word.trim()) )
            return true;
        else
            return false;
    }

    /**
     * This method will verify if the word is unique or not.
     * This method will be used on every "Add Word" button click
     * @param words List<Word>
     * @param word String
     * @return boolean
     */
    public boolean isTransientWordUnique(String[] words, String word, int i) {
        for ( int count=0; count< words.length; count++){
            if(count==i)
                continue;
            if(StringUtils.equalsIgnoreCase(words[count], word.trim())){
                return false;
            }
        }
        return true;
    }

    /**
     * This method will verify if the word is unique or not.
     * This method will be used on every "Add Word" button click
     * @param words List<Word>
     * @param word String
     * @return boolean
     */
    public boolean isWordUnique(List<Word> words, String word) {
        for (Word w : words) {
            //Toast.makeText(this, w.getWord() + " = " + word,Toast.LENGTH_LONG).show();
            if (StringUtils.equalsIgnoreCase(word, w.getWord())) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method will manage the focus on create quiz screen
     * @param editText EditText
     */
    public void manageFocus(EditText editText){
        View current = getCurrentFocus();
        if (current != null) current.clearFocus();
        editText.requestFocus();
    }
    
    /**
     * This method will check all the words before submitting quiz to databbase
     * @return boolean
     */
    public boolean isWordNamesValid(){
        for( EditText wordTextBox : wordTextBoxes ){
            if ( wordTextBox.isEnabled() &&
                    ( isEmptyWord(wordTextBox.getText().toString())
                            || StringUtils.isNumeric(wordTextBox.getText().toString())) ){
                return false;
            }
        }
        return true;
    }

    /**
     * This method will check all definitions are valid before submitting quiz to database
     * @return boolean
     */
    public boolean isDefinitionsValid(){
        for( EditText definitionTextBox : definitionTextBoxes ){
            if ( definitionTextBox.isEnabled() &&
                    ( isEmptyWord(definitionTextBox.getText().toString())
                            || StringUtils.isNumeric(definitionTextBox.getText().toString())) ){
                return false;
            }
        }
        return true;
    }

    /**
     * This method will validate the options inserted in the quiz
     */
    public boolean isOptionValid(String option){
        if ( StringUtils.isEmpty(option) || StringUtils.isNumeric(option) )
            return false;
        for ( String o : incorrectDefinitions ){
            if ( StringUtils.equalsIgnoreCase(o, option) || StringUtils.isNumeric(option))
                return false;
        }
        for ( Word w : words ){
            if ( StringUtils.equalsIgnoreCase(w.getWord(), option) || StringUtils.isNumeric(option) ||
                    StringUtils.equalsIgnoreCase(w.getDefinition(), option)){
                return false;
            }
        }
        return true;
    }

    /**
     * This method will save quiz into databbase
     * @param q Quiz
     */
    public long saveQuiz(Quiz q){
        return new QuizDBHelper(this).insertQuiz(q);
    }

    /**
     * Hide keyboard
     * @param view view
     */
    public void hideKeyboard(View view) {
        InputMethodManager im =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Reset screen
     */
    public void reset(){
        List<EditText> allTextBoxes = new ArrayList<>();
        allTextBoxes.addAll(wordTextBoxes);
        allTextBoxes.addAll(optionTextBoxes);
        allTextBoxes.addAll(definitionTextBoxes);
        quizName.setText(StringUtils.EMPTY);
        quizName.setError(null);
        quizName.requestFocus();
        quizDescription.setText(StringUtils.EMPTY);
        quizDescription.setError(null);
        quizDescription.clearFocus();
        transientDefinitions = new String[10];
        transientWords = new String[10];

        for ( EditText textBox : allTextBoxes ){
            textBox.setText(StringUtils.EMPTY);
            textBox.setEnabled(false);
            textBox.setVisibility(View.GONE);
            textBox.setError(null);
            textBox.clearFocus();
        }
        for (Button addWordButton : addWordButtons){
            addWordButton.setEnabled(false);
            addWordButton.setVisibility(View.GONE);
        }
        enableDefaultUI();
    }
}
