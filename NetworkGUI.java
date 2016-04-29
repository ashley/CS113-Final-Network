import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class NetworkGUI extends JFrame implements ActionListener{


   private final int WINDOW_WIDTH = 600;
   private final int WINDOW_HEIGHT = 800;
   private int size = 4;
   //private JButton panels[17];
   private JButton [] panels = new JButton[size];
   private JButton help, answer, reset;
   private JLabel time;
   private NetworkGameModel playGame; 
   private JLabel intro;   
   
   public NetworkGUI(){
   
      setTitle("Network Game");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      Border customBorder = new LineBorder(Color.WHITE, 8);
   
      JLabel intro = new JLabel("Intro");
      intro.setFont(new Font ("Comic Sans MS", Font.BOLD, 14));
      intro.setForeground(new Color(152,116,165)); //dark pink
      add(intro,BorderLayout.NORTH);
            
      Panel pOptions = new Panel();
      
      //help label
      help = new JButton("Help");
      help.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      help.setForeground(new Color(87,151,188)); //dark blue
      pOptions.add(help);
      
      //answer label
      answer = new JButton("Answer");
      answer.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      answer.setForeground(new Color(87,151,188)); //dark blue
      pOptions.add(answer);
      
      //reset label
      reset = new JButton("Reset");
      reset.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      reset.setForeground(new Color(87,151,188)); //dark blue
      pOptions.add(reset);
   
      //time Label
      time = new JLabel("Time");
      time.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
      time.setForeground(new Color(238,200,239)); //blue
      pOptions.add(time);
       
         
      add (pOptions,BorderLayout.SOUTH);
   
      Panel pGrid = new Panel();
      
      playGame = new NetworkGameModel();
      
      pGrid.setLayout(new GridLayout(playGame.getCols(),playGame.getRows()));
      for(int i=0;i<size;i++){
         String panelNum = i+1+"";
         panels[i] = new JButton (playGame.get(i));
         panels[i].setIcon(playGame.get(i));
         panels[i].setBackground(new Color(185,228,246)); //blue
         panels[i].setForeground(new Color(87,151,188)); //dark blue
         panels[i].setFont(new Font ("Comic Sans MS",Font.BOLD, 32));
         //panels[i].setBorder(customBorder);
         //set font
         panels[i].addActionListener(this);
         pGrid.add(panels[i]);
      }
   
      add (pGrid,BorderLayout.CENTER);
      
   
      setVisible(true);
      
   }//public
   
   
   public void actionPerformed(ActionEvent ae){
      JButton source = (JButton)ae.getSource(); 
      if (source != reset){ //check if the button is the grid or the other buttons
         int i=0;
         while( source != panels[i]){
            i++; 
         }
         panels[i].setIcon(playGame.makePath(i));
         System.out.println("in GUI" + playGame.getOrient(i));
         System.out.println("Test");
      } 
   }//actionPerformed
   
   
    
}//class