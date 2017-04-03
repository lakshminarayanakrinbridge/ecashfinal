package www.inbridge.com.ecashproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;

public class MerchantChangePassword extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_change_password);
        final EditText oldpassword=(EditText)findViewById(R.id.tv_oldpassword);
        final EditText newpassword=(EditText)findViewById(R.id.tv_newpassword);
        final EditText confirmpassword=(EditText)findViewById(R.id.tv_confirmpassword);


        Button cancelButton=(Button) findViewById(R.id.cancel_button);
        Button submitButton=(Button)findViewById(R.id.submit_button);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String stroldpassword=oldpassword.getText().toString().trim();
                final String strnewpassword=newpassword.getText().toString().trim();
                final String strconfirmpassword=confirmpassword.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ADMIN_CHANGE_PASSWORD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),stroldpassword,Toast.LENGTH_SHORT).show();

                                JSONObject jsonObject;
                                try {
                                    jsonObject = new JSONObject(response);
                                    if(jsonObject.getString("success").toString().trim().equals("true"))
                                    {
                                        Toast.makeText(getApplicationContext(),jsonObject.getString("user_msg"),Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(getApplicationContext(),AdminLoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else if(jsonObject.getString("error").toString().trim().equals("true"))
                                    {
                                        Toast.makeText(getApplicationContext(),jsonObject.getString("user_msg"),Toast.LENGTH_SHORT).show();
                                    }



                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //You can handle error here if you want
                                Toast.makeText(getApplicationContext(),"Please Check Your Network Connection",Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        SharedPreferences sharedPreferences=MerchantChangePassword.this.getSharedPreferences(Sharedpref.SHARED_PREF_NAME,MODE_PRIVATE);

                        params.put("m_id",sharedPreferences.getString(Sharedpref.MERCHANTID_SHARED_PREF,"m_id"));
                        params.put("old_password",stroldpassword);
                        params.put("new_password",strnewpassword);
                        params.put("confirm_password",strconfirmpassword);




                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });
    }

}
