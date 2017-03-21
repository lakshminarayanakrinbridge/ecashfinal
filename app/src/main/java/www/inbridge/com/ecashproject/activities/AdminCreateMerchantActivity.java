package www.inbridge.com.ecashproject.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;

public class AdminCreateMerchantActivity extends AppCompatActivity {

    Spinner spinner;
    EditText merchantnameEdittext;
    EditText merchantkeyEdittext;
    EditText accountnameEdittext;
    EditText ifsccodeEdittext;
    EditText accountnumberEdittext;
    EditText addressEdittext;
    EditText kycdetailseEdittext;
    EditText mobilenumberEdittext;
    EditText emailidEdittext;
    EditText latEdittext;
    EditText lngEdittext;
    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    private long category_id=0;
    //JSON Array
    private JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_merchant);
        init();
        // Inflate the layout for this fragment
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.category_spinner);
        getData();
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        // spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminCreateMerchantActivity.this, android.R.layout.simple_list_item_1, students);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    //Toast.makeText(getActivity(),Long.toString(position),Toast.LENGTH_SHORT).show();
                    category_id = id;
                }
                //Toast.makeText(getActivity(), "Selected",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        Button submitButton = (Button) findViewById(R.id.submit_button);
        Button btn_cancel = (Button) findViewById(R.id.cancel_button);

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Long.toString(category_id).equals("0"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select the Appropriate Category",Toast.LENGTH_LONG).show();
                }else if (mobilenumberEdittext.getText().toString().trim().equals("")) {
                    mobilenumberEdittext.setError("Mobile Number Required");
                    mobilenumberEdittext.setHint("Please Enter The Mobile Number");
                } else if (emailidEdittext.getText().toString().trim().equals("")) {
                    emailidEdittext.setError("E-mail Id is Required");
                    emailidEdittext.setHint("Please Enter The E-mail Id");

                } else if (merchantnameEdittext.getText().toString().trim().equals("")) {
                    merchantnameEdittext.setError("Merchant Name is required!");

                    merchantnameEdittext.setHint("please enter Merchant Name");
                } else if (merchantkeyEdittext.getText().toString().trim().equals("")) {
                    merchantkeyEdittext.setError(" Merchant Key  is required!");

                    merchantkeyEdittext.setHint("please enter Merchant Key");

                } else if (accountnameEdittext.getText().toString().trim().equals("")) {
                    accountnameEdittext.setError("Account Name is required!");
                    accountnameEdittext.setHint("please enter Account name");
                } else if (accountnumberEdittext.getText().toString().trim().equals("")) {
                    accountnumberEdittext.setError("Account Number is required!");
                    accountnumberEdittext.setHint("please enter Account number");
                } else if (ifsccodeEdittext.getText().toString().trim().equals("")) {
                    ifsccodeEdittext.setError("IFSC code is required!");
                    ifsccodeEdittext.setHint("please enter IFSC code");
                } else if (addressEdittext.getText().toString().trim().equals("")) {
                    addressEdittext.setError("address is required!");
                    addressEdittext.setHint("please enter addresss");
                } else if (kycdetailseEdittext.getText().toString().trim().equals("")) {
                    kycdetailseEdittext.setError("KYC details  is required!");
                    kycdetailseEdittext.setHint("please enter KYC details");
                } else if (accountnameEdittext.getText().toString().trim().equals("")) {
                    accountnameEdittext.setError("Account Name is required!");
                    accountnameEdittext.setHint("please enter Account name");
                } else if (latEdittext.getText().toString().equals("")) {
                    latEdittext.setError("Latitude Of The Location Is required!");
                    latEdittext.setHint("Please Enter The Proper Latitude");

                } else if (lngEdittext.getText().toString().equals("")) {
                    accountnameEdittext.setError("Account Name is required!");
                    accountnameEdittext.setHint("please enter The Proper Longitude");
                } else if (!emailValidator(emailidEdittext.getText().toString())) {
                    emailidEdittext.setError("E-mail Id is Incorrect");
                    emailidEdittext.setHint("Please Enter The E-mail Id");
                } else {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.DATA_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //If we are getting success from server
                                    String str = null;
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        str = obj.getString("user_msg");


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    //Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                                    if (str.equals("Merchant Created Successfully.")) {
                                        //Creating a shared preference
                                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                                        onBackPressed();


                                    } else {
                                        //If the server response is not success
                                        //Displaying an error message on toast

                                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //You can handle error here if you want
                                    Toast.makeText(getApplicationContext(),"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();


                            //Adding parameters to request

                            String merchantname = null;
                            String merchantkey = null;
                            String accountname = null;
                            String ifsccode = null;
                            String accontnumber = null;
                            String address = null;
                            String kycdetails = null;
                            String mobilenumber1 = null;
                            String emailid1 = null;
                            String latitude = null;
                            String longitude = null;

                            merchantname = merchantnameEdittext.getText().toString().trim();
                            merchantkey = merchantkeyEdittext.getText().toString().trim();
                            accountname = accountnameEdittext.getText().toString().trim();
                            ifsccode = ifsccodeEdittext.getText().toString().trim();
                            accontnumber = accountnumberEdittext.getText().toString().trim();
                            address = addressEdittext.getText().toString().trim();
                            kycdetails = kycdetailseEdittext.getText().toString().trim();
                            mobilenumber1 = mobilenumberEdittext.getText().toString().trim();
                            emailid1 = emailidEdittext.getText().toString().trim();
                            latitude = latEdittext.getText().toString().trim();
                            longitude = lngEdittext.getText().toString().trim();


                            params.put("mer_name", merchantname);
                            params.put("m_key", merchantkey);
                            params.put("Rmobile", mobilenumber1);
                            params.put("Remail", emailid1);
                            params.put("acc_name", accountname);
                            params.put("acc_num", accontnumber);
                            params.put("ifsc_code", ifsccode);
                            params.put("address", address);
                            params.put("kyc_detail", kycdetails);
                            params.put("lat", latitude);
                            params.put("lng", longitude);
                            params.put("mer_type", "1");
                            params.put("category_list", Long.toString(category_id));


                            //returning parameter
                            return params;
                        }
                    };

                    //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }
            }
        });
    }




            public boolean emailValidator(String email) {
                Pattern pattern;
                Matcher matcher;
                final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                pattern = Pattern.compile(EMAIL_PATTERN);
                matcher = pattern.matcher(email);
                return matcher.matches();
            }


            public void init() {

                merchantnameEdittext = (EditText) findViewById(R.id.merchantname_edittext);
                merchantkeyEdittext = (EditText) findViewById(R.id.merchantkey_edittext);
                accountnameEdittext = (EditText) findViewById(R.id.accountname_edittext);
                ifsccodeEdittext = (EditText) findViewById(R.id.ifsccode_edittext);
                accountnumberEdittext = (EditText) findViewById(R.id.accountnumber_edittext);
                addressEdittext = (EditText) findViewById(R.id.address_edittext);
                kycdetailseEdittext = (EditText) findViewById(R.id.kycdetails_edittext);
                mobilenumberEdittext = (EditText) findViewById(R.id.merchantmobile_edittext);
                emailidEdittext = (EditText) findViewById(R.id.merchantemail_edittext);
                latEdittext = (EditText) findViewById(R.id.lat_edittext);
                lngEdittext = (EditText) findViewById(R.id.lng_edittext);


            }


            private void getData() {
                //Creating a string request
                StringRequest stringRequest = new StringRequest(Request.Method.GET,Url.CATEGORY_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JSONObject j = null;
                                try {
                                    //Parsing the fetched Json String to JSON Object
                                    j = new JSONObject(response);

                                    //Storing the Array of JSON String to our JSON Array
                                    result = j.getJSONArray(Sharedpref.JSON_ARRAY);

                                    //Calling method getStudents to get the students from the JSON Array
                                    getStudents(result);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                //Creating a request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                //Adding request to the queue
                requestQueue.add(stringRequest);
            }

            private void getStudents(JSONArray j) {
                //Traversing through all the items in the json array

                students.add("Select Category");
                for (int i = 0; i < j.length(); i++) {
                    try {
                        //Getting json object
                        JSONObject json = j.getJSONObject(i);

                        //Adding the name of the student to array list
                        students.add(json.getString(Sharedpref.TAG_USERNAME));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminCreateMerchantActivity.this, android.R.layout.simple_list_item_1, students);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }


        }






