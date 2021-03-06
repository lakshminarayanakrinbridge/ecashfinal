package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;

public class MerchantSignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_singup);
        setTitle("Sign Up");
        Intent i = getIntent();

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

    public  void onClickNextbutton(View v) {
        EditText mobilenumberEditText = (EditText) findViewById(R.id.mobilenumber_edittext);
        EditText emailidEditText = (EditText) findViewById(R.id.emailaddress_edittext);

        final String mobilenumber = mobilenumberEditText.getText().toString();
        final String emailid = emailidEditText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Adding values to editor
        editor.putString(Sharedpref.MOBILENUMBER_SHARED_PREF, mobilenumber);
        //Saving values to editor
        editor.commit();

        final String mobilenumber1 = sharedPreferences.getString(Sharedpref.MOBILENUMBER_SHARED_PREF, "mobilenumber");

        if (mobilenumber.trim().length() != 10) {
            mobilenumberEditText.setError("Enter Correct Mobile Number");
        }
        else  if (!emailValidator(emailid)) {
            emailidEditText.setError("Enter Correct E-mail Id");
        }
        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.OTP_URL,
                    new Response.Listener<String>() {

                        String otp = null;


                        @Override
                        public void onResponse(String response) {
                           Toast.makeText(MerchantSignupActivity.this, response, Toast.LENGTH_LONG).show();
                            //If we are getting success from server
                            String user_msg=null;

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                user_msg=jsonObject.getString("user_msg");

                            }
                            catch (JSONException e)
                            {
                                Toast.makeText(getApplicationContext(),"Developer Exception While Json Object",Toast.LENGTH_LONG).show();
                            }
                            if (user_msg.equalsIgnoreCase(Sharedpref.LOGIN_FAILURE1)) {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(MerchantSignupActivity.this, Sharedpref.LOGIN_FAILURE1 + mobilenumber1, Toast.LENGTH_SHORT).show();
                            } else if (user_msg.equalsIgnoreCase(Sharedpref.LOGIN_FAILURE2)) {
                                Toast.makeText(MerchantSignupActivity.this, "Check Your Network Connection", Toast.LENGTH_SHORT).show();
                            } else if (user_msg.equalsIgnoreCase(Sharedpref.LOGIN_FAILURE3)) {
                                Toast.makeText(MerchantSignupActivity.this, Sharedpref.LOGIN_FAILURE3, Toast.LENGTH_SHORT).show();
                            } else if (user_msg.equalsIgnoreCase(Sharedpref.LOGIN_FAILURE4)) {
                                Toast.makeText(MerchantSignupActivity.this, Sharedpref.LOGIN_FAILURE4, Toast.LENGTH_SHORT).show();
                            } else {
                                //json parsing
                                try {

                                    JSONObject root = new JSONObject(response);
                                    // Toast.makeText(SignupActivity.this,"entered try",Toast.LENGTH_SHORT).show();
                                    if (root.has("OTP")) {
                                        otp = root.getString("OTP");

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putString(Sharedpref.MOBILENUMBER_SHARED_PREF, mobilenumber);
                                editor.putString(Sharedpref.EMAILID_SHARED_PREF, emailid);

                                editor.putString(Sharedpref.OTP_KEY, otp);


                                //Saving values to editor
                                editor.commit();

                                Intent i = new Intent(getApplicationContext(), OtpActivity.class);
                                i.putExtra("mobilenumber", mobilenumber);
                                i.putExtra("emailid", emailid);
                                i.putExtra("otp", otp);

                                startActivity(i);
                                finish();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            Toast.makeText(getApplicationContext(),"check your network connection!!",Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Sharedpref.KEY_MOBILENUMBER, mobilenumber);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finish();
    }
}