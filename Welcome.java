import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Welcome extends JPanel {
   private static final String INTRO = "intro";
   private static final String USED_BEFORE = "used before";
   private JLabel countDownLabel = new JLabel("");
   private HurdlerTimer hurdle;
      JPanel introSouthPanel = new JPanel();
      

   public Welcome() {
   
      introSouthPanel.add(new JLabel("Time:"));
      add(countDownLabel);

      hurdle = new HurdlerTimer();
      hurdle.start();
      System.out.println(hurdle.getStatus());
   }


   public void setCountDownLabelText(String text) {
      countDownLabel.setText(text);
   }

   public void showNextPanel() {
   }
}

class HurdlerTimer {
   private static final int TIMER_PERIOD = 1000;
   protected static final int MAX_COUNT = 10;
   private Welcome welcome; // holds a reference to the Welcome class
   private int count;
   private boolean timeStatus = true;
   
   public HurdlerTimer(){}

   public HurdlerTimer(Welcome welcome) {
      this.welcome = welcome; // initializes the reference to the Welcome class.
      String text = "(" + (MAX_COUNT - count) + ") seconds left";
      welcome.setCountDownLabelText(text);
   }

   public void start() {
      new Timer(TIMER_PERIOD, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (count < MAX_COUNT) {
               count++;
               String text = "(" + (MAX_COUNT - count) + ") seconds left";
               welcome.setCountDownLabelText(text); // uses the reference to Welcome
            } else {
               ((Timer) e.getSource()).stop();
               welcome.showNextPanel();
               timeStatus = false;
            }
         }
      }).start();
   }
   
   public boolean getStatus(){
      return timeStatus;
   }

}