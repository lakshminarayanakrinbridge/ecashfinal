package www.inbridge.com.ecashproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.activities.EditTerminalActivity;
import www.inbridge.com.ecashproject.preferences.Sharedpref;
import www.inbridge.com.ecashproject.utils.MerchantViewTerminalData;

import static www.inbridge.com.ecashproject.preferences.Sharedpref.newstr;

/**
 * Created by USER on 2/25/2017.
 */


    public class MerchantViewTerminalAdapter extends RecyclerView.Adapter<MerchantViewTerminalAdapter.MyHolder> {


    private LayoutInflater inflater;
    List<MerchantViewTerminalData> admindata = new ArrayList<>();
    Context context;

    public MerchantViewTerminalAdapter(Context context, List<MerchantViewTerminalData> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
        this.context=context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.layout_merchant_terminal_list, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        MerchantViewTerminalData data = admindata.get(position);
        // holder.textMerchantname.setText(data.getMname());
        //  holder.textmerchantkey.setText(data.getMkey());
        holder.cashiername.setText(data.getCashiername());
        holder.mobilenumber.setText(data.getMobilenumber());
        holder.emailid.setText(data.getEmailid());
        holder.terminalid.setText(data.getTerminalid());
        holder.editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MerchantViewTerminalData data = admindata.get(position);
                String cashiername = data.getCashiername();
                String emailid=data.getEmailid();
                String terminalcode=data.getTerminalid();

                newstr=cashiername;
                Sharedpref.newstr1=emailid;
                Sharedpref.newstr2=terminalcode;
                Toast.makeText(view.getContext(), newstr, Toast.LENGTH_LONG).show();


                SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Sharedpref.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Sharedpref.KEY_MERCHANTID,strcode);
                editor.commit();
                Intent i=new Intent(view.getContext(), EditTerminalActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);






            }
        });

    }


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView cashiername;
        TextView mobilenumber;
        TextView emailid;
        TextView terminalid;
        Button editbutton;

        public MyHolder(View itemView) {
            super(itemView);
            // textMerchantname = (TextView) itemView.findViewById(R.id.tv_mname);
             editbutton=(Button) itemView.findViewById(R.id.edit_button) ;
            terminalid = (TextView) itemView.findViewById(R.id.terminalid_textview);
            cashiername = (TextView) itemView.findViewById(R.id.cashiername_textview);
            mobilenumber = (TextView) itemView.findViewById(R.id.mobilenumber_textview);
            emailid = (TextView) itemView.findViewById(R.id.emailid_textview);

        }
    }
}

