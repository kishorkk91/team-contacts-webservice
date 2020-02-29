package config;

import com.example.contactsrestfulwebservices.persistence.ContactsEntity;
import com.example.contactsrestfulwebservices.repository.ContactsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class DataGenerator implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataGenerator.class);


    private final ContactsRepository contactsRepository;

    @Inject
    public DataGenerator(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        logger.info("data generating started......");

        ContactsEntity contactsEntity;
        String email = "demo@myap.com";
        String fname = "fname";
        String lname = "lname";

        for (int i = 5; i < 15; i++){
            contactsEntity = new ContactsEntity(i+email, i+fname, i+lname);
            contactsRepository.save(contactsEntity);
        }

        logger.info("data generating finished......");

    }
}
