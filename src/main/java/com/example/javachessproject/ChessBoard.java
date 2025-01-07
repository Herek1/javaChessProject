package com.example.javachessproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ChessBoard extends Application {

    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8;
    private Board board;
    private boolean turn;
    private List<Integer> moveFrom;
    private List<Integer> moveTo;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the board
        board = new Board();
        turn = true;
        moveFrom = new ArrayList<>();
        moveTo = new ArrayList<>();

        gridPane = createGrid();  // Initialize gridPane

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("KT szachy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() {
        GridPane gridPane = new GridPane();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);

                Unit unit = board.getUnit(List.of(row, col));
                Text pieceText = new Text(unit.getLook());
                pieceText.setFont(Font.font(24));

                StackPane stack = new StackPane();
                stack.getChildren().addAll(tile, pieceText);

                final int currentRow = row;
                final int currentCol = col;

                stack.setOnMouseClicked(event -> handleTileClick(currentRow, currentCol));

                gridPane.add(stack, col, row);
            }
        }

        return gridPane;
    }
    private void updateGrid() {
        gridPane.getChildren().clear();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);

                Unit unit = board.getUnit(List.of(row, col));
                Text pieceText = new Text(unit.getLook());
                pieceText.setFont(Font.font(24));

                StackPane stack = new StackPane();
                stack.getChildren().addAll(tile, pieceText);

                final int currentRow = row;
                final int currentCol = col;

                stack.setOnMouseClicked(event -> handleTileClick(currentRow, currentCol));

                gridPane.add(stack, col, row);
            }
        }
    }

    private void handleTileClick(int row, int col) {
        StackPane clickedTile = (StackPane) gridPane.getChildren().get(row * BOARD_SIZE + col);
        if (moveFrom.isEmpty()) {
            clickedTile.getChildren().get(0).setStyle("-fx-fill: yellow");
            moveFrom.add(row);
            moveFrom.add(col);
            System.out.println("Selected piece at: (" + row + ", " + col + ")");
        } else {
            moveTo.add(row);
            moveTo.add(col);
            System.out.println("Move piece to: (" + row + ", " + col + ")");

            Unit currentMove = board.getUnit(moveFrom);
            if (currentMove.getColor() == turn) {
                if (currentMove.move(moveFrom, moveTo, board)) {
                    Unit temp = board.getUnit(moveFrom);
                    board.setUnit(moveTo, temp);
                    board.setUnit(moveFrom, new Empty());
                    System.out.println("test? " + temp.getType());
                    if(temp.getType().equals("pawn") && moveTo.get(0).equals(board.getUnit(moveTo).getColor() ? 0 : 7)){
                        String type = showPromotionDialog();
                        board.promotePawn(moveTo,type);
                    }
                    //turn = !turn;
                }
            } else {
                System.out.println("Wrong color");
            }
            moveFrom.clear();
            moveTo.clear();
            updateGrid();
        }
    }

    private String showPromotionDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Pawn Promotion");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Button queenButton = new Button("♕");
        Button rookButton = new Button("♖");
        Button bishopButton = new Button("♗");
        Button knightButton = new Button("♘");
        Button pawnButton = new Button("♙");

        int buttonSize = 80;
        int fontSize = 20;
        queenButton.setPrefSize(buttonSize, buttonSize);
        rookButton.setPrefSize(buttonSize, buttonSize);
        bishopButton.setPrefSize(buttonSize, buttonSize);
        knightButton.setPrefSize(buttonSize, buttonSize);
        pawnButton.setPrefSize(buttonSize, buttonSize);
        queenButton.setStyle("-fx-font-size: " + fontSize + "px;");
        rookButton.setStyle("-fx-font-size: " + fontSize + "px;");
        bishopButton.setStyle("-fx-font-size: " + fontSize + "px;");
        knightButton.setStyle("-fx-font-size: " + fontSize + "px;");
        pawnButton.setStyle("-fx-font-size: " + fontSize + "px;");

        grid.add(queenButton, 0, 0);
        grid.add(rookButton, 1, 0);
        grid.add(bishopButton, 2, 0);
        grid.add(knightButton, 3, 0);
        grid.add(pawnButton, 4, 0);

        Scene dialogScene = new Scene(grid, 400, 100);
        dialog.setScene(dialogScene);

        final String[] chosenPiece = {null};

        queenButton.setOnAction(e -> {
            chosenPiece[0] = "Queen";
            dialog.close();
        });

        rookButton.setOnAction(e -> {
            chosenPiece[0] = "Rook";
            dialog.close();
        });

        bishopButton.setOnAction(e -> {
            chosenPiece[0] = "Bishop";
            dialog.close();
        });

        knightButton.setOnAction(e -> {
            chosenPiece[0] = "Knight";
            dialog.close();
        });

        pawnButton.setOnAction(e -> {
            chosenPiece[0] = "Pawn";
            dialog.close();
        });

        dialog.showAndWait();
        return chosenPiece[0] == null ? "Pawn" : chosenPiece[0];
    }
    public static void main(String[] args) {
        launch(args);
    }
}
