package io.spring.challengeJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EbookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void deleteEbookById(int id) {
        Ebook ebook = entityManager.find(Ebook.class, id);
        entityManager.remove(ebook);
    }

    public Ebook updateEbook(Ebook ebook) {
        return entityManager.merge(ebook);
    }

    public Ebook insertEbook(Ebook ebook) {
        return entityManager.merge(ebook);
    }

    public Ebook getEbookById(int id) {
        return entityManager.find(Ebook.class, id);
    }

    public List<Ebook> getAllEbooks() {
        TypedQuery<Ebook> query = entityManager.createNamedQuery("get_all_ebooks", Ebook.class);

        return query.getResultList();
    }
}