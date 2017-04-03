package www.inbridge.com.ecashproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import www.inbridge.com.ecashproject.R;
import www.inbridge.com.ecashproject.preferences.Sharedpref;

public class MerchantProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_profile);
        setTitle("Merchant Profile");

        TextView mcategoryname=(TextView) findViewById(R.id.mcategory_textview);
        TextView mname=(TextView) findViewById(R.id.mname_textview);
        TextView mkey=(TextView) findViewById(R.id.mkey_textview);
        TextView memail=(TextView) findViewById(R.id.memail_textview);
        TextView mmobile=(TextView) findViewById(R.id.mmobile_textview);
        TextView mifsc=(TextView) findViewById(R.id.mifsc_textview);
        TextView maccountnumber=(TextView) findViewById(R.id.maccountnumber_textview);
        TextView maccountname=(TextView) findViewById(R.id.maccountname_textview);
        TextView maddress=(TextView) findViewById(R.id.maddress_textview);
        TextView mkycdetails=(TextView) findViewById(R.id.mkyc_textview);
        SharedPreferences sharedPreferences=MerchantProfileActivity.this.getSharedPreferences(Sharedpref.SHARED_PREF_NAME,MODE_PRIVATE);
        mcategoryname.setText(sharedPreferences.getString(Sharedpref.CATEGORY_SHARED_PREF,"mcategory"));
        mname.setText(sharedPreferences.getString(Sharedpref.MERCHANT_NAME_SHARED_PREF,"mname"));
        mkey.setText(sharedPreferences.getString(Sharedpref.MERCHANT_KEY_SHARED_PREF,"mkey"));
        memail.setText(sharedPreferences.getString(Sharedpref.EMAILID_SHARED_PREF,"emailid"));
        mmobile.setText(sharedPreferences.getString(Sharedpref.MOBILENUMBER_SHARED_PREF,"mobilenumber"));
        mifsc.setText(sharedPreferences.getString(Sharedpref.IFSCCODE_SHARED_PREF,"ifsccode"));
        maccountnumber.setText(sharedPreferences.getString(Sharedpref.ACCOUNT_NUMBER_SHARED_PREF,"anumber"));
        maccountname.setText(sharedPreferences.getString(Sharedpref.ACCOUNT_NAME_SHARED_PREF,"aname"));
        maddress.setText(sharedPreferences.getString(Sharedpref.ADDRESS_SHARED_PREF,"address"));
        mkycdetails.setText(sharedPreferences.getString(Sharedpref.KYCDETAILS_SHARED_PREF,"kycdetails"));




    }

    public void onClickEditMerchantButton(View v)
    {
        Intent i=new Intent(MerchantProfileActivity.this,MerchantEditProfileActivity.class);
        startActivity(i);
        finish();
    }


}
