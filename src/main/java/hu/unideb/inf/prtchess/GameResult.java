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
public enum GameResult {
    BlackWin, WhiteWin, Drawn, Inprogress;
    
    @Override
    public String toString()
    {
        switch(this){
            case BlackWin: return "Black won";
            case WhiteWin: return "White won";
            case Drawn: return "Drawn";
            case Inprogress: return "In progress";
            default: return super.toString();
        }
    }
}
