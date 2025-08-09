package inauri.alex.library.management.app.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private Integer yearMoreThan;
  private Integer yearLessThan;
  private Boolean isAvailable;
}
