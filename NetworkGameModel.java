import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.Arrays;

class NetworkGameModel extends GameModel{
   public int SIZE = 4;
   private ImageIcon [] images = new ImageIcon[SIZE];
   private String [] panelType = {"corner", "corner", "corner","corner"};
   private int [] panelAnswer = {2,1,3,4};
   private int [] panelOrient = {1,1,1,1};

   NetworkGameModel(){
      //panelOrient[0] = 2;
      //panelOrient[1] = 1;
   }//initate

   void takeTurn(int i){
   }//check

   ImageIcon makePath(int i){
      int num = rotateNum(getOrient(i));
      String type = getType(i);
      return (new ImageIcon("pieces/" + type + num + ".png"));
   }

   boolean gameOverStatus(){
      return false;
   }//gameOverStatus
       
   ImageIcon get(int i){
      int num = getOrient(i);
      String type = getType(i);
      return(new ImageIcon("pieces/"+ type + num + ".png"));
   }//ImageIcon

   int rotateNum(int i){
      int num;
      if(i < 4){
         num = i+1;
      }
      else{
          i = 1;
         num = 1;
      }
      return num;
   }
   
   void setOrient(int orientationNum, int arrNum){
      panelOrient[arrNum] = orientationNum;
   }
   
   boolean checkStatus(){
       System.out.println("Orient: " + Arrays.toString(panelOrient));
        System.out.println("Asnwer: " + Arrays.toString(panelAnswer));
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
      return SIZE/2;
   }//getRows

   int getCols(){
      return SIZE/2;
   }//getCols 

   void display(){}  

   String reportWinner(){
      return "Weiner!";
   }//reportWinner




}