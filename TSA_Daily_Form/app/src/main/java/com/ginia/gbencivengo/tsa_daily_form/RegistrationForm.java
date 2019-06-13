package com.ginia.gbencivengo.tsa_daily_form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        final TextView passwordErrorText = (TextView)findViewById(R.id.PasswordMismatchText);
        passwordErrorText.setVisibility(View.INVISIBLE);

        final Button submitButtonField = (Button)findViewById(R.id.regisSubmitButton);
        submitButtonField.setClickable(true);
        submitButtonField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                regisHandler();
            }
        });

    }

    private void regisHandler()
    {
        final TextView passwordErrorText = (TextView)findViewById(R.id.PasswordMismatchText);

        final EditText usernameField = (EditText)findViewById(R.id.regisUserName);
        final EditText passwordField = (EditText)findViewById(R.id.regisPassword);
        final EditText confirmPasswordField = (EditText)findViewById(R.id.regisConfirmPass);
        final EditText ginia_idField = (EditText)findViewById(R.id.regisGINIA_ID);

        final String username = usernameField.getText().toString();
        final String password = passwordField.getText().toString();
        final String confirmPassword = confirmPasswordField.getText().toString();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.membergw.org/Android/regisHandler.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!(password.equals(confirmPassword)))
                        {
                            passwordErrorText.setVisibility(View.VISIBLE);
                        }
                        else
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
                params.put("newUser", username);
                params.put("newPass", password);
                params.put("passwordValidation", confirmPassword);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
