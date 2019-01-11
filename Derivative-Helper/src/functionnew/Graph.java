/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functionnew;


import java.io.*;
import java.util.Scanner;
import java.awt.*; //needed for graphics
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*; //needed for graphics
import static javax.swing.JFrame.EXIT_ON_CLOSE; //needed for graphics
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;



/**
 *
 * @author wuj0394
 */
public class Graph extends JFrame {
    
    Function original;
    Derivative derivative;
    int height = 800;
    int width = 800;
    double maxA = 20;
    double minA = -20;
    double maxB = 20;
    double minB = -20;
    double increment = 0.001;
    
    int [] xArray;
    int [] yArray;
    int [] xArrayD;
    int [] yArrayD;
    
    public Graph(Function a, Derivative d){
        original = a;
        derivative = d;
        

    }
    
   //Draws the current generation of living cells on the screen
    public void paint( Graphics g){
        Image img = createImage();
        g.drawImage(img,8,30,this);
    }
    
    
    
    
    public Image createImage(){
        BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        //x-axis
        g.setColor(Color.WHITE);
        g.drawLine(0, height/2, width, height/2);
        //y-axis
        g.drawLine(width/2, 0, width/2, height);
        
        
        //creates the labels on axis
        //x-axis
        for(int i = (int)minA; i<=maxA; i++){
            
            if(i%10==0){
                g.setColor(Color.white);
                g.drawString(Integer.toString(i), getScreenX(i), getScreenY(0)+12);
                
            }
            
        }
        //y-axis
        for(int i = (int)maxB; i>=minB; i--){
            
            if(i%10==0){
                if (i!=0){
                    g.drawString(Integer.toString(i), getScreenX(0)-18, getScreenY(i));
                }
            }
            
        }
        
        //draws the legend
        g.drawString("Legend", 20, 20);
        g.setColor(Color.yellow);
        g.drawString("Yellow: Original Function", 20, 40);
        g.setColor(Color.red);
        g.drawString("Red: Derivative", 20, 60);
        
        
        
        
        
        //drawing original function
        xArray = new int [(int)(maxA-minA)*(int)(1/increment)];
        yArray = new int [(int)(maxA-minA)*(int)(1/increment)];
        int index = 0;
        for (double x = minA; x < maxA; x += increment){
            xArray[index] = getScreenX(x);
            //System.out.println(index);
            if (original.getY(x) > maxB){
            }
            else{
                yArray[index] = getScreenY(original.getY(x));
            }
            
            
            index++;
        }
        
        for (int i = 0; i < xArray.length-1; i ++){
            g.setColor(Color.yellow);
            g.fillOval(xArray[i], yArray[i], 2,2);
        }
        
        
        //drawing derivative function
        xArrayD = new int [(int)(maxA-minA)*(int)(1/increment)];
        yArrayD = new int [(int)(maxA-minA)*(int)(1/increment)];
        int indexD = 0;
        for (double x = minA; x < maxA; x += increment){
            xArrayD[indexD] = getScreenX(x);
            if (derivative.getY(x) > maxB){
            }
            else{
            yArrayD[indexD] = getScreenY(derivative.getY(x));
            }
            indexD++;
        }
        
        for (int i = 0; i < xArrayD.length-1; i ++){
            g.setColor(Color.red);
            g.fillOval(xArrayD[i], yArrayD[i], 2,2);
        }
        
        
        
        
        
        
        
        return bufferedImage;
    }
    
    
    
    
    
    //Sets up the JFrame screen
    public void initializeWindow() {
        setTitle("Derivative Graph");
        setSize(height, width);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.black);
        setVisible(true); //calls paint() for the first time
    }
    
    
    public int getScreenX(double x){
        double pixPerUnitX = width/(maxA-minA);
        double xValueScreen = pixPerUnitX*Math.abs(x-minA);
        return(int)xValueScreen;
    }

    public int getScreenY(double y){
        double pixPerUnitY = height/(maxB-minB);
        double yValueScreen = pixPerUnitY*Math.abs(y-maxB);
        return(int)yValueScreen;

    }
    
    
    
    
    
    
}
