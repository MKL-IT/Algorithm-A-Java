import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Coord {

    private int cx;
    private int cy;

    Coord(){
        
    }

    Coord(int x, int y){
	cx = x;
	cy = y;
    }

    public int getCx(){
	return this.cx;

    } 

    public int getCy(){
	return this.cy;

    }

   public void setCx(int x){
	this.cx = x;

    }
    public void setCy(int y){
	this.cy = y;

    }

    public boolean equals(Coord c) {

        return (this.cx == c.cx) && (this.cy == c.cy);
    }


    public int hashCode() {

        return cx*1000 + cy;

    }

}
 