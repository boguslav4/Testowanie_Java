/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bczl
 */
public class LiczbaRzymskaTest {
    
    public LiczbaRzymskaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    //domyslne testy zaskakujaco sie sprawdzily
    /**
     * Test of rangeTest method, of class LiczbaRzymska.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRangeTestNegative() {
        System.out.println("rangeTest");
        int arabicNumber = -1;
        LiczbaRzymska instance = new LiczbaRzymska();
        instance.rangeTest(arabicNumber);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testRangeTestSmall() {
        System.out.println("rangeTest");
        int arabic = 0;
        LiczbaRzymska instance = new LiczbaRzymska();
        instance.rangeTest(arabic);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRangeTestBig() {
        System.out.println("rangeTest");
        int arabic = 4001;
        LiczbaRzymska instance = new LiczbaRzymska();
        instance.rangeTest(arabic);
    }

    /**
     * Test of toString method, of class LiczbaRzymska.
     */
    @Test
    public void testToString() {
       System.out.println("toString");
       int arabic = 5;
       LiczbaRzymska instance = new LiczbaRzymska();
       String expResult = "V";
       String result = instance.toString(arabic);
       assertEquals(expResult, result);
    }
    
    //metoda alternatywna
    @Test
    public void toStringBigNumberInRange() {
       LiczbaRzymska liczbaRzymska= new LiczbaRzymska();
       String resultT = liczbaRzymska.toString(2015);
       assertEquals("MMXV" , resultT);
    }  
    
    @Test
    public void toStringBorderNumber() {
       LiczbaRzymska liczbaRzymska= new LiczbaRzymska();
       String resultT = liczbaRzymska.toString(51);
       assertEquals("LI" , resultT);
    }  
    
    
    @Test
    public void toStringNumberWhichShouldGiveLongResult() {
       LiczbaRzymska liczbaRzymska= new LiczbaRzymska();
       String resultT = liczbaRzymska.toString(1999);
       assertEquals("MCMXCIX" , resultT);
    }
    
    @Test
    public void toStringBonusTest() {
       LiczbaRzymska liczbaRzymska= new LiczbaRzymska();
       String resultT = liczbaRzymska.toString(104);
       assertEquals("CIV" , resultT);
    }
}
