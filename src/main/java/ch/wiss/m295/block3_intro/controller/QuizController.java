package ch.wiss.m295.block3_intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import ch.wiss.m295.block3_intro.model.Category;
import ch.wiss.m295.block3_intro.model.Question;
import ch.wiss.m295.block3_intro.repositories.CategoryRepository;
import ch.wiss.m295.block3_intro.repositories.QuestionRepository;
import ch.wiss.wiss_quiz.exception.CategoryNotFoundException;
    
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

    @Autowired
    private CategoryRepository catRepo;

    @Autowired
    private QuestionRepository questionRepository;

    private static final int MAX_QUESTIONS = 3;

    @GetMapping(path = "")
    @ResponseBody
    public List<Question> getQuizQuestions(@RequestParam long category_id) {
        // Suche nach dem Category-Objekt
        Optional<Category> catOptional = catRepo.findById(category_id);
         // Überprüfe, ob die Kategorie existiert
        if (catOptional.isEmpty()) {
            return null;
        }

        // Finde die Fragen für die Kategorie
        List<Question> questions = questionRepository.getQuestionsByCategory(catOptional.get());

        // Shuffle die Fragen und gib die ersten MAX_QUESTIONS zurück
        if (questions.size() > MAX_QUESTIONS) {
            Collections.shuffle(questions);
            return questions.subList(0, MAX_QUESTIONS);
        }

        return questions; // Gibt die gesamte Liste zurück, wenn weniger als MAX_QUESTIONS
    }
}
