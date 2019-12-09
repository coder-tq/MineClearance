package ms;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class RePlay extends JFrame{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 280;
	Button replay;//重新开始按钮
    Button _exit;//退出按钮
    public RePlay(String name, JFrame fr){
    	super(name);
    	this.setAlwaysOnTop(true);//始终顶层显示
    	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		replay = new Button("Replay");//创建ReaPlay按钮
		replay.setFont(new Font("楷体", Font.BOLD, 20));
		_exit = new Button("Exit");//创建Exit按钮
		_exit.setFont(new Font("楷体", Font.BOLD, 20));
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
        this.add(replay);
        this.add(_exit);
        replay.setBounds(95,38,100,40);
        _exit.setBounds(95,136,100,40);
        replay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();//关闭父页面
            	fr.dispose();//关闭父页面
                ScanLei sl = new ScanLei("扫雷");//重新创建扫雷页面
            }
        });
        
        _exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	System.exit(0);//退出
            }
        });
    }
}
