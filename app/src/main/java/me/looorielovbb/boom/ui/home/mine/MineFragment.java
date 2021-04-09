package me.looorielovbb.boom.ui.home.mine;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.looorielovbb.boom.R;
import me.looorielovbb.boom.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {


    private String TAG = "MineFragment";

    public MineFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_mine);
        textView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tv_mine){
            String a = getActivity().getIntent().getStringExtra("a");
            Log.e(TAG, "onViewCreated: "+a );
            ToastUtils.show(getContext(),a);
        }
    }
}
