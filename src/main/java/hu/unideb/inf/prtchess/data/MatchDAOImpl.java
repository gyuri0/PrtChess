/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.data;

import hu.unideb.inf.prtchess.Field;
import hu.unideb.inf.prtchess.Move;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author György
 */
public class MatchDAOImpl implements MatchDAO {
    public static String uri;
    public static DocumentBuilder builder;
    public static DateFormat dateFormat;
    static
    {
       
        uri = "D:\\matches.xml";
        
        dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {  
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Match generateMatchFromMatchNode(Node node)
    {
        Match match = new Match();
        
        Element element = (Element) node;
        
        Date parsed = new Date();
        try {
            parsed = dateFormat.parse(element.getElementsByTagName("date").item(0).getTextContent());
        }
        catch(ParseException pe) {
            throw new IllegalArgumentException();
        }
        
        match.setDate(parsed);
        
        NodeList moves = element.getElementsByTagName("move");
        for(int i=0;i<moves.getLength();i++)
        {
            Move move = generateMoveFromMoveNode(moves.item(i));
            match.getMoves().add(move);
        }
        
        return match;
    }
    
    private Move generateMoveFromMoveNode(Node node)
    {
        Element moveElement = (Element) node;

        Node startFieldNode = moveElement.getElementsByTagName("startfield").item(0);
        Field startField = this.genereateFieldFromFieldNode(startFieldNode);
        
        Node endFieldNode = moveElement.getElementsByTagName("endfield").item(0);
        Field endField = this.genereateFieldFromFieldNode(endFieldNode);
        
        Move move = new Move(startField, endField);
        
        return move;
    }
    
    private Field genereateFieldFromFieldNode(Node node)
    {
        Element fieldElement = (Element) node;
        
        int row = Integer.parseInt(fieldElement.getElementsByTagName("row").item(0).getTextContent());
        int column = Integer.parseInt(fieldElement.getElementsByTagName("column").item(0).getTextContent());
        return new Field(row, column);
    }
    
    private void appendMatchNode(Document doc, Match match)
    {
        Node matchElement = doc.createElement("match");
        
        Node dateElement = doc.createElement("date");
        dateElement.appendChild(doc.createTextNode(dateFormat.format(match.getDate())));
        matchElement.appendChild(dateElement);
        
        for(Move move : match.getMoves())
        {
            Node moveNode = doc.createElement("move");
            
            Field startField = move.getStart();
            
            Node startFieldNode = doc.createElement("startfield");
            
            Node rowNode = doc.createElement("row");
            rowNode.appendChild(doc.createTextNode(String.valueOf(startField.getRow())));
            
            Node columnNode = doc.createElement("column");
            columnNode.appendChild(doc.createTextNode(String.valueOf(startField.getColumn())));
            
            startFieldNode.appendChild(rowNode);
            startFieldNode.appendChild(columnNode);
            
            Field endField = move.getEnd();
            
            Node endFieldNode = doc.createElement("endfield");
            
            rowNode = doc.createElement("row");
            rowNode.appendChild(doc.createTextNode(String.valueOf(endField.getRow())));
            
            columnNode = doc.createElement("column");
            columnNode.appendChild(doc.createTextNode(String.valueOf(endField.getColumn())));
            
            endFieldNode.appendChild(rowNode);
            endFieldNode.appendChild(columnNode);
            
            moveNode.appendChild(startFieldNode);
            moveNode.appendChild(endFieldNode);
            
            matchElement.appendChild(moveNode);
        }
        
        doc.getDocumentElement().appendChild(matchElement);
    }
    
    @Override
    public List<Match> getAllMatches() 
    {                      
        List<Match> returnList = new ArrayList<>();     
        try {
            Document doc = builder.parse(uri);
            NodeList matchNodeList = doc.getElementsByTagName("match");
            for(int i = 0; i<matchNodeList.getLength(); i++)
            {
                Node matchNode = matchNodeList.item(i);
                returnList.add(generateMatchFromMatchNode(matchNode));
            }
        } catch (SAXException | IOException ex) {
            Logger.getLogger(MatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnList;
    }

    @Override
    public void addMatch(Match match) {
        try {
            Document doc = builder.parse(uri);
            
            this.appendMatchNode(doc, match);
            
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult newFile = new StreamResult(
                    new File(uri));
            
            trans.transform(source, newFile);
        } catch (SAXException | IOException | TransformerException ex) {
            Logger.getLogger(MatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Match getMatchByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMatchByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
