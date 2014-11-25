import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    public void test_player_myturn() {
        Player player = new Player(true);
        boolean result = player.getTurn();
        assertTrue(result);
    }

    public void test_player_not_myturn() {
        Player player = new Player(false);
        boolean result = player.getTurn();
        assertFalse(result);
    }

    public void test_turn_over() {
        Player player = new Player(true);
        Player player1 = new Player(player);
        player.turnOver();
        boolean result = player1.getTurn();
        assertTrue(result);
    }
}
