package ch.wiss.m295.block3_intro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m295.block3_intro.model.Category;

/**
 * Repository für die Kategorie-Entitäten.
 * Dieses Repository stellt die Datenzugriffsschicht (Data Access Layer) bereit,
 * um CRUD-Operationen auf der Kategorie-Entität auszuführen.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /** 
     * Momentan gibt es keine zusätzlichen Methoden. 
     * Das JpaRepository-Interface stellt alle Standardoperationen 
     * zur Verfügung (z. B. Speichern, Löschen, Suchen nach ID).
     */
}
