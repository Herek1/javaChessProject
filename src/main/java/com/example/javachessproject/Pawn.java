package com.example.javachessproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Unit{
    public Pawn(boolean color){
        super("pawn",true,color,0);
    }

    @Override
    public boolean move(List<Integer> moveFrom, List<Integer> moveTo, Board board) {
        List<List<Integer>> validMoves = new ArrayList<>();
        Boolean checkColor = board.getUnit(moveFrom).getColor();
        List <Integer> moveToCheck = new ArrayList<>();
        //check move forward for White/black pawn
        try {
            moveToCheck = checkColor ? new ArrayList<>(Arrays.asList((moveFrom.get(0) - 1), moveFrom.get(1))) : new ArrayList<>(Arrays.asList((moveFrom.get(0) + 1), moveFrom.get(1)));
            if (board.getUnit(moveToCheck).getType().equals("empty")) {
                validMoves.add(moveToCheck);
            }
        }catch (Exception e){}
        //check move forward 2 spaces
        try {
            moveToCheck = checkColor ? new ArrayList<>(Arrays.asList((moveFrom.get(0) - 2), moveFrom.get(1))) : new ArrayList<>(Arrays.asList((moveFrom.get(0) + 2), moveFrom.get(1)));
            List<Integer> moveToCheck2 = checkColor ? new ArrayList<>(Arrays.asList((moveFrom.get(0) - 1), moveFrom.get(1))) : new ArrayList<>(Arrays.asList((moveFrom.get(0) + 1), moveFrom.get(1)));
            if (board.getUnit(moveToCheck).getType().equals("empty") && board.getUnit(moveToCheck2).getType().equals("empty") && moveFrom.get(0).equals(board.getUnit(moveFrom).getColor() ? 6 : 1)) {
                validMoves.add(moveToCheck);
            }
        }catch (Exception e){}
        for(int i=-1; i<=1; i+=2) {
            try {
                //diagonal right
                moveToCheck = checkColor ? new ArrayList<>(Arrays.asList((moveFrom.get(0) - 1), moveFrom.get(1) + i)) : new ArrayList<>(Arrays.asList((moveFrom.get(0) + 1), moveFrom.get(1) + i*-1));
                if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() && board.getUnit(moveToCheck).isAlive()) {
                    validMoves.add(moveToCheck);
                }
            }catch (Exception e){}
        }
        //Check if user move is valid
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
