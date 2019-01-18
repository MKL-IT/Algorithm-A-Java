import javax.swing.*;
import java.awt.*;

class ControlPanel extends JPanel{

    JRadioButton b1;
    JRadioButton b2;

    static int cX;
    static int cY;
   

    ControlPanel(int x, int y){

     	this.cX = x;
    	this.cY = y;
	
		this.setSize(cX, 40);
		this.setLocation(0, cY-40);
		
		b1 = new JRadioButton("Obstacle");
		b2 = new JRadioButton("Depart");
		ButtonGroup group = new ButtonGroup();

		setBackground(Color.black);

		group.add(b1);
		group.add(b2);

		this.add(b1);
		this.add(b2);

    }



}