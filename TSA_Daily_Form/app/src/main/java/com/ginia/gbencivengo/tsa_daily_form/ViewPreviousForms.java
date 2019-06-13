package com.ginia.gbencivengo.tsa_daily_form;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ViewPreviousForms extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int selection = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_previous_forms);

        final Button chooseFormSubmitButton = (Button)findViewById(R.id.ChooseWhichFormButton);

        final Spinner formsSpinner = (Spinner)findViewById(R.id.ChooseFormSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Forms_Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        formsSpinner.setAdapter(adapter);
        formsSpinner.setOnItemSelectedListener(this);

        chooseFormSubmitButton.setOnClickListener(new View.OnClickListener() {
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
                Intent viewForm_H123B = new Intent(this, Get_Form_H123B.class);
                startActivity(viewForm_H123B);
                break;
            case 2:
                Intent viewForm_H124B = new Intent(this, Get_Form_H124B.class);
                startActivity(viewForm_H124B);
                break;
        }
    }
}
