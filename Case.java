import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Case {

    Coord co;
    int cout; //distance minimum pour arriver au depart
    int estimation;   //bonne case = cout + heuristique
    private static final int infinity = 1000000000;

    Case(Coord c){ //initialisation

        this.co = c;
        this.cout = infinity;
        this.estimation = infinity;
    }
    
    Case(Coord c, Coord arrive){ //initialisation depart

       this.co = c; 
	   this.estimation = heuristique(this.co, arrive);
       this.cout = 0;
    }

    Case(Coord c, Coord depart, Coord arrive){ 
        this.co = c;
        this.cout = heuristique(this.co, depart);
        this.estimation = this.cout + heuristique(this.co, arrive);
    }

    public Coord getCo(){
	return this.co;
    }

    public int getCout(){
	return this.cout;
    } 

    public int getEstimation(){
	return this.estimation;
    }

    public void setCo(Coord c){
	this.co = c;
    }

    public void setCout(int c){
	this.cout = c;
    }

    public void setEstimation(int e){
	this.estimation = e;
    }


    public int heuristique(Coord a, Coord b){ // estimer distance entre case courante et l'arrivée
	
	int x = Math.abs( a.getCx() - b.getCx());
	int y = Math.abs( a.getCy() - b.getCy());
	
	return x+y;

    }


    public void modifierCase(Coord depart, Coord arrive){ 

        this.cout = heuristique(this.co, depart);
        this.estimation = this.cout + heuristique(this.co, arrive);
    }    
    

}