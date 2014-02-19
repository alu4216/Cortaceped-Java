import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class Cortacesped implements ActionListener {
	
	private Panel panel;
	private Cesped C;
	private Timer timer;
	private boolean[][] visitados; 
	private int size; 
	private int a=0;
	private int l=0;
	private int contador=0;
	
	private class Punto
	{
		int x;
		int y;
	}Punto punto;
	
	private class Elemento
	{
		int x;
		int y;
		int costoreal;
		int costoheuristica;
		Elemento padre;
	}

	ArrayList <Punto> recorrido = new ArrayList<Punto>(); // para el DFS
	ArrayList <Elemento> LA = new ArrayList<Elemento>();  //los dos son para el A*
	ArrayList <Elemento> LC = new ArrayList<Elemento>();
	Cortacesped(Panel o,Cesped o1){
		
		panel=o;
		C=o1;
		timer= new Timer(100,this);
		visitados= new boolean [C.getM()][C.getM()];
	}
	public void actionPerformed(ActionEvent e) {
		if(contador<size)
		{
		pintar(contador);
		contador++;
		}
		else 
			timer.stop();
	}
	public void pintar(int i){
		
		Punto punto= new Punto();
		punto=recorrido.get(i);
		C.setcesped('C',a,l);
		a=punto.x;
		l=punto.y;
		C.setcesped('X',a,l);
		
		panel.repaint();
		
	}
	public void iniciar_DFS(){
		Punto punto= new Punto();//añadir posición de salida a la lista para que vuelva a la casilla original
		 //reinicializar para voler a empezar el movimiento
		timer.stop(); 
		for(int i=0;i<C.getM();i++)
			for(int j=0;j<C.getM();j++)
				visitados[i][j]=false;
	    contador=0;a=0;l=0;
	    recorrido.removeAll(recorrido);
	    //medir el tiempo
	    long start = System.nanoTime(); 
	    DFS(0,0); // aqui llama al método que quiere calcular el tiempo de ejecución
	    long end = System.nanoTime(); 
	    long res = end - start;
	    
	    
	    //llamadas a DFS y empezar a mover
	    System.out.println("TIEMPOS Y MOVIMIENTOS DEL DFS ");
	    System.out.println("NanoSegundos: "+res);
	    System.out.println("Movimientos: "+recorrido.size()/2);
	    
	    punto.x=0;
	    punto.y=0;
	    recorrido.add(punto);//posición de salida añadido
	    size=recorrido.size()-1;
		timer.start();//inicia el movimiento en la pantalla	
		
	}
	public void iniciar_A()
	{
	 timer.stop(); 
	 contador=0;
	 recorrido.removeAll(recorrido);
	 LC.removeAll(LC);
	 LA.removeAll(LA);
	 long start = System.nanoTime(); 
	 estrella(); // aqui llama al método que quiere calcular el tiempo de ejecución
	 long end = System.nanoTime(); 
	 long res = end - start;
	 
	 System.out.println("TIEMPOS Y MOVIMIENTOS DE LA A* ");
	 System.out.println("NanoSegundos: "+res);
	 System.out.println("Movimientos: "+recorrido.size());
	
	 size=recorrido.size();
	 Collections.reverse(recorrido);//dar la vuelta a la lista puesto que está al reves el recorrido
	 timer.start();
	}
    public void  DFS( int i, int j){
    	
    	Punto punto = new Punto();
    	punto.x=i;
    	punto.y=j;
    	recorrido.add(punto);
    	recorrido.get(i);
    	visitados[i][j]=true;
    	//condiciones de llamada recursiva
    	//primero prioridad al ir a casillas no visitadas y accesibles regidas por prioridades
    	//PRIORIDAD 1ºarriba 2º derecha 3ºizquierda 4º abajo
    	
    	if(j>0 && C.getcesped(i, j-1)!='O'&& C.getcesped(i, j-1)!='G' && visitados[i][j-1]==false)
    	{
    	  DFS(i,j-1);
    	  punto.x=i;
    	  punto.y=j;
    	  recorrido.add(punto);
    	}
    	if(i<C.getM()-1 && C.getcesped(i+1, j)!='O' &&  C.getcesped(i+1, j)!='G' && visitados[i+1][j]==false )
    	{
    	 DFS(i+1,j);
    	 punto.x=i;
    	 punto.y=j;
    	 recorrido.add(punto);
    	}
    	if(i>0 && C.getcesped(i-1, j)!='O' && C.getcesped(i-1, j)!='G' && visitados[i-1][j]==false )
    	{
    	  DFS(i-1,j);
    	  punto.x=i;
    	  punto.y=j;
    	  recorrido.add(punto);
    	}
    	if(j<C.getM()-1 && C.getcesped(i, j+1)!='O' && C.getcesped(i, j+1)!='G' && visitados[i][j+1]==false )
    	{
    	  DFS(i,j+1);
    	  punto.x=i;
    	  punto.y=j;
    	  recorrido.add(punto);
    	}
    	
    }
    public void estrella(){
    	
    	int ini=0,fin=0;
    	int ini1=0;
    	int fin1=0;
    	a = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el ancho","Datos",JOptionPane.QUESTION_MESSAGE));
    	l = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el  largo","Datos",JOptionPane.QUESTION_MESSAGE));
    	
    	ini1= Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el ancho final","Datos",JOptionPane.QUESTION_MESSAGE));
    	fin1= Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el largo final ","Datos",JOptionPane.QUESTION_MESSAGE));
    	
    	ini=a;// para el panel, mirar funcion de pintar
    	fin=l;// idem que la anterior
    	C.setcesped('C',0,0);
    	C.setcesped('S', ini, fin);
    	C.setcesped('F', ini1, fin1);
    	panel.repaint();
    	
    	Elemento elementoinicial=new Elemento();
    	elementoinicial.x=ini;
    	elementoinicial.y=fin;
    	elementoinicial.padre=null;
    	elementoinicial.costoreal=0;
    	elementoinicial.costoheuristica= funcionHeuristica(ini1,fin1,ini,fin);
    	LA.add(elementoinicial);// condicion del do while, asi me ahorro comprobaciones
    	//LC.add(elementoinicial);
    	Elemento aux =elementoinicial;
    	Elemento aux1 = elementoinicial;
    	
    	do 
    	{	
    		aux=aux1;
    		Elemento elemento_aux; 
    		for(int k=0;k<LA.size();k++)
    		{
    		 elemento_aux=LA.get(k);

    		 if(elemento_aux.x==aux1.x && elemento_aux.y==aux1.y)
    		 {
    		  LC.add(LA.get(k));
    		  LA.remove(k);
    		  break;
    		 }
    		}
    		aux1=sucesores(aux,aux.x,aux.y,ini1,fin1);
    	 if(aux1==null) break;
    	 else  if((aux1.x==ini1 && aux1.y==fin1))
    	 {
    		 LC.add(aux1);
			 break;
    	 }
    	} while(aux1!=null); //mientras no lleguemos a la posicion final o LA con sucesores
    	
    	
    	aux=LC.get(LC.size()-1);
    	while(aux!= null)
    	{ 
    	 Punto punto= new Punto();
    	 punto.x=aux.x;
    	 punto.y=aux.y;
    	 recorrido.add(punto);
    	 aux= aux.padre;
    	}
    	
    }
    private int funcionHeuristica(int ini, int fin,int i , int j) {
		return Math.abs((ini-i)+(fin-j));
	}
    
    private Elemento sucesores(Elemento aux,int i,int j,int ini, int fin){// incluir todos los sucesores del padre a la lista abierta y comprobar 
    
    	 Elemento elemento_aux;
    	 boolean bandera =false;
    	 
    	 if(i+1 < C.getM() && C.getcesped(i+1,j)!= 'O' && C.getcesped(i+1,j)!= 'G') {	
         for(int k = 0 ; k < LC.size() ;k++ ) // comprobacion que no este en la lista cerrada
   	  	 {
        	 elemento_aux=LC.get(k);
        	 if(elemento_aux.x==i+1 && elemento_aux.y==j)
        	 {
        	  bandera=true;
        	  break;
        	 }
   	  	 }
         if(bandera==false)
         {
    	  for( int k = 0 ; k < LA.size() ;k++ ) // comprobacion que no este en la lista abierta
    	  {
    		  elemento_aux=LA.get(k);
    		  if(elemento_aux.x==i+1 && elemento_aux.y==j)// si está, mirar costes
    		  {
    			bandera=true;
    			if((elemento_aux.costoreal +elemento_aux.costoheuristica) < (aux.costoreal+funcionHeuristica(ini,fin,i+1,j)))
    			 {
    			  Elemento elemento= new Elemento();
    		      elemento.x=i+1;
    		      elemento.y=j;
    		      elemento.padre=aux;
    		      elemento.costoreal=elemento.padre.costoreal+1;
    		      elemento.costoheuristica= funcionHeuristica(ini,fin,i+1,j);
    		      LA.set(k,elemento);
    			 }
    			 break;
    		  }
    	  }
    	  if(bandera==false)//si no estaba en la lista abierta, se añade
    	  {
    	   Elemento elemento= new Elemento();
    	   elemento.x=i+1;
		   elemento.y=j;
		   elemento.padre=aux;
		   elemento.costoreal=elemento.padre.costoreal+1;
		   elemento.costoheuristica= funcionHeuristica(ini,fin,i+1,j);
		   LA.add(elemento);
    	  }
    	}
        bandera=false;
      
     }
     if(i-1 >= 0 && C.getcesped(i-1,j)!= 'O' && C.getcesped(i-1,j)!= 'G') {
       for(int k = 0 ; k < LC.size() ;k++ ) // comprobacion que no este en la lista cerrada
       {
        elemento_aux=LC.get(k);
        if(elemento_aux.x==i-1 && elemento_aux.y==j){
          bandera=true;
          break;
        }
       }
       if(bandera==false)
       {
       	for( int k = 0 ; k < LA.size() ;k++ ) // comprobacion que no este en la lista abierta
       	{
         elemento_aux=LA.get(k);
       	if(elemento_aux.x==i-1 && elemento_aux.y==j){// si está, mirar costes
       		bandera=true;
       	  if((elemento_aux.costoreal +elemento_aux.costoheuristica) < (aux.costoreal+funcionHeuristica(ini,fin,i-1,j))){
       		Elemento elemento= new Elemento();
     	    elemento.x=i-1;
       		elemento.y=j;
       		elemento.padre=aux;
       		elemento.costoreal=elemento.padre.costoreal+1;
       		elemento.costoheuristica= funcionHeuristica(ini,fin,i-1,j);
       		LA.set(k,elemento);
       	  }
       	  break;
       	}
       }
       if(bandera==false){//si no estaba en la lista abierta, se añade
    	 Elemento elemento= new Elemento();
    	 elemento.x=i-1;
   		 elemento.y=j;
   		 elemento.padre=aux;
   		 elemento.costoreal=elemento.padre.costoreal+1;
   		 elemento.costoheuristica= funcionHeuristica(ini,fin,i-1,j);
   		 LA.add(elemento);
       }
     }
       bandera=false;
   }
   if(j+1<C.getM() && C.getcesped(i,j+1)!= 'O' && C.getcesped(i,j+1)!= 'G') {
	   for(int k = 0 ; k < LC.size() ;k++ ) // comprobacion que no este en la lista cerrada
       {
        elemento_aux=LC.get(k);
        if(elemento_aux.x==i && elemento_aux.y==j+1){
           bandera=true;
           break;
          }
        }
	   if(bandera==false){
         for( int k = 0 ; k < LA.size() ;k++ ) // comprobacion que no este en la lista abierta
          {
           elemento_aux=LA.get(k);
         	if(elemento_aux.x==i && elemento_aux.y==j+1){// si está, mirar costes
         	  bandera=true;
         	  if((elemento_aux.costoreal +elemento_aux.costoheuristica)> (aux.costoreal+funcionHeuristica(ini,fin,i,j+1))){
         		Elemento elemento= new Elemento();  
       	        elemento.x=i;
         		elemento.y=j+1;
         		elemento.padre=aux;
         		elemento.costoreal=elemento.padre.costoreal+1;
         		elemento.costoheuristica= funcionHeuristica(ini,fin,i,j+1);
         		LA.set(k,elemento);
         	  }
         	  break;
         	}
         }
         if(bandera==false){//si no estaba en la lista abierta, se añade
         Elemento elemento= new Elemento();
      	 elemento.x=i;
         elemento.y=j+1;
         elemento.padre=aux;
         elemento.costoreal=elemento.padre.costoreal+1;
         elemento.costoheuristica= funcionHeuristica(ini,fin,i,j+1);
         LA.add(elemento);
         }
       }
         bandera=false;
     }
   if(j-1>= 0 && C.getcesped(i,j-1)!= 'O' && C.getcesped(i,j-1)!= 'G') {
	for(int k = 0 ; k < LC.size() ;k++ ) // comprobacion que no este en la lista cerrada
    {
     elemento_aux=LC.get(k);
     if(elemento_aux.x==i && elemento_aux.y==j-1){
        bandera=true;
        break;
     }
    }
	if(bandera==false){
      for( int k = 0 ; k < LA.size() ;k++ ) // comprobacion que no este en la lista abierta
       {
        elemento_aux=LA.get(k);
         if(elemento_aux.x==i && elemento_aux.y==j-1){// si está, mirar costes
        	 bandera=true;
           if((elemento_aux.costoreal +elemento_aux.costoheuristica)> (aux.costoreal+funcionHeuristica(ini,fin,i,j-1))){
        	 
        	 Elemento elemento= new Elemento();
       	     elemento.x=i;
             elemento.y=j-1;
             elemento.padre=aux;
             elemento.costoreal=elemento.padre.costoreal+1;
             elemento.costoheuristica= funcionHeuristica(ini,fin,i,j-1);
             LA.set(k,elemento);
           }
          break;
        }
      }
      if(bandera==false){//si no estaba en la lista abierta, se añade
    	 
    	Elemento elemento= new Elemento();  
      	elemento.x=i;
        elemento.y=j-1;
        elemento.padre=aux;
        elemento.costoreal=elemento.padre.costoreal+1;
        elemento.costoheuristica= funcionHeuristica(ini,fin,i,j-1);
        LA.add(elemento);
        }
      }
      bandera=false;
      
    }

   return (menorsucesor());  
   }
   private Elemento menorsucesor() // funcion para hallar el sucesor con menor costo
   {
	if(LA.isEmpty()) return null;
 	Elemento elemento_menor=LA.get(0);
 	Elemento elemento_aux;
 	for(int i=0;i < LA.size();i++)
 	{   
     elemento_aux=LA.get(i);

 		if((elemento_aux.costoreal+elemento_aux.costoheuristica)<(elemento_menor.costoreal + elemento_menor.costoheuristica))
 		 elemento_menor=elemento_aux; 
 	  }
 	 return elemento_menor;
 	 }
    
}

		
    		
    		
		
    	
    


	
	
    