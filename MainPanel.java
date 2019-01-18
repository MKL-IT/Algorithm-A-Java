import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


class MainPanel extends JPanel implements MouseListener, MouseMotionListener{


    public int tab[][];
 
    Coord caseDepart;
    Coord caseArrive;
    ControlPanel cp;
    ArrayList<Coord> path = new ArrayList<Coord>();

    static int mX;
    static int mY;

    


    
    MainPanel(ControlPanel cp, int x, int y){

    	this.mX = x;
    	this.mY = y-40;
		this.cp = cp;
		this.caseDepart = null;
		this.caseArrive = new Coord(-1, -1);
    	this.tab = new int[mX/50][mY/50];
		
		for(int i=0; i< mX/50; i++){
		    for(int j=0; j<mY/50; j++){

			int valeur;

			Random r = new Random();

			if(i%2 == 0 || j%6 == 0)
			valeur = r.nextInt(1);
			
			else
			valeur = r.nextInt(2);

			tab[i][j]=valeur;
		    }
		}

		
		this.setSize(mX, mY);
		this.setLocation(0, 0);
		
		addMouseListener(this);
		addMouseMotionListener(this);
    }





    public String algo(){

    	if(path.size() > 0){
			path.clear();
		}

	    Case cases[][] = new Case[mX/50][mY/50];

	    Queue<Case> openList = new PriorityQueue<Case>((mX/50)*(mY/50), new Comparator<Case>(){

		    public int compare(Case a, Case b) {
		        return  a.estimation - b.estimation;
		    }	    	
	    });

		boolean visited[][] = new boolean[mX/50][mY/50];
		HashMap<Coord, Coord> mapPath = new HashMap<Coord, Coord>();

		for(int i=0; i<mX/50; i++){
		    for(int j=0; j<mY/50; j++){

				if(i == caseDepart.getCx() && j == caseDepart.getCy() ){

					cases[i][j] = new Case(new Coord(i, j), caseArrive);
					openList.add(cases[i][j]);		 		    
			
				}else{

			    	cases[i][j] = new Case(new Coord(i, j) );

				}    
				visited[i][j]=false; 


		    }
	 	}


	 	while (openList.size() != 0){

	 		Case move = openList.remove();

	 		if(!move.co.equals(caseArrive) ){

	 			visited[move.co.getCx()][move.co.getCy()] = true;

	 			//case du dessus

	 			if(move.co.getCx() >= 0 && move.co.getCx() < mX/50 && move.co.getCy()+1 >= 0 && move.co.getCy()+1 < mY/50){

		 			if(tab[move.co.getCx()][move.co.getCy()+1] == 0 && !visited[move.co.getCx()][move.co.getCy()+1] ){

			 			if(!openList.contains(cases[move.co.getCx()][move.co.getCy()+1]) ){
			 				cases[move.co.getCx()][move.co.getCy()+1].modifierCase(move.co, caseArrive);
			 				openList.add(cases[move.co.getCx()][move.co.getCy()+1]);
			 				mapPath.put(cases[move.co.getCx()][move.co.getCy()+1].co, move.co);

			 			}else{

			 				if(move.cout +1 < cases[move.co.getCx()][move.co.getCy()+1].cout){
			 					cases[move.co.getCx()][move.co.getCy()+1].modifierCase(move.co, caseArrive);
			 					mapPath.put(cases[move.co.getCx()][move.co.getCy()+1].co, move.co);
			 				}
			 			}
		 			}
	 			}

	 			//case du dessous
	 			if(move.co.getCx() >= 0 && move.co.getCx() < mX/50 && move.co.getCy()-1 >= 0 && move.co.getCy()-1 < mY/50){

		 			if(tab[move.co.getCx()][move.co.getCy()-1] == 0 && !visited[move.co.getCx()][move.co.getCy()-1] ){

			 			if(!openList.contains(cases[move.co.getCx()][move.co.getCy()-1]) ){
			 				cases[move.co.getCx()][move.co.getCy()-1].modifierCase(move.co, caseArrive);
			 				openList.add(cases[move.co.getCx()][move.co.getCy()-1]);
			 				mapPath.put(cases[move.co.getCx()][move.co.getCy()-1].co, move.co);  

			 			}else{

			 				if(move.cout +1 < cases[move.co.getCx()][move.co.getCy()-1].cout){
			 					cases[move.co.getCx()][move.co.getCy()-1].modifierCase(move.co, caseArrive);
			 					mapPath.put(cases[move.co.getCx()][move.co.getCy()-1].co, move.co);
			 				}
			 			}
		 			}
	 			}

	 			//case de gauche
	 			if(move.co.getCx()-1 >= 0 && move.co.getCx()-1 < mX/50 && move.co.getCy() >= 0 && move.co.getCy() < mY/50){

		 			if(tab[move.co.getCx()-1][move.co.getCy()] == 0 && !visited[move.co.getCx()-1][move.co.getCy()] ){

			 			if(!openList.contains(cases[move.co.getCx()-1][move.co.getCy()]) ){
			 				cases[move.co.getCx()-1][move.co.getCy()].modifierCase(move.co, caseArrive);
			 				openList.add(cases[move.co.getCx()-1][move.co.getCy()]);
			 				mapPath.put(cases[move.co.getCx()-1][move.co.getCy()].co, move.co);

			 			}else{

			 				if(move.cout +1 < cases[move.co.getCx()-1][move.co.getCy()].cout){
			 					cases[move.co.getCx()-1][move.co.getCy()].modifierCase(move.co, caseArrive);
			 					mapPath.put(cases[move.co.getCx()-1][move.co.getCy()].co, move.co);
			 				}
			 			}
		 			}
	 			} 			

	 			//case de droite
	 			if(move.co.getCx()+1 >= 0 && move.co.getCx()+1 < mX/50 && move.co.getCy() >= 0 && move.co.getCy() < mY/50){

		 			if(tab[move.co.getCx()+1][move.co.getCy()] == 0 && !visited[move.co.getCx()+1][move.co.getCy()] ){

			 			if(!openList.contains(cases[move.co.getCx()+1][move.co.getCy()]) ){
			 				cases[move.co.getCx()+1][move.co.getCy()].modifierCase(move.co, caseArrive);
			 				openList.add(cases[move.co.getCx()+1][move.co.getCy()]);
			 				mapPath.put(cases[move.co.getCx()+1][move.co.getCy()].co, move.co);

			 			}else{

			 				if(move.cout +1 < cases[move.co.getCx()+1][move.co.getCy()].cout){
			 					cases[move.co.getCx()+1][move.co.getCy()].modifierCase(move.co, caseArrive);
			 					mapPath.put(cases[move.co.getCx()+1][move.co.getCy()].co, move.co);
			 				}
			 			}
		 			}
	 			}

	 		}else{

	 			Coord tmp = new Coord();
	 			tmp = mapPath.get(cases[caseArrive.getCx()][caseArrive.getCy()].co );
	 			//System.out.println("tmp arrive x= " + tmp.getCx() + "y= " + tmp.getCy());

	 			this.path.add(tmp);

	 			int k = 1;

	 			while(!tmp.equals(caseDepart) ){

	 				tmp = mapPath.get(tmp);
	 				//System.out.println("tmp x= " + tmp.getCx() + "y= " + tmp.getCy());
	 				path.add(tmp);
	 				k++;
	 			}

	 			if(k > 1)
	 				return "Le plus court chemin estimé mesure " + k + " cases.";

	 			return "Le plus court chemin estimé mesure " + k + " case.";


	 		}
	 	}

	 	return "Il n'y a pas de solution.";
	
    }


