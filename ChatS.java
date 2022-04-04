import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatS extends JFrame implements ActionListener
{
	JButton b;
	static JTextArea ta;
	static JTextField tf;
	static DataOutputStream out;
	
	public ChatS()
	{
		super("Server");
		JPanel p=new JPanel();
		p.setLayout(null);
		b=new JButton("Send");
		tf= new JTextField();
		ta= new JTextArea();
		
		JScrollPane x= new JScrollPane(ta);
		b.setBounds(140,260,100,30);
		tf.setBounds(10,220,300,30);
		x.setBounds(10,10,300,200);
		p.add(b);
		p.add(tf);
		p.add(x);
		add(p);
		b.addActionListener(this);
		tf.addActionListener(this);
		setSize(380,380);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) throws IOException
	{
		ChatS m= new ChatS();
		ServerSocket s= null;
		
		try
		{
			s=new ServerSocket((192.168.0.109),8080);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Socket c= null;
		
		try
		{
			c=s.accept();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		DataInputStream in = new DataInputStream(c.getInputStream());
		out= new DataOutputStream(c.getOutputStream());
		String s1;
		
		while(true)
		{
			s1=in.readUTF();
			ta.append("Client: "+s1+"\n");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			ta.append("Server: "+tf.getText()+"\n");
			out.writeUTF(tf.getText());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

