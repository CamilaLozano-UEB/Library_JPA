package edu.unbosque.jpa.repositories;

import edu.unbosque.jpa.entities.Edition;

import java.util.List;
import java.util.Optional;

public class EditionRepositoryImpl implements EditionRepository{
    @Override
    public Optional<Edition> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Edition> findAll() {
        return null;
    }

    @Override
    public Optional<Edition> save(Edition edition) {
        return Optional.empty();
    }

    @Override
    public void modify(Integer id, String name, String country) {

    }

    @Override
    public void delete(Integer id) {

    }
}
