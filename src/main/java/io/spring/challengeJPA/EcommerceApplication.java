package io.spring.challengeJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    EbookRepository ebookRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ebookRepo.insertEbook(new Ebook("Clean Code", "Robert C. Martin", 39.99));
        ebookRepo.insertEbook(new Ebook("Effective Java", "Joshua Bloch", 45.00));
        ebookRepo.insertEbook(new Ebook("Java Concurrency in Practice", "Brian Goetz", 50.00));
        ebookRepo.insertEbook(new Ebook("Design Patterns", "Erich Gamma", 55.00));

        logger.info("All Ebooks Data: {}", ebookRepo.getAllEbooks());
        logger.info("Inserting Ebook 5: {}", ebookRepo.insertEbook(new Ebook("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 54.99)));
        logger.info("Updating Ebook with ID 5: {}", ebookRepo.updateEbook(new Ebook(5, "Java Concurrency in Practice", "Brian Goetz", 80.00)));
        logger.info("Deleting Ebook with Id 2");
        ebookRepo.deleteEbookById(2);
        logger.info("All Ebooks Data: {}", ebookRepo.getAllEbooks());
    }

}
