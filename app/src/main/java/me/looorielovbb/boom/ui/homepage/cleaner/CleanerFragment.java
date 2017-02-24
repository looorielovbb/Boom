package me.looorielovbb.boom.ui.homepage.cleaner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.looorielovbb.boom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CleanerFragment extends Fragment {


    public CleanerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cleaner, container, false);
    }

    public static Fragment newInstance() {
        return new CleanerFragment();
    }
}
