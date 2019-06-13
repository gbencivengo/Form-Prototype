package com.ginia.gbencivengo.tsa_daily_form;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FormHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    private int selection = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_home);

        final TextView viewPreviousForms = (TextView)findViewById(R.id.ViewPrevCompletedFormsText);

        final Button submitFormButton = (Button)findViewById(R.id.SubmitFormSelectionButton);

        final Spinner formsSpinner = (Spinner)findViewById(R.id.FormsDropDown);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Forms_Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        formsSpinner.setAdapter(adapter);
        formsSpinner.setOnItemSelectedListener(this);

        viewPreviousForms.setTextColor(Color.BLUE);
        viewPreviousForms.setOnTouchListener(this);

        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formSelectionHandler();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selection = i; //simply return the position of the item on the spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Nothing, but since we are implementing an interface, we have to implement this method
        //even if it does nothing
    }

    private void formSelectionHandler()
    {
        switch (selection) {
            case 1:
                Intent FormH123B = new Intent(this, Form_H123B.class);
                startActivity(FormH123B);
                break;
            case 2:
                Intent FormH124B = new Intent(this, Form_H124B.class);
                startActivity(FormH124B);
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView currentText = (TextView)findViewById(view.getId());
        currentText.setTextColor(Color.MAGENTA);
        Intent viewPreviousForms = new Intent(this, ViewPreviousForms.class);
        startActivity(viewPreviousForms);
        return false;
    }
}
