package com.example.radiotechmobile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;


public class OscillatoryCircuit extends Fragment {

    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_oscillatory_circuit, container, false);;
        FragmentManager fragmentManager = getChildFragmentManager();

        tabLayout = view.findViewById(R.id.tabs_lc);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                   fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, new FragmentFrequency()).commit();
                } else if (tab.getPosition() == 1) {
                   fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, new FragmentL()).commit();
                } else if (tab.getPosition() == 2) {
                   fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, new FragmentC()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}