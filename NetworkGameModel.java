import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

class NetworkGameModel extends GameModel{
   private int col = 6;
   private int row = 6;
   public int SIZE = 36;
   private int level = 0;
   private Random r = new Random();
   private ImageIcon [] images = new ImageIcon[SIZE];
   private String [] panelType = {"","corner1","corner2","corner3","corner4",
                                  "node1", "node2","node3","node4",
                                  "tee1", "tee2", "tee3", "tee4",
                                  "straight1", "straight2"};
   private int [] panelAnswer = new int [36];
   private int [] panelOrient = new int [36];

   NetworkGameModel(){
   }//initate

   void takeTurn(int i){
    panelOrient[i] = rotateNum(getOrient(i)); // set new rotation
   }//check
       
   ImageIcon get(int i){ 
      String name = getType(i);
      return(resizeImage(name));
   }//ImageIcon

   ImageIcon resizeImage(String name){
      Toolkit kit = Toolkit.getDefaultToolkit();
      Image img = kit.getImage("pieces/"+ name + ".png");
      img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
      return (new ImageIcon(img));
   }

   int rotateNum(int i){ //rotate based on number
    int num = 0;
    if (i < 4 ){
      num = i + 1;
    } 
    else if (i > 4 && i < 8){
      num = i + 1;
    }
    else if(i > 8 && i < 12){
      num = i + 1;
    }
    else if(i > 12 && i < 14){
      num = i + 1;
    }
    else if(i == 4 || i == 8 || i == 12 ){
      num = i - 3;
    }
    else if(i == 14){
      num = 13;
    }
    return num;
   }
   
   void setOrient(int orientationNum, int arrNum){
      panelOrient[arrNum] = orientationNum;
   }
   
   boolean gameOverStatus(){
       //debug comment
       //System.out.println(" ");
       //System.out.println("Orient: " + Arrays.toString(panelOrient));
       //System.out.println("Asnwer: " + Arrays.toString(panelAnswer));
      if (Arrays.toString(panelAnswer).equals(Arrays.toString(panelOrient))){
         return true;
      }
      else{
         return false;
      }
   }

   int getOrient(int i){
      return panelOrient[i];
   }

   int getAnswer(int i){
      return panelAnswer[i];
   }

   String getType(int i){
      return panelType[i];
   }
   
   int getRows(){
      return row;
   }//getRows

   int getCols(){
      return col;
   }//getCols 

   void display(){}  

   String reportWinner(){
      return "You've Won!";
   }//reportWinner
   
   void setAnswer(){
      panelOrient = panelAnswer;
   }
   
   void resetOrient(){
      panelOrient = new int[36];
   }
   
   int reset(int i){
         int num = 0;
         int high, low;
         if(panelAnswer[i] <= 4){
            high = 4;
            low = 1;
         }
         else if(panelAnswer[i] >=5 && panelAnswer[i] <= 8){
          high = 8;
          low = 5;
         }
         else if(panelAnswer[i] >= 9 && panelAnswer[i] <= 12){
          high = 12;
          low = 9;
         }
         else{
            high = 14;
            low = 13;
         }
         num = r.nextInt(high-low) + low;
         panelOrient[i] = num;
         return num;
   }
   
   int [] getOrientArray(){
   return panelOrient;
   }
   
   void setNewGame(){ //where all the answers are
      if (level < 2){
         level += 1;
      }
      else{
         level = 0;
      }
      switch (level){
         case 0:
            int [] newPanel1 = {6,6,6,6,7,2,11,9,14,11,13,9,8,11,12,9,7,9,3,1,3,9,3,9,8,6,14,14,14,8,7,12,1,8,4,5};
            panelAnswer = newPanel1;
            break;
         case 1:
            int [] newPanel2 = {6,6,3,5,6,6,11,9,14,3,12,9,8,4,12,9,7,1,7,10,5,11,10,5,3,12,10,9,11,5,4,5,8,8,4,5};
            panelAnswer = newPanel2;
            break;
         case 2:
            int [] newPanel3 = {6,3,5,3,5,6,4,9,7,9,6,14,3,1,6,14,6,1,4,13,12,9,11,2,7,10,13,12,9,8,7,12,13,5,4,5};
            panelAnswer = newPanel3;
            break;
            
      }
   }
   

}