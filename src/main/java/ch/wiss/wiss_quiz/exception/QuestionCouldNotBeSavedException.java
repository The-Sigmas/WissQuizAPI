package ch.wiss.wiss_quiz.exception;

public class QuestionCouldNotBeSavedException extends RuntimeException {
    public QuestionCouldNotBeSavedException(String message) {
        super(message);
    }

    public QuestionCouldNotBeSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
