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
    
    private List<Move> moves;
    
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

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }    
}
