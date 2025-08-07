package inauri.alex.library.management.app.book.copy;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookCopyMapper {

  @Mapping(target = "book.id", source = "bookId")
  BookCopy map(BookCopyDto bookCopyDto);

  @Mapping(target = "bookId", source = "book.id")
  BookCopyDto map(BookCopy bookCopy);
}
