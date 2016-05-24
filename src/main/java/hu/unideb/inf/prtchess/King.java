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
public class King extends StandardMovePiece {
    
    public King(Field position, Color color, Table table)
    {
        super(position, color, PieceType.King,
                new int[]{ -1, -1, -1, 0, 0, 1, 1, 1},
                new int[]{ -1, 0, 1, -1, 1, -1, 0, 1},
                false,
                table
                );
    }
    
    
    @Override
    public List<Field> getAvailableFields() {
        List<Field> returnList = new ArrayList<>();
        
        //Special move : castling
        if(this.moveCount == 0)
        {  
            //castling left
            Piece leftRook = this.table.getPiece(this.position.getAdjancent(0, -4));
            if(leftRook != null && leftRook.moveCount == 0)
            {
                boolean middleFieldsAreEmpty = true;
                boolean fieldsAreUnderAttack = false;
                
                for(int i = 1; i <= 3; i++)
                {
                    if(this.table.getPiece(this.position.getAdjancent(0, -i)) != null)
                    {
                        middleFieldsAreEmpty = false;
                    }
                }
                
                for(int i = 0; i<=2;i++)
                {
                    if(this.table.isFieldControlledByEnemy(this.color, this.position.getAdjancent(0, -i)))
                    {
                        fieldsAreUnderAttack = true;
                    }
                }
                
                
                if(middleFieldsAreEmpty && !fieldsAreUnderAttack)
                {
                    returnList.add(this.position.getAdjancent(0, -2));
                }
            }
            
            //castling right
            Piece rightRook = this.table.getPiece(this.position.getAdjancent(0, 3));
            if(rightRook != null && rightRook.moveCount == 0)
            {
                boolean middleFieldsAreEmpty = true;
                boolean fieldsAreUnderAttack = false;
                
                for(int i = 1; i <= 2; i++)
                {
                    if(this.table.getPiece(this.position.getAdjancent(0, i)) != null)
                    {
                        middleFieldsAreEmpty = false;
                    }
                }
                
                for(int i = 0; i<=2;i++)
                {
                    if(this.table.isFieldControlledByEnemy(this.color, this.position.getAdjancent(0, i)))
                    {
                        fieldsAreUnderAttack = true;
                    }
                }
                
                if(middleFieldsAreEmpty && !fieldsAreUnderAttack)
                {
                    returnList.add(this.position.getAdjancent(0, 2));
                }
            }
        }
        
        returnList.addAll(super.getAvailableFields());
        
        return returnList;
    }
    
    
    @Override 
    public MoveWithDetails Move(Field endField)
    {
        
        //is this move a castling left?
        if(this.position.getColumn() - endField.getColumn() == 2)
        {
            Piece leftRook = this.table.getPiece(this.position.getAdjancent(0, -4));
            leftRook.position = leftRook.position.getAdjancent(0, 3);
        }
        else if(this.position.getColumn() - endField.getColumn() == -2)
        {
            Piece rightRook = this.table.getPiece(this.position.getAdjancent(0, 3));
            rightRook.position = rightRook.position.getAdjancent(0, -2);
        }
        
        return super.Move(endField);
    }
    
    @Override
    public void ReverseMove(MoveWithDetails move)
    {
        
        if(move.getStart().getColumn() - move.getEnd().getColumn() == 2)
        {
            // it was a left castling
            Piece leftRook = this.table.getPiece(this.position.getAdjancent(0, 1));
            leftRook.position = leftRook.position.getAdjancent(0, -3);
        }
        else if(move.getStart().getColumn() - move.getEnd().getColumn() == -2)
        {
            // it was a right castling
            Piece rightRook = this.table.getPiece(this.position.getAdjancent(0, -1));
            rightRook.position = rightRook.position.getAdjancent(0, 2);
        }
        
        super.ReverseMove(move);
    }
    
    @Override
    public String toString() {
        return "K";
    }
    
    
}
