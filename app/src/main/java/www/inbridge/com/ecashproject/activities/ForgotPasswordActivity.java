package www.inbridge.com.ecashproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.inbridge.com.ecashproject.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle("Forgot Password");
    }
}
