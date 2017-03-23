package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;

public class CreateTerminalOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_terminal_one);
        setTitle("Create Terminal");
        Intent i=getIntent();
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void onClickSubmitButton(View v)
    {

        EditText cashiernameEdittext=(EditText) findViewById(R.id.cashiername_edittext);
        EditText mobilenumberEdittext=(EditText) findViewById(R.id.mobilenumber_edittext);
        EditText emailidEdittext=(EditText) findViewById(R.id.emailid_edittext);
        if (cashiernameEdittext.getText().toString().trim().equals("")) {
            cashiernameEdittext.setError("Cashier Name Is Required");
            cashiernameEdittext.setHint("Please Enter The Cashier Name");
        } else if (cashiernameEdittext.getText().toString().trim().equals("")) {
            mobilenumberEdittext.setError("Mobile Number is Required");
            mobilenumberEdittext.setHint("Please Enter The Terminal Id");
        } else if (emailidEdittext.getText().toString().trim().equals("")) {
            emailidEdittext.setError("Email Is Required");
            emailidEdittext.setText("Please Enter the E-mail Id");
        } if (mobilenumberEdittext.getText().toString().trim().length() != 10) {
        mobilenumberEdittext.setError("Enter Correct Mobile Number");
    } else if (!emailValidator(emailidEdittext.getText().toString().trim())) {
        emailidEdittext.setError("Enter Correct E-mail Id");
    }
    else {

        Toast.makeText(getApplicationContext(), "entered", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences =getApplicationContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Sharedpref.KEY_CASHIERNAME2, cashiernameEdittext.getText().toString().trim());
        editor.putString(Sharedpref.KEY_CASHIERMOBILE2, mobilenumberEdittext.getText().toString().trim());
        editor.putString(Sharedpref.KEY_CASHIEREMAIl2, emailidEdittext.getText().toString().trim());
        editor.commit();

        onBackPressed();


    }

    }
    public void onClickCancelButton(View v)
    {
        onBackPressed();
    }
}

