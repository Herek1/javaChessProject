package com.example.javachessproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Miscellaneous {


    public static String getInput (String display){
        Scanner scanner = new Scanner(System.in);
        System.out.println(display);
        return scanner.nextLine();
    }

    public static List<Integer> convertPosition (String position){
        if (position.length() > 2){
            System.out.println("Invalid");
            return null;
        }

        List<Integer> list = new ArrayList<>();
        char part1 = position.toLowerCase().charAt(0);
        int part2 = Integer.parseInt(String.valueOf(position.charAt(1)));

        int part1int = switch (part1){
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> 99;
        };
        int part2Fix = switch (part2){
            case 1 -> 7;
            case 2 -> 6;
            case 3 -> 5;
            case 4 -> 4;
            case 5 -> 3;
            case 6 -> 2;
            case 7 -> 1;
            case 8 -> 0;
            default -> 99;
        };
        if(part1int == 99 || part2Fix == 99){
            System.out.println("Invalid2");
            return null;
        }
        list.add(part2Fix);
        list.add(part1int);
        return list;

    }
}
