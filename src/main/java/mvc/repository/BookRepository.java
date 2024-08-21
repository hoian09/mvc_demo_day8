package mvc.repository;

import mvc.entitybook.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    BookEntity findById(int bookID);
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByNameAndAuthor(String name, String author);


    BookEntity findByBookDetailsIsbn(String isbn);

    List<BookEntity> findByBookDetailsPriceLessThan(int price);
    List<BookEntity> findByBookDetailsLessThanEqual(int price);
    List<BookEntity> findByBookDetailsPriceGreaterThanEqual (int price);

    List<BookEntity> findByNameContaining(String name);
    @Query("select b from BookEntity b where b.name like ?1%")
    List<BookEntity> getBookNameStartWith(String name);

    @Query("select b from BookEntity b where b.bookDetails.price > ?1")
    List<BookEntity> getBookPriceGreaterThan (int price);
    @Query(value = "select * from book", nativeQuery = true)
    List<BookEntity> findAll();
    List<BookEntity> findByNameContainingOrAuthorContaining(String name, String author);

}
