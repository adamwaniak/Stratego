import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private int size;
    private Board board;
    private PlayerAI redPlayer;
    private PlayerAI bluePlayer;
    private ActivePlayer activePlayer;
    private ComputeScoreAlgorithm computeScoreAlgorithm;

    private enum ActivePlayer {
        RED, BLUE
    }

    public void start() {
        greetings();
        size = getBoardSizeFromUser();
        board = new Board(size);
        redPlayer = new PlayerAI(Player.Color.RED, board);
        bluePlayer = new PlayerAI(Player.Color.BLUE, board);
        computeScoreAlgorithm = new ComputeScoreAlgorithm(board, redPlayer, bluePlayer);
        initPlayerTour();
        printHelp();
        while (!board.isFilled()) {
            Field field;
            printScore();
            printBoard();
            printWhoMovesNow();
            if (activePlayer == ActivePlayer.BLUE) field = getFieldFromAI();
            else field = getFieldFromAI();
            changeFieldStatus(field);
            computeScoreAlgorithm.computeAndAssignScoreIfPossible(field);
            changePlayerTour();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        printBoard();
        printScore();

    }

    private Field getFieldFromAI() {
        if (activePlayer == ActivePlayer.BLUE) {
            return bluePlayer.closeLineOrRandom();
//            return null;
        } else {
            return redPlayer.makeRandomMove();
//            return null;
        }
    }

    private void greetings() {
        System.out.println("Hello in STRATEGO");
    }

    private int getBoardSizeFromUser() {
        System.out.println("Enter board size:");
        return scanner.nextInt();
    }

    private void printBoard() {
        for (int i = 0; i < size; i++) {
            System.out.print("\t " + i);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("\t---");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            for (int j = 0; j < size; j++) {
                System.out.print("\t|" + board.getField(i, j).statusToString() + "|");
            }
            System.out.println();
            for (int k = 0; k < size; k++) {
                System.out.print("\t---");
            }
            System.out.println();

        }
    }

    private void printScore() {
        System.out.println("Red player score: " + redPlayer.getScore() + "\tBlue player score: " + bluePlayer.getScore());
    }

    private void initPlayerTour() {
        if (Math.random() > 0.5) activePlayer = ActivePlayer.RED;
        else activePlayer = ActivePlayer.BLUE;
    }

    private void printWhoMovesNow() {
        if (activePlayer == ActivePlayer.RED) System.out.println("Player red move");
        else System.out.println("Player blue move");

    }

    private void printHelp() {
        System.out.println("To color tile enter 'rownum colnum'");
    }

    private Field getFieldFromUser() {
        Boolean status = false;
        int row = -1;
        int col = -1;
        Field field = null;
        while (!status) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            status = row >= 0 && row <= size - 1 && col >= 0 && col <= size - 1;
            if (status) {
                field = board.getField(row, col);
                if (field.getStatus() != Field.FieldStatus.EMPTY) {
                    System.out.println("Wrong input, field is already taken");
                    status = false;
                } else {
                    status = true;
                }
            } else {
                System.out.println("Wrong input");
            }
        }
        return field;
    }

    private void changeFieldStatus(Field field) {
        if (activePlayer == ActivePlayer.RED) field.setStatus(Field.FieldStatus.RED);
        else field.setStatus(Field.FieldStatus.BLUE);
    }

    private void changePlayerTour() {
        if (activePlayer == ActivePlayer.RED) activePlayer = ActivePlayer.BLUE;
        else activePlayer = ActivePlayer.RED;
    }
}
