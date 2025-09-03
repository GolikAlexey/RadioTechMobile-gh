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

public class FragmentR3 extends Fragment {

    Spinner spinner_R1;
    Spinner spinner_R2;
    Spinner spinner_R3;
    Button button_calculate_RRR;
    Button button_reset;
    EditText edit_text_input_R1;
    EditText edit_text_input_R2;
    EditText edit_text_input_R3;
    TextView text_result_RRR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_r3, container, false);
        button_calculate_RRR = view.findViewById(R.id.button_calculate_RRR);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_R1 = view.findViewById(R.id.spinner_R1);
        spinner_R2 = view.findViewById(R.id.spinner_R2);
        spinner_R3 = view.findViewById(R.id.spinner_R3);
        edit_text_input_R1 = view.findViewById(R.id.edit_text_input_R1);
        edit_text_input_R2 = view.findViewById(R.id.edit_text_input_R2);
        edit_text_input_R3 = view.findViewById(R.id.edit_text_input_R3);
        text_result_RRR = view.findViewById(R.id.text_result_RRR);

        button_calculate_RRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_text_input_R1.getText().length() == 0 || edit_text_input_R2.getText().length() == 0 || edit_text_input_R3.getText().length() == 0) {

                    text_result_RRR.setText("Заполните все поля");

                } else {
                    int position_R1 = spinner_R1.getSelectedItemPosition();
                    int position_R2 = spinner_R2.getSelectedItemPosition();
                    int position_R3 = spinner_R3.getSelectedItemPosition();
                    byte power_R1 = 0;
                    byte power_R2 = 0;
                    byte power_R3 = 0;
                    double R1 = Double.parseDouble(edit_text_input_R1.getText().toString());
                    double R2 = Double.parseDouble(edit_text_input_R2.getText().toString());
                    double R3 = Double.parseDouble(edit_text_input_R3.getText().toString());
                    float R;

                    if (R1 <= 0 || R2 <= 0 || R3 <= 0) {

                        text_result_RRR.setText("Ошибка ввода");

                    } else {

                        if (position_R1 == 0) {
                            power_R1 = 0;
                        } else if (position_R1 == 1) {
                            power_R1 = 3;
                        } else if (position_R1 == 2) {
                            power_R1 = 6;
                        }

                        if (position_R2 == 0) {
                            power_R2 = 0;
                        } else if (position_R2 == 1) {
                            power_R2 = 3;
                        } else if (position_R2 == 2) {
                            power_R2 = 6;
                        }

                        if (position_R3 == 0) {
                            power_R3 = 0;
                        } else if (position_R2 == 1) {
                            power_R3 = 3;
                        } else if (position_R2 == 2) {
                            power_R3 = 6;
                        }

                        R = (float) calculationRRR(R1, R2, R3, power_R1, power_R2, power_R3);

                        if (R < 1000) {
                            text_result_RRR.setText("R = " + R + " Ом");
                        } else if (R >= 1000 && R < 1000000) {
                            R = R / 1000;
                            text_result_RRR.setText("R = " + R + " КОм");
                        } else if (R >= 1000000) {
                            R = R / 1000000;
                            text_result_RRR.setText("R = " + R + " МОм");
                        }
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_RRR.setText("R = ");
            }
        });

        return view;
    }

    private double calculationRRR(double R1, double R2, double R3, byte power_R1, byte power_R2, byte power_R3) {

        return (R1 * Math.pow(10, power_R1) * R2 * Math.pow(10, power_R2) * R3 * Math.pow(10, power_R3)) / (R1 * Math.pow(10, power_R1) * R2 * Math.pow(10, power_R2) + R2 * Math.pow(10, power_R2) * R3 * Math.pow(10, power_R3) + R1 * Math.pow(10, power_R1) * R3 * Math.pow(10, power_R3));
    }
}