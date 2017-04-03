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
import android.widget.TextView;
import android.widget.Toast;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;

public class MerchantLoginActivity extends AppCompatActivity {

    //Textview to show currently logged in user
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_login);

        //Initializing textview
        textView = (TextView) findViewById(R.id.username_textview);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Sharedpref.MERCHANT_NAME_SHARED_PREF,"mname");
        Toast.makeText(MerchantLoginActivity.this,username,Toast.LENGTH_LONG).show();

        //Showing the current logged in email to textview
        textView.setText(username);
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
                        SharedPreferences preferences = getSharedPreferences(Sharedpref.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Sharedpref.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Sharedpref.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MerchantLoginActivity.this, LoginActivity.class);
                        startActivity(intent);
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


    public void onClickDashboardButton(View v)
    {
        Intent i=new Intent(MerchantLoginActivity.this,MerchantDashboardActivity.class);
        startActivity(i);



    }

    public void onClickTerminalButton(View v)
    {
        Intent i=new Intent(MerchantLoginActivity.this,MerchantViewTerminalActivity.class);
        startActivity(i);
    }

    public void onClickMyprofileButton(View v)
    {
        Intent i=new Intent(MerchantLoginActivity.this,MerchantProfileActivity.class);
        startActivity(i);
    }

    public void onClickManageOffersButton(View v)
    {
        Intent i=new Intent(MerchantLoginActivity.this,MerchantViewOfferActivity.class);
        startActivity(i);
    }

    public void onClickChangePasswordButton(View v)
    {
        Intent i=new Intent(MerchantLoginActivity.this,MerchantChangePassword.class);
        startActivity(i);
    }


}
