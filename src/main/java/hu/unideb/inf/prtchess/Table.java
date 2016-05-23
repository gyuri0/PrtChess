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
public class Table {
    private List<Piece> pieces = new ArrayList<Piece>();
    private List<MoveWithDetails> moves = new ArrayList<MoveWithDetails>();

    public List<MoveWithDetails> getMoves() {
        return moves;
    }
    
    public List<Piece> getPieces() {
        return pieces;
    }
    
    private Color playerToMove;

    public Color getPlayerToMove() {
        return playerToMove;
    }
    
    public Piece getPiece(Field field)
    {
        for(Piece piece : pieces)
        {
            if(piece.getPosition().equals(field))
            {
                return piece;
            }
        }
        
        return null;
    }
    
    public Field getKingPosition(Color color)
    {
        for(Piece piece : pieces)
        {
            if(piece.getPieceType() == PieceType.King && piece.getColor() == color)
            {
                return piece.getPosition();
            }
        }
        
        return null;
    }   
    
    public boolean isFieldControlledByEnemy(Color color, Field field)
    {       
        if(this.getControlledFields(color.getOpponent()).contains(field))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isKingUnderAttack(Color color)
    {
        Field kingPosition = this.getKingPosition(color);
         
        if(this.isFieldControlledByEnemy(color, kingPosition))
        {
            return true;
        }
        else
        {
            return false;
        }
                
    }
    
    public List<Field> getControlledFields(Color color)
    {
        List<Field> fields = new ArrayList<>();
        
        for(Piece piece : this.pieces)
        {
            if(piece.color == color)
            {
                fields.addAll(piece.getControlledFields());
            }
        }
        
        return fields;
    }
    
    public List<Field> getAvailableFields(Color color)
    {
        List<Field> fields = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        for(Piece piece : this.pieces)
        {
            if(piece.color == color)
            {
                pieces.add(piece);
            }
        }    
        
        for(Piece piece : pieces)
        {
            fields.addAll(piece.getAvailableFields());
        }
        
        return fields;
    }
    
    public void initialize()
    {
       for(int i=0;i<2;i++)
       {
            Color color;
            int row;
            if(i==0)
            {
                color = Color.White;
                row = 0;
            }
            else
            {
                color = Color.Black;
                row = 7;
            }
            
            this.pieces.add(new Rook(new Field(row,0), color, this));
            this.pieces.add(new Rook(new Field(row,7), color, this));
            this.pieces.add(new Knight(new Field(row,1), color, this));
            this.pieces.add(new Knight(new Field(row,6), color, this));
            this.pieces.add(new Bishop(new Field(row,2), color, this));
            this.pieces.add(new Bishop(new Field(row,5), color, this));
            this.pieces.add(new Queen(new Field(row,3), color, this));
            this.pieces.add(new King(new Field(row,4), color, this));
       }
       
       for(int i=0;i<8;i++)
       {
           pieces.add(new Pawn(new Field(1, i), Color.White, this));
           pieces.add(new Pawn(new Field(6, i), Color.Black, this));
       }
       
       this.playerToMove = Color.White;
    }
    
    public Piece[][] getTable()
    {
        Piece[][] table = new Piece[8][8];
        for(Piece piece : pieces)
        {
            Field field = piece.getPosition();
            table[field.getRow()][field.getColumn()] = piece;
        }
        
        return table;
    }
    
    public boolean Move(Move move)
    {
        Piece piece = this.getPiece(move.getStart());
        
        if(piece == null || piece.getColor() != playerToMove || 
                !piece.getAvailableFields().contains(move.getEnd()))
        {
            return false;
        }
        
        MoveWithDetails moveWithDetails = piece.Move(move.getEnd());
        this.moves.add(moveWithDetails);
        this.playerToMove = this.playerToMove.getOpponent();
        return true;
    }
    
    public void MoveBack()
    {
        if(this.moves.size() > 0)
        {
            MoveWithDetails move = this.moves.remove(this.moves.size() - 1);
            Piece piece = this.getPiece(move.getEnd());
            piece.ReverseMove(move);

            this.playerToMove = this.playerToMove.getOpponent();
        }
    }
    
    public GameResult getResult()
    {
        if(this.getAvailableFields(playerToMove).isEmpty())
        {
            if(this.isKingUnderAttack(playerToMove))
            {
                if(playerToMove == Color.Black)
                {
                    return GameResult.WhiteWin;
                }
                else
                {
                    return GameResult.BlackWin;
                }
            }
            else
            {
                return GameResult.Drawn;
            }
        }
        else
        {
            return GameResult.Inprogress;
        }
    }
    
    public void selectPromotion(PieceType pieceType)
    {
        MoveWithDetails lastMove = this.moves.get(this.moves.size()-1);
        lastMove.setPromotion(pieceType);
        Piece pawnToPromotion = this.getPiece(lastMove.getEnd());
        
        this.pieces.remove(pawnToPromotion);
        
        Piece newPiece = null;
        switch(pieceType)
        {
            case Bishop:
                newPiece = new Bishop(pawnToPromotion.getPosition(), pawnToPromotion.getColor(), this);
                break;
            case Knight:
                newPiece = new Knight(pawnToPromotion.getPosition(), pawnToPromotion.getColor(), this);
                break;
            case Queen:
                newPiece = new Queen(pawnToPromotion.getPosition(), pawnToPromotion.getColor(), this);
                break;
            case Rook:
                newPiece = new Rook(pawnToPromotion.getPosition(), pawnToPromotion.getColor(), this);
                break;
        }
        
        this.pieces.add(newPiece);
    }
    
    public Color isNeedToSelectPromotion()
    {
        if(this.moves.isEmpty())
        {
            return null;
        }
        
        MoveWithDetails lastMove = this.moves.get(this.moves.size()-1);
        Piece piece = this.getPiece(lastMove.getEnd());              
        
        if(piece.pieceType == PieceType.Pawn)
        {
            int row = piece.getPosition().getRow();
            if(row == 0 || row == 7)
            {
                return piece.getColor();
            }
        }
        
        return null;
    }
}
