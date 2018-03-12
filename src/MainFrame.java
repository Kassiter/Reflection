import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	
public MainFrame()
{
	super();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(440,400);
	setResizable(false);
	setTitle("Анализатор класса");
	setVisible(true);
	add(new MainPanel());
	repaint();
}
}
class MainPanel extends JPanel
{
	JTextField j1=new JTextField();
	JTextArea j2=new JTextArea();
	JLabel JL=new JLabel("Пусто");
	public MainPanel()
	{
		super();
		setSize(440,400);
		setLayout(null);
		JScrollPane j3=new JScrollPane(j2);
		j3.setHorizontalScrollBarPolicy(j3.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		j3.setVerticalScrollBarPolicy(j3.VERTICAL_SCROLLBAR_AS_NEEDED);
		j1.setBounds(getWidth()/2-30,0,getWidth()/2+30,30);
		j3.setBounds(0,50,getWidth()-40,getHeight()-140);
		j2.setEditable(false);
		JL.setBounds(0,25,this.getWidth(),25);
		add(JL);
		add(j1);
		add(j3);
		j3.setBackground(Color.WHITE);
		ButtonsPanel BP=new ButtonsPanel(this);
		BP.setBounds(0,getHeight()-100,getWidth(),100);
		add(BP);
		j1.addKeyListener(new KeyAdapter()
	    {
	      public void keyPressed(KeyEvent e)
	      {
	        if (e.getKeyCode() == KeyEvent.VK_ENTER)
	        {
	          BP.b1.doClick();
	        }
	      }
	    });
		repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("Полное имя класса:", 0, 20);
	}
}
class ButtonsPanel extends JPanel implements ActionListener,ItemListener
{
	JButton b1,b2;
	MainPanel MP;
	Checkbox c1,c2,c3,c4,c5,c6,c7;
	String name="";
	CheckboxGroup g1;
	public ButtonsPanel(MainPanel MP)
	{
		setLayout(null);
		g1=new CheckboxGroup();
		
		c1=new Checkbox("Полная информация",g1, true);
		c2=new Checkbox("Интерфейс класса",g1, false);
		c3=new Checkbox("Список методов",g1, false);
		c4=new Checkbox("Список переменных",g1, false);
		c5=new Checkbox("Собственные методы",g1, false);
		c6=new Checkbox("Собственные методы интерфейса",g1, false);
		c7=new Checkbox("Список конструкторов",g1, false);
		this.MP=MP;
		c1.addItemListener(this);
		c2.addItemListener(this);
		c3.addItemListener(this);
		c4.addItemListener(this);
		c5.addItemListener(this);
		c6.addItemListener(this);
		c7.addItemListener(this);
		b1=new JButton("Анализ");
		b1.addActionListener(this);
		b1.setBounds(0,10,80,60);
		add(b1);
		c1.setBounds(80,10,140,15);
		c2.setBounds(80,25,140,15);
		c3.setBounds(80,40,140,15);
		c4.setBounds(80,55,140,15);
		c5.setBounds(220,10,160,15);
		c6.setBounds(220,25,220,15);
		c7.setBounds(220,40,220,15);
		add(c1);
		
		add(c2);
		add(c3);
		add(c4);
		add(c5);
		add(c6);
		add(c7);
		repaint();
	}

	public void pressAnalyze(String name) {
		try
		{
		String s="";
		if(c1.getState())
			s=Reflector.getInfo(name);
		if(c2.getState())
			s=Reflector.InfoMethodsIntefrace(name);
		if(c3.getState())
			s=Reflector.InfoMethods(name);
		if(c4.getState())
			s=Reflector.InfoFields(name);
		if(c5.getState())
			s=Reflector.getInfoOwnMethods(name);
		if(c6.getState())
			s=Reflector.getInfoOwnInterfaceMethods(name);
		if(c7.getState())
			s=Reflector.getInfoConstructors(name);
		MP.j2.setText(s);
	
		}
		catch(ClassNotFoundException e)
		{
			if(MP.j1.getText().length()!=0)
				MP.j2.setText("Такого класса нет");
			MP.JL.setText("Пусто");
		}
		catch(NoClassDefFoundError e)
		{
			String wrong=e.getMessage();
			int begin=wrong.indexOf(":");
			int end=wrong.indexOf(")");
			pressAnalyze(wrong.substring(begin+2, end));
		}
		MP.JL.setText(name);
	}
	public void actionPerformed(ActionEvent ae) {
		String str=ae.getActionCommand();
		if(str.equals("Анализ"))
			pressAnalyze(MP.j1.getText());
		name=MP.j1.getText();
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		pressAnalyze(name);
		
	}
}
