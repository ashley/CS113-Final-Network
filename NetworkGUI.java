import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkGUI extends JFrame implements ActionListener{


   private final int WINDOW_WIDTH = 605;
   private final int WINDOW_HEIGHT = 685;
   private JButton [] panels = new JButton[36];
   private JButton help, answer, reset;
   private JLabel time;
   private NetworkGameModel playGame; 
   private JLabel intro;   
   private Timer timer = new Timer();
   
   public NetworkGUI(){
   
      setTitle("Network Game");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      Border customBorder = new LineBorder(Color.WHITE, 8);
   
      JLabel intro = new JLabel("Complete the circuit by clicking on the grid.");
      intro.setFont(new Font ("Comic Sans MS", Font.BOLD, 14));
      intro.setForeground(new Color(152,116,165)); //dark pink
      add(intro,BorderLayout.NORTH);
            
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
      answer.setForeground(new Color(87,151,188)); //dark blue
      answer.addActionListener(this);
      pOptions.add(answer);
      
      //reset label
      reset = new JButton("Reset");
      reset.setFont(new Font("Helvetica", Font.BOLD, 12));
      reset.setForeground(new Color(87,151,188)); //dark blue
      reset.addActionListener(this);
      pOptions.add(reset);
   
      //time Label
      time = new JLabel("Time");
      time.setFont(new Font("Helvetica", Font.BOLD, 12));
      time.setForeground(new Color(238,200,239)); //blue
      pOptions.add(time);
        
         
      add (pOptions,BorderLayout.SOUTH);
   
      Panel pGrid = new Panel();
      
      playGame = new NetworkGameModel();
      
      pGrid.setLayout(new GridLayout(playGame.getCols(),playGame.getRows()));
      for(int i=0;i<36;i++){
         playGame.setOrient(playGame.reset(i),i);
         panels[i] = new JButton (playGame.get(i));
         panels[i].setIcon(playGame.get(i));
         panels[i].setBackground(new Color(185,228,246)); 
         panels[i].setOpaque(true);//blue
         panels[i].addActionListener(this);
         pGrid.add(panels[i]);
      }
   
      add (pGrid,BorderLayout.CENTER);
      
   
      setVisible(true);
      
   }//public
   
   
   
   public void actionPerformed(ActionEvent ae){
      JButton source = (JButton)ae.getSource(); 
      if (source == answer){
         Object[] options = { "OK", "CANCEL" };
         int choice = JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                       null, options, options[0]);
         if(choice == 0){
            playGame.setAnswer();
            for(int i=0;i<36;i++){    
               panels[i].setIcon(playGame.get(i));   
            } 
         }
      }
      else if (source == help){
         JOptionPane.showMessageDialog(null, "Click on a cell to rotate the node.", "Help", JOptionPane.INFORMATION_MESSAGE);
      }
      else if (source == reset){
         answer.addActionListener(this);
         help.addActionListener(this);
         playGame.resetOrient();
         for(int i=0;i<36;i++){
            playGame.setOrient(playGame.reset(i),i);    
            panels[i].setIcon(playGame.get(i));
            panels[i].setBackground(new Color(185,228,246));
            panels[i].addActionListener(this);   
            panels[i].setBorderPainted(true);
            //System.out.print(playGame.getOrient(i)+" " + playGame.getAnswer(i));
         } 
      }
      else{ //panel
         int i=0;
         while( source != panels[i]){
            i++; 
         }
         playGame.takeTurn(i);
         panels[i].setIcon(playGame.get(playGame.getOrient(i)));
      } 
      if (playGame.gameOverStatus()){
            for(int x=0; x<36; x++){
               panels[x].setBackground(Color.GREEN);
               panels[x].setBorderPainted(false);
               panels[x].removeActionListener(this);
            }
            JOptionPane.showMessageDialog(null, playGame.reportWinner(), "Winner", JOptionPane.INFORMATION_MESSAGE);
            answer.removeActionListener(this);
            help.removeActionListener(this);
      }
   }//actionPerformed
   
   
    
}//class