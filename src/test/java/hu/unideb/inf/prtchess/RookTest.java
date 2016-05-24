/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author György
 */
public class RookTest {
    
    @Test
    public void getAvailableFieldsTest()
    {
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(0, 0));
        assertEquals(0, piece.getAvailableFields().size());
        
        table.Move(new Move(new Field(0,1), new Field(2,0)));
        table.Move(new Move(new Field(6,0), new Field(4,0)));
        
        assertEquals(1, piece.getAvailableFields().size());
        assertTrue(piece.getAvailableFields().contains(new Field(0, 1)));
        
        table.MoveBack();
        table.MoveBack();
        
        table.Move(new Move(new Field(1,0), new Field(3, 0)));
        table.Move(new Move(new Field(6,0), new Field(4,0)));
        
        assertEquals(2, piece.getAvailableFields().size());
        assertTrue(piece.getAvailableFields().contains(new Field(1, 0)));
        assertTrue(piece.getAvailableFields().contains(new Field(2, 0)));
    }
}
