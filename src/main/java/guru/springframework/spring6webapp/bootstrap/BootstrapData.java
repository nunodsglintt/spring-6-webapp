package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = this.authorRepository.save(eric);
        Book dddSaved = this.bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Jonhson");


        Book noEJB = new Book();
        noEJB.setTitle("Spring in Action");
        noEJB.setIsbn("124354673");

        Author rodSaved = this.authorRepository.save(rod);
        Book noEJBSaved = this.bookRepository.save(noEJB);



        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        // Add New Publisher to Bootstrap
        Publisher fakePub = new Publisher();
        fakePub.setPublisherName("Fake Pub Publications");
        fakePub.setAddress("Street LaMontana n 782");
        fakePub.setCity("Los Angeles");
        fakePub.setState("California");
        fakePub.setZip("90005");

        Publisher savedPublisher = this.publisherRepository.save(fakePub);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);

        this.authorRepository.save(ericSaved);
        this.authorRepository.save(rodSaved);
        this.bookRepository.save(dddSaved);
        this.bookRepository.save(noEJBSaved);



        System.out.println("In Bootstrap");
        System.out.println("Number of authors: "+ this.authorRepository.count());
        System.out.println("Number of books: "+ this.bookRepository.count());
        System.out.println("Number of publishers: "+ this.publisherRepository.count());

    }
}
