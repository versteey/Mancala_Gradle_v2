import junit.framework.TestCase;
import junit.framework.Assert;

public class PlayerTest extends TestCase {
    public void test_player_myturn() {
        Player player = new Player(true);
        boolean result = player.getTurn();
        Assert.assertTrue(result);
    }

    public void test_player_not_myturn() {
        Player player = new Player(false);
        boolean result = player.getTurn();
        Assert.assertFalse(result);
    }

    public void test_turn_over() {
        Player player = new Player(true);
        Player player1 = new Player(player);
        player.turnOver();
        boolean result = player1.getTurn();
        Assert.assertTrue(result);
    }
}