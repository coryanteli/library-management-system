package inauri.alex.library.management.app.book;

import inauri.alex.library.management.common.exception.LibraryException;
import inauri.alex.library.management.app.book.copy.BookCopy;
import inauri.alex.library.management.common.util.SpecificationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static inauri.alex.library.management.app.book.BookSpecifications.*;

@Service
@RequiredArgsConstructor
public class BookServiceBean implements BookService {

  private final BookRepository bookRepository;

  @Override
  public Book findById(int id) {
    Specification<Book> specification = SpecificationUtils.fetchJoin(Book_.copies).and(idEq(id));
    return bookRepository.findOne(specification).orElseThrow(LibraryException::notFound);
  }

  @Override
  public Page<Book> findAll(BookFindRequest request, Pageable pageable) {
    Specification<Book> specification =
        SpecificationUtils.fetchJoin(Book_.copies)
            .and(likeAuthor(request.getAuthor()))
            .and(likeTitle(request.getTitle()))
            .and(yearBetween(request.getYearMoreThan(), request.getYearLessThan()))
            .and(genresIn(request.getGenre()))
            .and(isbnEq(request.getIsbn()))
            .and(isAvailable(request.getIsAvailable()));
    return bookRepository.findAll(specification, pageable);
  }

  @Override
  @Transactional
  public Book create(Book book) {
    for (BookCopy copy : book.getCopies()) {
      copy.setBook(book);
    }
    return bookRepository.save(book);
  }

  @Override
  @Transactional
  public Book update(int id, Book book) {
    if (!bookRepository.existsById(id)) {
      throw LibraryException.notFound();
    }
    book.setId(id);
    for (BookCopy copy : book.getCopies()) {
      copy.setBook(book);
    }
    return bookRepository.save(book);
  }

  @Override
  @Transactional
  public void delete(int id) {
    if (!bookRepository.existsById(id)) {
      throw LibraryException.notFound();
    }
    bookRepository.deleteById(id);
  }
}
