package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Author;
import edu.unbosque.servlets.pojos.AuthorPOJO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepository {

    private EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Author> findById(Integer id) {
        Author author = entityManager.find(Author.class, id);
        return author != null ? Optional.of(author) : Optional.empty();
    }

    public List<Author> findAll() {
        return entityManager.createQuery("from Author").getResultList();
    }

    public Optional<Author> findByName(String name) {
        Author author = entityManager.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", name)
                .getSingleResult();
        return author != null ? Optional.of(author) : Optional.empty();
    }

    public Optional<Author> save(Author author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
            return Optional.of(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void modify(Integer id, String name, String country) {
        entityManager.getTransaction().begin();
        Author author = this.findById(id).get();
        if (author == null) return;
        author.setName(name);
        author.setCountry(country);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Author author) {
        entityManager.getTransaction().begin();

        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

}
