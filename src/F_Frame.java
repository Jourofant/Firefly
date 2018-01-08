import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class F_Frame extends JFrame {
	
	private static final long serialVersionUID = 8763692551589834346L;
	static MyPanel temp = null;
	
	public F_Frame() {
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		/** ���ھ��е����� **/
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width / 2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height / 2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);
	}
	
	public void getImage(int temp) throws AWTException, IOException{
		Rectangle screenRectangle = this.getBounds();
		Robot r = new Robot();
		BufferedImage image = r.createScreenCapture(screenRectangle);
		File f;
		if(temp == 1)f = new File("fri.png");
		else if(temp == 2)f = new File("mid.png");
			else f = new File("end.png");
		f.createNewFile();
		ImageIO.write(image, "png", f);
	}
	
	public void drawFirefly(Firefly[] b) {
		MyPanel temp = new MyPanel(b);
		this.add(temp);
	}//����ͼ�εĺ���
	
}

class MyPanel extends JPanel {
	Firefly b[];
	// ����һ��MyPanel��壬���ڻ�ͼ����
	// ����JPanel
	// Graphics �ǻ�ͼ����Ҫ�࣬��������һ֧����
	public MyPanel(Firefly b[]){
		this.b= b;
	}
	public void Change(Firefly b[]){
		this.b = b;
	}
	public void paint(Graphics g) {
		super.paint(g);// ���ø��ຯ����ɳ�ʼ������ ,��仰��������
		g.setColor(Color.blue);//������ɫ
		for(int i = 0;i < b.length;i++){
			if(b[i].flag)
			g.fillOval((int)b[i].x, (int)b[i].y, ((int)b[i].light*3)/2, ((int)b[i].light*3)/2);
		}//��ÿ��ө���Ⱥ����л���
	}
}//����ͼ�λ�����