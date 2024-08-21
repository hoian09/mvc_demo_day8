package mvc.main;

import mvc.configuration.JPAConfig;
import mvc.entitybook.BookDetailEntity;
import mvc.entitybook.BookEntity;
import mvc.entitybook.CategoryEntity;
import mvc.repository.BookRepository;
import mvc.repository.CategoryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    static CategoryRepository categoryRepository = (CategoryRepository) context.getBean("categoryRepository");
    public static void main(String[] args) {

        createNewBookEntryWithNewCategory();
        createNewBookEntry();
        createYetAnotherBookEntryWithAnotherCategory();
        createYetAnotherBook();
        findByAuthor("Roger");
        findByNameAndAuthor("Java A-Z", "Roger");
        findByPriceLessThan(80);
        findByBookDetailsIsbn("ISIBF1219323");
        findByNameContaining("Nu");
        findAllUsingQuery();
    }

    public static void findByAuthor(String author) {
        List<BookEntity> bookEntityList = bookRepository.findByAuthor(author);
        if (bookEntityList != null) {
            System.out.println("\nFind" + bookEntityList.size() + "books which author = " + author);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }
    private static void deleteBook() {
        bookRepository.deleteById(1);
        BookEntity bookEntity = bookRepository.findById(2);
        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        }
        bookRepository.deleteAll();
    }
    private static void updateBook() {
        BookEntity bookEntity = bookRepository.findById(2);
        if (bookEntity != null) {
            bookRepository.save(bookEntity);
        }
    }

    public static void findByNameAndAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameAndAuthor(name, author);
        if (bookEntityList != null) {
            System.out.println("\nFind" + bookEntityList.size() + "books which name = " + name + "and author = " + author);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByPriceLessThan(int price) {
        List<BookEntity> bookEntityList = bookRepository.findByBookDetailsPriceLessThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind" + bookEntityList.size() + "books price less than " + price);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContaining(String name) {
        List<BookEntity> bookEntityList = bookRepository.findByNameContaining(name);
        if (bookEntityList != null) {
            System.out.println("\nFind" + bookEntityList.size() + "books which containing name = " + name);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findAllUsingQuery() {
        List<BookEntity> bookEntityList = (List<BookEntity>) bookRepository.findAll();
        if (bookEntityList != null) {
            System.out.println("\nFind" + bookEntityList.size() + "books");
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByBookDetailsIsbn(String isbn) {
        BookEntity bookEntity = bookRepository.findByBookDetailsIsbn(isbn);
        if (bookEntity != null) {
            System.out.println("\nFind book which isbn = " + isbn );
            System.out.println(bookEntity.toString());
        }
    }
    public static void createNewBookEntry() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    public static void createNewBookEntryWithNewCategory() {
        CategoryEntity categoryEntity = createNewCatory();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createNewCatory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("IT");
        categoryEntity.setDescription("IT books");
        return categoryEntity;
    }
    private static BookEntity createNewBook() {
        BookDetailEntity bookDetailEntity = new BookDetailEntity();
        bookDetailEntity.setIsbn("ISIBF1219323");
        bookDetailEntity.setNumberOfPage(23);
        bookDetailEntity.setPrice(65);
        bookDetailEntity.setPublishDate(LocalDate.now());


        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setBookDetails(bookDetailEntity);
        bookDetailEntity.setBook(bookEntity);

        return bookEntity;
    }

    public static void createAnotherBookEntry() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);

        BookEntity bookEntity = createYetAnotherBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    public static void createYetAnotherBookEntryWithAnotherCategory() {
        CategoryEntity categoryEntity = createYetAnotherCatory();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createYetAnotherBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createYetAnotherCatory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Math");
        categoryEntity.setDescription("Math books");
        return categoryEntity;
    }
    private static BookEntity createYetAnotherBook() {
        BookDetailEntity bookDetailEntity = new BookDetailEntity();
        bookDetailEntity.setIsbn("ISINF2014587");
        bookDetailEntity.setNumberOfPage(20);
        bookDetailEntity.setPrice(100);
        bookDetailEntity.setPublishDate(LocalDate.now());


        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Phython for beginners");
        bookEntity.setAuthor("Bili");
        bookEntity.setBookDetails(bookDetailEntity);
        bookDetailEntity.setBook(bookEntity);

        return bookEntity;
    }
    public static void findBookNameStartWithUsingQuery(String name) {
        List<BookEntity> bookEntityList = bookRepository.getBookNameStartWith(name);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + "books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findBookPriceGreaterThanUsingQuery(int price) {
        List<BookEntity> bookEntityList = bookRepository.getBookPriceGreaterThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + "books");
            for (BookEntity bookEntity : bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }
}
