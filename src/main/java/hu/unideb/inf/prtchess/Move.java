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
public class Move {
    private Field start;
    private Field end;
    
    public Move(Field start, Field end)
    {
        this.start = start;
        this.end = end;
    }
    
    public Field getStart()
    {
        return this.start;
    }
    
    public Field getEnd()
    {
        return this.end;
    }
}
