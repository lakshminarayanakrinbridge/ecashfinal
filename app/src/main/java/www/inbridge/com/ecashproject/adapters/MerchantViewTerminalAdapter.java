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
import www.inbridge.com.ecashproject.utils.MerchantViewTerminalData;

/**
 * Created by USER on 2/25/2017.
 */


    public class MerchantViewTerminalAdapter extends RecyclerView.Adapter<MerchantViewTerminalAdapter.MyHolder> {


    private LayoutInflater inflater;
    List<MerchantViewTerminalData> admindata = new ArrayList<>();

    public MerchantViewTerminalAdapter(Context context, List<MerchantViewTerminalData> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.merchantviewoffer, parent, false);
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

        public MyHolder(View itemView) {
            super(itemView);
            // textMerchantname = (TextView) itemView.findViewById(R.id.tv_mname);
            terminalid = (TextView) itemView.findViewById(R.id.terminalid_textview);
            cashiername = (TextView) itemView.findViewById(R.id.cashiername_textview);
            mobilenumber = (TextView) itemView.findViewById(R.id.mobilenumber_textview);
            emailid = (TextView) itemView.findViewById(R.id.emailid_textview);

        }
    }
}

