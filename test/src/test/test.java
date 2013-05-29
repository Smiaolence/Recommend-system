package test;

//import java.math.*;

public class test {
	static double R[][]=new double[5][5];
	
	static double average(int a){					//计算用户a平均项目评分
		int count =0;
		double a_A=0;
		for(int i=0;i<5;i++){
			if(R[a][i]!=2.5){
				count++;
				a_A = a_A + R[a][i];
			}
		}
		a_A = a_A/count;
		return a_A;
	}
	
	static double sim1(int a,int b)					//第一种方法，用户未评分过项目，填入默认2.5
	{
		/*double a_A=0;
		double b_A=0;
		for(int i=0;i<5;i++){
			a_A = a_A+R[a][i];
			b_A = b_A+R[b][i];
		}
		a_A = a_A/5;
		b_A = b_A/5;*/
		double numerator=0;
		double denominator_1=0;
		double denominator_2=0;
		for(int i=0;i<5;i++){
			numerator = numerator +(R[a][i]-average(a))*(R[b][i]-average(b));
			//System.out.print( (R[a][i]-a_A)*(R[b][i]-b_A) + " ");
		}
		for(int i=0;i<5;i++){
			denominator_1 = (R[a][i]-average(a)) * (R[a][i]-average(a)) + denominator_1;
			denominator_2 = (R[b][i]-average(b)) * (R[b][i]-average(b)) + denominator_2;
		}
		double result= numerator/ Math.sqrt(denominator_1*denominator_2);
		//System.out.print(a_A + " " + b_A + " " +numerator + " ");
		return result;
	}

	
	static double sim2(int a,int b)						//第二种方法，用户未评分过项目，将其排除
	{	
		int count = 0;//用来计数a和b共同评分过多少项目
		double new_R[][]=new double[2][5];
		for(int i=0;i<5;i++){
			if( (R[a][i]!=2.5) && (R[b][i]!=2.5) ){
				new_R[0][count]=R[a][i];
				new_R[1][count]=R[b][i];
				count++;
			}
		}
		double numerator=0;
		double denominator_1=0;
		double denominator_2=0;
		for(int i=0;i<count;i++){
			numerator = numerator +(new_R[0][i]-average(a))*(new_R[1][i]-average(b));
		}
		for(int i=0;i<count;i++){
			denominator_1 = (new_R[0][i]-average(a)) * (new_R[0][i]-average(a)) + denominator_1;
			denominator_2 = (new_R[1][i]-average(b)) * (new_R[1][i]-average(b)) + denominator_2;
		}
		double result = numerator/ Math.sqrt(denominator_1*denominator_2);
		//System.out.print(a_A+" "+b_A+" ");
		return result;
	}
	
	static double predict1(int a,int t){
		double numerator=0;
		double denominator=0;
		for(int i=0;i<5;i++){
			numerator = sim2(a,i)*(R[i][t]-average(i)) + numerator;
			denominator = denominator + sim2(a,i);
		}
		System.out.print(average(a) + " " + numerator + " " + denominator + " ");
		return average(a)+numerator/denominator;	
	}
	
	public static void main(String args[]){
		R[0][0]=5;		R[0][1]=3;	R[0][2]=2.5;	R[0][3]=1;	R[0][4]=4;
		R[1][0]=2;		R[1][1]=5;	R[1][2]=2.5;	R[1][3]=4;	R[1][4]=2.5;
		R[2][0]=4;		R[2][1]=3;	R[2][2]=2;		R[2][3]=3;	R[2][4]=5;
		R[3][0]=5;		R[3][1]=4;	R[3][2]=2;		R[3][3]=2.5;R[3][4]=4;
		R[4][0]=2.5;	R[4][1]=2.5;R[4][2]=2.5;	R[4][3]=3;	R[4][4]=2;
		/*System.out.print(sim1(0,1));
		System.out.print(" ");
		System.out.print(sim2(0,1));
		System.out.print(" ");*/
		System.out.print(predict1(3,3));
	}

}
