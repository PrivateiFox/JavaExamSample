/*
 * Defines a list of questions, read from a file on disk.
 */
package q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Published: 25/12/2021
 *
 * @author Saar
 */
public class Questions {
    private static final int QUESTION_LINE_SIZE = 5;
    private static final int FIRST_QUESTION_ANSWER_LINE = 1;
    private static final int SECOND_QUESTION_ANSWER_LINE = 2;
    private static final int THIRD_QUESTION_ANSWER_LINE = 3;
    private static final int FOURTH_QUESTION_ANSWER_LINE = 4;
    private final List<Question> questions = new ArrayList<>();

    /**
     * A generic constructor
     */
    public Questions() {
    }

    /**
     * Loads in new question from the disk.
     */
    public void loadQuestions() {
        File questionsFilePath = new File("q1/exam.txt");
        Scanner input;
        try {
            input = new Scanner(questionsFilePath);
        } catch (FileNotFoundException ignored) {
            return;
        }
        List<String> questionBuildHelper = new ArrayList<>();
        while (input.hasNext()) {
            questionBuildHelper.add(input.nextLine());
        }
        if (questionBuildHelper.size() % QUESTION_LINE_SIZE != 0) return; // If there's a badly-defined question, don't load any questions.
        // Parse each question into a Question object, and append to the list
        for (int row = 0; row < questionBuildHelper.size() / QUESTION_LINE_SIZE; row++) {
            int position = row * QUESTION_LINE_SIZE;
            questions.add(new Question(questionBuildHelper.get(position),
                    questionBuildHelper.get(position + FIRST_QUESTION_ANSWER_LINE),
                    questionBuildHelper.get(position + SECOND_QUESTION_ANSWER_LINE),
                    questionBuildHelper.get(position + THIRD_QUESTION_ANSWER_LINE),
                    questionBuildHelper.get(position + FOURTH_QUESTION_ANSWER_LINE)));
        }
    }

    /**
     * Converts the questions from an ArrayList to an array, and returns them.
     * @return An array containing Question objects
     */
    public Question[] getQuestions() {
        return questions.toArray(new Question[0]);
    }
}
