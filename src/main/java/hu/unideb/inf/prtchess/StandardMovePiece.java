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
            int[] rowDirections, int[] columnDirections, boolean multiple,
            Table table)
    {
        super(position, color, pieceType, table);
        this.rowDirections = rowDirections;
        this.columnDirections = columnDirections;
        this.multiple = multiple;
    }
    
    @Override
    protected List<Field> getControlledFields()
    {
        List<Field> returnList = new ArrayList<>();       
        
        for(int i=0;i<rowDirections.length; i++)
        {
            Field field = this.position.getAdjancent(rowDirections[i], columnDirections[i]);
            
            if(multiple)
            {
                while(field != null && this.table.getPiece(field) == null)
                {
                    returnList.add(field);
                    field = field.getAdjancent(rowDirections[i], columnDirections[i]);
                }
            }
            
            if(field != null && (this.table.getPiece(field) == null || this.table.getPiece(field).color == this.color.getOpponent()))
            {
                returnList.add(field);
            }
            

        }
        
        return returnList;
    } 
}
