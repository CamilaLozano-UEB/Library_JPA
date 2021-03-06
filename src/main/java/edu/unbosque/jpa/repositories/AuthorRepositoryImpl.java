package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Author;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepository {

    private EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find an Author by id
     *
     * @param id the Author id to be searched
     * @return an optional object of author
     */
    @Override
    public Optional<Author> findById(Integer id) {
        Author author = entityManager.find(Author.class, id);
        return author != null ? Optional.of(author) : Optional.empty();
    }

    /**
     * Finds all the authors of the DB
     *
     * @return a list of Author
     */
    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("from Author").getResultList();
    }

    /**
     * Saves a new Author to the DB
     *
     * @param author the author to be saved
     * @return a message of the result
     */
    @Override
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

    /**
     * Find an Author by id and modify his attributes
     *
     * @param id      the new id
     * @param name    the new name
     * @param country the new country
     * @return a message of the result
     */
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

    /**
     * Delete an Author from th DB
     *
     * @param author the Author to delete
     */
    @Override
    public void delete(Author author) {
        entityManager.getTransaction().begin();
        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

}
