//package com.example.szachyui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Game {
//    private static boolean turn;
//    private static String move;
//    private static Board board;
//    public Game (){
//        board = new Board();
//        turn = true;
//        move = "";
//    }
//
//    public static void startGame(){
//        board.showBoard();
//        while (!(move.toLowerCase().equals("end"))){
//            String move = Miscellaneous.getInput(turn ? "White moves" : "Black moves");
//            if(move.toLowerCase().equals("end")){
//                System.exit(0);
//            }
//                makeMove(move);
//        }
//    }
//
//    public static void makeMove(String move){
//        String moveFromTemp = move.substring(0,2);
//        String moveToTemp = move.substring(2,4);
//        List<Integer> moveFrom = new ArrayList<>();
//        List<Integer> moveTo = new ArrayList<>();
//        moveFrom = Miscellaneous.convertPosition(moveFromTemp);
//        moveTo = Miscellaneous.convertPosition(moveToTemp);
//        Unit CurrentMove = board.getUnit(moveFrom);
//        if(CurrentMove.getColor() == turn) {
//            if (CurrentMove.move(moveFrom, moveTo, board)) {
//                Unit temp = board.getUnit(moveFrom);
//                board.setUnit(moveTo, temp);
//                board.setUnit(moveFrom, new Empty());
//                turn = !turn;
//            }
//        }else{
//            System.out.println("Wrong color");
//        }
//        board.showBoard();
//    }
//}
