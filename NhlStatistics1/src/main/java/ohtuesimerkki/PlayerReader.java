package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerReader implements Reader {

    private Scanner scanner;

    public PlayerReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] parts =  scanner.nextLine().split(";");            
            
            if (parts.length > 3) {
                players.add(new Player(parts[0].trim(), parts[1], extractInt(parts[3]), extractInt(parts[4])));
            }
        }

        return players;
    }

    private int extractInt(String str) {
        return Integer.parseInt(str.trim());
    }
}
