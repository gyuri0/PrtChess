/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author György
 */
public class FieldTest {
    
    @Test
    public void getAdjacentTest()
    {
        Field field = new Field(1,3);
        
        assertEquals(new Field(3,5), field.getAdjancent(2, 2));
        assertNull(field.getAdjancent(-2, 3));
    }
    
}
