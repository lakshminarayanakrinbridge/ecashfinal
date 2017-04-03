package www.inbridge.com.ecashproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import www.inbridge.com.ecashproject.adapters.MerchantViewTerminalAdapter;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;
import www.inbridge.com.ecashproject.utils.MerchantViewTerminalData;

public class MerchantViewTerminalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter viewMerchantterminalAdapter;
    List<MerchantViewTerminalData> information = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_view_terminal);
        setTitle("Merchant Terminal");
        recyclerView=(RecyclerView) findViewById(R.id.search_recyclerview1);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.ADMIN_MERCHANT_OFFER_LIST,
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
                                MerchantViewTerminalData adminData = new MerchantViewTerminalData(terminal.getString("tname"), terminal.getString("tnum"), terminal.getString( "temail"),terminal.getString(  "tcode"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            viewMerchantterminalAdapter=new MerchantViewTerminalAdapter(getApplicationContext(),information);
                            recyclerView.setAdapter(viewMerchantterminalAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MerchantViewTerminalActivity.this));





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

                SharedPreferences sharedPreferences=MerchantViewTerminalActivity.this.getSharedPreferences(Sharedpref.SHARED_PREF_NAME,MODE_PRIVATE);


                params.put(Sharedpref.MERCHANTID_SHARED_PREF,sharedPreferences.getString(Sharedpref.KEY_MERCHANTID,"m_id"));
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.e("value1", Integer.toString(information.size()));



    }
    public void onClickAddnewterminalButton(View v)
    {
        Intent i=new Intent(MerchantViewTerminalActivity.this,CreateTerminalActivity.class);
        startActivity(i);


    }

}
