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


public class FragmentC extends Fragment {

    Spinner spinner_F;
    Spinner spinner_L;
    Button button_calculate_C;
    Button button_reset;
    EditText edit_text_input_F;
    EditText edit_text_input_L;
    TextView text_result_C;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_c, container, false);
        button_calculate_C = view.findViewById(R.id.button_calculate_C);
        button_reset = view.findViewById(R.id.button_reset);
        spinner_F = view.findViewById(R.id.spinner_F);
        spinner_L = view.findViewById(R.id.spinner_L);
        edit_text_input_F = view.findViewById(R.id.edit_text_input_F);
        edit_text_input_L = view.findViewById(R.id.edit_text_input_L);
        text_result_C = view.findViewById(R.id.text_result_C);

        button_calculate_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_text_input_F.getText().length() == 0 || edit_text_input_L.getText().length() == 0){

                    text_result_C.setText("Заполните все поля");

                }else{
                    int position_F = spinner_F.getSelectedItemPosition();
                    int position_L = spinner_L.getSelectedItemPosition();
                    byte power_F = 0;
                    byte power_L = 0;
                    double F = Double.parseDouble(edit_text_input_F.getText().toString());
                    double L = Double.parseDouble(edit_text_input_L.getText().toString());
                    int C;

                    if(F<=0 || L<=0){

                        text_result_C.setText("Ошибка ввода");

                    }else{

                        if (position_F == 0){
                            power_F = 0;
                        } else if (position_F == 1){
                            power_F = 3;
                        } else if (position_F == 2){
                            power_F = 6;
                        }

                        if (position_L == 0){
                            power_L = -9;
                        } else if (position_L == 1){
                            power_L = -6;
                        } else if (position_L == 2){
                            power_L = -3;
                        }

                        C = (int)Math.round(1000000000 * calculationCapacity(F, L, power_F, power_L));

                        text_result_C.setText("C = " + C + " пФ");
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_result_C.setText("C = ");
            }
        });

        return view;
    }

    private double calculationCapacity(double F, double L, byte power_F, byte power_L){

        return 1000/(4*(Math.pow(Math.PI, 2))*Math.pow((F*Math.pow(10, power_F)), 2)*L*Math.pow(10, power_L));
    }
}