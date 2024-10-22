package ch.wiss.m295.block3_intro.model;
import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Question {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NonNull
  @NotBlank(message = "Frage text ist Pflicht")
  private String question;

  public long getId() {
    return id;
  }

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Answer> answers = new ArrayList<>();

  public void setId(long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  @Size(min = 3, max = 3, message = "Eine frage muss genau 3 antworten haben")
  public List<Answer> getAnswers() {
    return answers;
  }
  public void setAnswers(List<Answer> answers)  {
    this.answers = answers;
  }
  public Category getCategory() {
    return category;
}

  public void setCategory(Category category) {
    this.category = category;
  }

}
