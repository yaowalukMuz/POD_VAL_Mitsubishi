package com.hitachi_tstv.yodpanom.yaowaluk.pod_val_mitsubishi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.Response;

public class PlanDeliveryActivity extends AppCompatActivity {

    private String[] planDtl2IdStrings,amountStrings,loginStrings;
    private String planDtlIdString,planDtl2IdString,planNameString;





    @BindView(R.id.txt_name)
    TextView factoryTextviewPD;
    @BindView(R.id.lisPDAPlan)
    ListView lisPDAPlan;
    @BindView(R.id.btn_arrival)
    Button btnArrivalPD;
    @BindView(R.id.btn_confirm)
    Button btnDeparturePD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_delivery);
        ButterKnife.bind(this);


        //get Inten data
        loginStrings = getIntent().getStringArrayExtra("Login");


        PlanDeliveryAdapter planDeliveryAdapter = new PlanDeliveryAdapter(PlanDeliveryActivity.this, planDtl2IdStrings, amountStrings, planDtlIdString);
        lisPDAPlan.setAdapter(planDeliveryAdapter);

        factoryTextviewPD.setText(planNameString);


    }

    @OnClick({R.id.btn_arrival, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_arrival:
                UtilityClass utilityClass = new UtilityClass(PlanDeliveryActivity.this);

                break;
            case R.id.btn_confirm:
                break;
        }
    }


    private class SynUpdateArrivalPlan extends AsyncTask<Void, Void, String> {
        String latString,longString,timeString;

        public SynUpdateArrivalPlan(String latString, String longString, String timeString) {
            this.latString = latString;
            this.longString = longString;
            this.timeString = timeString;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody  =  new FormBody.Builder()
                        .add("isAdd","true")
                        .add("user_name", loginStrings[1])
                        .add("planDtl2Id", planDtl2IdString)
                        .add("gps_lat", latString)
                        .add("gps_lon", longString)
                        .add("timeStamp", timeString)
                        .build();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(MyConstant.urlUpdateArrivalPlan).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
               // return response.body().string();
                return null;
            }catch (Exception e) {
                Log.d("Tag", "e doInBack ==>" + e.toString()+"line::"+e.getStackTrace()[0].getLineNumber());
                return null;
            }


        }
    }

    private class SynUpdateDeparturePlan extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }


}

