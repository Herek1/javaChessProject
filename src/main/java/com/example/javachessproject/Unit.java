package com.example.javachessproject;

import java.util.List;
public abstract class Unit {
    private String type;
    private boolean isAlive;
    private boolean color; // True - White
    private String look;
    private boolean moved;

    public Unit(String type, boolean isAlive, boolean color, int look){
        this.type = type;
        this.isAlive = isAlive;
        this.color = color;
        this.look = switch (look) {
            case 0 -> color ? "♙" : "♟";
            case 1 -> color ? "♔" : "♚";
            case 2 -> color ? "♕" : "♛";
            case 4 -> color ? "♗" : "♝";
            case 5 -> color ? "♖" : "♜";
            case 6 -> color ? "♘" : "♞";
            default ->" ";
        };
        this.moved = false;
    }

    public String getType() {
        return type;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean getColor() {
        return color;
    }

    public String getLook() {
        return look;
    }

    public boolean getMoved(){return moved;}
    public void setMoved(boolean moved){this.moved = moved;}

    public abstract boolean move(List<Integer> moveFrom, List<Integer> moveTo, Board board);

    @Override
    public String toString() {
        return "" + look + "";
    }
}
