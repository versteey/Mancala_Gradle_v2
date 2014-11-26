/**
 * Created by eversteeg on 25-11-2014.
 */
public class GameField extends AbstractField{
    public GameField(int numberOfStones, Player player) {
        super(numberOfStones, player);
    }

    /**
     * This method initializes a whole mancala board.<br>
     * Every field has as many stones as the creator, except for the Kalaha's.<br>
     * Those Kalaha's are initialized with value 0.<br><br> easy
     *
     * It also sets the fieldLinks in Player
     */
    public void initialize(){
        Kalaha kalaha = new Kalaha(0, this.getOwner());
        Player opponent = this.getOwner().getOpponent();
        Kalaha kalaha1 = new Kalaha(0, opponent);
        this.getOwner().setLinkToGameField(this);
        GameField current = this;
        kalaha1.setNext(current);
        for (int i = 0; i < 5; i++){
            GameField temp = new GameField(this.getNumberOfStones(), this.getOwner());
            current.setNext(temp);
            current = temp;
        }
        current.setNext(kalaha);
        current = new GameField(this.getNumberOfStones(), opponent);
        opponent.setLinkToGameField(current);
        kalaha.setNext(current);
        for (int i = 0; i < 5; i++){
            GameField temp = new GameField(this.getNumberOfStones(), opponent);
            current.setNext(temp);
            current = temp;
        }
        current.setNext(kalaha1);

    }

    public void makeMove() {
        if (this.getOwner().getTurn()){
            int stones = makeZero();
            getNextField().notLastStone(stones);
        }
    }

    public int makeZero() {
        int result = this.getNumberOfStones();
        this.setNumberOfStones(0);
        return result;
    }

    @Override
    public void lastStone() {
        this.addStone();
        if (this.getNumberOfStones() == 1 && this.getOwner().getTurn()){
            Kalaha kalaha = this.getOwnKalaha();
            kalaha.addStone();
            this.makeZero();
            GameField opposite = this.getOpposite(0, Integer.MAX_VALUE);
            opposite.steal(kalaha);
        }
        // Else do nothing
        if(this.getOwner().getTurn()) {this.getOwner().turnOver(); }
        else { this.getOwner().getOpponent().turnOver(); }
    }

    public Kalaha getOwnKalaha(){
        return this.getNextField().getOwnKalaha();
    }

    public void steal(Kalaha kalaha){
        int stones = this.makeZero();
        kalaha.addStolenStones(stones);
    }

    public String[] toPrint(){
        String[] result = new String[14];
        AbstractField current = this;
        for (int i = 0; i < 14; i++){
            int stones = current.getNumberOfStones();
            if (stones < 10) { result[i] = " "+stones; }
            else { result[i] = "" + stones; }
            current = current.getNextField();
        }
        return result;
    }

    public static boolean[] choosePossibleGameField(Player player) {
        boolean[] choices = new boolean[6];
        AbstractField current = player.getLinkToGameField();
        for (int i = 0; i < choices.length; i++){
            if (current.getNumberOfStones() > 0) { choices[i] = true; }
            else { choices[i] = false; }
            current = current.getNextField();
        }
        return choices;
    }

    /**
     *
     * @param player The game is over when the current Player cannot move
     * @return Is true when game is over.
     */
    public static boolean isGameOver(Player player){
        boolean result = false;
        for (boolean b : choosePossibleGameField(player)){
            if(b) { result = true; }
        }
        if(!result) {
            clearFields(player);
        }
        return !result;
    }

    public static void clearFields(Player player) {
        GameField current = player.getLinkToGameField();
        Kalaha kalaha = current.getOwnKalaha();
        for (int i = 0; i < 6; i++) {
            current.steal(kalaha);
            current.getNextField();
        }
        current = (GameField) kalaha.getNextField();
        kalaha = current.getOwnKalaha();
        for (int i = 0; i < 6; i++) {
            current.steal(kalaha);
            current.getNextField();
        }
    }

    @Override
    public GameField getOpposite(int countPlace, int wanted) {
        if (countPlace == wanted){
            return this;
        }
        else {
            return this.getNextField().getOpposite(++countPlace, wanted);
        }
    }

    @Override
    public void notLastStone(int stones) {
        if (stones > 1) {
            this.addStone();
            this.getNextField().notLastStone(stones - 1);
        }
        else { this.lastStone(); }
    }
}
