package org.example.cli;

import org.example.model.PadelPlayer;
import org.example.service.PadelService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PadelCli {
    private final Scanner input;
    private final PadelService service;

    public PadelCli(Scanner input, PadelService service) {
        this.input = input;
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n---Padel Database Menu---" +
                    "\n1. Se liste af padel spillere" +
                    "\n2. Tilføj spiller" +
                    "\n3. Ret spiller level" +
                    "\n4. Ret spillers padel bat" +
                    "\n5. Slet spiller" +
                    "\n6. Se liste af padel bats" +
                    "\n7. Tilføj padel bat" +
                    "\n8. Slet padel bat" +
                    "\n0. Afslut");
            try {
                switch (ask("Vælg")) {
                    case "1" -> showPlayers();
                    case "2" -> addPlayer();
                    //case "3" -> changePlayerLevel();
                    //case "4" -> changePlayerBat();
                    //case "5" -> deletePlayer();
                    //case "6" -> showBats();
                    //case "7" -> addBat();
                    //case "8" -> deleteBat();
                    //case "0" -> { return; }
                    default -> System.out.println("Vælg fra 0 til 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Alder/id skal være et positivt heltal, og niveau skal være et tal mellem 1 og 7.");
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Databasefejl: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                return;
            }
        }
    }

    public void showPlayers() throws SQLException {
        List<PadelPlayer> padelPlayerList = service.findPlayers();
        if (padelPlayerList.isEmpty()) {
            System.out.println("Ingen padel spillere i listen.");
        }
        for (PadelPlayer player : padelPlayerList) {
            System.out.printf("%d | %s | %d | %.1f | %s | %d%n", player.getId(), player.getName(), player.getAge(), player.getLevel(), player.getClub(), player.getPadelBatId());
        }
    }

    public void addPlayer() throws SQLException {
        String name = ask("Navn");
        Integer age = Integer.parseInt(ask("Alder"));
        Double level = Double.parseDouble(ask("Niveau"));
        String club = ask("Klub");
        Integer padelBatId = Integer.parseInt(ask("Padel bat id"));
        service.addPlayer(name, age, level, club, padelBatId);
        System.out.println("Spilleren er gemt.");
    }

    private String ask(String prompt) {
        System.out.print(prompt + ": ");
        return input.nextLine().strip();
    }
}
