package inauri.alex.library.management.app.book;

import inauri.alex.library.management.app.book.copy.BookCopyMapper;
import org.mapstruct.Mapper;

@Mapper(uses = BookCopyMapper.class)
public interface BookMapper {

  Book map(BookDto bookDto);

  BookDto map(Book book);
}
