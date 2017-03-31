package www.inbridge.com.ecashproject.activities;

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
import www.inbridge.com.ecashproject.adapters.AdminViewMerchantOfferAdapter;
import www.inbridge.com.ecashproject.services.Url;
import www.inbridge.com.ecashproject.utils.AdminViewOfferData;

public class AdminViewMerchantOfferActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adminViewMerchantOfferAdapter;
    List<AdminViewOfferData> information = new ArrayList<>();
    EditText searchtext ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_merchant_offer);
        setTitle("Merchant Offer");
        recyclerView=(RecyclerView) findViewById(R.id.search_recyclerview1);
        searchtext= (EditText) findViewById(R.id.search);

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
                            jsonArray = jsonObject.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject terminal = jsonArray.getJSONObject(i);
                                AdminViewOfferData adminData = new AdminViewOfferData(terminal.getString("m_name"), terminal.getString("m_id"),terminal.getString("m_key"),terminal.getString("offer"),"valid", terminal.getString("category_name"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            adminViewMerchantOfferAdapter=new AdminViewMerchantOfferAdapter(getApplicationContext(),information);
                            recyclerView.setAdapter(adminViewMerchantOfferAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(AdminViewMerchantOfferActivity.this));





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


        searchtext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<AdminViewOfferData> filteredList = new ArrayList<>();

                for (int i = 0; i < information.size(); i++) {

                    final String text = information.get(i).getMname().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(information.get(i));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adminViewMerchantOfferAdapter = new AdminViewMerchantOfferAdapter(getApplicationContext(),filteredList);
                recyclerView.setAdapter(adminViewMerchantOfferAdapter);
                adminViewMerchantOfferAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }
}
