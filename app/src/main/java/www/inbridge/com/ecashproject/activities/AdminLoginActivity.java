package www.inbridge.com.ecashproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import www.inbridge.com.ecashproject.R;

public class AdminLoginActivity extends AppCompatActivity {

    private Button btnApprove;
    private Button btnCreate;
    private Button btnSearch;
    private Button btnDashboard;
    private Button btnChangePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        init();
    }

    public void init()
    {
        btnApprove=(Button) findViewById(R.id.btn_approve);
        btnCreate=(Button)findViewById(R.id.btn_create);
        btnDashboard=(Button)findViewById(R.id.btn_dashboard);
        btnSearch=(Button)findViewById(R.id.btn_dashboard);
        btnChangePassword=(Button)findViewById(R.id.btn_changepassword);
    }

    public void onClickDashboard(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AdminDashboardActivity.class);
        startActivity(intent);


    }
    public void onClickCreate(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AdminCreateMerchantActivity.class);
        startActivity(intent);
    }
    public void onClickApprove(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AdminApproveMerchantActivity.class);
        startActivity(intent);
    }
    public void onClickSearch(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AdminSearchMerchantActivity.class);
        startActivity(intent);
    }
    public void onClickChangePassword(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AdminChangePasswordActivity.class);
        startActivity(intent);
    }
}
