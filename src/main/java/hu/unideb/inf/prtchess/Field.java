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
public class Field {
    private int column;
    private int row;
    
    public Field(int row, int column)
    {
        this.column = column;
        this.row = row;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
    public int getRow()
    {
        return this.row;
    }

    public boolean isValid()
    {
        return this.row >=0 && this.row < 8 && this.column >= 0 && this.column < 8;
    }
    
    public Field getAdjancent(int rowDirection, int columnDirection)
    {
        Field newField = new Field(this.row + rowDirection, this.column + columnDirection);
        if(!newField.isValid())
        {
            return null;
        }
        
        return newField;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Field))
        {
            return false;
        }
        
        Field field = (Field)obj;
        
        return this.row == field.row && this.column == field.column;
    }
    
    
}
