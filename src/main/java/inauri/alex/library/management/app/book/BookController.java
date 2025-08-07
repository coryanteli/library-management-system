package inauri.alex.library.management.app.book;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {

  private final BookService bookService;

  private final BookMapper bookMapper;

  @GetMapping("{id}")
  public BookDto findById(@PathVariable int id) {
    return bookMapper.map(bookService.findById(id));
  }

  @GetMapping
  public Page<BookDto> findAll(
      @ModelAttribute @ParameterObject BookFindRequest request,
      @ParameterObject Pageable pageable) {
    return bookService.findAll(request, pageable).map(bookMapper::map);
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
    return ResponseEntity.status(CREATED)
        .body(bookMapper.map(bookService.create(bookMapper.map(bookDto))));
  }

  @PutMapping("{id}")
  public BookDto update(@PathVariable int id, @RequestBody BookDto bookDto) {
    return bookMapper.map(bookService.update(id, bookMapper.map(bookDto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    bookService.delete(id);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
