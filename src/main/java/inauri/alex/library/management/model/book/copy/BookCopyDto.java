package inauri.alex.library.management.model.book.copy;

import java.time.LocalDateTime;

public record BookCopyDto(Integer id, Integer book_id, boolean available, LocalDateTime addedAt) {}
