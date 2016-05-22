/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.data;

import hu.unideb.inf.prtchess.Field;
import hu.unideb.inf.prtchess.PieceType;

/**
 *
 * @author György
 */
public class StoredMove {
    private Field start;

    public Field getStart() {
        return start;
    }

    public void setStart(Field start) {
        this.start = start;
    }

    public Field getEnd() {
        return end;
    }

    public void setEnd(Field end) {
        this.end = end;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
    
    private Field end;
    
    private PieceType pieceType;
    
    public StoredMove(Field start, Field end)
    {
        this.start = start;
        this.end = end;
    }
}
