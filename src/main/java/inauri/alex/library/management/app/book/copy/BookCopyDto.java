package inauri.alex.library.management.app.book.copy;

import java.time.LocalDateTime;

public record BookCopyDto(Integer id, Integer bookId, boolean available, LocalDateTime addedAt) {}
