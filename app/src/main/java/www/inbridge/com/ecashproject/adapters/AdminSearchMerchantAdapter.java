package www.inbridge.com.ecashproject.adapters;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.activities.AdminEditMerchantActivity;
import www.inbridge.com.ecashproject.activities.AdminViewMerchantTerminal;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.services.Url;
import www.inbridge.com.ecashproject.utils.AdminSearchMerchantData;

/**
 * Created by USER on 2/25/2017.
 */

public class AdminSearchMerchantAdapter extends RecyclerView.Adapter<AdminSearchMerchantAdapter.MyHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<AdminSearchMerchantData> admindata = new ArrayList<>();

    public AdminSearchMerchantAdapter(Context context, List<AdminSearchMerchantData> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
        this.context=context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.complete_merchant_list_layout, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        AdminSearchMerchantData data = admindata.get(position);
        holder.textMerchantcode.setText(data.merchantcodeter);
        holder.textmerchantname.setText(data.merchantnameter);
        holder.textcategory.setText(data.categoryter);
        holder.ecashbalance.setText(data.ecashbalanceter);
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminSearchMerchantData data = admindata.get(position);
                strcode = data.merchantcodeter;
                Sharedpref.newstr=strcode;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();


                SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Sharedpref.KEY_MERCHANTID,strcode);
                editor.commit();
                Intent i=new Intent(view.getContext(), AdminViewMerchantTerminal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);






            }
        });


        holder.textview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AdminSearchMerchantData data = admindata.get(position);
                strcode = data.merchantcodeter;
                Sharedpref.newstr=strcode;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Sharedpref.KEY_MERCHANTID,strcode);
                editor.commit();

                Intent i=new Intent(view.getContext(),AdminViewMerchantTerminal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        holder.textedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                /*Fragment fragment=new AdminMerchantEditFragment();
                android.app.FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();*/

                AdminSearchMerchantData data = admindata.get(position);
                strcode = data.merchantcodeter;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ADMIN_MERCHANT_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                // Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
                                        SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                                        SharedPreferences.Editor e=sharedPreferences.edit();
                                        e.putString(Sharedpref.MOBILENUMBER_SHARED_PREF,jsonObject.getString("m_mobilenumber"));
                                        e.putString(Sharedpref.EMAILID_SHARED_PREF,jsonObject.getString("m_emailaddress"));
                                        e.putString(Sharedpref.MERCHANT_NAME_SHARED_PREF,jsonObject.getString("m_name"));
                                        e.putString(Sharedpref.MERCHANT_KEY_SHARED_PREF,jsonObject.getString("m_key"));
                                        e.putString(Sharedpref.ACCOUNT_NAME_SHARED_PREF,jsonObject.getString("m_accname"));
                                        e.putString(Sharedpref.ACCOUNT_NUMBER_SHARED_PREF,jsonObject.getString("m_accnumber"));
                                        e.putString(Sharedpref.KYCDETAILS_SHARED_PREF,jsonObject.getString("m_kyc"));
                                        e.putString(Sharedpref.LAT_SHARED_PREF,jsonObject.getString("m_lat"));
                                        e.putString(Sharedpref.LNG_SHARED_PREF,jsonObject.getString("m_lng"));
                                        e.putString(Sharedpref.ECASH_CASH_BAL_SHARED_PREF,jsonObject.getString("m_ecashbal"));
                                        e.putString(Sharedpref.ECASH_CASH_BACK_BAL_SHARED_PREF,jsonObject.getString("m_ecashcashback"));
                                        e.putString(Sharedpref.ADDRESS_SHARED_PREF,jsonObject.getString("m_address"));
                                        e.putString(Sharedpref.IFSCCODE_SHARED_PREF,jsonObject.getString("m_ifsc"));
                                        e.putString(Sharedpref.CATEGORY_SHARED_PREF,jsonObject.getString("cat_name"));
                                        e.commit();
                                        Intent i=new Intent(view.getContext(), AdminEditMerchantActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);


                                    } else {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
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

                        params.put("mid",strcode);
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


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textMerchantcode;
        TextView textcategory;
        TextView textmerchantname;
        TextView textedit;
        TextView textview;
        TextView ecashbalance;

        public MyHolder(View itemView) {
            super(itemView);
            textMerchantcode = (TextView) itemView.findViewById(R.id.merchantcode_textview);
            textcategory = (TextView) itemView.findViewById(R.id.merchantcategory_textview);
            textmerchantname = (TextView) itemView.findViewById(R.id.merchantname_textview);
            textedit = (TextView) itemView.findViewById(R.id.Edit_textview);
            textview = (TextView) itemView.findViewById(R.id.viewterminal_textview);
            ecashbalance = (TextView) itemView.findViewById(R.id.ecashbalance_textview);


        }
    }


}

