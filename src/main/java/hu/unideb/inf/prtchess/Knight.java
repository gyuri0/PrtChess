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
public class Knight extends StandardMovePiece {

    public Knight(Field position, Color color)
    {
        super(position, color, PieceType.Knight,
                new int[]{ -2, -2, -1, -1, 1, 1, 2, 2},
                new int[]{ -1, 1, -2, 2, -2, 2, -1, 1},
                false
                );
    }
    
    @Override
    public String toString() {
        return "N";
    }
    
    
}
