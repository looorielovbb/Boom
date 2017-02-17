package me.looorielovbb.boom.ui.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.looorielovbb.boom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment {


    public MeiziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meizi, container, false);
    }

    public static Fragment newInstance() {
        return new MeiziFragment();
    }
}
