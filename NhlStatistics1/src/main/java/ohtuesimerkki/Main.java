package ohtuesimerkki;

import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pageUrl = "http://nhlstatistics.herokuapp.com/players.txt";
        Scanner scanner;
        try {
            URL url = new URL(pageUrl);
            scanner = new Scanner(url.openStream());

            Statistics stats = new Statistics(new PlayerReader(scanner));

            System.out.println("Philadelphia Flyers");
            for (Player player : stats.team("PHI")) {
                System.out.println(player);
            }

            System.out.println("");

            System.out.println("Top scorers");
            for (Player player : stats.topScorers(10)) {
                System.out.println(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
