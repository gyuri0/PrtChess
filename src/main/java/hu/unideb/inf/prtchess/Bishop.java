/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author György
 */
public class Bishop extends StandardMovePiece {
    public Bishop(Field position, Color color, Table table)
    {
        super(position, color, PieceType.Bishop,
                new int[]{ -1, -1, 1, 1},
                new int[]{ -1, 1, -1, 1},
                true,
                table
                );
    }  

    @Override
    public String toString() {
        return "B";
    }
}
