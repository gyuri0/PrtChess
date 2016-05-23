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
public class Rook extends StandardMovePiece {

    public Rook(Field position, Color color, Table table)
    {
        super(position, color, PieceType.Rook,
                new int[]{ -1, 0, 0, 1},
                new int[]{ 0, -1, 1, 0},
                true,
                table
                );
    }

    @Override
    public String toString() {
        return "R";
    }
    
    
}
