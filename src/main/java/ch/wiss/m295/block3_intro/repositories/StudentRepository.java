package ch.wiss.m295.block3_intro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.wiss.m295.block3_intro.model.Student;

/**
 * Repository für die Student-Entitäten.
 * Dieses Repository stellt die Datenzugriffsschicht bereit,
 * um CRUD-Operationen auf der Student-Entität auszuführen.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    /** 
     * Momentan gibt es keine zusätzlichen Methoden. 
     * Das JpaRepository-Interface stellt alle Standardoperationen 
     * zur Verfügung (z. B. Speichern, Löschen, Suchen nach ID).
     */
}
