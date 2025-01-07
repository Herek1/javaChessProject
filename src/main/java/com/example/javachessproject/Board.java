package com.example.javachessproject;

import java.util.List;
public class Board {
    private Unit[][] unit;

    public Board (){
        unit = new Unit[8][8];
        //create 8x8 board filled with empty units
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                unit[i][j] = new Empty();
            }
        }
        for (int i = 0; i<8; i++) {
            unit[6][i] = new Pawn(true);
        }
        for (int i = 0; i<8; i++) {
            unit[1][i] = new Pawn(false);
        }
        unit [0][0] = new Rook (false);
        unit [0][7] = new Rook (false);
        unit [0][1] = new Knight(false);
        unit [0][6] = new Knight(false);
        unit [0][2] = new Bishop(false);
        unit [0][5] = new Bishop(false);
        unit [0][3] = new Queen(false);
        unit [0][4] = new King(false);
        unit [7][0] = new Rook (true);
        unit [7][7] = new Rook (true);
        unit [7][1] = new Knight(true);
        unit [7][6] = new Knight(true);
        unit [7][2] = new Bishop(true);
        unit [7][5] = new Bishop(true);
        unit [7][3] = new Queen(true);
        unit [7][4] = new King(true);
    }
    public Unit getUnit(List<Integer> positionInt) {
        return unit[positionInt.get(0)][positionInt.get(1)];
    }
    public void setUnit(List<Integer> positionInt, Unit unit){
        this.unit[positionInt.get(0)][positionInt.get(1)] = unit;
    }
    public void promotePawn (List<Integer> pawPosition, String unitType){
        Unit unit = getUnit(pawPosition);
        Unit chosenUnit = switch (unitType){
            case "Queen" -> new Queen(unit.getColor());
            case "Bishop" -> new Bishop(unit.getColor());
            case "Knight" -> new Knight(unit.getColor());
            case "Rook" -> new Rook(unit.getColor());
            default -> new Pawn(unit.getColor());
        };
        setUnit(pawPosition, chosenUnit);
    }
    public void showBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" | "+unit[i][j]);
                if (j==7){
                    System.out.print(" | ");
                    System.out.print(8-i);
                }
            }
            System.out.println();
            System.out.print("-".repeat(42));
            System.out.println();

        }
        System.out.println("   a    b    c   d    e   f    g    h");
    }

}
