package inauri.alex.library.management.app.book;

import inauri.alex.library.management.app.book.copy.BookCopyDto;
import java.util.List;

public record BookDto(
    Integer id,
    String title,
    String isbn,
    String author,
    Genre genre,
    Integer year,
    String description,
    List<BookCopyDto> copies) {}
