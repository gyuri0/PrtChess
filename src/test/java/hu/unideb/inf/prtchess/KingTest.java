/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author György
 */
public class KingTest {
    @Test
    public void getAvailableFieldTest()
    {
        Table table = new Table();
        table.initialize();
        
        Piece king = table.getPiece(new Field(0, 4));
        
        assertEquals(0, king.getAvailableFields().size());
        
        table.Move(new Move(new Field(1, 4), new Field(3, 4)));
        table.Move(new Move(new Field(6, 4), new Field(4, 4)));
        
        assertEquals(1, king.getAvailableFields().size());
        assertTrue(king.getAvailableFields().contains(new Field(1, 4)));
        
        table.Move(new Move(new Field(0, 6), new Field(2, 5)));
        table.Move(new Move(new Field(6, 5), new Field(5, 5)));
        
        assertEquals(1, king.getAvailableFields().size());
        assertTrue(king.getAvailableFields().contains(new Field(1, 4)));
        
        table.Move(new Move(new Field(0, 5), new Field(2, 3)));
        table.Move(new Move(new Field(5, 5), new Field(4, 5)));
        
        //now can castling
        assertEquals(3, king.getAvailableFields().size());
        assertTrue(king.getAvailableFields().contains(new Field(1, 4)));
        assertTrue(king.getAvailableFields().contains(new Field(0, 5)));
        assertTrue(king.getAvailableFields().contains(new Field(0, 6)));
        
        //this is a castling move
        table.Move(new Move(new Field(0, 4), new Field(0, 6)));
        assertEquals(PieceType.Rook, table.getPiece(new Field(0,5)).pieceType);
        assertEquals(PieceType.King, table.getPiece(new Field(0,6)).pieceType);
        
    }
}
