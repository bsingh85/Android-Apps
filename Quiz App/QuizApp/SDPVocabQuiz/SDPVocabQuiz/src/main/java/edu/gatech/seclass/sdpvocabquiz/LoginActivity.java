package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.sdpvocabquiz.Context.UserContext;
import edu.gatech.seclass.sdpvocabquiz.db.QuizDBHelper;

/**
 * A login screen that offers login via username.
 */
public class LoginActivity extends AppCompatActivity {

    QuizDBHelper quizDB;
    EditText username;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Create database
        quizDB = new QuizDBHelper(this);

        username = (EditText) findViewById(R.id.login_textbox_user_name);
        register = (Button) findViewById(R.id.login_button_register);
        login = (Button) findViewById(R.id.login_button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = username.getText().toString().trim();
                Boolean isAsciiPrintable = isAsciiPrintable(s1);
                if(s1.equals("")){
                    Toast.makeText(getApplicationContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                //check whether username is too long
                else if(s1.length() > 30) {
                    Toast.makeText(getApplicationContext(), "Username has too many characters!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                }
                //check whether username has non-printable characters
                else if(!isAsciiPrintable){
                    Toast.makeText(getApplicationContext(), "Username has non-printable characters!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                }
                else {
                    //check whether username exists already in quizDB
                    int userId = quizDB.chkusername(s1);
                    if(userId == -1){
                        Toast.makeText(getApplicationContext(), "Username doesn't exist!", Toast.LENGTH_SHORT).show();
                        username.setText("");
                    }
                    else{
                        UserContext.initialize(userId, s1);

                        Intent i = new Intent(LoginActivity.this, OperationsActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    private static boolean isAsciiPrintable(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) > 126 || s.charAt(i) < 32)
                return false;
        return true;
    }

}


