package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;

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
        setTitle("Admin");
        init();
    }

    public void init()
    {
        btnApprove=(Button) findViewById(R.id.btn_approve);
        btnCreate=(Button)findViewById(R.id.btn_create);
        btnDashboard=(Button)findViewById(R.id.btn_dashboard);
        btnSearch=(Button)findViewById(R.id.btn_search);
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

    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Sharedpref.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Sharedpref.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

}
