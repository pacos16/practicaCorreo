package com.germangascon.navigationdrawersample;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPresentacion extends Fragment {
    private TextView tvSample;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.rv_layout, container, false);

        Bundle b = getArguments();
        if(b != null) {
            if(b.containsKey("SHARE")) {

            }
        }
        return layout;
    }
}
