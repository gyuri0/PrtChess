/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import hu.unideb.inf.prtchess.data.Match;
import hu.unideb.inf.prtchess.data.MatchDAO;
import hu.unideb.inf.prtchess.data.StoredMove;
import hu.unideb.inf.prtchess.gui.MainApp;
import java.util.Date;

/**
 *
 * @author György
 */
public class Game {
    
    private Table currentState;
    
    public static Game getGameFromMatch(Match match)
    {
        Game game = new Game();
        for(StoredMove move : match.getMoves())
        {
            game.move(new Move(move.getStart(), move.getEnd()));
            if(move.getPieceType() != null)
            {
                game.selectPromotion(move.getPieceType());
            }
        }
        
        return game;
    }
    
    public Game()
    {
        this.currentState = new Table();
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
    
    public Table getTableState()
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
            MoveWithDetails move = this.currentState.getMoves().get(i);
            StoredMove storedMove = new StoredMove(move.getStart(), move.getEnd());
            if(move.getPromotion() != null)
            {
               storedMove.setPieceType(move.getPromotion());
            }
            
            match.getMoves().add(storedMove);
        }
        
        
        MainApp.MatchDAO.addMatch(match);
    }
}
