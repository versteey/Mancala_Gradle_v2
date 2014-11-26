import junit.framework.TestCase;

public class GameFieldTest extends TestCase {
    public void test_initialize_gamefield_4_stones(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4,player);
        int result = gfield.getNumberOfStones();
        assertEquals(4, result);
    }

    public void test_numberOfStones_becomes_zero(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4,player);
        gfield.makeZero();
        assertEquals(0, gfield.getNumberOfStones());
    }

    public void test_not_last_stone_add_1(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(1, player);
        gfield.notLastStone(1);
        int result = gfield.getNumberOfStones();
        assertEquals(2, result);
    }

    public void test_lastStone_myturn_is_over(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4, player);
        gfield.lastStone();
        boolean result = player.getTurn();
        assertFalse(result);
    }

    public void test_lastStone_myturn_add_1(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4,player);
        gfield.lastStone();
        int result = gfield.getNumberOfStones();
        assertEquals(5,result);
    }

    public void test_next_AbstractField(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4,player);
        GameField gfield1 = new GameField(3,player);
        gfield.setNext(gfield1);
        Object result = gfield.getNextField();
        assertEquals(gfield1, result);
    }

    public void test_make_normal_move_end_own_stone(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(3, player);
        GameField gfield1 = new GameField(3, player);
        GameField gfield2 = new GameField(3, player);
        GameField gfield3 = new GameField(3, player);
        gfield.setNext(gfield1);
        gfield1.setNext(gfield2);
        gfield2.setNext(gfield3);
        gfield.makeMove();
        int result = gfield3.getNumberOfStones();
        boolean result1 = player.getTurn();
        assertEquals(4, result);
        assertFalse(result1);
    }

    public void test_make_normal_move_end_enemy_stone(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(3, player);
        Kalaha gfield1 = new Kalaha(3, player);
        GameField gfield2 = new GameField(3, player1);
        GameField gfield3 = new GameField(3, player1);
        gfield.setNext(gfield1);
        gfield1.setNext(gfield2);
        gfield2.setNext(gfield3);
        gfield.makeMove();
        int result = gfield3.getNumberOfStones();
        boolean result1 = player.getTurn();
        assertEquals(4, result);
        assertFalse(result1);
    }

    public void test_make_move_last_stone_own_kalaha(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4, player);
        GameField gfield1 = new GameField(3, player);
        GameField gfield2 = new GameField(3, player);
        GameField gfield3 = new GameField(3, player);
        Kalaha kalaha = new Kalaha(0, player);
        gfield.setNext(gfield1);
        gfield1.setNext(gfield2);
        gfield2.setNext(gfield3);
        gfield3.setNext(kalaha);
        gfield.makeMove();
        int result = kalaha.getNumberOfStones();
        boolean result1 = player.getTurn();
        assertEquals(1, result);
        assertTrue(result1);
    }

    public void test_make_move_skip_enemy_kalaha(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(6, player);
        GameField gfield1 = new GameField(3, player);
        Kalaha kalaha = new Kalaha(0, player);
        GameField gfield2 = new GameField(3, player1);
        GameField gfield3 = new GameField(3, player1);
        Kalaha kalaha1 = new Kalaha(0, player1);
        gfield.setNext(gfield1);
        gfield1.setNext(kalaha);
        kalaha.setNext(gfield2);
        gfield2.setNext(gfield3);
        gfield3.setNext(kalaha1);
        kalaha1.setNext(gfield);
        gfield.makeMove();
        int result = kalaha1.getNumberOfStones();
        int result1 = gfield1.getNumberOfStones();
        assertEquals("enemy kahala", 0, result);
        assertEquals(5, result1);
    }

    public void test_no_make_move_on_enemy_gamefield(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(1,player1);
        GameField gfield1 = new GameField(1,player1);
        gfield.setNext(gfield1);
        gfield.makeMove();
        int result = gfield1.getNumberOfStones();
        assertEquals(1,result);
    }

    public void test_move_and_steal_from_enemy(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(1,player);
        Kalaha kalaha = new Kalaha(0, player);
        GameField gfield1 = new GameField(0,player);
        GameField gfield2 = new GameField(10,player1);
        gfield.setNext(gfield1);
        gfield1.setNext(kalaha);
        kalaha.setNext(gfield2);
        gfield.makeMove();
        int result = kalaha.getNumberOfStones();
        assertEquals(11, result);
    }

    public void test_move_dont_steal_when_on_enemy_gamefield(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(3,player);
        GameField gfield1 = new GameField(0,player);
        Kalaha kalaha = new Kalaha(0, player);
        GameField gfield2 = new GameField(0,player1);
        gfield.setNext(gfield1);
        gfield1.setNext(kalaha);
        kalaha.setNext(gfield2);
        gfield.makeMove();
        int result = gfield2.getNumberOfStones();
        assertEquals(1, result);
    }

    public void test_initialize_gamefields_kalahas(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4, player);
        gfield.initialize();
        Kalaha isMyKalahaConnected = gfield.getOwnKalaha();
        Kalaha isEnemyKalahaConnected = isMyKalahaConnected.getNextField().getOwnKalaha();
        GameField isCycle = (GameField)(isEnemyKalahaConnected.getNextField());
        assertNotNull("There was no kalaha found", isMyKalahaConnected);
        assertNotNull("There was no enemy kalaha found", isEnemyKalahaConnected);
        assertTrue("Game is not a cycle", isCycle == gfield);
    }

    public void test_choose_possible_gamefield_to_makeMove(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(4,player);
        gfield.initialize();
        player.setLinkToGameField(gfield);
        player1.setLinkToGameField((GameField) gfield.getOwnKalaha().getNextField());
        boolean[] result = GameField.choosePossibleGameField(player);
        for(boolean b : result) assertTrue(b);
    }

    public void test_is_game_over(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield =  new GameField(0, player);
        gfield.initialize();
        player.setLinkToGameField(gfield);
        boolean result = GameField.isGameOver(player);
        assertTrue(result);
    }

    public void test_is_game_over_and_all_fields_empty(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield = new GameField(0, player);
        gfield.initialize();
        player1.getLinkToGameField().addStone();
        GameField.isGameOver(player);
        int result = player1.getLinkToGameField().getNumberOfStones();
        assertEquals(0, result);
    }

    public void test_end_turn_on_enemy_field(){
        Player player = new Player(true);
        Player player1 = new Player(player);
        GameField gfield =  new GameField(2, player);
        gfield.initialize();
        gfield.makeMove();
        assertFalse(player.getTurn());
        assertTrue(player1.getTurn());
    }

}
