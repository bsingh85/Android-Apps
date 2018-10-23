package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;

public class RegisterActivity extends AppCompatActivity {

    QuizDBHelper quizHelper;
    EditText username;
    EditText major;
    EditText email;
    Spinner seniority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        quizHelper = new QuizDBHelper(this);
        Button registerButton = findViewById(R.id.register_button_register);
        Button registerReset = findViewById(R.id.register_button_reset);
        Button registerCancel = findViewById(R.id.register_cancel);
        username = findViewById(R.id.register_textbox_user_name);
        major = findViewById(R.id.register_textbox_major);
        email = findViewById(R.id.register_textbox_email);
        seniority = findViewById(R.id.register_spinner_sen_level);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s1 = username.getText().toString().trim();
                String s2 = major.getText().toString().trim();
                String s3 = email.getText().toString().trim();
                Boolean isAsciiPrintable1 = isAsciiPrintable(s1);
                Boolean isAsciiPrintable2 = isAsciiPrintable(s2);
                int userId = quizHelper.chkusername(s1);
                String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

                if(s1.equals("")){
                    Toast.makeText(getApplicationContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                //check whether username is too long
                else if((s1.length() > 30)){
                    Toast.makeText(getApplicationContext(), "Username has too many characters!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                }
                //check whether username has non-printable characters
                else if(!isAsciiPrintable1){
                    Toast.makeText(getApplicationContext(), "Username has non-printable characters!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                }
                else if(!s1.matches("[a-zA-Z0-9. ]*")){
                    Toast.makeText(getApplicationContext(), "Username cannot contain special characters!", Toast.LENGTH_SHORT).show();
                }
                else if(s2.equals("")){
                    Toast.makeText(getApplicationContext(), "Major cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else if((s2.length() > 30)){
                    Toast.makeText(getApplicationContext(), "Major has too many characters!", Toast.LENGTH_SHORT).show();
                    major.setText("");
                }
                else if(!isAsciiPrintable2){
                    Toast.makeText(getApplicationContext(), "Major has non-printable characters!", Toast.LENGTH_SHORT).show();
                    major.setText("");
                }
                else if(!s2.matches("[a-zA-Z0-9._ -]*")){
                    Toast.makeText(getApplicationContext(), "Major cannot contain special characters!", Toast.LENGTH_SHORT).show();
                }
                else if(s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!s3.matches(EMAIL_REGEX)){
                    Toast.makeText(getApplicationContext(), "Email has wrong format!", Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
                else if(userId != -1){
                    Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                }
                else if (quizHelper.insert(username.getText().toString().trim(), major.getText().toString().trim(), seniority.getSelectedItem().toString(), email.getText().toString().trim())){
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });

        registerReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        registerCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reset();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void reset(){
        username.setText("");
        major.setText("");
        email.setText("");
        seniority.setSelection(0);
    }

    private static boolean isAsciiPrintable(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) > 126 || s.charAt(i) < 32)
                return false;
        return true;
    }

}
