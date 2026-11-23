package org.example.cli;

import org.example.model.PadelBat;
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
                    "\n3. Opdater spiller nivea" +
                    "\n4. Ret spillers padelbat" +
                    "\n5. Slet spiller" +
                    "\n6. Se liste af padel bats" +
                    "\n7. Tilføj padel bat" +
                    "\n8. Slet padel bat" +
                    "\n0. Afslut");
            try {
                switch (ask("Vælg")) {
                    case "1" -> showPlayers();
                    case "2" -> addPlayer();
                    case "3" -> changePlayerLevel();
                    case "4" -> changePlayerBat();
                    case "5" -> deletePlayer();
                    case "6" -> showBats();
                    case "7" -> addBat();
                    case "8" -> deleteBat();
                    case "0" -> { return; }
                    default -> System.out.println("Vælg fra 0 til 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Alder/id skal være et positivt heltal");
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

    public void changePlayerLevel() throws SQLException {
        Integer playerId = Integer.parseInt(ask("Indtast ID på spilleren som skal have ændret niveau"));
        Double newLevel = Double.parseDouble(ask("Spillerens nye niveau"));
        service.changePlayerLevel(playerId, newLevel);
        System.out.println("Spillerens niveau er opdateret");
    }

    public void changePlayerBat() throws SQLException {
        Integer playerId = Integer.parseInt(ask("Indtast ID på spilleren der skal have ændret padelbat"));
        Integer batId = Integer.parseInt(ask("Indtast ID for det nye padelbat"));
        service.changePlayerBat(playerId, batId);
        System.out.println("Spillerens padelbat er opdateret");
    }

    public void deletePlayer() throws SQLException {
        Integer playerId = Integer.parseInt(ask("Indtast ID på spilleren der skal slettes"));
        boolean deleted = service.deletePlayer(playerId);
        if (deleted) {
            System.out.println("Spilleren er slettet.");
        } else {
            System.out.println("Spilleren findes ikke.");
        }
    }

    public void showBats() throws SQLException {
        List<PadelBat> padelBatList = service.findBats();
        if (padelBatList.isEmpty()) {
            System.out.println("Ingen padel spillere i listen.");
        }
        for (PadelBat bat : padelBatList) {
            System.out.printf("%d | %s | %s | %s%n", bat.getId(), bat.getModel(), bat.getBrand(), bat.getForm());
        }
    }

    public void addBat() throws SQLException {
        String model = ask("Indtast modelnavn");
        String brand = ask("Indtast brandnavn");
        String form = ask("Indtast form");
        service.addBat(model,brand,form);
        System.out.println("Padelbattet er gemt!");
    }

    public void deleteBat() throws SQLException {
        Integer batId = Integer.parseInt(ask("Indtast ID på det padelbat der skal slettes"));
        boolean deleted = service.deleteBat(batId);
        if (deleted) {
            System.out.println("Padelbattet er slettet.");
        } else {
            System.out.println("Padelbattet findes ikke.");
        }
    }

    private String ask(String prompt) {
        System.out.print(prompt + ": ");
        return input.nextLine().strip();
    }
}
