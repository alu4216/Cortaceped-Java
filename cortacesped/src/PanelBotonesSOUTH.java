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
	    private JButton A�adirObstaculo= new JButton("A.Piedra");
		private JButton A�adirObstaculo2= new JButton("A.Agua");
		private JButton A�adirCesped= new JButton("A.Cesped");
		private JButton A�adirCespedAlto = new JButton("A.C.Alto");
		
		public PanelBotonesSOUTH(Cesped o,Panel o2 ) {
			 C=o;
			 panel=o2;
			
			setLayout(new FlowLayout());
			A�adirObstaculo.addActionListener(new NewAction());
			A�adirObstaculo2.addActionListener(new NewAction());
			A�adirCesped.addActionListener(new NewAction());
			A�adirCespedAlto.addActionListener(new NewAction());
			
			add(A�adirObstaculo);
			add(A�adirObstaculo2);
			add(A�adirCesped);
			add(A�adirCespedAlto);
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
				if(arg0.getSource() ==A�adirObstaculo ) {
				 PedirDatos();
				 C.setcesped('O',i,j);
				 panel.repaint();
				}
				if(arg0.getSource() ==A�adirObstaculo2 ) {
					 PedirDatos();
					 C.setcesped('G',i,j);
					 panel.repaint();
					}
				if(arg0.getSource() == A�adirCesped ) {
					 PedirDatos();
					 C.setcesped('C',i,j);
					 panel.repaint();
					}
				if(arg0.getSource() == A�adirCespedAlto ) {
					 PedirDatos();
					 C.setcesped('A',i,j);
					 panel.repaint();
					}
			
				
				
			}
		}
    }
