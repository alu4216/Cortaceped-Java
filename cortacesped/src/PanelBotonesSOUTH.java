import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotonesSOUTH extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private Cesped C;
	    private Panel panel;
	    private JButton AñadirObstaculo= new JButton("A.Piedra");
		private JButton AñadirObstaculo2= new JButton("A.Agua");
		private JButton AñadirCesped= new JButton("A.Cesped");
		private JButton AñadirCespedAlto = new JButton("A.C.Alto");
		
		public PanelBotonesSOUTH(Cesped o,Panel o2 ) {
			 C=o;
			 panel=o2;
			
			setLayout(new FlowLayout());
			AñadirObstaculo.addActionListener(new NewAction());
			AñadirObstaculo2.addActionListener(new NewAction());
			AñadirCesped.addActionListener(new NewAction());
			AñadirCespedAlto.addActionListener(new NewAction());
			
			add(AñadirObstaculo);
			add(AñadirObstaculo2);
			add(AñadirCesped);
			add(AñadirCespedAlto);
		}
		private class NewAction implements ActionListener  {
			int i=0,j=0;
			private void PedirDatos(){
			      i = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Introduzca el ancho ","Datos",JOptionPane.QUESTION_MESSAGE));
				  j = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Introduzca el alto ","Datos",JOptionPane.QUESTION_MESSAGE));
			}
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() ==AñadirObstaculo ) {
				 PedirDatos();
				 C.setcesped('O',i,j);
				 panel.repaint();
				}
				if(arg0.getSource() ==AñadirObstaculo2 ) {
					 PedirDatos();
					 C.setcesped('G',i,j);
					 panel.repaint();
					}
				if(arg0.getSource() == AñadirCesped ) {
					 PedirDatos();
					 C.setcesped('C',i,j);
					 panel.repaint();
					}
				if(arg0.getSource() == AñadirCespedAlto ) {
					 PedirDatos();
					 C.setcesped('A',i,j);
					 panel.repaint();
					}
			
				
				
			}
		}
    }
