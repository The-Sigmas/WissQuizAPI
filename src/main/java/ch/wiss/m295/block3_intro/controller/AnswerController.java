package ch.wiss.m295.block3_intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.block3_intro.model.Answer;
import ch.wiss.m295.block3_intro.repositories.AnswerRepository;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    // Create a new answer
    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerRepository.save(answer);
    }

    // Get all answers
    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    // Get answer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable long id) {
        return answerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an answer
    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable long id, @RequestBody Answer answerDetails) {
        return answerRepository.findById(id)
                .map(answer -> {
                    answer.setAnswer(answerDetails.getAnswer());
                    answer.setCorrect(answerDetails.isCorrect());
                    return ResponseEntity.ok(answerRepository.save(answer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an answer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable long id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
