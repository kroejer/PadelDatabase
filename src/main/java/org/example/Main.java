package org.example;


import org.example.cli.PadelCli;
import org.example.repository.PadelRepository;
import org.example.service.PadelService;

import java.util.Scanner;

public class Main {
    static void main() {
        PadelRepository repository = new PadelRepository();
        PadelService service = new PadelService(repository);
        try (Scanner input = new Scanner(System.in)) {
            new PadelCli(input, service).run();
        }
    }
}