    public void ajouterArrive(int x, int y){

		if(tab[x][y] == 0 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50  ){
		    tab[x][y]=3; 
		    repaint();
		}
    }

    

    public void ajouterObstacle(int x, int y){
	
		if(tab[x][y] == 0 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50 && cp.b1.isSelected() ){
		    tab[x][y]=1; 
		    repaint();
		}
    }
    
    public void supprimerObstacle(int x, int y ){
	
	if(tab[x][y] == 1 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50 && cp.b1.isSelected() ){
	    tab[x][y]=0; 


	    repaint();
	}
    }

    public void ajouterDepart(int x, int y){
	
	if(tab[x][y] == 0 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50 && cp.b2.isSelected() ){	    
	    tab[x][y]=2;
	    caseDepart = new Coord(x, y);
	    repaint();    
	}
	
    }

  public void supprimerDepart(int x, int y ){
	
	if(tab[x][y] == 2 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50 && cp.b2.isSelected() ){
	    tab[x][y]=0; 
	    if(path.size() > 0){
			path.clear();
		}
	    repaint();
	}
    }


    public boolean depart(){

	for(int i=0; i<mX/50 ;i++){	    
	    for(int j=0; j<mY/50; j++){

		if(tab[i][j] == 2)
		    return true;	
	    }	
	}
	return false;
    }
    



