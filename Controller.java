/*
 * A controller class for handling the various quiz functions.
 */
package q1;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Random;

/**
 * Published: 25/12/2021
 *
 * @author Saar
 */
public class Controller {
    private final Random random = new Random();
    private final Questions questions = new Questions();
    private Question[] currentQuizQuestions;
    private int scorePerQuestion;
    private int currentQuestionPosition = -1;
    private int score = 0;

    @FXML
    private Button reloadQuestionsButton;

    @FXML
    private Button generateQuizButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Button answerButton1;

    @FXML
    private Button answerButton2;

    @FXML
    private Button answerButton3;

    @FXML
    private Button answerButton4;

    @FXML
    private Button nextQuestionButton;

    @FXML
    private Label resultLabel;

    /**
     * Loads new questions from file(loadQuestions) and updates the score per question, resets the quiz board.
     * @param event Unused
     */
    @FXML
    void reloadQuestions(ActionEvent event) {
        this.questions.loadQuestions();
        this.currentQuizQuestions = this.questions.getQuestions();
        if (this.currentQuizQuestions.length == 0){
            this.currentQuestionPosition = -1;
            this.scorePerQuestion = 0;
        }
        else{
            this.currentQuestionPosition = 0;
            this.scorePerQuestion = 100 / currentQuizQuestions.length;
            // Reload the quiz after loading the questions
            this.generateQuizLogic();
        }
    }

    /**
     * A wrapper for the "generateQuizButton" button.
     * @param event Unused
     */
    @FXML
    void generateQuiz(ActionEvent event) {
        generateQuizLogic();
    }

    /**
     * Resets the quiz parameters, then reloads the quiz board.
     */
    private void generateQuizLogic() {
        if (currentQuestionPosition == -1) // exit straight away if there's no questions loaded.
            return;
        this.resetQuizParameters();
        this.loadQuizBoardState();
    }

    /**
     * Resets the current quiz position and score
     */
    private void resetQuizParameters() {
        currentQuestionPosition = 0;
        score = 0;
    }

    /**
     * Gets new questions, sets up the first question and randomizes the answer order.
     */
    private void loadQuizBoardState() {
        Question currentQuestion = this.currentQuizQuestions[this.currentQuestionPosition];
        String[] possibleAnswers = currentQuestion.getAnswersText();
        randomizeAnswerOrder(possibleAnswers);
        setQuestionLabelAndButtons(currentQuestion.getQuestionText(), possibleAnswers);
    }

    /**
     * Randomizes the answer order in place, at random.
     * @param possibleAnswers An array of answers to randomize
     */
    private void randomizeAnswerOrder(String[] possibleAnswers) {
        int random_position;
        String temporaryAnswer;
        for (int position = 0; position < possibleAnswers.length; position++) {
            random_position = random.nextInt(possibleAnswers.length);
            temporaryAnswer = possibleAnswers[position];
            possibleAnswers[position] = possibleAnswers[random_position];
            possibleAnswers[random_position] = temporaryAnswer;
        }
    }

    /**
     *
     * @param questionText The question to be displayed
     * @param possibleAnswers An array of answers to be displayed
     */
    private void setQuestionLabelAndButtons(String questionText, String[] possibleAnswers) {
        this.questionLabel.setText(questionText);
        this.answerButton1.setText(possibleAnswers[0]);
        this.answerButton1.setDisable(false);
        this.answerButton2.setText(possibleAnswers[1]);
        this.answerButton2.setDisable(false);
        this.answerButton3.setText(possibleAnswers[2]);
        this.answerButton3.setDisable(false);
        this.answerButton4.setText(possibleAnswers[3]);
        this.answerButton4.setDisable(false);
        this.nextQuestionButton.setDisable(true);
        this.resultLabel.setText("");
    }

    /**
     * Let the user know his answer was correct, and enable the "next question" button
     */
    void setAnswerCorrect(){
        this.resultLabel.setText("Correct answer");
        this.nextQuestionButton.setDisable(false);
        this.answerButton1.setDisable(true);
        this.answerButton2.setDisable(true);
        this.answerButton3.setDisable(true);
        this.answerButton4.setDisable(true);

        this.score += this.scorePerQuestion;
    }

    /**
     * Let the user know his answer was incorrect, and enable the "next question" button
     */
    void setAnswerIncorrect(){
        this.resultLabel.setText("Incorrect answer");
        this.nextQuestionButton.setDisable(false);
        this.answerButton1.setDisable(true);
        this.answerButton2.setDisable(true);
        this.answerButton3.setDisable(true);
        this.answerButton4.setDisable(true);
    }

    /**
     * Advances the answered question counter.
     * If we reached the last question, show the score
     */
    private void advancePosition() {
        this.currentQuestionPosition++;
        if (this.currentQuestionPosition == this.currentQuizQuestions.length) this.endQuizAndShowScore();
    }

    /**
     * Use the question label to show the end score, and disable the "next question" button
     */
    void endQuizAndShowScore() {
        this.questionLabel.setText(String.format("Final score: %d", this.score));
        this.nextQuestionButton.setDisable(true);
    }

    /**
     * Load the next question into the board when the "next question" button is pressed.
     * @param event Unused
     */
    @FXML
    void nextQuestion(ActionEvent event) {
        this.loadQuizBoardState();
    }

    /**
     * Reads the text from the current board, checks if it matches the correct answer, and lets the user know
     * @param event the button click event, passed to us by javafx
     */
    @FXML
    void selectAnswer(ActionEvent event) {
        Question currentQuestion = this.currentQuizQuestions[this.currentQuestionPosition];
        if (((Button)event.getSource()).getText().equals(currentQuestion.getTrueAnswer())) setAnswerCorrect();
        else setAnswerIncorrect();
        advancePosition();
    }

}
