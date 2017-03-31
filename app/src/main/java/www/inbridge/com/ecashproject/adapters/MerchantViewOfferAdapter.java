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
import www.inbridge.com.ecashproject.utils.MerchantViewOfferData;

/**
 * Created by USER on 3/31/2017.
 */


public class MerchantViewOfferAdapter extends RecyclerView.Adapter<MerchantViewOfferAdapter.MyHolder> {


    private LayoutInflater inflater;
    List<MerchantViewOfferData> admindata = new ArrayList<>();

    public MerchantViewOfferAdapter(Context context, List<MerchantViewOfferData> admindata) {
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

        MerchantViewOfferData data = admindata.get(position);
       // holder.textMerchantname.setText(data.getMname());
      //  holder.textmerchantkey.setText(data.getMkey());
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
           // textMerchantname = (TextView) itemView.findViewById(R.id.tv_mname);
            //textmerchantkey = (TextView) itemView.findViewById(R.id.tv_mkey);
            textmerchantoffer = (TextView) itemView.findViewById(R.id.tv_moffer);
            textmerchantvalidity = (TextView) itemView.findViewById(R.id.tv_validity);
            textmerchantcategory=(TextView) itemView.findViewById(R.id.tv_cat);

        }
    }
}