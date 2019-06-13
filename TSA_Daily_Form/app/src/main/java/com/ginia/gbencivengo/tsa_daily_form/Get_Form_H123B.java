package com.ginia.gbencivengo.tsa_daily_form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class Get_Form_H123B extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> FormTableInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_form__h123_b);
        getTableData();

    }

    private void getTableData()
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.membergw.org/Android/getXYZForms.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseJSON(response);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("urlError","Uh Oh, Something went wrong");
                error.printStackTrace();
            }
        });

        stringRequest.setShouldCache(false);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseJSON(String jsonString) throws JSONException
    {
        FormTableInfo = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> temp = new HashMap<String,String>();
        JSONArray jsonArray = new JSONArray(jsonString);

        //Adding the Table Headers
        temp.put("Id","Id");
        temp.put("DateTime","DateTime");
        temp.put("QuestionOne","QuestionOne");
        temp.put("QuestionTwo","QuestionTwo");
        temp.put("QuestionThree","QuestionThree");
        temp.put("QuestionFour","QuestionFour");
        temp.put("QuestionFive","QuestionFive");
        temp.put("QuestionSix","QuestionSix");
        temp.put("AdditionalInformation","AdditionalInformation");
        FormTableInfo.add(temp);

        for (int counter = 0; counter < jsonArray.length(); counter++)
        {
            temp = new HashMap<String,String>();
            temp.put("Id",jsonArray.getJSONObject(counter).getString("Id"));
            temp.put("DateTime",jsonArray.getJSONObject(counter).getString("DateTime"));
            temp.put("QuestionOne",jsonArray.getJSONObject(counter).getString("QuestionOne"));
            temp.put("QuestionTwo",jsonArray.getJSONObject(counter).getString("QuestionTwo"));
            temp.put("QuestionThree",jsonArray.getJSONObject(counter).getString("QuestionThree"));
            temp.put("QuestionFour",jsonArray.getJSONObject(counter).getString("QuestionFour"));
            temp.put("QuestionFive",jsonArray.getJSONObject(counter).getString("QuestionFive"));
            temp.put("QuestionSix",jsonArray.getJSONObject(counter).getString("QuestionSix"));
            temp.put("AdditionalInformation",jsonArray.getJSONObject(counter).getString("AdditionalInformation"));
            FormTableInfo.add(temp);
        }
        ListView FormListView = (ListView)findViewById(R.id.Form_H123B_Table);;
        ListViewAdapter adapter = new ListViewAdapter(this,R.id.FormTable,FormTableInfo);;
        FormListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