	public void paintComponent(Graphics g){

		super.paintComponent(g);
		this.removeAll();
	    

		if(caseArrive.getCx() != -1 && caseArrive.getCy() != -1){ //arrive
			g.setColor(Color.red);
			g.fillRect (caseArrive.getCx()*50, caseArrive.getCy()*50, 50, 50);
		}

		if(path.size() > 0){

			for(Coord c :path){

				if(!c.equals(caseDepart)){
					g.setColor(Color.red);
					//g.fillRect (c.getCx()*50, c.getCy()*50, 50, 50);
					g.drawLine(c.getCx()*50 , c.getCy()*50, c.getCx()*50+50, c.getCy()*50+50);
					g.drawLine(c.getCx()*50 , c.getCy()*50+50, c.getCx()*50+50, c.getCy()*50);
				}
			}
		}


		for(int i=0; i<mX/50 ;i++){
		    
		    for(int j=0; j<mY/50; j++){

				if(tab[i][j] == 0){ //vide
				    g.setColor(Color.gray.darker());
				    g.drawRect (i*50, j*50, 50, 50);
				}

				if(tab[i][j] == 1){ //obstacle
				    g.setColor(Color.gray.darker());		    
				    g.fillRect (i*50, j*50, 50, 50);
				}

				if(tab[i][j] == 2){ //depart
				    g.setColor(Color.green);
				    g.fillRect (i*50, j*50, 50, 50);
				    //System.out.println(tab[i][j]);
				}	
		    }
		}
	}


 	public void mouseClicked(MouseEvent e){

		int buttonDown = e.getButton();	

		int x = e.getX()/50;
		int y = e.getY()/50;
		
		if(buttonDown == MouseEvent.BUTTON1 && !depart() ){ //gauche
		    ajouterDepart(x, y);
		   
		}

		if(buttonDown == MouseEvent.BUTTON3){ //droit
		    supprimerDepart(x, y);
		}

    }

    public void mousePressed(MouseEvent e){

		int buttonDown = e.getButton();	

		int x = e.getX()/50;
		int y = e.getY()/50;
	
		if(buttonDown == MouseEvent.BUTTON1){ //gauche
		    ajouterObstacle(x, y);	   
		}

		if(buttonDown == MouseEvent.BUTTON3){ //droit
		    supprimerObstacle(x, y);
		}
    }

    public void mouseReleased(MouseEvent e){
	
    }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e){
	
    }
    public void mouseDragged(MouseEvent e){
	
    }

    public void mouseMoved(MouseEvent e){


    	Coord tmp = new Coord(caseArrive.getCx(), caseArrive.getCy());
    	caseArrive.setCx(-1);
    	caseArrive.setCy(-1);

    	if(cp.b1.isSelected() && path.size() > 0){
    		path.clear();
    	}

		if( cp.b2.isSelected() && depart() ){

			int buttonDown = e.getButton();	
			int x = e.getX()/50;
			int y = e.getY()/50;

			if(tab[x][y] == 0 && x >= 0 && x < mX/50 && y >= 0 && y < mY/50  ){
				caseArrive = new Coord(x, y);
				//repaint();

				if(!tmp.equals(caseArrive)){
					System.out.println(algo());
				}
	    	}

		}
		repaint();
    }

}