package com.example.javachessproject;

import java.util.List;

public class Empty extends Unit {
    public Empty(){
        super("empty",false,false,7);
    }
    @Override
    public boolean move(List<Integer> moveFrom, List<Integer> moveTo, Board board) {
        System.out.println("Empty");
        return false;
    }
}
