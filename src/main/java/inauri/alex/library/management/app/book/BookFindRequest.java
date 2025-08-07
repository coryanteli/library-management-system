package inauri.alex.library.management.app.book;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookFindRequest {
  private String title;
  private String author;
  private String isbn;
  private List<Genre> genre;
  private Integer year;
}
