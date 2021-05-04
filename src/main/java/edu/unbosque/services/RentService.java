package edu.unbosque.services;

import edu.unbosque.jpa.repositories.RentRepository;
import edu.unbosque.jpa.repositories.UserRepository;

import javax.ejb.Stateless;

@Stateless
public class RentService {
    UserRepository userRepository;
    RentRepository rentRepository;
}
