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
public class TableTest {
    
    @Test
    public void initializeTest() {
        TableState table = new TableState();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(0, 0));
        
        assertEquals(PieceType.Rook, piece.pieceType);
        assertEquals(Color.White, piece.color);
        
        assertNull(table.getPiece(new Field(4, 4)));
    }
    
    @Test
    public void getKingPositionTest(){
        TableState table = new TableState();
        table.initialize();
        
        assertEquals(new Field(0, 4), table.getKingPosition(Color.White));
        assertEquals(new Field(7, 4), table.getKingPosition(Color.Black));
    }
    
    @Test
    public void getAvailableFieldTest()
    {
        TableState table = new TableState();
        table.initialize();
        
        assertEquals(20, table.getAvailableFields(Color.White).size());
    }
    
   
}
