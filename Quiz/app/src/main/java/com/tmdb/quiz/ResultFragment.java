package com.tmdb.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private static final String ARG_SCORE = "score";
    private static final String ARG_TOTAL = "total";

    private int score;
    private int total;

    public static ResultFragment newInstance(int score, int total) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_TOTAL, total);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
            total = getArguments().getInt(ARG_TOTAL);
        }

        TextView resultText = view.findViewById(R.id.result_text);
        resultText.setText("Your score: " + score + " out of " + total);

        view.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).navigateTo(new StartFragment());
            }
        });

        return view;
    }
}
