import java.awt.AWTException;
import java.io.IOException;


public class Main {
	
	static int Firefly_max = 20;//ʵ����ө���Ⱥ�����������ֵ
	static int temp = 20;//��������
	
	public static void main(String[] args) throws InterruptedException, AWTException, IOException{
		F_Frame a = new F_Frame();//ʵ������ͼ����a
		Firefly b[] = new Firefly[Firefly_max];//ʵ����һ������20��ө���Ⱥ����b��
		Firefly.FireflyCreat(b);//��ÿ��Ⱥ����г�ʼ��
		a.drawFirefly(b);//���Ƴ�ʼ���
		Thread.sleep(2500);
		a.getImage(1);
		Thread.sleep(1500);//������ͣ5s
		while(temp-- >= 0){
			Thread.sleep(600);//ÿ���ƶ��ļ��
			a.getContentPane().removeAll();//��������е�ͼ���Ա����´λ���
			Firefly.FireflyTouch(b);//�Կ��ж���ө���Ⱥ����������ж�
			for(int i = 0;i < b.length;i++)
					if(b[i].flag){
						Firefly.move(i, b);//���ݲ�ͬȺ�����������ƶ�
						Firefly.Lmax = b[i].light >Firefly.Lmax?b[i].light:Firefly.Lmax;//��¼��ǰ�Ĺ�ǿ��ֵ��
					}
			System.out.println("MaxLigh: "+Firefly.Lmax);//����õ��Ĺ�ǿ��ֵ��
			a.drawFirefly(b);//����ͼ�εĻ���
			a.validate();//���ô���
			if(temp == 10)a.getImage(2);
		}
		a.getImage(3);
	}

}
