package com.example.javachessproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Unit {
    public Knight(boolean color){
        super("knight",true,color,6);
    }
    @Override
    public boolean move(List<Integer> moveFrom, List<Integer> moveTo, Board board) {
        List<List<Integer>> validMoves = new ArrayList<>();
        Boolean checkColor = board.getUnit(moveFrom).getColor();
        List <Integer> moveToCheck;
        for (int i = -1; i <= 1; i+=2){
            for (int j = -1; j <= 1; j+=2) {
                try {
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0) + 2 * i), moveFrom.get(1) + j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                    }
                } catch (Exception e) {
                }
            }
        }
        for (int i = -1; i <= 1; i+=2){
            for (int j = -1; j <= 1; j+=2) {
                try {
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0) + i), moveFrom.get(1) + 2 * j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                    }
                } catch (Exception e) {
                }
            }
        }
        if(validMoves.contains(moveTo)) {
            System.out.println("valid move");
            return true;
        }
        else{
            System.out.println("ivvalid move");
            return false;
        }
    }
}
