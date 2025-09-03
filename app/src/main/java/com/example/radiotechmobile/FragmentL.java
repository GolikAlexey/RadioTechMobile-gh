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


public class FragmentL extends Fragment {

    Spinner spinner_F;
    Spinner spinner_C;
    Button button_calculate_L;
    Button button_reset;
    EditText edit_text_input_F;
    EditText edit_text_input_C;
    TextView text_result_L;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_l, container, false);
        button_calculate_L = view.findViewById(R.id.button_calculate_L);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_F = view.findViewById(R.id.spinner_F);
        spinner_C = view.findViewById(R.id.spinner_C);
        edit_text_input_F = view.findViewById(R.id.edit_text_input_F);
        edit_text_input_C = view.findViewById(R.id.edit_text_input_C);
        text_result_L = view.findViewById(R.id.text_result_L);

        button_calculate_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_text_input_F.getText().length() == 0 || edit_text_input_C.getText().length() == 0){

                    text_result_L.setText("Заполните все поля");

                }else{
                    int position_F = spinner_F.getSelectedItemPosition();
                    int position_C = spinner_C.getSelectedItemPosition();
                    byte power_F = 0;
                    byte power_C = 0;
                    double F = Double.parseDouble(edit_text_input_F.getText().toString());
                    double C = Double.parseDouble(edit_text_input_C.getText().toString());
                    double L;

                    if(F<=0 || C<=0){

                        text_result_L.setText("Ошибка ввода");

                    }else {

                        if (position_F == 0) {
                            power_F = 0;
                        } else if (position_F == 1) {
                            power_F = 3;
                        } else if (position_F == 2) {
                            power_F = 6;
                        }

                        if (position_C == 0) {
                            power_C = -12;
                        } else if (position_C == 1) {
                            power_C = -9;
                        } else if (position_C == 2) {
                            power_C = -6;
                        }

                        L = 0.001 * Math.round(1000000000 * calculationInductance(F, C, power_F, power_C));

                        text_result_L.setText("L = " + L + " мкГн");
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_L.setText("L = ");
            }
        });

        return view;
    }

    private double calculationInductance(double F, double C, byte power_F, byte power_C){

        return 1/(4*(Math.pow(Math.PI, 2))*Math.pow((F*Math.pow(10, power_F)), 2)*C*Math.pow(10, power_C));
    }
}