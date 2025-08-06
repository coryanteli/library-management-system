package inauri.alex.library.management.model.book;

import inauri.alex.library.management.model.book.copy.BookCopy;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String title;

  @NonNull private String author;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  private Integer year;

  private String description;

  @OneToMany(mappedBy = "book")
  private List<BookCopy> copies;
}
