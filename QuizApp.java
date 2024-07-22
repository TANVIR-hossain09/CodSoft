import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApp {
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private boolean answered;

    public QuizApp() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
        answered = false;

        // Sample questions
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("What is 5 + 7?", new String[]{"10", "11", "12", "13"}, 2));
        questions.add(new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "J.K. Rowling", "Ernest Hemingway", "Mark Twain"}, 0));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.questionText);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println((i + 1) + ": " + question.options[i]);
            }

            answered = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up! Moving to the next question.\n");
                        currentQuestionIndex++;
                        timer.cancel();
                        start();
                    }
                }
            }, 15000); // 15 seconds per question

            int answer = scanner.nextInt();
            timer.cancel();
            answered = true;

            if (answer - 1 == question.correctAnswerIndex) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was: " + question.options[question.correctAnswerIndex] + "\n");
            }
            currentQuestionIndex++;
        }

        System.out.println("Quiz finished!");
        System.out.println("Your final score is: " + score + " out of " + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.start();
    }
}
