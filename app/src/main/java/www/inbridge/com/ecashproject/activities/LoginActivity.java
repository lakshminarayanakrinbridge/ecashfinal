package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView newtoecashTextView;
    private TextView forgotpasswordTextView;
    private ProgressBar progressBar;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("eCash");
        init();
    }

    public void init()
    {
        progressBar=(ProgressBar)findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.INVISIBLE);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);
        newtoecashTextView=(TextView) findViewById(R.id.newtoecash_textview);
        forgotpasswordTextView=(TextView) findViewById(R.id.forgotpassword_textview);
    }

    public void onClickLogin(View v) {
       username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (usernameEditText.getText().toString().trim().equals("")) {
            usernameEditText.setError("UserName Is Required!");
            usernameEditText.setHint("Please Enter UserName");
        }
        else if (passwordEditText.getText().toString().trim().equals("")) {
            passwordEditText.setError(" Password Is Required!");
            passwordEditText.setHint("Please Enter Password");

        }
        else if(usernameEditText.getText().toString().trim().length()!=10) {
            Toast.makeText(LoginActivity.this,"Please Enter The Correct MobileNumber",Toast.LENGTH_LONG).show();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            getResponse();

        }
    }

    public void getResponse()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String usrRole=null;
                        String str12="false";


                        try {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject = new JSONObject(response);
                            usrRole=jsonObject.getString("user_msg");
                            str12 = jsonObject.getString("success");


                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }

                        if (str12.equalsIgnoreCase(Sharedpref.LOGIN_SUCCESS)) {


                            try {
                                JSONObject jsonObject1 = new JSONObject(response);
                                usrRole = jsonObject1.getString("user_role");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Developer Exception 1", Toast.LENGTH_LONG).show();
                            }

                            if (usrRole.equals("merchant")) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    //If we are getting success from server

                                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(Sharedpref.MERCHANT_KEY_SHARED_PREF,jsonObject.getString("m_key"));
                                    editor.putString(Sharedpref.ACCOUNT_NAME_SHARED_PREF, jsonObject.getString("m_key"));
                                    editor.putString(Sharedpref.ACCOUNT_NUMBER_SHARED_PREF, jsonObject.getString("m_account_no"));
                                    editor.putString(Sharedpref.IFSCCODE_SHARED_PREF, jsonObject.getString("m_ifsc_code"));
                                    editor.putString(Sharedpref.ADDRESS_SHARED_PREF, jsonObject.getString("m_address"));
                                    editor.putString(Sharedpref.KYCDETAILS_SHARED_PREF, jsonObject.getString("kyc_details"));
                                    editor.putString(Sharedpref.MERCHANT_NAME_SHARED_PREF, jsonObject.getString("m_name"));
                                    editor.putBoolean(Sharedpref.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString(Sharedpref.USERNAME_SHARED_PREF, username);
                                    editor.putString(Sharedpref.KEY_MERCHANTID, jsonObject.getString("m_id"));
                                    editor.putString(Sharedpref.MOBILENUMBER_SHARED_PREF, jsonObject.getString("mobile_no"));
                                    editor.putString(Sharedpref.EMAILID_SHARED_PREF,  jsonObject.getString("email"));
                                    editor.putString(Sharedpref.MERCHANT_CODE_SHARED_PREF, jsonObject.getString("m_code"));
                                    editor.commit();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Developer Exception at json parsing", Toast.LENGTH_LONG).show();
                                }
                                Intent intent = new Intent(LoginActivity.this, MerchantLoginActivity.class);
                                progressBar.setVisibility(View.GONE);
                                startActivity(intent);
                                finish();
                            } else if(usrRole.equals("admin")) {


                                Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
                                progressBar.setVisibility(View.GONE);
                                startActivity(intent);
                                finish();


                            } else if (usrRole.equals("terminal")) {

                                Intent i=new Intent(LoginActivity.this,TerminalLoginActivity.class);
                                progressBar.setVisibility(View.GONE);
                                startActivity(i);
                                finish();

                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"check your network connection!!",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Sharedpref.KEY_USERNAME, username);
                params.put(Sharedpref.KEY_PASSWORD, password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onClickNewtoEcash(View v) {

        Intent i = new Intent(LoginActivity.this, MerchantSignupActivity.class);
        startActivity(i);
        finish();


    }

    public void onClickForgotPassword(View v) {
    }
    @Override
    public void onBackPressed()
    {

        LoginActivity.this.finish();
        System.exit(0);

    }
}
