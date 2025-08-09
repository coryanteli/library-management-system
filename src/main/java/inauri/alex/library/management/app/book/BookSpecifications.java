package inauri.alex.library.management.app.book;

import inauri.alex.library.management.app.book.copy.BookCopy_;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static inauri.alex.library.management.common.util.SpecificationUtils.*;

public class BookSpecifications {

  public static Specification<Book> idEq(Integer id) {
    return eq(Book_.id, id);
  }

  public static Specification<Book> likeAuthor(String author) {
    return like(Book_.author, author);
  }

  public static Specification<Book> likeTitle(String title) {
    return like(Book_.title, title);
  }

  public static Specification<Book> yearBetween(Integer yearMore, Integer yearLess) {
    return greaterOrEqual(Book_.year, yearMore).and(lessOrEqual(Book_.year, yearLess));
  }

  public static Specification<Book> genresIn(List<Genre> genres) {
    return in(Book_.genre, genres);
  }

  public static Specification<Book> isbnEq(String isbn) {
    return eq(Book_.isbn, isbn);
  }

  public static Specification<Book> isAvailable(Boolean isAvailable) {
    return innerEq(Book_.copies, BookCopy_.available, isAvailable);
  }
}
