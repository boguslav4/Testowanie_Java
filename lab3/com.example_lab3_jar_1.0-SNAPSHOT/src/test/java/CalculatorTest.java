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
public class CalculatorTest {
    
    //delta - odchylenie powstale przy zaokraglaniu
    private static final double maxDifference = 1e-15;
    
    public CalculatorTest() {
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

    /**
     * Test of add method, of class Calculator.
     */
    
    
   //Dodawanie
    @Test
    public void addingThreeToTwoAndTwoToThreeResultShouldBeTheSame() {
        Calculator calculator = new Calculator();
        double resultT = calculator.add(3.0, 2.0);
        assertEquals(5, resultT, maxDifference);
        resultT = calculator.add(2.0, 3.0);
        assertEquals(5, resultT, maxDifference);
    }

    @Test
    public void addingPositiveAndNegative() {
        Calculator calculator = new Calculator();
        double resultT = calculator.add(2, -4);
        assertEquals(-2, resultT, maxDifference);
    }
    
    
    //dzielenie
    @Test(expected = ArithmeticException.class)
        public void divisionNumberByZero() {
        Calculator calculator = new Calculator();
        calculator.div(0.0, 0.0);
    }
        
    @Test
    public void divisionOneByOne() {
        Calculator calculator = new Calculator();
        double resultT = calculator.div(1.0,1.0);
       assertEquals(1.0 , resultT, maxDifference);
    }    
    
    @Test
    public void divisionPositiveByNegative() {
        Calculator calculator = new Calculator();
        double resultT = calculator.div(3.0, -3.0);
       assertEquals(-1.0 , resultT, maxDifference);
    }   
    
    //mnozenie
    @Test
    public void multiplaPositiveAndNegative() {
        Calculator calculator = new Calculator();
        double resultT = calculator.multi(3.0, -3.0);
       assertEquals(-9.0 , resultT, maxDifference);
    }   
    
    @Test
    public void multiplaPositiveAndPositive() {
        Calculator calculator = new Calculator();
        double resultT = calculator.multi(3.0,3.0);
       assertEquals(9.0 , resultT, maxDifference);
    }   
    
    @Test
    public void multiplaZeroAndPositive() {
        Calculator calculator = new Calculator();
        double resultT = calculator.multi(0.0, 3.0);
       assertEquals(0.0 , resultT, maxDifference);
    }  
    
    @Test
    public void multiplaZeroAndNegative() {
        Calculator calculator = new Calculator();
        double resultT = calculator.multi(0.0, -3.0);
       assertEquals(0.0 , resultT, maxDifference);
    }   
    
    //odejmowanie
    @Test
    public void subBiggerNumberFromSmallerNumber() {
        Calculator calculator = new Calculator();
        double resultT = calculator.sub(3.0, 10.0);
       assertEquals(-7.0, resultT, maxDifference);
    }   
    
    @Test
    public void subSmallerNumberFromBiggerNumber() {
        Calculator calculator = new Calculator();
        double resultT = calculator.sub(10.0, 3.0);
       assertEquals(7.0 , resultT, maxDifference);
    } 
    
    @Test
    public void subZeroFromZero() {
        Calculator calculator = new Calculator();
        double resultT = calculator.sub(0.0, 0.0);
       assertEquals(0.0, resultT, maxDifference);
    }
    
    @Test
    public void subNumberFromZero() {
       Calculator calculator = new Calculator();
       double resultT = calculator.sub(0.0, 3.0);
       assertEquals(-3.0, resultT, maxDifference);
    }
    
    @Test
    public void subNegativeFromPositive() {
       Calculator calculator = new Calculator();
       double resultT = calculator.sub(10.0, -3.0);
       assertEquals(13.0, resultT, maxDifference);
    }
    
    @Test
    public void subNegativeFromNegative() {
       Calculator calculator = new Calculator();
       double resultT = calculator.sub (-10.0, -11.0);
       assertEquals(1.0, resultT, maxDifference);
    }
    
    //relacja wiekszosci
    @Test
    public void greaterA() {
        Calculator calculator = new Calculator();
        boolean resultT = calculator.greater(10.0, 0.0);
        assertTrue(resultT);
    }
    
    @Test
    public void greaterB() {
        Calculator calculator = new Calculator();
        boolean resultT = calculator.greater(0.0, 10.0);
        assertFalse(resultT);
    }
  
}
