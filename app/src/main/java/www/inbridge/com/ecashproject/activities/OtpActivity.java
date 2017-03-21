package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;

public class OtpActivity extends AppCompatActivity {

    Exam_Timer exam_Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Intent intent =getIntent();
        String mobilenumber = intent.getStringExtra("mobilenumber");
        String emailid = intent.getStringExtra("emailid");
        String otp=intent.getStringExtra("otp");
        exam_Timer = new Exam_Timer(120000, 1000);
        exam_Timer.start();




    }

    public void onClickNextotpbutton(View v) {
        EditText otpEditText = (EditText) findViewById(R.id.otp_edittext);
        String otp = otpEditText.getText().toString();
        Toast.makeText(getApplicationContext(), otp, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String otp_pref = sharedPreferences.getString(Sharedpref.OTP_KEY, "otp");

        if (otp.equalsIgnoreCase(otp_pref)) {
            Intent i = new Intent(getApplicationContext(), MerchantRegistrationActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "wrong otp entered", Toast.LENGTH_LONG).show();
        }


    }


    public class Exam_Timer extends CountDownTimer {

        public Exam_Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {

            Button btn=(Button) findViewById(R.id.resendotp_button);
            btn.setVisibility(View.VISIBLE);

        }

    }



    public void onClickresendotpbutton(View v) {
        Intent i=new Intent(getApplicationContext(),MerchantSignupActivity.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finish();

    }
}

