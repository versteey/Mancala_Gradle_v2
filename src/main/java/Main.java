import java.util.Scanner;

/**
 * Created by eversteeg on 30-10-2014.
 */

public class Main {
    private Scanner scan =  new Scanner(System.in);

    public static void main(String[] args){
        Main main = new Main();
        Player player =  new Player(true);
        Player opponent = new Player(player);
        GameField creator;
        if(args == null) {
            creator = new GameField(4, player);
        }
        else {
            creator = new GameField(Integer.parseInt(args[0]), player);
        }
        creator.initialize();
        Player current = player;
        System.out.println(main.toString(current));
        while( !GameField.isGameOver(current) ){
            main.AskInput(current).makeMove();
            if(!current.getTurn()){
                System.out.println("Your turn is over!");
                current = current.getOpponent();
            }
            System.out.println(main.toString(current));
        }
        System.out.println(main.toString(current));
    }

    private GameField AskInput(Player player) {
        GameField result = player.getLinkToGameField();
        System.out.println("Choose your move!");
        String[] values = player.getLinkToGameField().toPrint();
        try {
            int move = scan.nextInt();
            if(move < 1 || move > 6){
                throw new MoveOutOfBoundException();
            }
            else{
                int stones = Integer.parseInt(values[move - 1].trim());
                if(stones == 0) throw new IllegalMoveException();
                else result = player.getGameField(move - 1);
            }
        } catch(MoveOutOfBoundException moobe) {
            System.err.println(moobe.getLocalizedMessage());
        } catch (IllegalMoveException ime) {
            System.err.println(ime.getLocalizedMessage());
        }
        return result;
    }

    public String toString(Player player){
        String[] a = player.getLinkToGameField().toPrint();
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------------------------------------------------+\n");
        sb.append("|            6      5      4      3      2      1           |\n");
        sb.append("|         +----+ +----+ +----+ +----+ +----+ +----+         |\n");
        sb.append("|         |    | |    | |    | |    | |    | |    |         |\n");
        sb.append("|         | "+a[12]+" | | "+a[11]+" | | "+a[10]+" | | "+a[9]+" | | "+a[8]+" | | "+a[7]+" |         |\n");
        sb.append("| +----+  |    | |    | |    | |    | |    | |    |  +----+ |\n");
        sb.append("| |    |  +----+ +----+ +----+ +----+ +----+ +----+  |    | |\n");
        sb.append("| | "+a[13]+" |                                             | "+a[6]+" | |\n");
        sb.append("| |    |  +----+ +----+ +----+ +----+ +----+ +----+  |    | |\n");
        sb.append("| +----+  |    | |    | |    | |    | |    | |    |  +----+ |\n");
        sb.append("|         | "+a[0]+" | | "+a[1]+" | | "+a[2]+" | | "+a[3]+" | | "+a[4]+" | | "+a[5]+" |         |\n");
        sb.append("|         |    | |    | |    | |    | |    | |    |         |\n");
        sb.append("|         +----+ +----+ +----+ +----+ +----+ +----+         |\n");
        sb.append("|            1      2      3      4      5      6           |\n");
        sb.append("+-----------------------------------------------------------+\n");
        return sb.toString();
    }
}

class MoveOutOfBoundException extends Exception {
    public MoveOutOfBoundException() {
        super("This move is invalid, please choose a number between 1..6.");
    }
    private static final long serialVersionUID = 1L;
}

class IllegalMoveException extends Exception {
    public IllegalMoveException() {
        super("This move is invalid, please choose a field with at least one stone.");
    }
    private static final long serialVersionUID = 1L;
}
