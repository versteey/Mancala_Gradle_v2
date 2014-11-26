import junit.framework.TestCase;

public class MainTest extends TestCase {
    public void test_main(){
        Main main = new Main();
        Player player =  new Player(true);
        Player opponent = new Player(player);
        GameField creator = new GameField(4, player);
        creator.initialize();
        assertEquals(creator.getNextField(), player.getGameField(1));
    }

    public void test_toPrint(){
        Main main = new Main();
        Player player =  new Player(true);
        Player opponent = new Player(player);
        GameField creator = new GameField(4, player);
        creator.initialize();
        String[] a = creator.toPrint();
        String[] b = new String[] {" 4"," 4"," 4"," 4"," 4"," 4"," 0"," 4"," 4"," 4"," 4"," 4"," 4"," 0"};
        for( int i = 0 ; i < b.length; i++){
            assertTrue(b[i].equals(a[i]));
        }
    }
}