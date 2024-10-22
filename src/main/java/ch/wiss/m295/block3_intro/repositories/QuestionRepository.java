package ch.wiss.m295.block3_intro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m295.block3_intro.model.Category;
import ch.wiss.m295.block3_intro.model.Question;

/**
 * Repository für die Kategorie-Entitäten.
 * Dieses Repository stellt die Datenzugriffsschicht (Data Access Layer) bereit,
 * um CRUD-Operationen auf der Kategorie-Entität auszuführen.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> getQuestionsByCategory(Category category);
    /** 
     * Momentan gibt es keine zusätzlichen Methoden. 
     * Das JpaRepository-Interface stellt alle Standardoperationen 
     * zur Verfügung (z. B. Speichern, Löschen, Suchen nach ID).
     */
}
