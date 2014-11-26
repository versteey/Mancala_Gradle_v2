import junit.framework.TestCase;

public class MainTest extends TestCase {
    public void test_main(){
        Main main = new Main();
        Player player =  new Player(true);
        Player opponent = new Player(player);
        GameField creator = new GameField(4, player);
        creator.initialize();
        assertEquals(creator,player.getGameField(0));
    }
}