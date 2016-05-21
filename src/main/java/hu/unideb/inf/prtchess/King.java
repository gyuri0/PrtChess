/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

/**
 *
 * @author György
 */
public class King extends StandardMovePiece {
    
    public King(Field position, Color color)
    {
        super(position, color, PieceType.King,
                new int[]{ -1, -1, -1, 0, 0, 1, 1, 1},
                new int[]{ -1, 0, 1, -1, 1, -1, 0, 1},
                false
                );
    }
    
    /*
    @Override
    public List<Field> getAvailableFields(TableState tableState) {
        List<Field> returnList = new ArrayList<Field>();
        
        int[] rd = new int[]{ -1, -1, -1, 0, 0, 1, 1, 1};
        int[] cd = new int[]{ -1, 0, 1, -1, 1, -1, 0, 1};
        
        for(int i=0;i<rd.length; i++)
        {
            Field field = this.position.getAdjancent(rd[i], cd[i]);           
            
            if(field != null && 
                    (tableState.getPiece(field) == null ||
                    tableState.getPiece(field).color == this.color.getOpponent()))
            {
                returnList.add(field);
            }
        }
        
        //Special move : castling
        if(this.moveCount == 0)
        {  
            //castling left
            Piece leftRook = tableState.getPiece(this.position.getAdjancent(0, -4));
            if(leftRook != null && leftRook.moveCount == 0)
            {
                boolean middleFieldsAreEmpty = true;
                boolean fieldsAreUnderAttack = false;
                
                for(int i = 1; i <= 3; i++)
                {
                    if(tableState.getPiece(this.position.getAdjancent(0, -i)) != null)
                    {
                        middleFieldsAreEmpty = false;
                    }
                }
                
                for(int i = 0; i<=4;i++)
                {
                    if(tableState.isFieldUnderAttack(color, this.position.getAdjancent(0, -i)))
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
            Piece rightRook = tableState.getPiece(this.position.getAdjancent(0, 3));
            if(rightRook != null && rightRook.moveCount == 0)
            {
                boolean middleFieldsAreEmpty = true;
                for(int i = 1; i <= 2; i++)
                {
                    if(tableState.getPiece(this.position.getAdjancent(0, i)) != null)
                    {
                        middleFieldsAreEmpty = false;
                    }
                }
                if(middleFieldsAreEmpty)
                {
                    returnList.add(this.position.getAdjancent(0, 2));
                }
            }
        }
        
        return returnList;
    }
    */
    
    @Override 
    public MoveWithDetails Move(TableState tableState, Field endField)
    {
        
        //is this move a castling left?
        if(this.position.getColumn() - endField.getColumn() == 2)
        {
            Piece leftRook = tableState.getPiece(this.position.getAdjancent(0, -4));
            leftRook.position = leftRook.position.getAdjancent(0, 3);
        }
        else if(this.position.getColumn() - endField.getColumn() == -2)
        {
            Piece rightRook = tableState.getPiece(this.position.getAdjancent(0, 3));
            rightRook.position = rightRook.position.getAdjancent(0, -2);
        }
        
        return super.Move(tableState, endField);
    }
    
    @Override
    public void ReverseMove(TableState tableState, MoveWithDetails move)
    {
        
        if(move.getStart().getColumn() - move.getEnd().getColumn() == 2)
        {
            // it was a left castling
            Piece leftRook = tableState.getPiece(this.position.getAdjancent(0, 1));
            leftRook.position = leftRook.position.getAdjancent(0, -3);
        }
        else if(move.getStart().getColumn() - move.getEnd().getColumn() == -2)
        {
            // it was a right castling
            Piece rightRook = tableState.getPiece(this.position.getAdjancent(0, -1));
            rightRook.position = rightRook.position.getAdjancent(0, 2);
        }
        
        super.ReverseMove(tableState, move);
    }
    
    @Override
    public String toString() {
        return "K";
    }
    
    
}
