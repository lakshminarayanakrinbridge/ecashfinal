package www.inbridge.com.ecashproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.inbridge.com.ecashproject.R;

public class MerchantViewTerminalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_view_terminal);
        setTitle("Merchant Terminal");
    }
}
