
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


	class Panel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Cesped C;
		private BufferedImage imagen;
		private BufferedImage imagen1;
		private BufferedImage imagen2;
		private BufferedImage imagen3;
		private BufferedImage imagen4;
		private BufferedImage imagen5;
		private BufferedImage imagen6;
		public Panel(Cesped o) {
			C=o;
			try {
				imagen = ImageIO.read(Panel.class.getResource("/imagenes/cespedcorto.png"));
				imagen1 = ImageIO.read(Panel.class.getResource("/imagenes/cespedalto.png"));
				imagen2 = ImageIO.read(Panel.class.getResource("/imagenes/piedra.png"));
				imagen3 = ImageIO.read(Panel.class.getResource("/imagenes/cortacesped.png"));
				imagen4 = ImageIO.read(Panel.class.getResource("/imagenes/agua.png"));
				imagen5 = ImageIO.read(Panel.class.getResource("/imagenes/inicio.png"));
				imagen6 = ImageIO.read(Panel.class.getResource("/imagenes/final.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		
			
			for(int i = 0; i< C.getM(); i++ ) {
				for(int j = 0; j < C.getM(); j++) {
					if(C.getcesped(i,j) == 'O')
					{
						g.drawImage(imagen2, 50*i,50*j, null);
					   //g.setColor(Color.LIGHT_GRAY);
					   //g.fillRect(50 * i, 50 * j, 50, 50);
					}
					else if(C.getcesped(i,j) == 'G'){
						
						g.drawImage(imagen4, 50*i,50*j, null);
					}
					else if(C.getcesped(i,j)=='C')
					{
					  g.drawImage(imagen, 50*i,50*j, null);
					  //g.setColor(Color.green);
					  //g.fillRect(50 * i, 50 * j, 50, 50);
					}
					else if(C.getcesped(i,j)=='A')
					{
					  g.drawImage(imagen1, 50*i,50*j, null);
					  //g.setColor(Color.green.darker());
					  //g.fillRect(50 * i, 50 * j, 50, 50);
					}
					else if (C.getcesped(i, j)== 'S')
					{
						g.drawImage(imagen5, 50*i,50*j, null);
					}
					else if (C.getcesped(i, j)== 'F')
					{
						g.drawImage(imagen6, 50*i,50*j, null);
					}
					else
					{
					  g.drawImage(imagen3, 50*i,50*j, null);
					  //g.setColor(Color.red.darker());
					  //g.fillOval(50*i,50*j,50,50);
					}
				}
				}
			//g.drawImage(imagen, 0, 0, null);
			}
	}





