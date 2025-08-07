package inauri.alex.library.management.app.book;

import inauri.alex.library.management.common.exception.LibraryException;
import inauri.alex.library.management.app.book.copy.BookCopy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceBean implements BookService {

  private final BookRepository bookRepository;

  @Override
  public Book findById(int id) {
    return bookRepository.getById(id).orElseThrow(LibraryException::notFound);
  }

  @Override
  public Page<Book> findAll(BookFindRequest request, Pageable pageable) {
    return bookRepository.getAll(request, pageable);
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
  public Book update(int id, Book book) {
    if (!bookRepository.existsById(id)) {
      throw LibraryException.notFound();
    }
    return bookRepository.save(book);
  }

  @Override
  public void delete(int id) {
    if (!bookRepository.existsById(id)) {
      throw LibraryException.notFound();
    }
    bookRepository.deleteById(id);
  }
}
