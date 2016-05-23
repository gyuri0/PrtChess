/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author György
 */
public interface MatchDAO {
    public List<Match> getAllMatches();
    
    public void addMatch(Match match);
    
    public void deleteMatch(Match match);
}
