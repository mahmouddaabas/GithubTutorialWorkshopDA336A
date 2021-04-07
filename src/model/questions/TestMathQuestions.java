package model.questions;

import view.UI;

/**
 * Test class for testing MathQuestions. To be removed in final release.
 * @author Mattias Bengtsson
 */
public class TestMathQuestions {
    private UI ui;
    private MathQuestions mathQuestion;

    /**
     * Initializes UI and starts asking questions
     */
    public TestMathQuestions() {
        ui = new UI();

        startQuiz();
    }

    /**
     * Indefinitely asks new questions in the console.
     */
    public void startQuiz() {
        String answerText;
        int answerIndex;
        boolean isAnswered;

        // choose which type of question
//        mathQuestion = new Addition2Numbers(1, 9, 1, 9);
//        mathQuestion = new Subtraction2Numbers(1, 18, 1, 9, false);
//        mathQuestion = new Multiplication2Numbers(1, 9, 1, 9);
//        mathQuestion = new Addition3Numbers(1, 9, 1, 9, 1, 9);
        mathQuestion = new Division2Numbers(10, 100, 2, 10);

        while (true) {
            mathQuestion.generateNewQuestion();

            ui.printMessage(mathQuestion.getQuestion());
            ui.printArray(mathQuestion.getAnswerStr());

            ui.printMessage("Write your answer (a, b, c, or d): ");
            isAnswered = false;
            answerIndex = -1;
            while (!isAnswered) {
                answerText = ui.readText();
                answerText = answerText.toLowerCase();
                switch (answerText) {
                    case "a":
                        answerIndex = 0;
                        break;
                    case "b":
                        answerIndex = 1;
                        break;
                    case "c":
                        answerIndex = 2;
                        break;
                    case "d":
                        answerIndex = 3;
                        break;
                    default:
                        ui.printMessage("Invalid input. Try again!");
                        break;
                }

                if (answerIndex != -1) {
                    if (mathQuestion.compareAnswer(answerIndex)) {
                        ui.printMessage("CORRECT ANSWER!!!");
                        isAnswered = true;
                    } else {
                        ui.printMessage("Incorrect. Try again!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TestMathQuestions test = new TestMathQuestions();
    }
}
