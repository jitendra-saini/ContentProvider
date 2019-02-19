package com.example.contentprovider;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonMain extends AppCompatActivity {

    private String Tag= JsonMain.class.getSimpleName();
    private ListView listView;
    ArrayList<HashMap<String,String>> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_main);


           contactlist=new ArrayList<>();
           listView=findViewById(R.id.list);

         new GetContacts().execute();


    }

         private class GetContacts extends AsyncTask<Void ,Void,Void>{


             @Override
             protected void onPreExecute() {
                 super.onPreExecute();
                 Toast.makeText(JsonMain.this,"Json data is downloading ",Toast.LENGTH_LONG).show();
             }

             @Override
             protected Void doInBackground(Void... voids) {

                 HttpHandler httpHandler=new HttpHandler();
                 String URl="https://api.androidhive.info/contacts/";
                 String JsonStr=httpHandler.makeServiceCall(URl);
                 Log.e(Tag,"Resoponse from Url:"+JsonStr);

                 if (JsonStr!=null){

                   try{


                       JSONObject jsonObject=new JSONObject(JsonStr);
                       JSONArray jsonArray=jsonObject.getJSONArray("contacts");
                       //looping through all contacts
                       for(int i=0;i<jsonArray.length();i++){

                           JSONObject c=jsonArray.getJSONObject(i);
                           String name=c.getString("name");
                           String email=c.getString("email");
                           String address=c.getString("address");


                           //phone node is Json object
                           JSONObject phone=c.getJSONObject("phone");
                           String mobile=phone.getString("mobile");

                          HashMap<String,String> contact=new HashMap<>();


                          //adding contacts
                           contact.put("name",name);
                           contact.put("email",email);
                           contact.put("adress",address);
                           contact.put("mobile",mobile);
                           //adding contacts to contactlist
                             contactlist.add(contact);


                       }

                   } catch (JSONException e) {
                       e.printStackTrace();

                       Log.e(Tag,"Json parsing error"+e.getMessage());

                   }



                 }else {



                     Log.e(Tag,"Couldn't get Json from server");
                 }

                 return null;
             }

             @Override
             protected void onPostExecute(Void aVoid) {


                 super.onPostExecute(aVoid);
                        Log.e(Tag,"Json data"+contactlist);

                 ListAdapter adapter = new SimpleAdapter(JsonMain.this, contactlist,
                         R.layout.list_item, new String[]{ "name","mobile"},
                         new int[]{R.id.email, R.id.mobile});
                 listView.setAdapter(adapter);

             }
         }




}
