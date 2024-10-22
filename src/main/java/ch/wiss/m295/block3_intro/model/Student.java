package ch.wiss.m295.block3_intro.model;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
public class Student {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NonNull
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
