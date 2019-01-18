import javax.swing.*;
import java.awt.*;
import java.util.*;

public class A1 {

	static final int X = 15*50;  //entre 2 et 26
	static final int Y = 13*50+40; // entre 2 et 14


    public static void main(String[] args) {

	JFrame frame = new JFrame();

	frame.setSize(X, Y);
	frame.setLocation(0, 0);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(null);

	ControlPanel control= new ControlPanel(X, Y);
	JPanel main = new MainPanel(control, X, Y);

	frame.add(control);
	frame.add(main);

	frame.setResizable(false);
	frame.setVisible(true);

    }  
}