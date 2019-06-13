package com.ginia.gbencivengo.tsa_daily_form;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginButton = (Button)findViewById(R.id.logInButton);
        final Button regisButton = (Button)findViewById(R.id.registerButton);

        loginButton.setClickable(true);
        regisButton.setClickable(true);

        //The listener for the Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logInHandler();
            }
        });
        //The listener for the Registration Button
        regisButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                regisHandler();
            }
        });
    }

    //This takes the input from the App, sends it to Membergw's log in handler
    //Where it'll check it against the credentials it has in the MySQL Db.
    private void logInHandler() {
        final EditText usernameField = (EditText)findViewById(R.id.loginUsername);
        final EditText passwordField = (EditText)findViewById(R.id.loginPassword);

        //Get the Username and Password Entered
        Editable username = usernameField.getText();
        Editable password = passwordField.getText();

        final String usernameSTR = username.toString();
        final String passwordSTR = password.toString();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.membergw.org/Android/loggedin.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true"))
                        {
                            //Login Successful
                            Intent pickForm = new Intent(MainActivity.this, FormHome.class);
                            startActivity(pickForm);
                        }
                        else if (response.equals("false"))
                        {
                            //Login Failed
                            final TextView errorMsg = (TextView)findViewById(R.id.LoginErrorMsgField);
                            errorMsg.setVisibility(View.VISIBLE);
                            errorMsg.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    errorMsg.setVisibility(View.INVISIBLE);
                                }
                            },5000);
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
                params.put("inputUserName", usernameSTR);
                params.put("inputPassword", passwordSTR);
                params.put("login", "Log In");

                return params;
            }
        };

        stringRequest.setShouldCache(false);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void regisHandler()
    {
        Intent regisIntent = new Intent(this,RegistrationForm.class);
        startActivity(regisIntent);
    }
}
