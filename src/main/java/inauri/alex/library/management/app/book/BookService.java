package inauri.alex.library.management.app.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book findById(int id);

    Page<Book> findAll(BookFindRequest request, Pageable pageable);

    Book create(Book book);

    Book update(int id, Book book);

    void delete(int id);
}
