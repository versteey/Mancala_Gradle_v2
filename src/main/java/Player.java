/**
 * Created by eversteeg on 25-11-2014.
 */
public class Player {
    private boolean turn;
    private GameField linkToGameField;
    private Player opponent;

    public Player(boolean turn) {
        this.turn = turn;
    }

    /**
     * if a first player is made the now made Player can be made and implemented with the opponent variable correctly.
     * It also changes the opponent variable of the given Player to the newly made Player.
     *
     * @param player is the already existing Player.
     */
    public Player(Player player){
        this.turn = false;
        this.opponent = player;
        this.opponent.setOpponent(this);
    }

    /**
     * Sets the oppenent variable of a Player.
     * @param opponent type Player
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * Gets the oppenent variable of a Player.
     * @return opponent
     * @type Player
     */
    public Player getOpponent(){
        return this.opponent;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public boolean getTurn(){
        return this.turn;
    }

    public void turnOver() {
        this.setTurn(false);
        this.opponent.setTurn(true);
    }

    public void setLinkToGameField(GameField gameField){
        this.linkToGameField = gameField;
    }

    public GameField getLinkToGameField(){
        return linkToGameField;
    }

    public GameField getGameField(int wanted){
        GameField current = this.getLinkToGameField();
        for(int i = 0; i < wanted; i++){
            current = (GameField)current.getNextField();
        }
        return current;
    }
}
