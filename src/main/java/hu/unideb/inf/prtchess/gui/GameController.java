package hu.unideb.inf.prtchess.gui;

import hu.unideb.inf.prtchess.Color;
import hu.unideb.inf.prtchess.Field;
import hu.unideb.inf.prtchess.Game;
import hu.unideb.inf.prtchess.Move;
import hu.unideb.inf.prtchess.Piece;
import hu.unideb.inf.prtchess.PieceType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class GameController implements Initializable {
    
    private GraphicsContext gc;
    private Game game;
    private int squareSize = 50;
    private int startRow;
    private int startColumn;
    private Piece[][] pieces; 
    
    @FXML
    private Canvas tableCanvas;   
    
    @FXML
    private Label status;
    
    private static PieceType[][] promotionPieces;
    
    static
    {
        promotionPieces = new PieceType[][]
        {
            {PieceType.Bishop, PieceType.Knight},
            {PieceType.Queen, PieceType.Rook}
        };
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gc = tableCanvas.getGraphicsContext2D();
        
        /*
        MatchDAOImpl matchDAOImpl = new MatchDAOImpl();
        List<Match> matches = matchDAOImpl.getAllMatches();
        
        this.game = Game.getGameFromMatch(matches.get(matches.size() - 1));
        */
        
        this.game = new Game();
        this.Refresh();
    }
     
    public void setGame(Game game)
    {
        this.game = game;
        this.Refresh();
    }
    
    @FXML 
    private void moveBack(ActionEvent event)
    {
        this.game.moveBack();
        
        this.Refresh();
    }
    
    @FXML 
    private void dragStart(MouseEvent event)
    {   
        if(this.game.isNeedToSelectPromotion() == null)
        {
            this.startColumn = (int)event.getX() / squareSize;
            this.startRow = (int)(400-event.getY()) / squareSize;        

            Piece piece = this.pieces[startRow][startColumn];

            if(piece != null)
            {
                Image image = PieceImage.getImageForPiece(piece);        
                tableCanvas.getScene().setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
            }
        }
        else
        {          
            PieceType pieceType = promotionPieces[(int)(event.getX() / 200)][(int)(event.getY() / 200)];
            System.out.println(pieceType);
            this.game.selectPromotion(pieceType);            
            this.Refresh();       
        }
    }
    
    @FXML 
    private void dragEnd(MouseEvent event)
    {   
        if(this.game.isNeedToSelectPromotion() == null)
        {
            int endRow = (int)(400-event.getY()) / squareSize;
            int endColumn = (int)event.getX() / squareSize;

            this.game.move(new Move(new Field(this.startRow, this.startColumn), new Field(endRow, endColumn)));
            this.Refresh();

            tableCanvas.getScene().setCursor(Cursor.DEFAULT);
        }
        
    }
    
    @FXML
    private void save()
    {
        this.game.Save();
    }
    
    private void Refresh()
    {
        this.pieces = game.getTableState().getTable();
        this.status.setText(game.getResult().toString());
        
        if(game.isNeedToSelectPromotion() != null)
        {
            this.drawPromotion(game.isNeedToSelectPromotion());
        }
        else
        {
            this.drawTable();
        }
    }
    
    private void drawPromotion(Color color)
    {      
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillRect(0, 0, squareSize*8, squareSize*8);
        
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                PieceType pieceType = promotionPieces[i][j];
                gc.drawImage(
                        PieceImage.getImageForPiece(color, pieceType),
                        i*squareSize*4, 
                        j*squareSize*4, 
                        squareSize*4, 
                        squareSize*4
                );
            }
        }
    }
    
    private void drawTable()
    {              
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if((i+j) % 2 == 1)
                {
                    gc.setFill(javafx.scene.paint.Color.WHITE);
                }
                else
                {
                    gc.setFill(javafx.scene.paint.Color.BROWN);
                }
                gc.fillRect(j*squareSize, (7-i)*squareSize, squareSize, squareSize);
                if(this.pieces[i][j] != null)
                {
                    gc.drawImage(
                            PieceImage.getImageForPiece(this.pieces[i][j]), 
                            j*squareSize, 
                            (7-i)*squareSize, 
                            squareSize, 
                            squareSize);                   
                }
            }
        }
    }
}
