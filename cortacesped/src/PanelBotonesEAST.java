import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class PanelBotonesEAST extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton Aleatorio= new JButton("Aleatorio");
	private JButton DFS = new JButton("DFS");
	private JButton A = new JButton ("A*");
	private Cesped C;
	private Panel panel;
	private Cortacesped CC;
	public PanelBotonesEAST(Cesped o,Panel o1,Cortacesped o2)
	{
		C=o;
		panel=o1;
		CC=o2;
		setLayout(new FlowLayout());
		Aleatorio.addActionListener(new NewAction());
		DFS.addActionListener(new NewAction());
		A.addActionListener(new NewAction());
		
		add(Aleatorio);
		add(DFS);
		add(A);
	
	}
	private class NewAction implements ActionListener  {

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == Aleatorio ){
				
				int var= Integer.parseInt(JOptionPane.showInputDialog(null,
						"Introduzca % máximo de obstaculos","Datos",JOptionPane.QUESTION_MESSAGE));
				C.inicializar_aleatorio(var);
				panel.repaint();
			}
			
			if(arg0.getSource() == DFS ) {
				
				CC.iniciar_DFS();
			}
			
			if(arg0.getSource()==A){
				CC.iniciar_A();
			}
			}
			
		}
		
	}

