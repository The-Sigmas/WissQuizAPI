package ch.wiss.wiss_quiz.exception;

public class QuestionLoadException extends RuntimeException {
    public QuestionLoadException(String message) {
        super(message);
    }

    public QuestionLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
