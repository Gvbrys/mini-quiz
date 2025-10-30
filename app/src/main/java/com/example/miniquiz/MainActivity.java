package com.example.miniquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView questionTextView;
    private Button startButton;
    private Button answerButtonA;
    private Button answerButtonB;
    private Button answerButtonC;
    private TextView scoreTextView;
    private Button resetButton;

    private Question[] questions;
    private int currentQuestionIndex;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
        initializeQuestions();
        resetQuiz();
    }

    private void initializeViews() {
        titleTextView = findViewById(R.id.titleTextView);
        questionTextView = findViewById(R.id.questionTextView);
        startButton = findViewById(R.id.startButton);
        answerButtonA = findViewById(R.id.answerButtonA);
        answerButtonB = findViewById(R.id.answerButtonB);
        answerButtonC = findViewById(R.id.answerButtonC);
        scoreTextView = findViewById(R.id.scoreTextView);
        resetButton = findViewById(R.id.resetButton);
    }

    private void setupClickListeners() {
        startButton.setOnClickListener(v -> startQuiz());
        resetButton.setOnClickListener(v -> resetQuiz());
        answerButtonA.setOnClickListener(v -> checkAnswer(answerButtonA.getText().toString()));
        answerButtonB.setOnClickListener(v -> checkAnswer(answerButtonB.getText().toString()));
        answerButtonC.setOnClickListener(v -> checkAnswer(answerButtonC.getText().toString()));
    }

    private void initializeQuestions() {
        questions = new Question[]{
                new Question("Programista może zastosować framework Angular w celu implementacji aplikacji:", "typu back-end", "typu front-end", "desktopowej", "typu front-end"),
                new Question("W którym języku programowania kod źródłowy programu, przed jego uruchomieniem, musi być skompilowany do kodu maszynowego konkretnej architektury procesora?", "C++", "Java", "PHP", "C++"),
                new Question("Który atak hakerski polega na zasypywaniu serwera dużą liczbą zapytań, co powoduje jego przeciążenie?", "DDoS", "SQL Injection", "Phishing", "DDoS"),
                new Question("Zmienna typy logicznego może przyjąć wartości:", "trzy dowolne naturalne", "0 oraz dowolną całkowitą", "true, false", "true, false"),
                new Question("Dane z serwera do aplikacji front-end można przesłać za pomocą:", "protokołu SSH", "formatu JSON", "metody POST", "formatu JSON")
        };
    }

    private void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        updateScore();
        startButton.setVisibility(View.GONE);
        resetButton.setVisibility(View.VISIBLE);
        showQuestion();
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        updateScore();
        startButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.GONE);
        hideQuestion();
    }


    private void showQuestion() {
        questionTextView.setVisibility(View.VISIBLE);
        answerButtonA.setVisibility(View.VISIBLE);
        answerButtonB.setVisibility(View.VISIBLE);
        answerButtonC.setVisibility(View.VISIBLE);

        Question currentQuestion = questions[currentQuestionIndex];
        questionTextView.setText(currentQuestion.getQuestion());
        answerButtonA.setText(currentQuestion.getOptionA());
        answerButtonB.setText(currentQuestion.getOptionB());
        answerButtonC.setText(currentQuestion.getOptionC());
    }

    private void hideQuestion() {
        questionTextView.setVisibility(View.GONE);
        answerButtonA.setVisibility(View.GONE);
        answerButtonB.setVisibility(View.GONE);
        answerButtonC.setVisibility(View.GONE);
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions[currentQuestionIndex];
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            updateScore();
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            showQuestion();
        } else {
            endQuiz();
        }
    }

    private void updateScore() {
        scoreTextView.setText("Wynik: " + score);
    }

    private void endQuiz() {
        questionTextView.setText("Koniec quizu! Twój wynik: " + score + " / " + questions.length);
        answerButtonA.setVisibility(View.GONE);
        answerButtonB.setVisibility(View.GONE);
        answerButtonC.setVisibility(View.GONE);
    }
}