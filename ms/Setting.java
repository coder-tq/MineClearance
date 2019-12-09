package ms;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Setting extends JFrame{
	public static final int DEFAULT_WIDTH = 150;
	public static final int DEFAULT_HEIGHT = 180;
	int row, crol;
	JTextField _row;//行数文本框
    JTextField _crol;//列数文本框
    JTextField _num;//雷数文本框
    Button confirm;//确认按钮
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
        
        JLabel rowLabel = new JLabel("Rows");//“行数”
        rowLabel.setFont(new Font("楷体", Font.BOLD,16));
        rowLabel.setHorizontalAlignment(JTextField.CENTER);
        JLabel crolLabel = new JLabel("Columns");//“列数”
        crolLabel.setFont(new Font("楷体", Font.BOLD,16));
        crolLabel.setHorizontalAlignment(JTextField.CENTER);
        JLabel num = new JLabel("Mine");//“雷数”
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
                    if(r>=9 && r<=34 && c>=9 && c<=30 && n>=10 && n<=(r-1)*(c-1)){//限制行数、列数及雷数
                    	dispose();//关闭父页面
                    	mainwindow w = new mainwindow(r, c, n);//传参确定自定义行、列、雷数
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Out of Range", "Error.mxy", JOptionPane.ERROR_MESSAGE); 
                    }
            }
        }
        
        confirm.addActionListener(new ResponseButton());
	}
}
