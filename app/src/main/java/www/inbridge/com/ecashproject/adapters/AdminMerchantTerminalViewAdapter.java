package www.inbridge.com.ecashproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.utils.AdminMerchantTerminalData;

/**
 * Created by USER on 2/25/2017.
 */

public class AdminMerchantTerminalViewAdapter extends RecyclerView.Adapter<AdminMerchantTerminalViewAdapter.MyHolder> {

    private LayoutInflater inflater;
    List<AdminMerchantTerminalData> admindata = new ArrayList<>();

    public AdminMerchantTerminalViewAdapter(Context context, List<AdminMerchantTerminalData> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.admin_terminal_list, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        AdminMerchantTerminalData data = admindata.get(position);
        holder.textterminalcode.setText(data.getTerminalid());
        holder.textmerchantname.setText(data.getCashiername());
        holder.textmobile.setText(data.getMobilenumber());
        holder.textemail.setText(data.getEmailid());


    }


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textterminalcode;
        TextView textmerchantname;
        TextView textmobile;
        TextView textemail;

        public MyHolder(View itemView) {
            super(itemView);
            textterminalcode = (TextView) itemView.findViewById(R.id.terminalid_textview);
            textmerchantname = (TextView) itemView.findViewById(R.id.cashiername_textview);
            textmobile = (TextView) itemView.findViewById(R.id.mobilenumber_textview);
            textemail = (TextView) itemView.findViewById(R.id.emailid_textview);


        }
    }
}

