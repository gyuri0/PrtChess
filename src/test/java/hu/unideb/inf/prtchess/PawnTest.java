/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author György
 */
public class PawnTest {
    
    @Test
    public void testAvailableFields()
    {
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(1, 2));
        //this piece can move 1 or 2 forward
        
        List<Field> availableFields = piece.getAvailableFields();
        assertEquals(2, availableFields.size());
        assertTrue(availableFields.contains(new Field(2, 2)));
        assertTrue(availableFields.contains(new Field(3, 2)));
        
        table.Move(new Move(new Field(1, 2), new Field(3, 2)));
        table.Move(new Move(new Field(6, 1), new Field(4, 1)));
        
        availableFields = table.getPiece(new Field(3, 2)).getAvailableFields();
        
        //can't move 2 forward but can capture
        assertEquals(2, availableFields.size());
        assertTrue(availableFields.contains(new Field(4, 1)));
        assertTrue(availableFields.contains(new Field(4, 2)));
    }
}
