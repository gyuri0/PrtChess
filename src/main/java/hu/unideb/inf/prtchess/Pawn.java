/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author György
 */
public class Pawn extends Piece{

    public Pawn(Field position, Color color, Table table)
    {
        super(position, color, PieceType.Pawn, table);
    }
    
    private boolean isOnBaseLine()
    {
        int row = this.getPosition().getRow();
        return (this.color == Color.White && row == 1) 
                ||  (this.color == Color.Black && row == 6);
    }
    
    private boolean isOnEnPassantLine()
    {
        int row = this.getPosition().getRow();
        return (this.color == Color.White && row == 4)
                || (this.color == Color.Black && row == 3);
    }
    
    private int moveDirection()
    {
        if(this.color == Color.White)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
    @Override
    public List<Field> getControlledFields() {
        List<Field> returnList = new ArrayList<>();
        
        Field oneBefore = this.position.getAdjancent(this.moveDirection(), 0);
        Field twoBefore = this.position.getAdjancent(this.moveDirection()*2, 0);
        Field leftBefore = this.position.getAdjancent(this.moveDirection(), -1);
        Field rightBefore = this.position.getAdjancent(this.moveDirection(), 1);
        
        if(this.table.getPiece(oneBefore) == null)
        {
            returnList.add(oneBefore);
            if(this.isOnBaseLine() && this.table.getPiece(twoBefore) == null)
            {
                returnList.add(twoBefore);
            }
        }
        
        if(leftBefore != null && this.table.getPiece(leftBefore) != null && 
                this.table.getPiece(leftBefore).getColor() == this.color.getOpponent())
        {
            returnList.add(leftBefore);
        }
        
        if(rightBefore != null && this.table.getPiece(rightBefore) != null && 
                this.table.getPiece(rightBefore).getColor() == this.color.getOpponent())
        {
            returnList.add(rightBefore);
        }
        
        //special move: en passant
        if(this.isOnEnPassantLine())
        {
            MoveWithDetails lastMove = this.table.getMoves().get(this.table.getMoves().size() - 1);
            Piece piece = this.table.getPiece(lastMove.getEnd());
            if(piece.pieceType == PieceType.Pawn &&
                Math.abs(lastMove.getEnd().getRow() - lastMove.getStart().getRow()) == 2)
            {
                if(piece.position.getColumn() == this.position.getColumn() - 1)
                {
                    returnList.add(leftBefore);
                }
                
                if(piece.position.getColumn() == this.position.getColumn() + 1)
                {
                    returnList.add(rightBefore);
                }
            }
        }
        
        return returnList;
    }

    @Override
    public MoveWithDetails Move(Field endField)
    {       
        Piece piece = this.table.getPiece(endField);
        if(piece == null && endField.getColumn() != this.position.getColumn())
        {
            //this is an en passant move
            Piece capturedPiece = this.table.getPiece(endField.getAdjancent(-this.moveDirection(), 0));
            capturedPiece.position = endField;
            
            MoveWithDetails move = super.Move(endField);
            move.setEnPassant(true);
            
            return move;
        }
        
        return super.Move(endField);
    }
    
    @Override 
    public void ReverseMove(MoveWithDetails move)
    {        
        if(move.isEnPassant())
        {
            super.ReverseMove(move);
            
            Piece piece = move.getCapturedPiece();          
            piece.position = piece.position.getAdjancent(-this.moveDirection(), 0);
        }
        else
        {        
            super.ReverseMove(move);
        }
    }
    
    @Override
    public String toString() {
        return "P";
    }  
}
