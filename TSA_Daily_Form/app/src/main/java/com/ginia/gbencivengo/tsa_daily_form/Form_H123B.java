package com.ginia.gbencivengo.tsa_daily_form;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Form_H123B extends AppCompatActivity {

    private String[] FormQuestions;
    private boolean QuestionOneAnswer,
                    QuestionTwoAnswer,
                    QuestionThreeAnswer,
                    QuestionFourAnswer,
                    QuestionFiveAnswer,
                    QuestionSixAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form__h123_b);

        //Getting the TextViews from the Table
        final TextView FormBody = (TextView)findViewById(R.id.FormH123B_Body);
        final TextView QuestionOne = (TextView)findViewById(R.id.FormH123B_Q1_Text);
        final TextView QuestionTwo = (TextView)findViewById(R.id.FormH123B_Q2_Text);
        final TextView QuestionThree = (TextView)findViewById(R.id.FormH123B_Q3_Text);
        final TextView QuestionFour = (TextView)findViewById(R.id.FormH123B_Q4_Text);
        final TextView QuestionFive = (TextView)findViewById(R.id.FormH123B_Q5_Text);
        final TextView QuestionSix = (TextView)findViewById(R.id.FormH123B_Q6_Text);

        final RadioGroup QuestionOneRadio = (RadioGroup)findViewById(R.id.FormH123B_Q1_RadioGroup);
        final RadioGroup QuestionTwoRadio = (RadioGroup)findViewById(R.id.FormH123B_Q2_RadioGroup);
        final RadioGroup QuestionThreeRadio = (RadioGroup)findViewById(R.id.FormH123B_Q3_RadioGroup);
        final RadioGroup QuestionFourRadio = (RadioGroup)findViewById(R.id.FormH123B_Q4_RadioGroup);
        final RadioGroup QuestionFiveRadio = (RadioGroup)findViewById(R.id.FormH123B_Q5_RadioGroup);
        final RadioGroup QuestionSixRadio = (RadioGroup)findViewById(R.id.FormH123B_Q6_RadioGroup);

        final Button submitFormButton = (Button)findViewById(R.id.Form123B_SubmitButton);

        //Getting the String array that has all the questons
        FormQuestions = getResources().getStringArray(R.array.Form_H123B);

        //Setting the Question Text
        FormBody.setText(FormQuestions[0]);
        QuestionOne.setText(FormQuestions[1]);
        QuestionTwo.setText(FormQuestions[2]);
        QuestionThree.setText(FormQuestions[3]);
        QuestionFour.setText(FormQuestions[4]);
        QuestionFive.setText(FormQuestions[5]);
        QuestionSix.setText(FormQuestions[6]);

        //Question One Radio Group Listener
        QuestionOneRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q1_RadioYes)
                    QuestionOneAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q1_RadioNo)
                    QuestionOneAnswer = false;
            }
        });

        //Question Two Radio Group Listener
        QuestionTwoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q2_RadioYes)
                    QuestionTwoAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q2_RadioNo)
                    QuestionTwoAnswer = false;
            }
        });

        //Question Three Radio Group Listener
        QuestionThreeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q3_RadioYes)
                    QuestionThreeAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q3_RadioNo)
                    QuestionThreeAnswer = false;
            }
        });

        //Question Four Radio Group Listener
        QuestionFourRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q4_RadioYes)
                    QuestionFourAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q4_RadioNo)
                    QuestionFourAnswer = false;
            }
        });

        //Question Five Radio Group Listener
        QuestionFiveRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q5_RadioYes)
                    QuestionFiveAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q5_RadioNo)
                    QuestionFiveAnswer = false;
            }
        });

        //Question Six Radio Group Listener
        QuestionSixRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q6_RadioYes)
                    QuestionSixAnswer = true;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.FormH123B_Q6_RadioNo)
                    QuestionSixAnswer = false;
            }
        });

        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formHandler();
            }
        });
    }

    private void formHandler()
    {
        final EditText AdditionalInfoForm = (EditText)findViewById(R.id.FormH123B_AddInfo);
        final String additionalInfo = AdditionalInfoForm.getText().toString();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.membergw.org/Android/FormXYZHandler.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toLowerCase().equals("success"))
                        {
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("urlError","Uh Oh, Something went wrong");
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("QuestionOne",Boolean.toString(QuestionOneAnswer));
                params.put("QuestionTwo",Boolean.toString(QuestionTwoAnswer));
                params.put("QuestionThree",Boolean.toString(QuestionThreeAnswer));
                params.put("QuestionFour",Boolean.toString(QuestionFourAnswer));
                params.put("QuestionFive",Boolean.toString(QuestionFiveAnswer));
                params.put("QuestionSix",Boolean.toString(QuestionSixAnswer));
                params.put("AdditionalInfo",additionalInfo);

                return params;
            }
        };

        stringRequest.setShouldCache(false);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
