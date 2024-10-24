package ch.wiss.m295.block3_intro.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.wiss.m295.block3_intro.model.Question;
import ch.wiss.m295.block3_intro.repositories.QuestionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired 
    QuestionRepository questionRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Question>> getQuestion() {
        // Holt alle Studenten aus der Datenbank; Antwort enthält eine Liste der Studenten als JSON-Array
        return ResponseEntity.ok().body(questionRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestions(@PathVariable long id) {
        // Abrufen der Kategorie mit der angegebenen ID
        return ResponseEntity.ok().body(questionRepository.findById(id).get());
    }
    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        // Ausgabe der Kategorie, die erstellt werden soll, zu Debug-Zwecken
        System.out.println("Creating Question: " + question);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(question.getId())
        .toUri();
        return ResponseEntity.created(location).body(questionRepository.save(question));

}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id) {
        if (!questionRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 Not Found, wenn die Frage nicht existiert
        }
        questionRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content für erfolgreiche Löschung
    }
}

