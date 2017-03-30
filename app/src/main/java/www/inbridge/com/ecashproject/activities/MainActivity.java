package www.inbridge.com.ecashproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import www.inbridge.com.ecashproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("eCash");
        setContentView(R.layout.activity_main);

        Thread startTimer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    loginRegister();
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        startTimer.start();
    }

    public void loginRegister()
    {
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
