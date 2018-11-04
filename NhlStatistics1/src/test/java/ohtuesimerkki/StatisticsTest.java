package ohtuesimerkki;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StatisticsTest {

    private Statistics underTest;

    @Before
    public void setUp() throws Exception {
        File file = new File("src/test/resources/players.txt");
        Reader reader = new PlayerReader(new Scanner(file));
        underTest = new Statistics(reader);
    }

    @Test
    public void findsPlayerByName() {
        String expectedName = "Sidney Crosby";
        Player found = underTest.search(expectedName);
        assertEquals(expectedName, found.getName());
    }

    @Test
    public void findsPlayersByTeam() {
        String expectedTeam = "PHI";
        List<Player> found = underTest.team(expectedTeam);

        Set<String> foundTeams = found.stream().map(Player::getTeam).collect(Collectors.toSet());
        assertEquals(Collections.singleton(expectedTeam), foundTeams);
    }

    @Test
    public void doesNotFindNonExistentPlayer() {
        String nonexistentPlayer = "Matti Meikäläinen";
        Player found = underTest.search(nonexistentPlayer);
        assertNull(found);
    }

    @Test
    public void doesNotFindNonExistentTeam() {
        String nonexistentTeam = "OOO";
        List<Player> found = underTest.team(nonexistentTeam);
        assertTrue(found.isEmpty());
    }

    @Test
    public void findsTopScorers() {
        int expected = 10;
        List<Player> found = underTest.topScorers(expected);

        assertEquals(expected, found.size());

        int max = -1;
        for (Player player : found) {
            int score = player.getPoints();
            if (score < max) {
                fail("Top Scorers not ordered properly.");
            }
        }
    }

    @Test
    public void topScorersDoesNotAcceptNegative() {
        List<Player> found = underTest.topScorers(-1);
        assertTrue(found.isEmpty());
    }
}