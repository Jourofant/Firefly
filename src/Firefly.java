/*=========================================================================
=============================��ө���Ĵ���=================================
=========================================================================*/

import java.util.Random;

public class Firefly {
	
	boolean flag;//�Ƿ��ѱ�����
	int aim;//�����ߣ�-1�ĳ�ʼֵ����δ������
	double light;// ����ǿ��
	double r;// �����뾶
	double x;// λ�ú�����
	double y;// λ��������
	
	static Random ran = new Random();//ʵ���������
	static int move_max = 40;// ����ƶ����ֵ
	static int move = 50;//������ʱ���ƶ�ֵ
	static int bound_x = 800;
	static int bound_y = 600;//ө���λ�ó�ʼ���ı߽�
	static double Lmax = 0;////��ǿ����ֵ
	


	static double distance(double a,double b){
		return Math.sqrt((a-b)*(a-b));
	}//������ө���Ⱥ��ľ���
	
	public Firefly() {
		FireflyCreat();
		this.check_a();
	}// Ĭ�Ϲ�����

	public Firefly(double light, double r, double x, double y){
		aim = -1;
		this.light = light;
		this.r = r;
		this.x = x;
		this.y = y;
	}// ���ι�������������
	
	public void move_random() {
		x += ran.nextInt(move_max*2)-move_max;
		x += ran.nextDouble();
		x = x>800?800:x;
		x = x<0?0:x;
		
		y += ran.nextInt(move_max*2)-move_max;
		y += ran.nextDouble();
		y = y>600?600:y;
		y = y<0?0:y;
	}// ����ƶ�����

	public static void move_attract(int i,Firefly a[]) {
		if(a[i].flag//�жϸ�Ⱥ���Ƿ��ѱ�����
				&& a[a[i].aim].light > a[i].light//�жϸ�Ⱥ��Ĺ�ǿ�Ƿ�����;������������
				&& Math.sqrt((a[i].x-a[a[i].aim].x)*(a[i].x-a[a[i].aim].x)) <= move
				&&Math.sqrt((a[i].y-a[a[i].aim].y)*(a[i].y-a[a[i].aim].y)) <= move//�ж��Ƿ���ө���Ⱥ��
				){
			a[i].flag = false;//��Ⱥ������Ϊ�ѱ�����
			a[a[i].aim].light += a[i].light/3;
			a[a[i].aim].r += a[i].light/6;//�����߷������ݱ仯
			return;
		}
		if(a[i].aim != -1 && a[i].light > a[a[i].aim].light){
			a[i].aim = -1;
			a[i].move_random();
			return;
		}//���ƶ����������������ߵĹ�ǿ�����仯��������������������ʧ
		a[i].x = a[i].x < a[a[i].aim].x ? a[i].x+move : a[i].x-move;
		a[i].x = a[i].x>800?750:a[i].x;
		a[i].x = a[i].x<0?0:a[i].x;
		
		a[i].y = a[i].y < a[a[i].aim].y ? a[i].y+move : a[i].y-move;
		a[i].y = a[i].y>600?550:a[i].y;
		a[i].y = a[i].y<0?0:a[i].y;
	}// �����ƶ�����
	
	public static void move(int i,Firefly a[]){
		if(a[i].aim == -1)
			a[i].move_random();
		else 
			move_attract(i,a);
	} //�ƶ�����
	
	public void check_a(){
		System.out.println("check:");
		System.out.println("light:"+this.light + " r:" +this.r+ " x:" + this.x + " y:" + this.y);
	}//��ӡө��浱ǰλ��
	
	public static void check_b(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			System.out.println("check "+(i+1)+":");
			System.out.println("flag:"+a[i].flag+" aim:"+(a[i].aim+1)+" x:"+a[i].x + " y:" + a[i].y);
		}//�鿴��ǰ����ө���Ⱥ��������������
	}
	
	void FireflyCreat(){
		aim = -1;
		flag = true;
		light = ran.nextInt(40);
		light += ran.nextDouble();
		r = 30;
		r += ran.nextDouble();
		x = ran.nextInt(bound_x);
		x += ran.nextDouble();
		y = ran.nextInt(bound_y);
		y += ran.nextDouble();
	}//��ͨ��Ĭ�Ϲ����������Ķ�������ݽ��г�ʼ��
	
	static void FireflyCreat(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			a[i] = new Firefly();
		}
	}//��������г�ʼ��
	
	static void FireflyTouch(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			if(a[i].flag)
			for(int j = 0;j < a.length;j++){
				if(j == i)continue;//��������������ж�
				if(a[j].flag && distance(a[i].x-a[j].x,a[i].y-a[j].y) <= a[i].r)
					if(a[j].aim == -1 || a[a[j].aim].light < a[i].light)a[j].aim = i;
			}
		}
	}//���������ж�
	
}