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
public class KnightTest {
    
    @Test
    public void getAvailableFieldTest()
    {
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(0, 1));
        List<Field> availableFields = piece.getAvailableFields();
        assertEquals(2, availableFields.size());
        assertTrue(availableFields.contains(new Field(2, 0)));
        assertTrue(availableFields.contains(new Field(2, 2)));
        
        table.Move(new Move(new Field(0,1), new Field(2,0)));
        table.Move(new Move(new Field(6,1), new Field(4,1)));
        // can capture (4, 1) pawn
        
        assertTrue(piece.getAvailableFields().contains(new Field(4, 1)));
    }
}
