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
public class StandardMovePiece extends Piece{
    private int[] rowDirections;
    private int[] columnDirections;
    private boolean multiple;
    
    public StandardMovePiece(Field position, Color color, PieceType pieceType,
            int[] rowDirections, int[] columnDirections, boolean multiple)
    {
        super(position, color, pieceType);
        this.rowDirections = rowDirections;
        this.columnDirections = columnDirections;
        this.multiple = multiple;
    }
    
    @Override
    protected List<Field> getControlledFields(TableState tableState)
    {
        List<Field> returnList = new ArrayList<>();       
        
        for(int i=0;i<rowDirections.length; i++)
        {
            Field field = this.position.getAdjancent(rowDirections[i], columnDirections[i]);
            
            if(multiple)
            {
                while(field != null && tableState.getPiece(field) == null)
                {
                    returnList.add(field);
                    field = field.getAdjancent(rowDirections[i], columnDirections[i]);
                }
            }
            
            if(field != null && (tableState.getPiece(field) == null || tableState.getPiece(field).color == this.color.getOpponent()))
            {
                returnList.add(field);
            }
            

        }
        
        return returnList;
    } 
}
