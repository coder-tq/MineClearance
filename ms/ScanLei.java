package ms;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ScanLei extends JFrame{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 450;
	Button low;//Easy按钮
    Button middle;//Middle按钮
    Button high;//Expert按钮
    Button setting;//Costom按钮
    public void setBak(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = null;
        img = new ImageIcon("saolei.jpg"); //添加背景图片
        JLabel background = new JLabel(img); 
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
    
	public ScanLei(String name){
		super(name);
		
		setBak(); //调用背景方法
	    Container c = getContentPane(); //获取JFrame面板
	    JPanel jp = new JPanel(); //创建个JPanel
	    jp.setOpaque(false); //把JPanel设置为透明 这样就不会遮住后面的背景 可在JPanel随意加组件了
	    c.add(jp);
	    
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setResizable(false);
		
		low = new Button("Easy");//创建Easy按钮
		low.setFont(new Font("楷体", Font.BOLD, 20));
		middle = new Button("Middle");//创建Middle按钮
		middle.setFont(new Font("楷体", Font.BOLD, 20));
		high = new Button("Expert");//创建Expert按钮
		high.setFont(new Font("楷体", Font.BOLD, 20));
		setting = new Button("Custom");//创建Custom按钮
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
                mainwindow w = new mainwindow(9, 9, 10);//传参确定Easy行、列、雷数
            }
        });
        middle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();//关闭父页面
                mainwindow w = new mainwindow(16, 16, 40);//传参确定Middle行、列、雷数
            }
        });
        high.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();//关闭父页面
                mainwindow w = new mainwindow(30, 16, 99);//传参确定Expert行、列、雷数
            }
        });
        setting.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();//关闭父页面
            	Setting s = new Setting();//弹出Setting自定义行列数
            }
        });
        this.repaint();//重绘
	}
}
