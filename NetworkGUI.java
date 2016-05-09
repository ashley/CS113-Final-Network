import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class NetworkGUI extends JFrame implements ActionListener{

   private final int WINDOW_WIDTH = 605;
   private final int WINDOW_HEIGHT = 685;
   private JButton [] panels = new JButton[36];
   private JButton [] puzzles = new JButton[3];
   private JButton help, answer, reset;
   private JLabel time;
   private NetworkGameModel playGame; 
   private JLabel intro,levelTitle;   
   
   public NetworkGUI(){
   
      //Default game syntax
      setTitle("Network Game");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      Border customBorder = new LineBorder(Color.WHITE, 8);
      
      //Instructions on the top
      JLabel intro = new JLabel("Complete the circuit by clicking on the grid.");
      intro.setFont(new Font ("Helvetica", Font.PLAIN, 14));
      add(intro,BorderLayout.NORTH);
      
      
      //Panel for option buttons
      Panel pOptions = new Panel();
            
      //help label
      help = new JButton("Help");
      help.setFont(new Font("Helvetica", Font.BOLD, 12));
      help.setForeground(new Color(87,151,188)); //dark blue
      help.addActionListener(this);
      pOptions.add(help);
      
      //answer label
      answer = new JButton("Answer");
      answer.setFont(new Font("Helvetica", Font.BOLD, 12));
      answer.setForeground(new Color(87,151,188));
      answer.addActionListener(this);
      pOptions.add(answer);
      
      //reset label
      reset = new JButton("New Game");
      reset.setFont(new Font("Helvetica", Font.BOLD, 12));
      reset.setForeground(Color.GRAY);
      pOptions.add(reset);
      
      add(pOptions,BorderLayout.SOUTH);
            
      //initialize game model
      playGame = new NetworkGameModel();
      playGame.setNewGame(); //intialize answer grid
      
      //Grid for the Game
      Panel pGrid = new Panel();
      pGrid.setLayout(new GridLayout(playGame.getCols(),playGame.getRows()));
      for(int i=0;i<36;i++){
         playGame.setOrient(playGame.reset(i),i); //set Orientation and reset orientation grid
         panels[i] = new JButton ();
         panels[i].setIcon(playGame.get(playGame.getOrient(i)));
         panels[i].setOpaque(true);//blue
         panels[i].addActionListener(this);
         pGrid.add(panels[i]);
      }
      add (pGrid,BorderLayout.CENTER);
     
     
      setVisible(true);
      
   }//public
   
   
   
   public void actionPerformed(ActionEvent ae){
      JButton source = (JButton)ae.getSource(); 
      if (source == answer){ //Answer pressed
         System.out.println("answer");
         Object[] options = { "OK", "CANCEL" }; //Pop-up window
         int choice = JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                       null, options, options[0]);
         if(choice == 0){//When OK is pressed
            playGame.setAnswer();
            for(int i=0;i<36;i++){    
               panels[i].setIcon(playGame.get(playGame.getOrient(i))); 
               panels[i].removeActionListener(this);  
            } 
         }
      }
      else if (source == help){//When Help is pressed
         JOptionPane.showMessageDialog(null, "Click on a cell to rotate the node.", "Help", JOptionPane.INFORMATION_MESSAGE);
      }
      else if (source == reset){//When new game is pressed
         System.out.println("reset");
         playGame.setNewGame(); //New answers
         playGame.resetOrient();
         for(int i=0;i<36;i++){//resets each panel
            playGame.setOrient(playGame.reset(i),i);    
            panels[i].setIcon(playGame.get(playGame.getOrient(i)));
            panels[i].setBackground(new Color(185,228,246));
            panels[i].setBorderPainted(true);
            panels[i].addActionListener(this);
         }
         //Cleaning bugs from multiple action listeners on option buttons
         answer.addActionListener(this);
         answer.setForeground(new Color(87,151,188));
         reset.removeActionListener(this);
         reset.setForeground(Color.GRAY); 
         help.addActionListener(this);
         help.setForeground(new Color(87,151,188));
      }
      else{ //panel is pressed
         int i=0;
         while( source != panels[i]){
            i++; 
         }
         playGame.takeTurn(i); // rotates
         panels[i].setIcon(playGame.get(playGame.getOrient(i)));
      } 
      if (playGame.gameOverStatus()){//checks if panels are correctly rotated
         help.removeActionListener(this);
         help.setForeground(Color.GRAY); 
         reset.addActionListener(this);
         answer.setForeground(Color.GRAY);
         answer.removeActionListener(this);
         reset.setForeground(new Color(87,151,188));
         for(int x=0; x<36; x++){
            panels[x].setBackground(Color.GREEN);
            panels[x].setBorderPainted(false);
         }      
      }
   }//actionPerformed
   
   
    
}//class