package com.example.javachessproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Unit{
    public King(boolean color){
        super("king",true,color,1);
    }
    @Override
    public boolean move(List<Integer> moveFrom, List<Integer> moveTo,Board board) {
        List<List<Integer>> validMoves = new ArrayList<>();
        Boolean checkColor = board.getUnit(moveFrom).getColor();

        //check all
        List <Integer> moveToCheck;
        for (int i=-1 ; i<2;i++){
            for (int j=-1 ; j<2;j++){
                try {
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0) + i), moveFrom.get(1) + j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                    }
                }catch (Exception e){
                    continue;
                }
            }
        }

        //castle O-0

        moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) + 2));
        List <Integer> initialRookPositionR = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) + 3));
        List <Integer> newRookPositionR = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) + 1));
        //check if fields between king and rook are empty
        if(moveToCheck.equals(moveTo)) {
            if (!(board.getUnit(new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) + 1))).isAlive())) {
                if (!(board.getUnit(moveToCheck).isAlive())) {
                    if (!this.getMoved() && !board.getUnit(initialRookPositionR).getMoved()) {
                        validMoves.add(moveToCheck);
                        board.setUnit(newRookPositionR, new Rook(checkColor));
                        board.setUnit(initialRookPositionR, new Empty());
                    }
                }
            }
        }

        //castle O-0-0
        moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) - 2));
        List <Integer> initialRookPositionL = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) - 4));
        List <Integer> newRookPositionL = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) - 1));
        //check if fields between king and rook are empty
        if(moveToCheck.equals(moveTo)) {
            if (!(board.getUnit(new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) - 1))).isAlive())) {
                if (!(board.getUnit(new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) - 2))).isAlive())) {
                    if (!(board.getUnit(moveToCheck).isAlive())) {
                        if (!this.getMoved() && !board.getUnit(initialRookPositionL).getMoved()) {
                            validMoves.add(moveToCheck);
                            board.setUnit(newRookPositionL, new Rook(checkColor));
                            board.setUnit(initialRookPositionL, new Empty());
                        }
                    }
                }
            }
        }


        if(validMoves.contains(moveTo)) {
            System.out.println("valid move");
            this.setMoved(true);
            return true;
        }
        else{
            System.out.println("ivvalid move");
            return false;
        }
    }
}
