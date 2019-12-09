package ms;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import ms.Main;

public class mainwindow{
	String title = "";
	public mainwindow(int row,int col,int num)
	{
		Play py= new Play();
		py.go(row,col,num);
	}
}

class Play extends JFrame implements ActionListener{

	public int len;//格子边长
	public int row,col,last;//行、列、剩余雷数
	public JPanel headPanel;
	public JLabel label1;//文本框
	public int mp[][];//雷阵
	public Container contentPane;
	public JButton[][] btns;//按钮
	public int layminesflag;//是否布雷，0为未布雷，1为已布雷
	public void go(int row,int col,int num)//核心函数
	{
		this.len = Math.min(50, Math.min(600/col, 800/row));//根据行列计算边长
		this.row = row;
		this.col = col;
		this.last = num;
		layminesflag = 0;//初始化为0
		initGUI();//加载gui
	}
	public void initGUI() 
	{
		contentPane = this.getContentPane();
		this.setLayout(null);
		this.setVisible(true);
		this.setTitle("扫雷 ver1.0");
		this.setSize(col*this.len+10, row*this.len+45);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout());
		label1 = new JLabel("剩余地雷数量： "+last);
		headPanel.add(label1);
		headPanel.setBounds(0, 0, col*this.len, 25);
		headPanel.setVisible(true);
		contentPane.add(headPanel);
		mp = new int[row][col];
		btns = new JButton[row][col];
		loadBtns();//加载按钮
		this.setSize(col*this.len+10, row*this.len+35);
		this.repaint();
	}
	public void loadBtns()
	{
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				btns[i][j] = new JButton("");
				btns[i][j].setMargin(new Insets(0, 0, 0, 0));
				btns[i][j].setBounds(j*this.len+5, i*this.len+30, this.len, this.len);
				btns[i][j].setFont(new Font(null, Font.BOLD, Math.min(30, len-10)));
				btns[i][j].addActionListener(this);
				btns[i][j].addMouseListener(new NormoreMouseEvent());
			}
		}
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				contentPane.add(btns[i][j]);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	class NormoreMouseEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent e) 
		{
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col; j++)
				{
					if(!btns[i][j].isEnabled()) continue;//若按钮被按下过直接跳过
					if(e.getSource() == btns[i][j])
					{
						if (e.getButton() == MouseEvent.BUTTON3)//右键按下 
						{
							if(btns[i][j].getText() == "")
							{
								btns[i][j].setText("🏴");
								last--;
								label1.setText("剩余地雷数量： "+last);
							}
							else
							{
								btns[i][j].setText("");
								last++;
								label1.setText("剩余地雷数量： "+last);
							}
						}
						else if(e.getButton() == MouseEvent.BUTTON1)//左键按下
						{
							if(btns[i][j].getText() == "🏴") continue;//若按钮插旗则跳过
							if(mp[i][j] == 1)
							{
								btns[i][j].setText("💣");
								doEnd(false);
								continue;
							}
							DFS(i,j);//踩雷算法，深度优先搜索
							isEnd();//判断是否获胜
						}
					}
				}
			}
		}
	}
	int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};//方向数组，用于深度优先搜索
	public void DFS(int x,int y)
	{
		if(layminesflag == 0)
		{
			layminesflag = 1;
			laymines(x,y,last);
		}
		if(x > row-1 || y > col-1 || x < 0 || y < 0) return;
		if(!btns[x][y].isEnabled()) return;
		if(btns[x][y].getText() == "🏴") return;
		int sum = 0;
		for(int i = 0; i < 8; i++)
		{
			int temx = x+pos[i][0];
			int temy = y+pos[i][1];
			if(temx > row-1 || temy > col-1 || temx < 0 || temy < 0) continue;
			if(mp[temx][temy] == 1)
			{
				sum++;
			}
		}
		if(sum != 0)
		{
			btns[x][y].setText(""+sum);
			btns[x][y].setEnabled(false);
			return;
		}
		else
		{
			for(int i = 0; i < 8; i++)
			{
				btns[x][y].setEnabled(false);
				DFS(x+pos[i][0],y+pos[i][1]);
			}
		}
		
	}
	public Random ranx = new Random();
	public Random rany = new Random();
	public void laymines(int x,int y,int num)//布雷算法，模拟微软，在按下的按钮周围9格必定无雷
	{
		mp[x][y] = 1;
		for(int i = 0; i < 8; i++)
		{
			if(x+pos[i][0] > row-1 || y+pos[i][1] > col-1 || x+pos[i][0] < 0 || y+pos[i][1] < 0) continue;
			mp[x+pos[i][0]][y+pos[i][1]] = 1;
		}
		while(num > 0)//布雷
		{
			int randx = ranx.nextInt(row);
			int randy = rany.nextInt(col);
			if(mp[randx][randy] == 1) continue;
			else {
				mp[randx][randy] = 1;
				num--;
			}
		}
		mp[x][y] = 0;
		for(int i = 0; i < 8; i++)
		{
			if(x+pos[i][0] > row-1 || y+pos[i][1] > col-1 || x+pos[i][0] < 0 || y+pos[i][1] < 0) continue;
			mp[x+pos[i][0]][y+pos[i][1]] = 0;
		}
		return;
	}
	public void isEnd()
	{
		int flag = 0;
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(mp[i][j] == 0&&btns[i][j].isEnabled()) {flag = 1; break;}//还有没按下且不是雷的按钮
			}
		}
		if(flag == 0) {
			System.out.println("done");
			doEnd(true);
		}
	}
	public void doEnd(boolean flag)//结束游戏，弹出重新开始框，锁定游戏主界面，显示所有地雷，判断插旗对错
	{
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(!btns[i][j].isEnabled()) continue;
				if(btns[i][j].getText() == "🏴")
				{
					if(mp[i][j] == 1) btns[i][j].setText("✔");
					else btns[i][j].setText("×");
				}
				else if(mp[i][j] == 1) {btns[i][j].setText("💣");btns[i][j].setEnabled(false);}
			}
		}
		if(flag)
			label1.setText("You Win!");
		else label1.setText("You Loss!");
		this.repaint();
		this.setEnabled(false);
		RePlay rp = new RePlay("Replay", this);
	}
}

