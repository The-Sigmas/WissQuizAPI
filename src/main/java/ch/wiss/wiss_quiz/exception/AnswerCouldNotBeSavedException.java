package ch.wiss.wiss_quiz.exception;

public class AnswerCouldNotBeSavedException extends RuntimeException {
    public AnswerCouldNotBeSavedException(String message) {
        super(message);
    }

    public AnswerCouldNotBeSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
