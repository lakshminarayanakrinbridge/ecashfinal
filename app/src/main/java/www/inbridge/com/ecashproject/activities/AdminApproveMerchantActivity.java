package www.inbridge.com.ecashproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import www.inbridge.com.ecashproject.adapters.AdminApproveMerchantAdapter;
import www.inbridge.com.ecashproject.services.Url;
import www.inbridge.com.ecashproject.utils.AdminApproveMerchantData;

public class AdminApproveMerchantActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AdminApproveMerchantAdapter adminAdapter;
    List<AdminApproveMerchantData> information = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approve_merchant);
        setTitle("Approve Merchant");


        recyclerView=(RecyclerView) findViewById(R.id.merchant_recyclerview);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.APPROVE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject terminal = jsonArray.getJSONObject(i);
                                AdminApproveMerchantData adminData = new AdminApproveMerchantData(terminal.getString("m_name"), terminal.getString("category_name"), terminal.getString("m_code"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            adminAdapter=new AdminApproveMerchantAdapter(getApplicationContext(),information);
                            recyclerView.setAdapter(adminAdapter);
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


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.e("value1", Integer.toString(information.size()));



    }
}
