/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.gui;

import hu.unideb.inf.prtchess.Game;
import hu.unideb.inf.prtchess.data.Match;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author György
 */
public class LoadGameController implements Initializable {

    @FXML
    private ComboBox choiceBox;
    
    @FXML
    private void loadGame(ActionEvent actionEvent)
    {
        Match match = (Match) choiceBox.getValue();
        if(match == null)
        {
            return;
        }
        Game game = Game.getGameFromMatch(match);
        
        Parent root;
        try {
            Stage stage = (Stage) choiceBox.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
            root = loader.load();
            loader.<GameController>getController().setGame(game);
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void deleteGame(ActionEvent actionEvent)
    {
        MainApp.MatchDAO.deleteMatch((Match)this.choiceBox.getValue());
        this.choiceBox.getItems().remove(this.choiceBox.getValue());
        
    }
    
    public LoadGameController()
    {

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
              
        List<Match> matches = MainApp.MatchDAO.getAllMatches();
        
        ObservableList<Match> observableArrayList = FXCollections.observableArrayList(matches);
        choiceBox.getItems().addAll(observableArrayList);
    }
    
}
