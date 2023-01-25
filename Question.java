package q1;

/**
 * Published: 25/12/2021
 *
 * @author Saar
 */
public class Question {
    private final String question;
    private final String[] answers = new String[4];

    public Question(String question, String trueAnswer, String falseAnswer1, String falseAnswer2,
                    String falseAnswer3) {
        this.question = question;
        this.answers[0] = trueAnswer;
        this.answers[1] = falseAnswer1;
        this.answers[2] = falseAnswer2;
        this.answers[3] = falseAnswer3;
    }

    public String getQuestionText() {
        return question;
    }

    public String[] getAnswersText() {
        return answers.clone();
    }

    public String getTrueAnswer() { return this.answers[0]; }
}
