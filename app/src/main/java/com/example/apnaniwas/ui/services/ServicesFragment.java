package com.example.apnaniwas.ui.services;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.VariableBag;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ServicesFragment extends Fragment  {
    private static String JSON_ARRAY="response" ;
    int num;
    private static final String SERVICE_CONTACT_URL = VariableBag.BASE_URL+"/fetchServiceNumber.php";
    private String ServiceJSON;
    private JSONArray reponseArray;
    private ArrayList<ServiceContactModel> ServiceContactData = new ArrayList<ServiceContactModel>();
    private JSONObject ServicejsonObject;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_services, container, false);
        getServiceContacts();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ServicesViewModel> servicesDataList = new ArrayList<>();

        for(int i = 0; i< ServicesFragment.services.id.length; i++)
        {
            servicesDataList.add(new ServicesViewModel(
                    ServicesFragment.services.mServiceName[i],
                    ServicesFragment.services.mServiceNum,
                    ServicesFragment.services.mServiceImg[i],
                    ServicesFragment.services.id[i]
            ));
        }
        RecyclerView.Adapter adapter = new ServicesFragmentAdapter(getContext(), servicesDataList, ServiceContactData);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getServiceContacts() {
        class GetAllImages extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // loading = ProgressDialog.show(getContext(), "Fetching Data","Please Wait...",true,true);
            }
            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                try {
                    ServiceJSON = response;
                    JSONObject postjsonObject = null;
                    postjsonObject = new JSONObject(ServiceJSON);
                    reponseArray = postjsonObject.getJSONArray(JSON_ARRAY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                extractJSON(reponseArray);
            }
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }
        }
        GetAllImages gai = new GetAllImages();
        gai.execute(SERVICE_CONTACT_URL);
    }
    private void extractJSON(JSONArray servicejsonObject){
        try {
            for(int i = 0; i< servicejsonObject.length(); i++) {
                JSONObject jsonObject = servicejsonObject.getJSONObject(i);
                ServiceContactData.add(new ServiceContactModel(jsonObject.getString("service_id"),jsonObject.getString("service_name"),jsonObject.getString("contact_no")));
                //  Log.e("extractJSONSERVICE: ",jsonObject.getString("contact_no") );
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*    @Override
    public void numberToPhone(int number) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+number)); //change this with number
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }*/


    public static class services{
        static String[] mServiceName = {"Plumber Service","Electrician Service","Security Service","Gas Service",
                "Garbage Service","Complain"};
        static Integer mServiceNum = R.drawable.blue_call;
        static Integer[] mServiceImg = {R.drawable.ic_service_plumber_foreground,R.drawable.ic_service_electrician_foreground,R.drawable.ic_service_security_foreground,R.drawable.ic_service_gas_foreground,R.drawable.ic_service_garbage_foreground,R.drawable.ic_service_complain_foreground};

        static Integer[] id ={0,1,2,3,4,5};
    }



}


