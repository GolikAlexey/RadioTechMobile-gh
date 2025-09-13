package com.example.radiotechmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CurrentLimitingResistor extends Fragment {

    Button button_calculate_R;
    Button button_reset;
    EditText edit_text_input_U;
    EditText edit_text_input_U_LED;
    EditText edit_text_input_I_LED;
    TextView text_result_R;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_limiting_resistor, container, false);
        button_calculate_R = view.findViewById(R.id.button_calculate_R);
        button_reset = view.findViewById(R.id.button_reset);
        edit_text_input_U = view.findViewById(R.id.editTextInput_U);
        edit_text_input_U_LED = view.findViewById(R.id.editTextInput_U_led);
        edit_text_input_I_LED = view.findViewById(R.id.editTextInput_I_led);
        text_result_R = view.findViewById(R.id.text_result_R);

        button_calculate_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_text_input_U.getText().length() == 0 || edit_text_input_U_LED.getText().length() == 0 || edit_text_input_I_LED.getText().length() == 0) {

                    text_result_R.setText("Заполните все поля");

                } else {

                    double U = Double.parseDouble(edit_text_input_U.getText().toString());
                    double U_LED = Double.parseDouble(edit_text_input_U_LED.getText().toString());
                    double I_LED = Double.parseDouble(edit_text_input_I_LED.getText().toString());
                    float R;

                    if (U <= 0 || U_LED <= 0 || I_LED <= 0) {

                        text_result_R.setText("Ошибка ввода");

                    } else {

                        R = (float)calculationR(U, U_LED, I_LED);

                        if (R < 1000) {
                            text_result_R.setText("R = " + (int)Math.ceil(R)  + " Ом");
                        } else if (R >= 1000 && R < 1000000) {
                            R = R / 1000;
                            text_result_R.setText("R = " + R + " КОм");
                        } else if (R >= 1000000) {
                            R = R / 1000000;
                            text_result_R.setText("R = " + R + " МОм");
                        }
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_R.setText("R = ");
            }
        });

        return view;
    }

    private double calculationR(double U, double U_LED, double I_LED) {

        return (U-U_LED)/(I_LED*Math.pow(10,-3));
    }
}