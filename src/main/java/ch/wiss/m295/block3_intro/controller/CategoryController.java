package ch.wiss.m295.block3_intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.block3_intro.model.Category;
import ch.wiss.m295.block3_intro.repositories.CategoryRepository;
import ch.wiss.wiss_quiz.exception.CategoryCouldNotBeSavedException;

/**
 * Controller für die Verwaltung von Kategorien.
 * Dieser Controller stellt REST-Endpoints zur Verfügung, 
 * um Kategorien zu erstellen und abzurufen.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    // Dependency Injection des CategoryRepository
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * GET-Anfrage zum Abrufen einer Kategorie anhand ihrer ID.
     * @param id Die ID der Kategorie.
     * @return ResponseEntity, das die Kategorie enthält.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        // Abrufen der Kategorie mit der angegebenen ID
        return ResponseEntity.ok().body(categoryRepository.findById(id).get());
    }

    /**
     * GET-Anfrage zum Abrufen aller Kategorien.
     * @return ResponseEntity, das eine Liste aller Kategorien enthält.
     */
    @GetMapping("/")
    public ResponseEntity<Iterable<Category>> getCategories() {
        // Abrufen aller Kategorien
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    /**
     * POST-Anfrage zum Erstellen einer neuen Kategorie.
     * @param category Die Kategorie, die erstellt werden soll.
     * @return ResponseEntity, das die erstellte Kategorie enthält.
     */
    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        // Ausgabe der Kategorie, die erstellt werden soll, zu Debug-Zwecken
        System.out.println("Creating category: " + category);

        // Speichern der neuen Kategorie im Repository
        try {
			Category savedCategory = categoryRepository.save(category);
		} catch (Exception e) {
			throw new CategoryCouldNotBeSavedException("Could not save category", e);
		}   

        // Rückgabe der erstellten Kategorie
        return ResponseEntity.ok().body(category);
    }
}
