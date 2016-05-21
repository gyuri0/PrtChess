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
public abstract class Piece {
    protected Field position;
    protected Color color;
    protected PieceType pieceType;
    protected int moveCount;
    
    
    public Piece(Field position, Color color, PieceType pieceType)
    {
        this.position = position;
        this.color = color;
        this.pieceType = pieceType;
        this.moveCount = 0;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    public PieceType getPieceType()
    {
        return this.pieceType;
    }
    
    public Field getPosition()
    {
        return this.position;
    }
    
    public void setPosition(Field newPosition)
    {
        this.position = newPosition;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Piece))
        {
            return false;
        }
        
        Piece piece = (Piece)obj;
        
        return this.position.equals(piece.position);
    }
    
    protected abstract List<Field> getControlledFields(TableState tableState);
    
    public List<Field> getAvailableFields(TableState tableState)
    {
        Field currentField = this.position;
        List<Field> availableFields = new ArrayList<Field>();
        List<Field> fields = this.getControlledFields(tableState);
        for(Field field : fields)
        {
            //Move
            MoveWithDetails moveResult = this.Move(tableState, field);

            //Check our king is safe
            if(!tableState.isKingUnderAttack(this.color))
            {
                availableFields.add(field);
            }

            //Move back
            this.ReverseMove(tableState, moveResult);
        }

        this.position = currentField;
        return availableFields;
    }
    
    public MoveWithDetails Move(TableState tableState, Field endField)
    {
        MoveWithDetails move = new MoveWithDetails(this.getPosition(), endField);
        Piece endPiece = tableState.getPiece(endField);
        
        if(endField != null)
        {
            move.setCapturedPiece(endPiece);
            tableState.getPieces().remove(endPiece);
        }
        
        this.setPosition(endField);
        this.moveCount++;
        
        return move;
    }
    
    public void ReverseMove(TableState tableState, MoveWithDetails move)
    {
        this.setPosition(move.getStart());
        
        Piece capturedPiece = move.getCapturedPiece();
        
        if(capturedPiece != null)
        {
            tableState.getPieces().add(capturedPiece);
        }
        
        if(move.getPromotion() != null)
        {
            tableState.getPieces().remove(this);
            tableState.getPieces().add(new Pawn(this.position, this.color));
        }
        
        this.moveCount--;
    }
}
