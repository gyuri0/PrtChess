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
        Table table = new Table();
        table.initialize();
        
        Piece piece = table.getPiece(new Field(0, 0));
        
        assertEquals(PieceType.Rook, piece.pieceType);
        assertEquals(Color.White, piece.color);
        
        assertNull(table.getPiece(new Field(4, 4)));
    }
    
    @Test
    public void getKingPositionTest(){
        Table table = new Table();
        table.initialize();
        
        assertEquals(new Field(0, 4), table.getKingPosition(Color.White));
        assertEquals(new Field(7, 4), table.getKingPosition(Color.Black));
    }
    
    @Test
    public void getAvailableFieldTest()
    {
        Table table = new Table();
        table.initialize();
        
        assertEquals(20, table.getAvailableFields(Color.White).size());
    }
    
    @Test
    public void MoveTest()
    {
        Table table = new Table();
        table.initialize();
        
        // a pawn can't move 3 field forward, so it's an invalid move
        assertFalse(table.Move(new Move(new Field(1,0), new Field(4,0))));
        
        // but it can move 2 field
        assertTrue(table.Move(new Move(new Field(1,0), new Field(3, 0))));
        
        // The enemy can't move this piece
        assertFalse(table.Move(new Move(new Field(3,0), new Field(4, 0))));
    }
    
    @Test
    public void getPlayerToMoveTest()
    {
        Table table = new Table();
        table.initialize();
        
        //White is the first
        assertEquals(Color.White, table.getPlayerToMove());
        
        //after an invalid move the next player is white
        table.Move(new Move(new Field(1,0), new Field(5,5)));
        assertEquals(Color.White, table.getPlayerToMove());
        
        //but after a valid move the next player is black
        table.Move(new Move(new Field(1,0), new Field(2,0)));
        assertEquals(Color.Black, table.getPlayerToMove());
        
        //after move back the next player is white
        table.MoveBack();
        assertEquals(Color.White, table.getPlayerToMove());
    }
    
    @Test
    public void getResultTest()
    {
        Table table = new Table();
        table.initialize();
        
        assertEquals(GameResult.Inprogress, table.getResult());
        
        //Fool's mate (this is the fastest checkmate)
        table.Move(new Move(new Field(1, 5), new Field(2, 5)));
        assertEquals(GameResult.Inprogress, table.getResult());
        
        table.Move(new Move(new Field(6, 4), new Field(4, 4)));
        assertEquals(GameResult.Inprogress, table.getResult());
        
        table.Move(new Move(new Field(1, 6), new Field(3, 6)));
        assertEquals(GameResult.Inprogress, table.getResult());
        
        table.Move(new Move(new Field(7, 3), new Field(3, 7)));
        assertEquals(GameResult.BlackWin, table.getResult());
    }
}
