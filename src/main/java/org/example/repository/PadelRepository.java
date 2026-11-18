package org.example.repository;

import org.example.dal.DatabaseConnection;
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
}
