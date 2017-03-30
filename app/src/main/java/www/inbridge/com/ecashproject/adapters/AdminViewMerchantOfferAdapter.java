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
import www.inbridge.com.ecashproject.utils.AdminViewOfferData;

/**
 * Created by USER on 3/30/2017.
 */

public class AdminViewMerchantOfferAdapter extends RecyclerView.Adapter<AdminViewMerchantOfferAdapter.MyHolder> {


    private LayoutInflater inflater;
    List<AdminViewOfferData> admindata = new ArrayList<>();

    public AdminViewMerchantOfferAdapter(Context context, List<AdminViewOfferData> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.adminviewoffer, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        AdminViewOfferData data = admindata.get(position);
        holder.textMerchantname.setText(data.getMname());
        holder.textmerchantkey.setText(data.getMkey());
        holder.textmerchantcategory.setText(data.getMcategory());
        holder.textmerchantoffer.setText(data.getMoffer());
        holder.textmerchantvalidity.setText(data.getMvalidity());


    }


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textMerchantname;
        TextView textmerchantkey;
        TextView textmerchantoffer;
        TextView textmerchantvalidity;
        TextView textmerchantcategory;

        public MyHolder(View itemView) {
            super(itemView);
            textMerchantname = (TextView) itemView.findViewById(R.id.tv_mname);
            textmerchantkey = (TextView) itemView.findViewById(R.id.tv_mkey);
            textmerchantoffer = (TextView) itemView.findViewById(R.id.tv_moffer);
            textmerchantvalidity = (TextView) itemView.findViewById(R.id.tv_validity);
            textmerchantcategory=(TextView) itemView.findViewById(R.id.tv_cat);

        }
    }
}
