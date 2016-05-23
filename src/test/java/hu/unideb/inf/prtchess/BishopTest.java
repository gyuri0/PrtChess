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
public class BishopTest {
    @Test
    public void getAvailableFieldTest()
    {
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(0, 2));
        List<Field> availableFields = piece.getAvailableFields();
        assertEquals(0, availableFields.size());
        
        table.Move(new Move(new Field(1,1), new Field(2, 1)));
        table.Move(new Move(new Field(6,1), new Field(5, 1)));
        assertEquals(2, piece.getAvailableFields().size());
        assertTrue(piece.getAvailableFields().contains(new Field(1,1)));
        assertTrue(piece.getAvailableFields().contains(new Field(2,0)));
    }
}
