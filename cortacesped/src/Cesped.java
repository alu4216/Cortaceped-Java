

import javax.swing.JOptionPane;


public class Cesped {
	private static char cesped[][];
	private int M;
   
	Cesped(){
		PedirDatos();
		cesped= new char [M][M];
		inicializar();
	} 
	void setcesped(char aux,int i,int j){
		
		cesped[i][j]=aux;
	}
	char getcesped(int i,int j)
	{
		return (cesped[i][j]);
	}
	int getM(){ 
		return M;
	}
	
	void  PedirDatos(){
		M = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el ancho y largo","Datos",JOptionPane.QUESTION_MESSAGE));
		}
	public void inicializar()
	{
		for(int i = 0; i< M; i++ ) 
			for(int j = 0; j < M; j++) 
				cesped[i][j]='C';
			
		cesped[0][0]='X';
	}
	public void inicializar_aleatorio(int var) {
		
		int cont =0;
		int cont2=(var *(M*M))/100;
		for(int i = 0; i< M; i++ ) {
			for(int j = 0; j < M; j++) {
				int z = (int) (Math.random() *3);
				if(z== 0)
				{
					cont++;
				    if(cont<cont2){
				    	int v = (int) (Math.random() *2);
						if (v%2 == 0)
							cesped[i][j]='O';
						else
							cesped[i][j]='G';
				    }
				    else
				    	cesped[i][j]='C';
				}
				else if(z==1)
					cesped[i][j] = 'C';
				else
					cesped[i][j]= 'A';
			}
		}
		cesped[0][0]='X';
	}
	
	
}
