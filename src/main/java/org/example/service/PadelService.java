package org.example.service;

import org.example.model.PadelPlayer;
import org.example.repository.PadelRepository;

import java.sql.SQLException;
import java.util.List;

public class PadelService {
    private final PadelRepository repository;

    public PadelService(PadelRepository repository) {
        this.repository = repository;
    }

    public List<PadelPlayer> findPlayers() throws SQLException {
        return repository.findAllPlayers();
    }

    public void addPlayer(String name, Integer age, Double level, String club, Integer padelBatId) throws SQLException {
        if (name == null || name.isBlank() || name.length() > 50) {
            throw new IllegalArgumentException("Navnet skal være mellem  1 og 50 tegn.");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Alderen skal være et positivt heltal.");
        }
        if (level == null || level <= 0 || level > 7) {
            throw new IllegalArgumentException("Niveauet skal være et tal mellem 1 og 7.");
        }
        if (padelBatId == null || padelBatId <= 0) {
            throw new IllegalArgumentException("Padel bat id'et skal være et positivt heltal.");
        }
        repository.addPlayer(name, age, level, club, padelBatId);
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id skal være positivt.");
        }
    }
}
