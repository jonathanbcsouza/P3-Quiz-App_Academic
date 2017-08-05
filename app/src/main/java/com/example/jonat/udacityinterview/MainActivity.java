package com.example.jonat.udacityinterview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
/*
 Essas strings foram utilizadas para gerar a informação que o usuario escolheu.
 Método que foram chamadas: "createUserChoicesReport" , penúltimo metodo desse código.
 Assim a informação digitada pelo usuario é recebida pelo "createUserChoicesReport"  e enviada ao Intent de e-mail
*/

    private String userAnswerOne = "";
    private String userAnswerTwoA = "";
    private String userAnswerTwoB = "";
    private String userAnswerTwoC = "";
    private String userAnswerThree = "";
    private String userAnswerFour = "";

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.yes_radio_button:

                findViewById(R.id.yes_radio_button).setEnabled(false);

                if (checked) {
                    Log.v("Question1_Checkbox ", "YES");
                    userAnswerOne = getString(R.string.yes);
                    totalScore = totalScore + 25;
                    break;
                }
            case R.id.no_radio_button:

                findViewById(R.id.no_radio_button).setEnabled(false);
            {
                if (checked)
                    Log.v("Question1_Checkbox ", "No");
                userAnswerOne = getString(R.string.no);
                break;
            }
        }
    }


    public void checkReturnA(View view) {

        CheckBox checkBoxA = (CheckBox) findViewById(R.id.checkbox_a);
        boolean checkA = checkBoxA.isChecked();

        checkBoxA.setEnabled(false);

        if (checkA) {
            totalScore = totalScore + 13;
            userAnswerTwoA = "(A)";
            Log.v("Question_2", " Option A CLicked?" + checkA);
        }
    }

    public void checkReturnB(View view) {
        CheckBox checkBoxb = (CheckBox) findViewById(R.id.checkbox_b);
        boolean checkB = checkBoxb.isChecked();

        checkBoxb.setEnabled(false);

        if (checkB) {
            totalScore = totalScore + 12;
            userAnswerTwoB = "(B)";
            Log.v("Question_2", "Option B CLicked?" + checkB);
        }
    }

    public void checkReturnC(View view) {
        CheckBox checkBoxc = (CheckBox) findViewById(R.id.checkbox_c);
        boolean checkC = checkBoxc.isChecked();

        checkBoxc.setEnabled(false);

        if (checkC) {
            totalScore = totalScore - 12;
            userAnswerTwoC = "(C)";
            Log.v("Question_2", " Option C CLicked?" + checkC);
        }
    }

    public void radioButtonClicked2(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.yes_radio_button2:
                findViewById(R.id.yes_radio_button2).setEnabled(false);

                if (checked) {
                    totalScore = totalScore + 25;
                    userAnswerFour = getString(R.string.yes);
                    Log.v("Question 4 Answer", "YES");
                    break;
                }
            case R.id.no_radio_button2:
                findViewById(R.id.no_radio_button2).setEnabled(false);

                if (checked) {
                    userAnswerFour = getString(R.string.no);
                    Log.v("Question 4 Answer", "No");
                    break;
                }
        }
    }

    public void shareMethod(View view) {

        EditText answer3 = (EditText) findViewById(R.id.add_name_text);
        String userAnswer3 = answer3.getText().toString().trim();

        if (userAnswer3.equalsIgnoreCase("Enlightening")) {
            totalScore = totalScore + 25;
            userAnswerThree = userAnswer3;
        } else {
            userAnswerThree = userAnswer3;
            Log.v("Question_2", " Option C CLicked?" + userAnswer3);

        }

        if (totalScore > 50) {
            Toast.makeText(this, getString(R.string.congrats) + "\n" + getString(R.string.yourFinalScoreIs) + " " + totalScore, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.sorry) + "\n" + getString(R.string.yourFinalScoreIs) + " " + totalScore, Toast.LENGTH_LONG).show();
        }

        EditText userName = (EditText) findViewById(R.id.your_name);
        String userNameText = userName.getText().toString().trim();

        Intent sendToEmail = new Intent(Intent.ACTION_SENDTO);
        sendToEmail.setData(Uri.parse("mailto:"));
        sendToEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject) + " " + userNameText);
        sendToEmail.putExtra(Intent.EXTRA_TEXT, createUserChoicesReport() + "\n" + createCorrectAnswerReport());

        if (sendToEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendToEmail);
        }

    }

    private String createUserChoicesReport() {
        String userAnswerReport = getString(R.string.userAnswers) + "\n";
        userAnswerReport += getString(R.string.rightAnswerText) + " 1: " + userAnswerOne;
        userAnswerReport += "\n" + getString(R.string.rightAnswerText) + " 2: " + userAnswerTwoA + " " + userAnswerTwoB + " " + userAnswerTwoC;
        userAnswerReport += "\n" + getString(R.string.rightAnswerText) + " 3: " + userAnswerThree;
        userAnswerReport += "\n" + getString(R.string.rightAnswerText) + " 4: " + userAnswerFour + "\n";
        return userAnswerReport;
    }

    private String createCorrectAnswerReport() {
        String quizAnswer = getString(R.string.correctAnswers) + "\n";
        quizAnswer += getString(R.string.rightAnswerText) + " 1: " + getString(R.string.answerOne);
        quizAnswer += "\n" + getString(R.string.rightAnswerText) + " 2: " + getString(R.string.answerTwo);
        quizAnswer += "\n" + getString(R.string.rightAnswerText) + " 3: " + getString(R.string.answerThree);
        quizAnswer += "\n" + getString(R.string.rightAnswerText) + " 4: " + getString(R.string.answerFour) + "\n";
        quizAnswer += "\n" + getString(R.string.finalScore) + " " + totalScore + "\n" + getString(R.string.finalMessage);
        return quizAnswer;

    }

}


