package inauri.alex.library.management.app.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

  @Query(
      """
              select b from Book b
              left join fetch b.copies
              where (:#{#bookFindRequest.author} is not null or b.author = :#{#bookFindRequest.author})
              and (:#{#bookFindRequest.title} is not  null or b.title LIKE LOWER(CONCAT('%', :#{#bookFindRequest.title}, '%')))
              and (:#{#bookFindRequest.year} is not  null or b.year = :#{#bookFindRequest.year})
              and (:#{#bookFindRequest.genre} is not  null or b.genre in :#{#bookFindRequest.genre})
              and (:#{#bookFindRequest.isbn} is not  null or b.isbn = :#{#bookFindRequest.isbn})
              """)
  Page<Book> getAll(BookFindRequest bookFindRequest, Pageable pageable);

  @Query(
      """
            select b from Book b
            left join fetch b.copies
            where b.id = :id
        """)
  Optional<Book> getById(int id);
}
