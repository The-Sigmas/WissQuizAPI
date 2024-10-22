package ch.wiss.m295.block3_intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.block3_intro.model.Student;
import ch.wiss.m295.block3_intro.repositories.StudentRepository;

@RestController // Kennzeichnet diese Klasse als REST-Controller, der HTTP-Anfragen entgegennimmt und Antworten generiert
@RequestMapping("/students") // Definiert die Basis-URL für alle Methoden in dieser Klasse
public class StudentController {

    @Autowired // Das StudentRepository wird automatisch von Spring Boot instanziiert und injiziert
    private StudentRepository studentRepository;

    // GET-Anfrage für einen bestimmten Studenten anhand seiner ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        // Holt den Studenten aus der Datenbank anhand der ID; Antwort enthält das Studentenobjekt als JSON
        return ResponseEntity.ok().body(studentRepository.findById(id).get());
    }

    // GET-Anfrage, um alle Studenten abzurufen
    @GetMapping("/")
    public ResponseEntity<Iterable<Student>> getStudents() {
        // Holt alle Studenten aus der Datenbank; Antwort enthält eine Liste der Studenten als JSON-Array
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    // POST-Anfrage, um einen neuen Studenten zu erstellen
    @PostMapping("/") // Der Request-Body muss ein JSON-Objekt mit den Attributen eines Studenten enthalten
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Loggt die Erstellung des Studenten und speichert ihn in der Datenbank
        System.out.println("Creating student: " + student);
        return ResponseEntity.ok().body(studentRepository.save(student));
    }
}
