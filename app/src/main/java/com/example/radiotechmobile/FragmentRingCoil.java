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

public class FragmentRingCoil extends Fragment {
    Spinner spinner_material;
    Spinner spinner_big_diameter_ring_coil;
    Spinner spinner_diameter_ring_coil;
    Spinner spinner_h_ring_coil;
    Button button_calculate_inductance;
    Button button_reset;
    EditText edit_text_input_N_turns;
    EditText edit_text_input_big_diameter_ring_coil;
    EditText edit_text_input_diameter_ring_coil;
    EditText edit_text_input_h_ring_coil;
    TextView text_result_inductance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ring_coil, container, false);
        button_calculate_inductance = view.findViewById(R.id.button_calculate_Inductance);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_material = view.findViewById(R.id.spinner_material);
        spinner_big_diameter_ring_coil = view.findViewById(R.id.spinner_big_diameter_ring_coil);
        spinner_diameter_ring_coil = view.findViewById(R.id.spinner_diameter_ring_coil);
        spinner_h_ring_coil = view.findViewById(R.id.spinner_h_ring_coil);
        edit_text_input_N_turns = view.findViewById(R.id.edit_text_input_N_turns);
        edit_text_input_big_diameter_ring_coil = view.findViewById(R.id.edit_text_input_big_diameter_ring_coil);
        edit_text_input_diameter_ring_coil = view.findViewById(R.id.edit_text_input_diameter_ring_coil);
        edit_text_input_h_ring_coil = view.findViewById(R.id.edit_text_input_h_ring_coil);
        text_result_inductance = view.findViewById(R.id.text_result_inductance);

        button_calculate_inductance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_text_input_N_turns.getText().length() == 0 || edit_text_input_big_diameter_ring_coil.getText().length() == 0 || edit_text_input_diameter_ring_coil.getText().length() == 0 || edit_text_input_h_ring_coil.getText().length() == 0) {

                    text_result_inductance.setText("Заполните все поля");

                } else {
                    int position_material = spinner_material.getSelectedItemPosition();
                    int position_big_diameter_ring_coil = spinner_big_diameter_ring_coil.getSelectedItemPosition();
                    int position_diameter_ring_coil = spinner_diameter_ring_coil.getSelectedItemPosition();
                    int position_h_ring_coil = spinner_h_ring_coil.getSelectedItemPosition();
                    int m = 0;
                    byte power_big_diameter_ring_coil = 0;
                    byte power_diameter_ring_coil = 0;
                    byte power_h_ring_coil = 0;
                    int N = Integer.parseInt(edit_text_input_N_turns.getText().toString());
                    double D = Double.parseDouble(edit_text_input_big_diameter_ring_coil.getText().toString());
                    double d = Double.parseDouble(edit_text_input_diameter_ring_coil.getText().toString());
                    double h = Double.parseDouble(edit_text_input_h_ring_coil.getText().toString());
                    double L;

                    if (N <= 0 || D <= 0 || d <= 0 || h<=0) {

                        text_result_inductance.setText("Ошибка ввода");

                    } else {

                        if (position_material == 0) {
                            m = 0;
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

                        if (m==0){

                            text_result_inductance.setText("Выберите сердечник");

                        } else{
                            if (position_big_diameter_ring_coil == 0) {
                                power_big_diameter_ring_coil = -3;
                            } else if (position_big_diameter_ring_coil == 1) {
                                power_big_diameter_ring_coil = -2;
                            }

                            if (position_diameter_ring_coil == 0) {
                                power_diameter_ring_coil = -3;
                            } else if (position_diameter_ring_coil == 1) {
                                power_diameter_ring_coil = -2;
                            }

                            if (position_h_ring_coil == 0) {
                                power_h_ring_coil = -3;
                            } else if (position_h_ring_coil == 1) {
                                power_h_ring_coil = -2;
                            }

                            L = 1000000*calculationInductanceRingCoil(m, N, D, d, h, power_big_diameter_ring_coil, power_diameter_ring_coil, power_h_ring_coil);

                            text_result_inductance.setText("L = " + (float)L + " мкГн");
                        }
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

    private double calculationInductanceRingCoil(int m, int N, double D, double d, double h, byte power_big_diameter_ring_coil, byte power_diameter_ring_coil, byte power_h_ring_coil) {
        double M0 = 4*Math.PI*Math.pow(10, -7);
        return (M0*m*Math.pow(N, 2)*h*Math.pow(10, power_h_ring_coil)*Math.log((D*Math.pow(10, power_big_diameter_ring_coil))/(d*Math.pow(10, power_diameter_ring_coil))))/(2*Math.PI);
    }
}