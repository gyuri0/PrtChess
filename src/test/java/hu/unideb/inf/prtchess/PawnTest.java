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
    
    @Test
    public void enPassantTest()
    {
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(1, 2));
        table.Move(new Move(new Field(1, 2), new Field(3, 2)));
        table.Move(new Move(new Field(6, 7), new Field(5, 7)));
        table.Move(new Move(new Field(3, 2), new Field(4, 2)));
        table.Move(new Move(new Field(6, 3), new Field(4, 3)));
        
        //Now en passant is available
        assertTrue(piece.getAvailableFields().contains(new Field(5, 3)));
        
        // and if take this move we remove the enemy piece from (4, 3);
        assertNotNull(table.getPiece(new Field(4, 3)));
        table.Move(new Move(new Field(4, 2), new Field(5, 3)));
        assertNull(table.getPiece(new Field(4, 3)));
        
    }
}
