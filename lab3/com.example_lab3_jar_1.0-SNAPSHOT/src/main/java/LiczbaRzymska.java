/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bczl
 */
public class LiczbaRzymska {
    
    public void rangeTest(int arabicNumber){
        if(arabicNumber < 1 || arabicNumber > 3999)
            throw new IllegalArgumentException("Number not in range, roman number must be not highter that 3999");
        
    }
    
    
    public String toString(int arabic){
        String RomanNumber="";
        
    while (arabic >= 1000) {
        RomanNumber = RomanNumber + "M";
        arabic = arabic - 1000;        
    }
          
    while (arabic >= 900) {
        RomanNumber = RomanNumber + "CM";
        arabic = arabic -  900;
    }
    
    while (arabic >= 500) {
        RomanNumber = RomanNumber + "D";
        arabic = arabic - 500;
    }
    
    while (arabic >= 400) {
        RomanNumber = RomanNumber + "CD";
        arabic = arabic - 400;
    }
    
    while (arabic>= 100) {
        RomanNumber = RomanNumber + "C";
        arabic = arabic - 100;
    }
    
    while (arabic >= 90) {
        RomanNumber = RomanNumber + "XC";
        arabic = arabic - 90;
    }
    while (arabic >= 50) {
        RomanNumber = RomanNumber + "L";
        arabic = arabic - 50;
    }
    
    while (arabic >= 40) {
        RomanNumber = RomanNumber + "XL";
        arabic = arabic - 40;
    }
    
    while (arabic >= 10) {
        RomanNumber = RomanNumber + "X";
        arabic = arabic - 10;
    }
    
    while (arabic >= 9) {
        RomanNumber = RomanNumber + "IX";
        arabic = arabic - 9;
    }
    
    while (arabic >= 5) {
        RomanNumber = RomanNumber + "V";
        arabic = arabic - 5;
    }
    
    while (arabic >= 4) {
        RomanNumber = RomanNumber + "IV";
        arabic = arabic - 4;
    }
    
    while (arabic >= 1) {
        RomanNumber = RomanNumber + "I";
        arabic = arabic - 1;
    }    
    
    return RomanNumber;
    }
}
