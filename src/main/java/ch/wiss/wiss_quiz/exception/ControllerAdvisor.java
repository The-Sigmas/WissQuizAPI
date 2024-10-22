package ch.wiss.wiss_quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CategoryCouldNotBeSavedException.class)
    public ResponseEntity<String> handleCategoryCouldNotBeSavedException(CategoryCouldNotBeSavedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryLoadException.class)
    public ResponseEntity<String> handleCategoryLoadException(CategoryLoadException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuestionCouldNotBeSavedException.class)
    public ResponseEntity<String> handleQuestionCouldNotBeSavedException(QuestionCouldNotBeSavedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AnswerCouldNotBeSavedException.class)
    public ResponseEntity<String> handleAnswerCouldNotBeSavedException(AnswerCouldNotBeSavedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QuestionLoadException.class)
    public ResponseEntity<String> handleQuestionLoadException(QuestionLoadException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
