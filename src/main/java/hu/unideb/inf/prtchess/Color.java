/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

/**
 *
 * @author György
 */
public enum Color {
    White, Black;
    
    public Color getOpponent()
    {
        if(this == Color.White)
        {
            return Color.Black;
        }
        else
        {
            return Color.White;
        }
    }
}
