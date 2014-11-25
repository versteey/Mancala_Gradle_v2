/**
 * Created by eversteeg on 25-11-2014.
 */
public abstract class AbstractField {
    private int numberOfStones;
    private Player owner;
    private AbstractField next;

    public AbstractField(int numberOfStones, Player player) {
        this.numberOfStones = numberOfStones;
        this.owner = player;
    }

    public int getNumberOfStones(){
        return numberOfStones;
    }

    public void setNumberOfStones(int numberOfStones){
        this.numberOfStones = numberOfStones;
    }

    public abstract void notLastStone(int stones);

    public void addStone(){
        this.numberOfStones++;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setNext(AbstractField next) {
        this.next = next;
    }

    public AbstractField getNextField() {
        return next;
    }

    public abstract GameField getOpposite(int countPlace, int wanted);

    public abstract void lastStone();

    public abstract Kalaha getOwnKalaha();
}
