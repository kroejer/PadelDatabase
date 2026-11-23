package org.example.service;

import org.example.model.PadelBat;
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
        validateName(name);
        validateAge(age);
        validateLevel(level);
        validateBatId(padelBatId);
        repository.addPlayer(name, age, level, club, padelBatId);
    }

    public void changePlayerLevel(Integer playerId, Double level) throws SQLException {
        validatePlayerId(playerId);
        validateLevel(level);
        repository.changePlayerLevel(playerId, level);
    }

    public void changePlayerBat(Integer playerId, Integer batId) throws SQLException {
        validatePlayerId(playerId);
        validateBatId(batId);
        repository.changePlayerBat(playerId, batId);
    }

    public boolean deletePlayer(Integer playerId) throws SQLException {
        validatePlayerId(playerId);
        return repository.deletePlayer(playerId);
    }

    public List<PadelBat> findBats() throws SQLException {
        return repository.findAllBats();
    }

    public void addBat(String model, String brand, String form) throws SQLException {
        validateModel(model);
        validateBrand(brand);
        validateForm(form);
        repository.addBat(model,brand,form);
    }

    public boolean deleteBat(Integer batId) throws SQLException {
        validateBatId(batId);
        return repository.deleteBat(batId);
    }

    private void validatePlayerId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Spiller ID'et skal være et positivt heltal.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > 50) {
            throw new IllegalArgumentException("Navnet skal være mellem  1 og 50 tegn.");
        }
    }

    private void validateAge(Integer age) {
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Alderen skal være et positivt heltal.");
        }
    }

    private void validateLevel(Double level) {
        if (level == null || level < 1 || level > 7) {
            throw new IllegalArgumentException("Niveauet skal være et tal mellem 1 og 7.");
        }
    }

    private void validateBatId(Integer batId) {
        if (batId == null || batId <= 0) {
            throw new IllegalArgumentException("Padelbat ID'et skal være et positivt heltal.");
        }
    }

    private void validateModel(String model) {
        if (model == null || model.isBlank() || model.length() > 50) {
            throw new IllegalArgumentException("Modelnavnet skal være mellem 1 og 50 tegn.");
        }
    }

    private void validateBrand(String brand) {
        if (brand == null || brand.isBlank() || brand.length() > 50) {
            throw new IllegalArgumentException("Brandnavnet skal være mellem 1 og 50 tegn.");
        }
    }

    private void validateForm(String form) {
        if (form == null || form.isBlank() || form.length() > 50) {
            throw new IllegalArgumentException("Formen skal være mellem 1 og 50 tegn.");
        }
    }
}
