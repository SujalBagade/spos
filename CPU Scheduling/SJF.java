import java.util.Scanner;
 
public class SJF {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("enter no of process:");
		int n = sc.nextInt();
		int process_id[] = new int[n];
		int complete_time[] = new int[n]; 
                int arrival_time[] = new int[n];
		int burst_time[] = new int[n];
		int turn_around_time[] = new int[n];
		int waiting_time[] = new int[n];  //wt means waiting time
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int st=0, total_processes=0;
		float avgwt=0, avgta=0;
 
		for(int i=0;i<n;i++)
		{
			System.out.println ("enter process " + (i+1) + " arrival time:");
			arrival_time[i] = sc.nextInt();
			System.out.println ("enter process " + (i+1) + " brust time:");
			burst_time[i] = sc.nextInt();
			process_id[i] = i+1;
			f[i] = 0;
		}
		
		boolean a = true;
		while(true)
		{
			int c=n, min=999;
			if (total_processes == n) 
				break;
			
			for (int i=0; i<n; i++)
			{
				/*
				 * If i'th process arrival time is less than or equal to system time and its flag=0 and burst<min 
				 * this process will be first 
				 */ 
				if ((arrival_time[i] <= st) && (f[i] == 0) && (burst_time[i]<min))
				{
					min=burst_time[i];
					c=i;
				}
			}
			
			/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
			if (c==n) 
				st++;
			else
			{
				complete_time[c]=st+burst_time[c];
				st+=burst_time[c];
				turn_around_time[c]=complete_time[c]-arrival_time[c];
				waiting_time[c]=turn_around_time[c]-burst_time[c];
				f[c]=1;
				total_processes++;
			}
		}
		
		System.out.println("\npid  arrival brust  complete turn waiting");
		for(int i=0;i<n;i++)
		{
			avgwt+= waiting_time[i];
			avgta+= turn_around_time[i];
			System.out.println(process_id[i]+"\t"+arrival_time[i]+"\t"+burst_time[i]+"\t"+complete_time[i]+"\t"+turn_around_time[i]+"\t"+waiting_time[i]);
		}
		System.out.println ("\naverage tat is "+ (float)(avgta/n));
		System.out.println ("average wt is "+ (float)(avgwt/n));
		sc.close();
	}
}