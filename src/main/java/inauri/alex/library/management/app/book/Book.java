package inauri.alex.library.management.app.book;

import inauri.alex.library.management.app.book.copy.BookCopy;
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

  private String isbn;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  private Integer year;

  private String description;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BookCopy> copies;
}
