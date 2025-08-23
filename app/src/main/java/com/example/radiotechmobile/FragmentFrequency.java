package com.example.radiotechmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class FragmentFrequency extends Fragment {

    Spinner spinner_L;
    Spinner spinner_C;
    Button button_calculate_F;
    Button button_reset;
    EditText edit_text_input_L;
    EditText edit_text_input_C;
    TextView text_result_F;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frequency, container, false);
        button_calculate_F = view.findViewById(R.id.button_calculate_F);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_L = view.findViewById(R.id.spinner_L);
        spinner_C = view.findViewById(R.id.spinner_C);
        edit_text_input_L = view.findViewById(R.id.edit_text_input_L);
        edit_text_input_C = view.findViewById(R.id.edit_text_input_C);
        text_result_F = view.findViewById(R.id.text_result_F);

        button_calculate_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_text_input_L.getText().length() == 0 || edit_text_input_C.getText().length() == 0){

                    text_result_F.setText("Заполните все поля");

                }else{

                    int position_L = spinner_L.getSelectedItemPosition();
                    int position_C = spinner_C.getSelectedItemPosition();
                    byte power_L = 0;
                    byte power_C = 0;
                    double L = Double.parseDouble(edit_text_input_L.getText().toString());
                    double C = Double.parseDouble(edit_text_input_C.getText().toString());
                    double F;

                    if (position_L == 0){
                        power_L = -9;
                    } else if (position_L == 1){
                        power_L = -6;
                    } else if (position_L == 2){
                        power_L = -3;
                    }

                    if (position_C == 0){
                        power_C = -12;
                    } else if (position_C == 1){
                        power_C = -9;
                    } else if (position_C == 2){
                        power_C = -6;
                    }

                    F = 0.0000001 * Math.round(10 * calculationFrequency(L, C, power_L, power_C));

                    text_result_F.setText("F = " + F + " МГц");
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_F.setText("F = ");
            }
        });

        return view;
    }

    private double calculationFrequency(double L, double C, byte power_L, byte power_C){

        return 1/(2*Math.PI*Math.sqrt(L*Math.pow(10, power_L)*C*Math.pow(10, power_C)));
    }
}