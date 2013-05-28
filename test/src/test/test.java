package test;

//import java.math.*;

public class test {
	static double R[][]=new double[5][5];
	
	static double sim1(int a,int b)					//第一种方法，用户未评分过项目，填入默认2.5
	{
		double a_A=0;
		double b_A=0;
		for(int i=0;i<5;i++){
			a_A = a_A+R[a][i];
			b_A = b_A+R[b][i];
		}
		a_A = a_A/5;
		b_A = b_A/5;
		double numerator=0;
		double denominator_1=0;
		double denominator_2=0;
		for(int i=0;i<5;i++){
			numerator = numerator +(R[a][i]-a_A)*(R[b][i]-b_A);
			//System.out.print( (R[a][i]-a_A)*(R[b][i]-b_A) + " ");
		}
		for(int i=0;i<5;i++){
			denominator_1 = (R[a][i]-a_A) * (R[a][i]-a_A) + denominator_1;
			denominator_2 = (R[b][i]-b_A) * (R[b][i]-b_A) + denominator_2;
		}
		double result= numerator/ Math.sqrt(denominator_1*denominator_2);
		//System.out.print(a_A + " " + b_A + " " +numerator + " ");
		return result;
	}

	
	static double sim2(int a,int b)						//第二种方法，用户未评分过项目，将其排除
	{	
		int count = 0;//用来计数a和b共同评分过多少项目
		int count_1=0;
		int count_2=0;//算平均评分时的分母
		double a_A=0;
		double b_A=0;
		double new_R[][]=new double[2][5];
		for(int i=0;i<5;i++){
			if(R[a][i]!=2.5){
				count_1++;
				a_A = a_A+R[a][i];
			}
			if(R[b][i]!=2.5){
				count_2++;
				b_A = b_A+R[b][i];
			}
			if( (R[a][i]!=2.5) && (R[b][i]!=2.5) ){
				new_R[0][count]=R[a][i];
				new_R[1][count]=R[b][i];
				count++;
			}
		}
		a_A = a_A/count_1;
		b_A = b_A/count_2;
		double numerator=0;
		double denominator_1=0;
		double denominator_2=0;
		for(int i=0;i<count;i++){
			numerator = numerator +(new_R[0][i]-a_A)*(new_R[1][i]-b_A);
		}
		for(int i=0;i<count;i++){
			denominator_1 = (new_R[0][i]-a_A) * (new_R[0][i]-a_A) + denominator_1;
			denominator_2 = (new_R[1][i]-b_A) * (new_R[1][i]-b_A) + denominator_2;
		}
		double result = numerator/ Math.sqrt(denominator_1*denominator_2);
		//System.out.print(a_A+" "+b_A+" ");
		return result;
	}
	
	public static void main(String args[]){
		R[0][0]=5;		R[0][1]=3;	R[0][2]=2.5;	R[0][3]=1;	R[0][4]=4;
		R[1][0]=2;		R[1][1]=5;	R[1][2]=2.5;	R[1][3]=4;	R[1][4]=2.5;
		R[2][0]=4;		R[2][1]=3;	R[2][2]=2;		R[2][3]=3;	R[2][4]=5;
		R[3][0]=5;		R[3][1]=4;	R[3][2]=2;		R[3][3]=2.5;R[3][4]=4;
		R[4][0]=2.5;	R[4][1]=2.5;R[4][2]=2.5;	R[4][3]=3;	R[4][4]=2;
		System.out.print(sim1(0,4));
		System.out.print(" ");
		System.out.print(sim2(0,4));
	}

}
