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


public class FragmentCylindricalCoil extends Fragment {
    Spinner spinner_material;
    Spinner spinner_diameter_coil;
    Spinner spinner_length_coil;
    Button button_calculate_inductance;
    Button button_reset;
    EditText edit_text_input_N_turns;
    EditText edit_text_input_diameter_coil;
    EditText edit_text_input_length_coil;
    TextView text_result_inductance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cylindrical_coil, container, false);
        button_calculate_inductance = view.findViewById(R.id.button_calculate_Inductance);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_material = view.findViewById(R.id.spinner_material);
        spinner_diameter_coil = view.findViewById(R.id.spinner_diameter_coil);
        spinner_length_coil = view.findViewById(R.id.spinner_length_coil);
        edit_text_input_N_turns = view.findViewById(R.id.edit_text_input_N_turns);
        edit_text_input_diameter_coil = view.findViewById(R.id.edit_text_input_diameter_coil);
        edit_text_input_length_coil = view.findViewById(R.id.edit_text_input_length_coil);
        text_result_inductance = view.findViewById(R.id.text_result_inductance);

        button_calculate_inductance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_text_input_N_turns.getText().length() == 0 || edit_text_input_diameter_coil.getText().length() == 0 || edit_text_input_length_coil.getText().length() == 0) {

                    text_result_inductance.setText("Заполните все поля");

                } else {
                    int position_material = spinner_material.getSelectedItemPosition();
                    int position_diameter_coil = spinner_diameter_coil.getSelectedItemPosition();
                    int position_length_coil = spinner_length_coil.getSelectedItemPosition();
                    int m = 0;
                    byte power_diameter_coil = 0;
                    byte power_length_coil = 0;
                    int N = Integer.parseInt(edit_text_input_N_turns.getText().toString());
                    double d = Double.parseDouble(edit_text_input_diameter_coil.getText().toString());
                    double l = Double.parseDouble(edit_text_input_length_coil.getText().toString());
                    double L;

                    if (N <= 0 || d <= 0 || l <= 0) {

                        text_result_inductance.setText("Ошибка ввода");

                    } else {

                        if (position_material == 0) {
                            m = 1;
                        } else if (position_material == 1) {
                            m = 7;
                        } else if (position_material == 2) {
                            m = 9;
                        } else if (position_material == 3) {
                            m = 20;
                        } else if (position_material == 4) {
                            m = 30;
                        } else if (position_material == 5) {
                            m = 50;
                        } else if (position_material == 6) {
                            m = 60;
                        } else if (position_material == 7) {
                            m = 100;
                        } else if (position_material == 8) {
                            m = 200;
                        } else if (position_material == 9) {
                            m = 300;
                        } else if (position_material == 10) {
                            m = 400;
                        } else if (position_material == 11) {
                            m = 450;
                        } else if (position_material == 12) {
                            m = 600;
                        } else if (position_material == 13) {
                            m = 700;
                        } else if (position_material == 14) {
                            m = 800;
                        } else if (position_material == 15) {
                            m = 1000;
                        } else if (position_material == 16) {
                            m = 1500;
                        } else if (position_material == 17) {
                            m = 1600;
                        } else if (position_material == 18) {
                            m = 2000;
                        } else if (position_material == 19) {
                            m = 3000;
                        } else if (position_material == 20) {
                            m = 4000;
                        } else if (position_material == 21) {
                            m = 6000;
                        } else if (position_material == 22) {
                            m = 10000;
                        } else if (position_material == 23) {
                            m = 20000;
                        } else if (position_material == 24) {
                            m = 25000;
                        }

                        if (position_diameter_coil == 0) {
                            power_diameter_coil = -3;
                        } else if (position_diameter_coil == 1) {
                            power_diameter_coil = -2;
                        }

                        if (position_length_coil == 0) {
                            power_length_coil = -3;
                        } else if (position_length_coil == 1) {
                            power_length_coil = -2;
                        }

                        L = 1000000*calculationInductanceCylindricalCoil(m, N, d, l, power_diameter_coil, power_length_coil);

                        text_result_inductance.setText("L = " + (float)L + " мкГн");
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_inductance.setText("L = ");
            }
        });

        return view;
    }

    private double calculationInductanceCylindricalCoil(int m, int N, double d, double l, byte power_diameter_coil, byte power_length_coil) {
        double M0 = 4*Math.PI*Math.pow(10, -7);
        double S = (Math.PI*Math.pow((d*Math.pow(10, power_diameter_coil)), 2))/4;
        return (M0*m*Math.pow(N, 2)*S)/(l*Math.pow(10, power_length_coil));
    }
}