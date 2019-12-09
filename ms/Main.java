package ms;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ms.mainwindow;

class RePlay extends JFrame{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 280;
	Button replay;
    Button _exit;
    public RePlay(String name, JFrame fr){
    	super(name);
    	this.setAlwaysOnTop(true);
    	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		replay = new Button("Replay");
		replay.setFont(new Font("楷体", Font.BOLD, 20));
		_exit = new Button("Exit");
		_exit.setFont(new Font("楷体", Font.BOLD, 20));
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
        this.add(replay);
        this.add(_exit);
        replay.setBounds(95,38,100,40);
        _exit.setBounds(95,136,100,40);
        replay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            	fr.dispose();
                ScanLei sl = new ScanLei("扫雷");
            }
        });
        
        _exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	System.exit(0);
            }
        });
    }
}

//C:\\softwares\\JAVA\\workspace\\saolei.jpg

class ScanLei extends JFrame{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 450;
	Button low;
    Button middle;
    Button high;
    Button setting;
    public void setBak(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = null;
        img = new ImageIcon("saolei.jpg"); //添加图片
        JLabel background = new JLabel(img); 
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
    
	public ScanLei(String name){
		super(name);
		
		setBak(); //调用背景方法
	    Container c = getContentPane(); //获取JFrame面板
	    JPanel jp = new JPanel(); //创建个JPanel
	    jp.setOpaque(false); //把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
	    c.add(jp);
	    
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setResizable(false);
		
		low = new Button("Easy");
		low.setFont(new Font("楷体", Font.BOLD, 20));
		middle = new Button("Middle");
		middle.setFont(new Font("楷体", Font.BOLD, 20));
		high = new Button("Expert");
		high.setFont(new Font("楷体", Font.BOLD, 20));
		setting = new Button("Custom");
		setting.setFont(new Font("楷体", Font.BOLD, 20));
		
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
        this.add(low);
        this.add(middle);
        this.add(high);
        this.add(setting);
        
        low.setBounds(95,38,100,40);
        middle.setBounds(95,136,100,40);
        high.setBounds(95,234,100,40);
        setting.setBounds(95,332,100,40);
        
        low.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
                mainwindow w = new mainwindow(9, 9, 10);
            }
        });
        middle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
                mainwindow w = new mainwindow(16, 16, 40);
            }
        });
        high.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
                mainwindow w = new mainwindow(30, 16, 99);
            }
        });
        setting.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            	Setting s = new Setting();
            }
        });
        this.repaint();
	}
}

class Setting extends JFrame{
	public static final int DEFAULT_WIDTH = 150;
	public static final int DEFAULT_HEIGHT = 180;
	int row, crol;
	JTextField _row;
    JTextField _crol;
    JTextField _num;
    Button confirm;
	Setting(){
		super("Custom");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		_row = new JTextField(35); 
        _row.setFont(new Font("楷体", Font.BOLD,16));
        _crol = new JTextField(35); 
        _crol.setFont(new Font("楷体", Font.BOLD,16));
        _num = new JTextField(35); 
        _num.setFont(new Font("楷体", Font.BOLD,16));
        confirm = new Button("OK");
		confirm.setFont(new Font("楷体", Font.BOLD, 12));
        
        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("楷体", Font.BOLD,16));
        rowLabel.setHorizontalAlignment(JTextField.CENTER);
        JLabel crolLabel = new JLabel("Columns");
        crolLabel.setFont(new Font("楷体", Font.BOLD,16));
        crolLabel.setHorizontalAlignment(JTextField.CENTER);
        JLabel num = new JLabel("Mine");
        num.setFont(new Font("楷体", Font.BOLD,16));
        num.setHorizontalAlignment(JTextField.CENTER);
        
        this.add(rowLabel);
        this.add(_row);
        this.add(crolLabel);
        this.add(_crol);
        this.add(num);
        this.add(_num);
        this.add(confirm);
        
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLocationRelativeTo(null);
        rowLabel.setBounds(-15,-11,100,60);
        crolLabel.setBounds(-15,19,100,60);
        num.setBounds(-15,49,100,60);
        _row.setBounds(78,10,60,20);
        _crol.setBounds(78,40,60,20);
        _num.setBounds(78,70,60,20);
        confirm.setBounds(65,110,30,20);
        
        class ResponseButton implements ActionListener{
            public void actionPerformed(ActionEvent e){
            		int r = Integer.valueOf(_row.getText());
            		int c = Integer.valueOf(_crol.getText());
            		int n = Integer.valueOf(_num.getText());
                    if(r>=9 && r<=34 && c>=9 && c<=30 && n>=10 && n<=(r-1)*(c-1)){
                    	dispose();
                    	mainwindow w = new mainwindow(r, c, n);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Out of Range", "Error.mxy", JOptionPane.ERROR_MESSAGE); 
                    }
            }
        }
        
        confirm.addActionListener(new ResponseButton());
	}
}

public class Main {

	public static void main(String[] args) {
		ScanLei sl = new ScanLei("扫雷");
	}

}