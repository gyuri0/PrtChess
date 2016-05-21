/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import hu.unideb.inf.prtchess.data.Match;
import hu.unideb.inf.prtchess.data.MatchDAO;
import hu.unideb.inf.prtchess.data.MatchDAOImpl;
import java.util.Date;

/**
 *
 * @author György
 */
public class Game {
    
    private TableState currentState;
    
    public static Game getGameFromMatch(Match match)
    {
        Game game = new Game();
        for(Move move : match.getMoves())
        {
            game.move(move);
        }
        
        return game;
    }
    
    public Game()
    {
        this.currentState = new TableState();
        this.currentState.initialize();
    }
    
    public void move(Move move)
    {
        this.currentState.Move(move);
    }
    
    public Color getPlayerToMove()
    {
        return this.currentState.getPlayerToMove();
    }
    
    public TableState getTableState()
    {
        return this.currentState;
    }
    
    public void moveBack()
    {
        this.currentState.MoveBack();
    }
    
    public GameResult getResult()
    {
        return this.currentState.getResult();
    }
    
    public Color isNeedToSelectPromotion()
    {
        return this.currentState.isNeedToSelectPromotion();
    }
    
    public void selectPromotion(PieceType pieceType)
    {
        this.currentState.selectPromotion(pieceType);
    }
    
    public void Save()
    {
        Match match = new Match();
        match.setDate(new Date());
        for(int i=0;i < this.currentState.getMoves().size(); i++)
        {
            match.getMoves().add(this.currentState.getMoves().get(i));
        }
        
        MatchDAO daoImpl = new MatchDAOImpl();
        daoImpl.addMatch(match);
    }
}
