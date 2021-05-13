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

    public String save(Author author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
            return "Se ha registrado exitosamente!";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar el autor!";
        }
    }

    @Override
    public String modify(Integer id, String name, String country) {
        entityManager.getTransaction().begin();
        Optional<Author> author = this.findById(id);
        if (!author.isPresent()) return "No existe el autor con el id ingresado!";
        author.get().setName(name);
        author.get().setCountry(country);
        entityManager.getTransaction().commit();
        return "Se ha modificado exitosamente!";
    }

    @Override
    public void delete(Author author) {
        entityManager.getTransaction().begin();

        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

}
