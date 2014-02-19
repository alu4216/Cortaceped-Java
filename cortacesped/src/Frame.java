import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;



public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Cesped C = new Cesped();
	private Panel panel = new Panel(C);
	private Cortacesped CC= new Cortacesped(panel,C);
	private PanelBotonesSOUTH PBS =new PanelBotonesSOUTH(C,panel);
	private PanelBotonesEAST PBE = new PanelBotonesEAST(C,panel,CC);
	private JScrollPane scroll;
	public Frame() {
		setSize(C.getM()*50,C.getM()*50);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		scroll= new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(panel); 
		scroll .getViewport().setView(panel); //hace justo lo mismo que la anterir. Si no se incluye no pinta nada (Frame vacio)
		scroll.repaint();//no hace falta realmente esta.
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(C.getM()*50,C.getM()*50));//tamaño de las barras
		add(scroll, BorderLayout.CENTER);
		add(PBS, BorderLayout.SOUTH);
	    add(PBE,BorderLayout.EAST);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

