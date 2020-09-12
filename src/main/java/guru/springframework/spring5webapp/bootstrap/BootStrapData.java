package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher litera = new Publisher("Litera", "Bucuresti, Sector 1, 1234");
        publisherRepository.save(litera);

        Author eric = new Author("Eric", "Evans");
        Book ericBook = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(ericBook);
        ericBook.getAuthors().add(eric);

        ericBook.setPublisher(litera);
        litera.getBooks().add(ericBook);

        authorRepository.save(eric);
        bookRepository.save(ericBook);
        publisherRepository.save(litera);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "111222");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(litera);
        litera.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(litera);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books uploaded: " + bookRepository.count());
        System.out.println("Number of publishers uploaded: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + litera.getBooks().size());
    }
}
