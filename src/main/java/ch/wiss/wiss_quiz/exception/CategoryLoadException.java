package ch.wiss.wiss_quiz.exception;

public class CategoryLoadException extends RuntimeException {
    public CategoryLoadException(String message) {
        super(message);
    }

    public CategoryLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
