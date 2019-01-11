/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functionnew;



/**
 *
 * @author wuj0394
 */
public class Function {
    
    int degree; //don
    int numTerms; //don
    int [] exponents; //don
    int [] coeff; //don
    int [] indexOfSigns; //don
    String [] terms; //don
    int numSigns = 0; //don
    String input; //This is the input in a String (done)
    String [] equation; //This is the input as an Array (done)
    
    
    //
    public Function(String s){
        
        
        this.input = s;
        this.equation = this.input.split("");
        
        
        //first space bug fix
        if(equation[0].equals("")){
            String [] equationSub = new String[equation.length-1];
        //for loop to make a new array, 1 less long, and copy from i+1 to i in other array. 
            for(int i =1; i<equation.length; i++){
                equationSub[i-1] = equation[i];
            }
            
            equation = equationSub;
        }
        
        //goes through the input 
        for (int i = 0; i < equation.length; i++){
            if (equation[i].equalsIgnoreCase("+")||equation[i].equalsIgnoreCase("-")){
                //if there is a negative sign after equal sign do nothing
                if(equation[i].equalsIgnoreCase("-")&&equation[i-1].equalsIgnoreCase("=")){
                }
                else if (equation[i].equalsIgnoreCase("-") && equation[i-1].equalsIgnoreCase("^")){
                }
                //count number of signs
                else{
                    this.numSigns += 1;
                }
            }
        }
        
        numTerms = numSigns + 1;
        exponents = new int[numTerms];
        coeff = new int [numTerms];
        indexOfSigns = new int[numSigns];
        int indexSignCounter = 0;
        for (int i = 0; i < equation.length; i ++){
            if (equation[i].equalsIgnoreCase("+")||equation[i].equalsIgnoreCase("-")){
                //if there is a negative sign after equal sign do nothing
                if(equation[i].equalsIgnoreCase("-")&&equation[i-1].equalsIgnoreCase("=")){
                }
                else if (equation[i].equalsIgnoreCase("-") && equation[i-1].equalsIgnoreCase("^")){
                }
                else{
                    indexOfSigns[indexSignCounter] = i;
                    indexSignCounter ++;
                }
            }
        }
        
        
        
        terms = new String[numTerms];
        
        //split the input into a term array
        for (int i = 0; i < numTerms; i++){
            
            if(i == 0 && numTerms > 1){ //first term and more than one term
                terms[i] = input.substring(2, indexOfSigns[i]);
            }
            else if( i == 0 && numTerms == 1){ //only one term
                terms[i] = input.substring(2, input.length());
            }
            else if (i == numTerms-1){ //last term
                terms[i] = input.substring(indexOfSigns[i-1],input.length());
            }
            else { //terms in between
                terms[i] = input.substring(indexOfSigns[i-1],indexOfSigns[i]);
            }
        }
        
        //finding exponents and coeff
        for(int i = 0; i < numTerms; i ++){
            
            //getting exponents
            if (!(terms[i].contains("x")) && !(terms[i].contains("^"))){
                exponents[i] = 0;
            }
            else if (!(terms[i].contains("^"))){
                exponents[i] = 1;
            }
            else{
                int caratIndex = terms[i].indexOf("^");
                exponents[i] = Integer.parseInt(terms[i].substring(caratIndex+1, terms[i].length()));
            }
            
            //getting coeff
            if (!(terms[i].contains("x")) && !(terms[i].contains("^"))){ //constant terms
                if (terms[i].contains("+")){
                    coeff[i] = Integer.parseInt(terms[i].substring(1,terms[i].length()));
                }
                else if (terms[i].contains("-")){
                    coeff[i] = Integer.parseInt(terms[i].substring(0,terms[i].length()));
                }
            }
            else{
                int xIndex = terms[i].indexOf("x");
                if (xIndex == 0){ //frist term with coeff 1
                    coeff[i] = 1;
                }
                else if (xIndex == 1 && terms[i].contains("+")){ //middle temr with coeff 1
                    coeff[i] = 1;
                }
                else if (xIndex == 1 && terms[i].substring(0, 1).equalsIgnoreCase("-")){ //middle term with coeff -1
                    System.out.println("sdsdsdsdsd");
                    coeff[i] = -1;
                }
                else{ //middle term with coeff >1 or < 0
                    if (terms[i].contains("+")){ //when the term contains a plus
                        coeff[i] = Integer.parseInt(terms[i].substring(1,xIndex));
                    }
                    else{ //if not
                        coeff[i] = Integer.parseInt(terms[i].substring(0,xIndex));
                    }
                }
            }
        }
        degree = exponents[0];
    }
    
    
    public String displayFunction(int [] exp, int [] coe){
        
        String [] displayTerm = new String [numTerms];
        
        for (int i = 0; i < numTerms; i ++){
            if(i == 0){ //if its the first term
                if (coe[i]<0){ //negative term
                    if(exp[i]==0){ //constant term
                        displayTerm [i] = Integer.toString(coe[i]);
                    }
                    else if (exp[i] == 1){ //degree one term
                        displayTerm [i] = coe[i]+"x";
                    }
                    else{
                        displayTerm [i] = coe[i]+"x^" + exp[i];
                    }
                }
                else{ //positive term
                    if(exp[i]==0){ //constant term
                        displayTerm [i] = Integer.toString(coe[i]);
                    }
                    else if (exp[i] == 1){ //degree one term
                        displayTerm [i] = coe[i]+"x";
                    }
                    else{
                        displayTerm [i] = coe[i]+"x^" + exp[i];
                    }
                }
            }
            else{
                if (coe[i]<0){ //negative term
                    if(exp[i]==0){ //constant term
                        displayTerm [i] = Integer.toString(coe[i]);
                    }
                    else if (exp[i] == 1){ //degree one term
                        displayTerm [i] = coe[i]+"x";
                    }
                    else{
                        displayTerm [i] = coe[i]+"x^" + exp[i];
                    }
                }
                else{ //positive term
                    if(exp[i]==0){ //constant term
                        displayTerm [i] = "+"+coe[i];
                    }
                    else if (exp[i] == 1){ //degree one term
                        displayTerm [i] = "+"+coe[i]+"x";
                    }
                    else{
                        displayTerm [i] = "+"+coe[i]+"x^" + exp[i];
                    }
                }
            }
        }
        String output = "";
        for (int i =0 ; i < numTerms; i++){
            output += displayTerm[i];
        }
        String Foutput = "y="+output;
        return Foutput;
    }
    
    public double getY(double x){
        double yValue =0;
        for(int i = 0; i < numTerms; i ++){
            double termValue = coeff[i] * Math.pow(x, exponents[i]);
            yValue += termValue;
            
        }
        return yValue;
    } 

}
