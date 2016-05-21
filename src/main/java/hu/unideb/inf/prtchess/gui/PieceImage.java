/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.gui;

import hu.unideb.inf.prtchess.Color;
import hu.unideb.inf.prtchess.Piece;
import hu.unideb.inf.prtchess.PieceType;
import java.net.URL;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author György
 */
public class PieceImage {
    
    private static HashMap<String, Image> images = new HashMap();
    
    public static Image getImageForPiece(Piece piece)
    {
        return PieceImage.getImageForPiece(piece.getColor(), piece.getPieceType());
    }
    
    public static Image getImageForPiece(Color pieceColor, PieceType pieceType)
    {
        String resourcePath = "/images/"+pieceColor.toString().toLowerCase()
                + "_" +
                pieceType.toString().toLowerCase()+ ".png";
        
        if(PieceImage.images.containsKey(resourcePath))
        {
            return images.get(resourcePath);
        }
        else
        {
            URL url = PieceImage.class.getResource(resourcePath);
            
            Image image = new Image(url.toString(), 200, 200, true, true);
            PieceImage.images.put(resourcePath, image);
            return image;
        }
    }
}
