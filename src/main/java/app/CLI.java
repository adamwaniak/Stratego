package app;

import app.AIAlgorithms.AlgorithmsFacade;

import java.util.Scanner;

class CLI {

    private Scanner scanner = new Scanner(System.in);
    private int size;
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;
    private ActivePlayer activePlayer;
    private ComputeScoreAlgorithm computeScoreAlgorithm;
    private GameMode gameMode;
    private AlgorithmsFacade algorithms;

    private enum ActivePlayer {
        RED, BLUE
    }

    private enum GameMode {
        AI_VS_AI, USER_VS_AI, USER_VS_USER
    }

    void start() {
        greetings();
        size = getBoardSizeFromUser();
        gameMode = getGameModeFromUser();
        board = new Board(size);
        initProperties(board);
        initPlayerTour();
        printHelp();
        while (!board.isFilled()) {
            Field field;
            printScore();
            printBoard();
            printWhoMovesNow();
            field = getFieldFromPlayer();
            changeFieldStatus(field);
            computeScoreAlgorithm.computeAndAssignScoreIfPossible(field);
            changePlayerTour();
        }

        printBoard();
        printScore();

    }

    private void initProperties(Board board) {
        redPlayer = new Player(Player.Color.RED);
        bluePlayer = new Player(Player.Color.BLUE);
        computeScoreAlgorithm = new ComputeScoreAlgorithm(board, redPlayer, bluePlayer);
        algorithms = new AlgorithmsFacade(board);
    }

    private Field getFieldFromPlayer() {
        Field field = null;
        switch (gameMode) {
            case AI_VS_AI:
                field = getFieldFromAI();
                break;
            case USER_VS_AI:
                if (activePlayer == ActivePlayer.BLUE) {
                    field = getFieldFromAI();
                } else {
                    field = getFieldFromUser();
                }
                break;
            case USER_VS_USER:
                field = getFieldFromUser();
                break;
        }
        return field;
    }

    private Field getFieldFromAI() {
        if (activePlayer == ActivePlayer.BLUE) {
            return algorithms.alphaBetaAlgorithm(3);
        } else {
            return algorithms.alphaBetaAlgorithm();
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
        System.out.println("Choose first player:\n1 - Red\n2 - Blue");
        String input = scanner.next();
        switch (input) {
            case "1":
                activePlayer = ActivePlayer.RED;
                break;
            case "2":
                activePlayer = ActivePlayer.BLUE;
                break;
            default:
                System.out.println("Wrong input, try again");
                initPlayerTour();
        }
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

    private GameMode getGameModeFromUser() {
        System.out.println("Chose game mode: \n1 - User vs User\n2 - User vs AI\n3 - AI vs AI");
        String userInput = scanner.next();
        switch (userInput) {
            case "1":
                return GameMode.USER_VS_USER;
            case "2":
                return GameMode.USER_VS_AI;
            case "3":
                return GameMode.AI_VS_AI;
            default:
                System.out.println("Wrong input, try again.");
                getGameModeFromUser();
        }
        return null;
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
