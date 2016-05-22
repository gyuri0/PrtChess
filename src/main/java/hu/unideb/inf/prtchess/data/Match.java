/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.data;

import hu.unideb.inf.prtchess.Move;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author György
 */
public class Match {
    private Date date;
    
    private List<StoredMove> moves;
    
    public Match()
    {
        this.moves = new ArrayList<>();
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<StoredMove> getMoves() {
        return moves;
    }

    public void setMoves(List<StoredMove> moves) {
        this.moves = moves;
    }
    
    @Override
    public String toString()
    {
        return this.date.toString();
    }
}
