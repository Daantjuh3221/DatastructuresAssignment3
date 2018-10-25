package nl.hva.ict.ds;

import static junit.framework.TestCase.assertEquals;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import nl.hva.ict.ds.util.NameReader;
import org.junit.Before;
import org.junit.Test;

/**
 * If you have any tests that should be overwritten or added please put them to this class.
 */
public class ExtendedPlayerFinderTest extends HighScorePlayerFinderTest {
    private Random randomizer = new SecureRandom();

    @Test
    public void whenNoPlayerMatchNonShouldBeFoundDoubleHashing() {
        List<Player> emptyList = super.highscores.findPlayer("no", "body");
        assertEquals(0, emptyList.size());
    }


    @Test
    public void collisionsShouldHappen() {
        String[] firstNames = new NameReader("/firstnames.txt").getNames();
        String[] lastNames = new NameReader("/lastnames.txt").getNames();
        int[] arraySizes = {10501, 11701, 13309, 15401};
        for(int arraySize : arraySizes){
            highscores = new HighScorePlayerFinder(arraySize); // Please adjust this size!
            for (int i = 0; i < 10000; i++) {
                String firstName = firstNames[randomizer.nextInt(firstNames.length)];
                String lastName = lastNames[randomizer.nextInt(lastNames.length)];
                highscores.add(new Player(firstName, lastName, randomizer.nextInt(1000)));
            }
            highscores.showCollisions(arraySize);
        }

    }
}
