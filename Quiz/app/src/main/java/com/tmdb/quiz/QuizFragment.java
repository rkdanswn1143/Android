package com.tmdb.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tmdb.quiz.MainActivity;

public class QuizFragment extends Fragment {
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "1. RecyclerView와 ListView의 차이점은 무엇인가요?",
            "2. RecyclerView에서 데이터 항목의 레이아웃을 정의하는 클래스는 무엇인가요?",
            "3. ListView를 사용할 때 데이터 항목의 레이아웃을 재사용하기 위해 권장되는 패턴은 무엇인가요?",
            "4. Fragment의 생명 주기 메서드 중에서 Fragment가 처음에 UI를 생성할 때 호출되는 메서드는 무엇인가요?",
            "5. RecyclerView를 사용할 때 레이아웃을 수평으로 배치하고 싶다면 어떤 LayoutManager를 사용해야 하나요?"
    };

    private String[][] options = {
            {"RecyclerView는 더 유연하고, 다양한 레이아웃 매니저를 지원합니다.",
                    "ListView는 RecyclerView보다 더 많은 메모리를 사용합니다.",
                    "RecyclerView는 데이터 변경 시 자동으로 UI를 업데이트합니다.",
                    "ListView는 ViewHolder 패턴을 기본적으로 지원합니다."},
            {"ViewHolder", "Adapter", "LayoutManager", "ViewGroup"},
            {"Singleton 패턴", "Observer 패턴", "ViewHolder 패턴", "Factory 패턴"},
            {"onCreate()", "onCreateView()", "onActivityCreated()", "onAttach()"},
            {"GridLayoutManager", "LinearLayoutManager", "StaggeredGridLayoutManager"}
    };

    private int[] correctAnswers = {0, 0, 2, 1, 1};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        updateQuestion(view);

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = view.findViewById(R.id.options_group);
                int selectedOptionId = radioGroup.getCheckedRadioButtonId();
                if (selectedOptionId != -1) {
                    int selectedOptionIndex = radioGroup.indexOfChild(view.findViewById(selectedOptionId));
                    if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
                        score++;
                    }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        updateQuestion(view);
                    } else {
                        ((MainActivity) getActivity()).navigateTo(ResultFragment.newInstance(score, questions.length));
                    }
                }
            }
        });

        return view;
    }

    private void updateQuestion(View view) {
        TextView questionText = view.findViewById(R.id.question_text);
        RadioGroup radioGroup = view.findViewById(R.id.options_group);

        questionText.setText(questions[currentQuestionIndex]);
        radioGroup.clearCheck();
        radioGroup.removeAllViews();

        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(options[currentQuestionIndex][i]);
            radioGroup.addView(radioButton);
        }
    }
}
