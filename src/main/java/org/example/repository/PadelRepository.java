package org.example.repository;

import org.example.dal.DatabaseConnection;
import org.example.model.PadelBat;
import org.example.model.PadelPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PadelRepository {
    public List<PadelPlayer> findAllPlayers() throws SQLException {
        String sql = "SELECT * FROM padelplayers ORDER BY padelplayerid";
        List<PadelPlayer> padelPlayerList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rows = statement.executeQuery()) {
                while (rows.next()) {
                    padelPlayerList.add(new PadelPlayer(rows.getInt("padelplayerid"), rows.getString("name"), rows.getInt("age"),
                            rows.getDouble("level"), rows.getString("club"), rows.getInt("padelbatid")));
                }
            }
        }
        return padelPlayerList;
    }

    public void addPlayer(String name, Integer age, Double level, String club, Integer padelBatId) throws SQLException {
        String sql = "INSERT INTO padelplayers (name, age, level, club, padelbatid) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setDouble(3, level);
            statement.setString(4, club);
            statement.setInt(5, padelBatId);
            statement.executeUpdate();
        }
    }

    public void changePlayerLevel(Integer playerId, Double level) throws SQLException {
        String sql = "UPDATE padelplayers SET level=? WHERE padelplayerid=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, level);
            statement.setInt(2, playerId);
            statement.executeUpdate();
        }
    }

    public void changePlayerBat(Integer playerId, Integer batId) throws SQLException {
        String sql = "UPDATE padelplayers SET padelbatid=? WHERE padelplayerid=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, batId);
            statement.setInt(2, playerId);
            statement.executeUpdate();
        }
    }

    public boolean deletePlayer(Integer playerId) throws SQLException {
        String sql = "DELETE FROM padelplayers WHERE padelplayerid=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playerId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public List<PadelBat> findAllBats() throws SQLException {
        String sql = "SELECT * FROM padelbats ORDER BY padelbatid";
        List<PadelBat> padelBatList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rows = statement.executeQuery()) {
                while (rows.next()) {
                    padelBatList.add(new PadelBat(rows.getInt("padelbatid"), rows.getString("model"), rows.getString("brand"), rows.getString("form")));
                }
            }
        }
        return padelBatList;
    }

    public void addBat(String model, String brand, String form) throws SQLException {
        String sql = "INSERT INTO padelbats (model, brand, form) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model);
            statement.setString(2, brand);
            statement.setString(3, form);
            statement.executeUpdate();
        }
    }

    public boolean deleteBat(Integer batId) throws SQLException {
        String sql = "DELETE FROM padelbats WHERE padelbatid=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, batId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }
}
