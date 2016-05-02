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
   private Random r = new Random();
   private ImageIcon [] images = new ImageIcon[SIZE];
   private String [] panelType = {"node", "node", "node","tee","straight","node",
                                  "tee","corner","node","tee","tee","corner",
                                  "straight","node","node","tee","corner","straight",
                                  "corner","tee","straight","tee","node","corner",
                                  "node","straight","tee","tee","tee","node",
                                  "node", "straight","tee","node","corner","node"
                                  };
   private int [] panelAnswer = {2, 2, 3, 2, 1, 1, 3, 1, 3, 4, 2, 2, 2, 2, 3, 2, 1, 2, 4, 4, 1, 1, 3, 1, 3, 1, 2, 4, 2, 1, 3, 1, 4, 1, 4, 1};
   private int [] panelOrient = new int [36];

   NetworkGameModel(){
      //panelOrient[0] = 2;
      //panelOrient[1] = 1;
   }//initate

   void takeTurn(int i){
   }//check

   boolean gameOverStatus(){
      return false;
   }//gameOverStatus
       
   ImageIcon get(int i){
      int num = getOrient(i);
      String type = getType(i);
      return(resizeImage(num,type));
   }//ImageIcon

   ImageIcon resizeImage(int num, String type){
      Toolkit kit = Toolkit.getDefaultToolkit();
      Image img = kit.getImage("pieces/"+ type + num + ".png");
      img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
      return (new ImageIcon(img));
   }

   int rotateNum(int i,String type){
      int num;
      if (!type.equals("straight")){      
         if(i < 4){
            num = i+1;
         }
         else{
             i = 1;
            num = 1;
         }
      }
      else{
         if(i == 1){
            num = 2;
         }
         else{
            num = 1;
         }
      }
      return num;
   }
   
   void setOrient(int orientationNum, int arrNum){
      panelOrient[arrNum] = orientationNum;
   }
   
   boolean checkStatus(){
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
      if(i == -1){
       System.out.println("Orient: " + Arrays.toString(panelOrient));
       return 0;
      }
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
      return "Weiner!";
   }//reportWinner
   
   void setAnswer(){
      panelOrient = panelAnswer;
   }
   
   void resetOrient(){
      panelOrient = new int[36];
   }
   
   int reset(int i){
         int num = 0;
         int high, low = 1;
         if(panelType[i].equals("straight")){
            high = 2;
         }
         else{
            high = 4;
         }
         num = r.nextInt(high-low) + low;
         return num;
   }
   

}