package ch.wiss.wiss_quiz.exception;

public class CategoryCouldNotBeSavedException extends RuntimeException {
    public CategoryCouldNotBeSavedException(String message) {
        super(message);
    }

    public CategoryCouldNotBeSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}