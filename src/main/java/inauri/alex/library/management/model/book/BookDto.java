package inauri.alex.library.management.model.book;

import inauri.alex.library.management.model.book.copy.BookCopyDto;
import java.util.List;

public record BookDto(
    Integer id,
    String title,
    String author,
    Genre genre,
    Integer year,
    String description,
    List<BookCopyDto> copies) {}
