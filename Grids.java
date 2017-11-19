import java.awt.*;
import javax.swing.*;

// For drawing Maze and path from start to end


public class Grids extends JFrame {
    int height;
    int width;
    String data[];
    String path[];
    int xs,ys;

    public Grids( int height, int width, String data[],String path)    {
        this.height=height;
        this.width=width;
        this.data=data;
        this.path=path.split(" ");

        setSize( width*130, height*110 );
        setVisible( true );

    }
    public void paint( Graphics g )
    {
        int count=0,a=0,b=0;

        g.setColor(new Color(00));
        for ( int x = 100; x < 30*height+100; x += 30 )
            for ( int y = 100; y < 30*width+100; y += 30 )
            {
                int temp=Integer.parseInt(data[count++]);
                g.drawRect( y, x, 30, 30 );
                g.drawString(temp+"",y+10,x+25);

                if(temp>=16 && temp<32)
                {
                    a=x;
                    b=y;
                }
            }

            g.setColor(new Color(234567));
            g.fillRect(b,a,30,30);



        for (int i = 0; i <path.length ; i++) {

            g.setColor(new Color(45567));
            if(path[i].equals("'up',"))
                a=a-30;
            else if(path[i].equals("'down',"))
                a=a+30;
            else if(path[i].equals("'left',"))
             b=b-30;
            else if(path[i].equals("'right',"))
                b=b+30;

            g.fillRect(b+6,a+6,20,20);
        }


        g.setColor(new Color(14581379));
        g.fillRect(b,a,30,30);


          count=0;
        g.setColor(new Color(00));
        int x=0,y=0;
        for (  x = 100; x < 30*height+100; x += 30 )
            for (  y = 100; y < 30*width+100; y += 30 )
                g.drawString(data[count++]+"",y+10,x+25);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("[ "+path[0],100,x+=30);

        y=100;

        for (int i = 1; i<path.length  ; i++) {
            if(i%25==0)
            {
                y=50;
                x=x+30;
            }
            g.drawString(path[i],y+=55,x);
        }
        g.drawString("]",y+=100,x);
    }

}