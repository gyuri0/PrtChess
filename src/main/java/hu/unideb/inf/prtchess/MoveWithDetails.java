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
public class MoveWithDetails extends Move{
    
    private Piece capturedPiece;

    private PieceType promotion;

    private boolean enPassant;

    public boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }
    
    public PieceType getPromotion() {
        return promotion;
    }

    public void setPromotion(PieceType promotion) {
        this.promotion = promotion;
    }
    
    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }
    
    public MoveWithDetails(Field startField, Field endField)
    {
        super(startField, endField);
        this.promotion = null;
        this.enPassant = false;
    }
}
