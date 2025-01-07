package com.example.javachessproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Unit{
    public Queen(boolean color){
        super("queen",true,color,2);
    }
    @Override
    public boolean move(List<Integer> moveFrom, List<Integer> moveTo,Board board) {
        List<List<Integer>> validMoves = new ArrayList<>();
        Boolean checkColor = board.getUnit(moveFrom).getColor();

        //rook moves
        List <Integer> moveToCheck;
        for (int i = -1; i <= 1; i+=2){
            int j=1;
            //up n down
            while(true){
                try{
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0) + i*j), moveFrom.get(1)));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                        j+=1;
                        if(board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() && board.getUnit(moveToCheck).isAlive()){
                            break;
                        }
                    }else {
                        break;
                    }
                }catch(Exception e){
                    break;
                }
            }
            j=1;
            while(true){
                //left right
                try{
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0)), moveFrom.get(1) + i*j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                        j+=1;
                        if(board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() && board.getUnit(moveToCheck).isAlive()){
                            break;
                        }
                    }else {
                        break;
                    }
                }catch(Exception e){
                    break;
                }
            }
        }
        for (int i = -1; i <= 1; i+=2){
            int j=1;
            // \ - this one
            while(true){
                try{
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0) + i*j), moveFrom.get(1)+i*j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                        j+=1;
                        if(board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() && board.getUnit(moveToCheck).isAlive()){
                            break;
                        }
                    }else {
                        break;
                    }
                }catch(Exception e){
                    break;
                }
            }
            // / - this one
            j=1;
            while(true){
                try{
                    moveToCheck = new ArrayList<>(Arrays.asList((moveFrom.get(0))+i*j*-1, moveFrom.get(1) + i*j));
                    if (board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() || !board.getUnit(moveToCheck).isAlive()) {
                        validMoves.add(moveToCheck);
                        j+=1;
                        if(board.getUnit(moveToCheck).getColor() != board.getUnit(moveFrom).getColor() && board.getUnit(moveToCheck).isAlive()){
                            break;
                        }
                    }else {
                        break;
                    }
                }catch(Exception e){
                    break;
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