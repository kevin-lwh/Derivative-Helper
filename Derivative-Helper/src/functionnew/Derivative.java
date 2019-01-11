/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functionnew;

import java.util.Scanner;

/**
 *
 * @author wuj0394
 */
public class Derivative extends Function {
    int []c;//coeff of the derivatiove
    int[]e;//exponents of the derivative
    int m;//the slope of the tangent line at x 
    boolean containsConstant = false;
    int constantIndex = -1;

    public Derivative(String s){
        super(s);
        getDerivative();
        
        
    }
    
    public void displayDerivative(){
        //displays original function AND the derivative
        super.displayFunction(this.e, this.c);//y'=

        
    }
    
    
//assume the equation is simplified    
    public void getDerivative(){//go through the number of terms to change coeff/exponent and also change any constants to 0
       
        for(int i = 0; i < numTerms; i ++){
            
            if(exponents[i] == 0){
                containsConstant = true;
                constantIndex = i;
            }
            
        }
        
        
        if(!containsConstant){
            c = new int[numTerms];
            e = new int[numTerms];
            for(int i =0; i < numTerms; i ++){
                c[i]=coeff[i]*exponents[i];
                e[i]=exponents[i]-1;  
                
            }    
        }
        
        else{
            
            if(exponents[numTerms-1] == 0){ //if last term is constant
                numTerms = numTerms -1;
                c = new int[numTerms];
                e = new int[numTerms];
                for (int i = 0; i<numTerms;i++){
                    c[i]=coeff[i]*exponents[i];
                    e[i]=exponents[i]-1;
                }   
            }
            
            else{ //if constant in middle
                numTerms = numTerms -1;
                c = new int[numTerms];
                e = new int[numTerms];
                for(int i =0; i < numTerms; i ++){
                    if (i < constantIndex){
                        c[i]=coeff[i]*exponents[i];
                        e[i]=exponents[i]-1;
                    }
                    else if (i >= constantIndex){
                        c[i]=coeff[i+1]*exponents[i+1];
                        e[i]=exponents[i+1]-1;  
                    }
                }
                
            }
        }
    }

        
    
    
    public double getDerivativeAtPoint(double x1){
        double yValue =0;
        for(int i = 0; i < numTerms; i ++){
            double termValue = c[i] * Math.pow(x1, e[i]);
            yValue += termValue;
            
        }
        return yValue;
    }
    
    public double getY(double x){
        double yValue =0;
        for(int i = 0; i < numTerms; i ++){
            double termValue = c[i] * Math.pow(x, e[i]);
            yValue += termValue;
            
        }
        return yValue;
    } 
    
    
    
}
