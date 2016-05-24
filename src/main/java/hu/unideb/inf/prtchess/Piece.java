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
    protected Table table;
    
    public Piece(Field position, Color color, PieceType pieceType, Table table)
    {
        this.position = position;
        this.color = color;
        this.pieceType = pieceType;
        this.moveCount = 0;
        this.table = table;
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
    
    protected abstract List<Field> getControlledFields();
    
    public List<Field> getAvailableFields()
    {
        Field currentField = this.position;
        List<Field> availableFields = new ArrayList<>();
        List<Field> fields = this.getControlledFields();
        for(Field field : fields)
        {
            //Move
            MoveWithDetails moveResult = this.Move(field);

            //Check our king is safe
            if(!this.table.isKingUnderAttack(this.color))
            {
                availableFields.add(field);
            }

            //Move back
            this.ReverseMove(moveResult);
        }

        this.position = currentField;
        return availableFields;
    }
    
    public MoveWithDetails Move(Field endField)
    {
        MoveWithDetails move = new MoveWithDetails(this.getPosition(), endField);
        Piece endPiece = this.table.getPiece(endField);
        
        if(endField != null)
        {
            move.setCapturedPiece(endPiece);
            this.table.getPieces().remove(endPiece);
        }
        
        this.setPosition(endField);
        this.moveCount++;
        
        return move;
    }
    
    public void ReverseMove(MoveWithDetails move)
    {
        this.setPosition(move.getStart());
        
        Piece capturedPiece = move.getCapturedPiece();
        
        if(capturedPiece != null)
        {
            this.table.getPieces().add(capturedPiece);
        }
        
        if(move.getPromotion() != null)
        {
            this.table.getPieces().remove(this);
            this.table.getPieces().add(new Pawn(this.position, this.color, this.table));
        }
        
        this.moveCount--;
    }
}
