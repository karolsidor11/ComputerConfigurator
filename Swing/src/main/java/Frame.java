import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Frame extends JApplet
{
    public void init()
    {
        setSize(400,400);
        setName("Zegar analogowy");
        javax.swing.Timer timer = new javax.swing.Timer(0,new ZegarTask());
        timer.setDelay(1000);
        timer.start();
    }
    //---------------------------
    public void paint(Graphics g)
    {
        g.setColor(new Color(232, 172, 183));
        g.fillRect(0,0,400,400);
        g.setColor(new Color(208,207,204));
        g.fillOval(0,0,400,400);
        g.setColor(Color.red);
        double angle;
        int x1,y1,x2,y2;
        for(int i=0;i<12;i++)
        {
            angle = 2*Math.PI*(i/12.0);
            x1 = (int)(200*Math.cos(angle))+200;
            y1 = (int)(200*Math.sin(angle))+200;
            x2 = (int)(180*Math.cos(angle))+200;
            y2 = (int)(180*Math.sin(angle))+200;
            g.drawLine(x1,y1,x2,y2);
        }
        for(int i=0;i<60;i++)
        {
            angle = 2*Math.PI*(i/60.0);
            x1 = (int)(200*Math.cos(angle))+200;
            y1 = (int)(200*Math.sin(angle))+200;
            x2 = (int)(190*Math.cos(angle))+200;
            y2 = (int)(190*Math.sin(angle))+200;
            g.drawLine(x1,y1,x2,y2);
        }
        GregorianCalendar date = new GregorianCalendar();
        int hours = date.get(Calendar.HOUR_OF_DAY)%12;
        int minutes = date.get(Calendar.MINUTE);
        int seconds = date.get(Calendar.SECOND);
        angle = 2*Math.PI*(seconds/60.0)-(Math.PI/2);
        x1 = (int)(160*Math.cos(angle));
        y1 = (int)(160*Math.sin(angle));
        g.drawLine(200,200,200+x1,200+y1);
        angle = 2*Math.PI*((minutes*60+seconds)/3600.0)-(Math.PI/2);
        x1 = (int)(140*Math.cos(angle));
        y1 = (int)(140*Math.sin(angle));
        g.drawLine(200,200,200+x1,200+y1);
        angle = 2*Math.PI*((hours*60*60+minutes*60+seconds)/43200.0)-(Math.PI/2);
        x1 = (int)(120*Math.cos(angle));
        y1 = (int)(120*Math.sin(angle));
        g.drawLine(200,200,200+x1,200+y1);
    }
    //------------------------
    class ZegarTask implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            repaint();
        }
    }
}