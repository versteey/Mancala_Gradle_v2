/**
 * Created by eversteeg on 25-11-2014.
 */
public class Kalaha extends AbstractField{
    public Kalaha(int numberOfStones, Player player) {
        super(numberOfStones, player);
    }

    @Override
    public void lastStone() {
        this.addStone();
        //Player keeps his turn so do nothing
    }

    public Kalaha getOwnKalaha(){
        return this;
    }

    public void addStolenStones(int stolenStones) {
        this.setNumberOfStones(this.GetNumberOfStones() + stolenStones);
    }

    @Override
    public GameField getOpposite(int countPlace, int wanted) {
        return this.getNextField().getOpposite(1, countPlace);
    }

    @Override
    public void notLastStone(int stones) {
        if( !this.getOwner().getTurn() ){
            this.getNextField().notLastStone(stones);
        }
        else {
            if (stones > 1) {
                this.addStone();
                this.getNextField().notLastStone(stones - 1);
            }
            else this.lastStone();
        }
    }
}
