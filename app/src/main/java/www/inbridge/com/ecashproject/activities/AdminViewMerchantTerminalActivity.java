package www.inbridge.com.ecashproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
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
import java.util.List;
import java.util.Map;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.adapters.AdminMerchantTerminalViewAdapter;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;
import www.inbridge.com.ecashproject.utils.AdminMerchantTerminalData;

public class AdminViewMerchantTerminalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdminMerchantTerminalViewAdapter adminMerchantTerminalViewAdapter;
    List<AdminMerchantTerminalData> information = new ArrayList<>();
    EditText searchtext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_merchant_terminal);
        Intent i=getIntent();
        setTitle("Merchant Terminals");
        recyclerView=(RecyclerView) findViewById(R.id.terminal_recyclerview);
        searchtext= (EditText) findViewById(R.id.search);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ADMIN_TERMINAL_LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("tlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject terminal = jsonArray.getJSONObject(i);
                                AdminMerchantTerminalData adminData = new AdminMerchantTerminalData(terminal.getString("cashier_name"), terminal.getString("t_mobileNo"), terminal.getString("t_email"),terminal.getString("terminal_code"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            adminMerchantTerminalViewAdapter=new AdminMerchantTerminalViewAdapter(getApplicationContext(),information);
                            recyclerView.setAdapter(adminMerchantTerminalViewAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



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

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                // String mid=sharedPreferences.getString(Config.MERCHANT_KEY_SHARED_PREF,Config.KEY_MERCHANTID);

                params.put("m_id",sharedPreferences.getString(Sharedpref.KEY_MERCHANTID,"m_id"));

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e("value1", Integer.toString(information.size()));

        searchtext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<AdminMerchantTerminalData> filteredList = new ArrayList<>();

                for (int i = 0; i < information.size(); i++) {

                    final String text = information.get(i).getCashiername().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(information.get(i));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adminMerchantTerminalViewAdapter = new AdminMerchantTerminalViewAdapter(getApplicationContext(),filteredList);
                recyclerView.setAdapter(adminMerchantTerminalViewAdapter);
                adminMerchantTerminalViewAdapter.notifyDataSetChanged();  // data set changed
            }
        });

    }
}






