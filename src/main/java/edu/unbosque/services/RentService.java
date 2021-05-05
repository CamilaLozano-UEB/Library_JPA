package edu.unbosque.services;

import edu.unbosque.jpa.repositories.RentRepository;
import edu.unbosque.jpa.repositories.CostumerRepository;

import javax.ejb.Stateless;

@Stateless
public class RentService {
    CostumerRepository costumerRepository;
    RentRepository rentRepository;
}
