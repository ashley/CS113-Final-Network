import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

class NetworkGameModel extends GameModel{
   public int SIZE = 4;
   private ImageIcon [] images = new ImageIcon[SIZE];
   private String [] panelType = {"straight", "corner", "t","straight"};
   private int [] panelAnswer = {1,2,3,4};
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
      return (new ImageIcon("pieces/" + type + num + ".jpeg"));
   }

   boolean gameOverStatus(){
      return false;
   }//gameOverStatus
       
   ImageIcon get(int i){
      int num = getOrient(i);
      String type = getType(i);
      return(new ImageIcon("pieces/"+ type + num + ".jpeg"));
   }//ImageIcon

   int rotateNum(int i){
    System.out.println("Int coming in" + i);
    System.out.println("actual int " + panelOrient[i]);
      int num;
      if(i != 4){
         num = i+1;
      }
      else{
         num = 1;
      }
      System.out.println("Rotate method after if/else " + num);
      setOrient(num, i);
      System.out.println("Rotate method after if/else " + panelOrient[i]);
      return num;
   }
   
   void setOrient(int orientationNum, int arrNum){
      panelOrient[arrNum] = orientationNum;
   }

   int getOrient(int i){
      System.out.println(i + " " + panelOrient[i]);
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