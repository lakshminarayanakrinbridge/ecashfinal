package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminEditMerchantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_merchant);
        setTitle("Edit Merchant");
        Intent i=getIntent();
        final TextView mobilenumber=(TextView) findViewById(R.id.mobilenumber_textview);
        final TextView emailidtext=(TextView) findViewById(R.id.emailid_textview);
        final TextView approvalstatus=(TextView) findViewById(R.id.approvedStatus_textview);
        final EditText name=(EditText) findViewById(R.id.merchantname_edittext);
        final EditText key=(EditText) findViewById(R.id.merchantkey_edittext);
        final EditText accname=(EditText) findViewById(R.id.accountname_edittext);
        final EditText accno=(EditText) findViewById(R.id.accountnumber_edittext);
        final EditText ifsc=(EditText) findViewById(R.id.ifsccode_edittext);
        final EditText lat=(EditText) findViewById(R.id.lat_edittext);
        final EditText lng=(EditText) findViewById(R.id.lng_edittext);
        final TextView cat=(TextView) findViewById(R.id.cat_textview);
        final EditText kyc=(EditText) findViewById(R.id.kycdetails_edittext);
        final EditText cashbal=(EditText) findViewById(R.id.ecashbal_edittext);
        final EditText cashbackbal=(EditText) findViewById(R.id.ecashcashback_edittext);
        final EditText address=(EditText) findViewById(R.id.address_edittext);

        Button btn=(Button)findViewById(R.id.submit_button);
        Button btn_cancel=(Button) findViewById(R.id.cancel_button);






        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String mid=sharedPreferences.getString(Sharedpref.MERCHANTID_SHARED_PREF,"mid");
        String mno=sharedPreferences.getString(Sharedpref.MOBILENUMBER_SHARED_PREF,"mobilenumber");
        String emailid=sharedPreferences.getString(Sharedpref.EMAILID_SHARED_PREF,"emailid");
        String status=sharedPreferences.getString(Sharedpref.APPROVAL_STATUS,"approvalstatus");
        String category=sharedPreferences.getString(Sharedpref.CATEGORY_SHARED_PREF,"mcategory");
        String mname=sharedPreferences.getString(Sharedpref.MERCHANT_NAME_SHARED_PREF,"mname");
        String mkey=sharedPreferences.getString(Sharedpref.MERCHANT_KEY_SHARED_PREF,"mkey");
        String maccname=sharedPreferences.getString(Sharedpref.ACCOUNT_NAME_SHARED_PREF,"aname");
        String maccno=sharedPreferences.getString(Sharedpref.ACCOUNT_NUMBER_SHARED_PREF,"anumber");
        String mkyc=sharedPreferences.getString(Sharedpref.KYCDETAILS_SHARED_PREF,"kycdetails");
        String mifsc=sharedPreferences.getString(Sharedpref.IFSCCODE_SHARED_PREF,"ifsccode");
        String mcash=sharedPreferences.getString(Sharedpref.ECASH_CASH_BAL_SHARED_PREF,"cashbal");
        String mcashbackbal=sharedPreferences.getString(Sharedpref.ECASH_CASH_BACK_BAL_SHARED_PREF,"cashbackbal");
        String mlat=sharedPreferences.getString(Sharedpref.LAT_SHARED_PREF,"lat");
        String mlng=sharedPreferences.getString(Sharedpref.LNG_SHARED_PREF,"lng");
        String maddr=sharedPreferences.getString(Sharedpref.ADDRESS_SHARED_PREF,"address");


        mobilenumber.setText(mno);
        emailidtext.setText(emailid);
        approvalstatus.setText(status);
        cat.setText(category);
        name.setText(mname);
        key.setText(mkey);
        accname.setText(maccname);
        accno.setText(maccno);
        ifsc.setText(mifsc);
        lat.setText(mlat);
        lng.setText(mlng);
        kyc.setText(mkyc);
        cashbal.setText(mcash);
        cashbackbal.setText(mcashbackbal);
        address.setText(maddr);

        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AdminSearchMerchantActivity.class);
                startActivity(i);
                finish();


            }
        } );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(getApplicationContext(), mid,Toast.LENGTH_LONG).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ADMIN_MERCHANT_UPDATE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                //Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();

                                      /*  Fragment fragment=new SearchMerchantFragment();
                                     FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
                                        //fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();*/



                                    } else {
                                        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //You can handle error here if you want
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("m_code",mid);
                        params.put("m_key",key.getText().toString().trim());
                        params.put("m_name",name.getText().toString().trim());
                        params.put("m_acc_name",accname.getText().toString().trim());
                        params.put("m_account_no",accno.getText().toString().trim());
                        params.put("m_ifsc_code",ifsc.getText().toString().trim());
                        params.put("m_address",address.getText().toString().trim());
                        params.put("kyc_details",kyc.getText().toString().trim());
                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);

            }
        });

    }
}
