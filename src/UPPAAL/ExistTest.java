package UPPAAL;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import UPPAAL.Read;

public class ExistTest {
	public static Scanner cin = new Scanner(System.in);
	public static ArrayList <UppaalTemPlate> templates = new ArrayList <UppaalTemPlate> ();
	static String []s = null;
	static double locationX = -1;
	static double transitionX = -1;
	static boolean f = false;
	static ArrayList<Double> time1 = new ArrayList<Double>();
	static ArrayList<Double> time2 = new ArrayList<Double>();
	static ArrayList<Double> time3 = new ArrayList<Double>();
	
	public static void main(String[] args) throws Exception 
	{
		
		SAXReader reader=new SAXReader();//获取解析器
	    Document dom= reader.read("UPPAAL.xml");//解析XML获取代表整个文档的dom对象
	    Element root=dom.getRootElement();//获取根节点
	    
	    Read uppaal=new Read();
	    uppaal.load(root);
	    
	    
	    templates = uppaal.getUppaalTemplates();
	    
	    /*ArrayList <UppaalTransition> messageList = new  ArrayList <UppaalTransition>();排序消息用
	   
	    for(Object OTemplate:templates)
	    {
	    	UppaalTemPlate templateI = (UppaalTemPlate) OTemplate;
	    	
	    	for(Object OTransition:templateI.getTransitions())
	    	{
	    		UppaalTransition transitionI = (UppaalTransition) OTransition;
	    		if(transitionI.getName().charAt(transitionI.getName().length()-1) == '!')
	    			messageList.add(transitionI);//添加发送方的transition
	    	}
	    }
	    
	    Collections.sort(messageList,comparator);//对messageList根据时间进行排序
	     */	    
	   
	    System.out.println("输入要进行的一致性测试:1存在性；2前向一致性；3逆向一致性；4双向一致性测试；5实时测试 ");
	    String temp = null;
	    while(cin.hasNext()) 
	    {
	    	String c = cin.next();
	    	switch(c)
	    	{
	    	case "1":
	    		System.out.println("输入接口迁移序列xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		break;
	    	
	    	case "2":
	    		System.out.println("输入接口迁移序列1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		System.out.println("输入接口迁移序列2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		
	    		if(compare(time1,time2))
	    			System.out.println("符合前向一致性");
	    		else
	    			System.out.println("不符合前向一致性");
	    		break;
	    	case "3":
	    		System.out.println("输入接口迁移序列1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		System.out.println("输入接口迁移序列2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		
	    		if(!compare(time1,time2))
	    			System.out.println("符合逆向一致性");
	    		else
	    			System.out.println("不符合逆向一致性");
	    		break;
	    	case "4":
	    		System.out.println("输入接口迁移序列1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		
	    		System.out.println("输入接口迁移序列2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		
	    		System.out.println("输入接口迁移序列3xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time3 = findTimeAccordingToS0();
	    		if(time3.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列");
	    		
	    		if(compare(time1,time2) && compare(time2,time3))
	    			System.out.println("符合双向一致性测试");
	    		else
	    			System.out.println("不符合双向一致性测试");
	    		break;
	    	case "5":
	    		 f = false;
	    		 locationX = 0;
	    		 transitionX = 0;
	    		System.out.println("输入接口迁移序列xxx_xxx");
	    		temp = cin.nextLine(); 
	    		temp = cin.nextLine();
	    		
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("不存在此接口迁移序列");
	    		else
	    			System.out.println("存在此接口迁移序列"+"\nlocation最大时间："+locationX+"\ntransition最小时间:"+transitionX);
	    		
	    		
	    		
	    		
	    		break;
	    	default:
	    		System.out.println("输入错误！");
	    		break;
	    		
	    	}
	    
	    	System.out.println("输入要进行的一致性测试:1存在性；2前向一致性；3逆向一致性；4双向一致性测试；5实时测试 ");
		    
	    }
	    
	    
	    
	    
	}
	private static boolean compare(ArrayList<Double> t1, ArrayList<Double> t2) {
		// TODO 自动生成的方法存根
		for(int i = 0 ;i<t1.size(); i++)
			for(int j=0;j<t2.size();j++)
			{
				
				if(t1.get(i)>t2.get(j))
					return false;
			}
		return true;
	}
	private static ArrayList<Double> findTimeAccordingToS0() {
		// TODO 自动生成的方法存根
		ArrayList<Double> times =new ArrayList<Double>();
		
		
		for(Object OTemplate:templates)
	    {
	    	UppaalTemPlate templateI = (UppaalTemPlate) OTemplate;
	    	
	    	int count = 0;//计数第几个a！
	    	for(Object OTransition:templateI.getTransitions())
	    	{
	    		
	    		UppaalTransition transitionI = (UppaalTransition) OTransition;
	    		if(transitionI.out && transitionI.getName().equals(s[0]+"!"))//找到a！
	    		{
	    			count++;
	    			//从toName中找到第count个a?
	    			if(DFS(transitionI.getToName(),count,s[0]+"?",0,
	    					templateI.getLocations().get(transitionI.getSource()).getX(),//1
	    						transitionI.getX()))//加a！
	    				
	    				times.add(transitionI.getTime());//将所有符合序列的开始时间记录至times
	    			
	    		}
	    	}
	    }		
		return times;
		
		
	}
	/*static Comparator<UppaalTransition> comparator = new Comparator<UppaalTransition>() {
		@Override
		public int compare(UppaalTransition o1, UppaalTransition o2) {//排序？
			// TODO 自动生成的方法存根
			if(o1.getTime()>o2.getTime())
				return 1;
			else
				return 0;
		}     
	};*/
	private static boolean DFS(String templateName, int count, String name, int i,double lx, double tx) {
		// TODO 自动生成的方法存根
		if(i == s.length)
		{
			if(!f)
			{	
				locationX = lx;
				transitionX = tx;
			}
			return true;
		}
		int countb = 0;
		for(Object OTemplate:templates)
	    {
	    	UppaalTemPlate templateI = (UppaalTemPlate) OTemplate;
	    	if(templateI.getName().equals(templateName))//找到a？所在的template
	    	{
	    		Iterator transition_Iterator = (Iterator) templateI.getTransitions().iterator();
	    		while(transition_Iterator.hasNext()){
	    			
	    			UppaalTransition transitionI = (UppaalTransition) transition_Iterator.next();
	    			if(transitionI.out && transitionI.getName().equals(name))//是外部接口 并且 这个接口的message名是a？
	    			{
	    				count--;
	    				if(count == 0)//找到第count个a？
	    				{
	    					lx+= templateI.getLocations().get(transitionI.getTarget()).getX();//1+(5) .. (3)
	    					if(!transition_Iterator.hasNext())//没有下一个
	    						break;
	    					
	    					
		    				while(transition_Iterator.hasNext() )
	    					{
		    					transitionI = (UppaalTransition) transition_Iterator.next();
		    					
		    					tx+= transitionI.getX();//a!+(b+c!)
		    					
		    					
		    					if(transitionI.out == true)//下一个transition是接口
		    						break;
		    					else
		    						lx+= templateI.getLocations().get(transitionI.getTarget()).getX();//1+5+(6)
	    					}
		    				if(transitionI.out && transitionI.getName().equals(s[i+1]+"!"))//下一个是b！
		    				{
		    					countb++;
		    						
		    						if(DFS(transitionI.getToName(), countb, s[i+1]+"?", i+1,lx,tx))
		    						return true;
		    					
		    					
		    			
		    				}
	    			
	    				}	    			
	    			}
	    			
	    			if(i+1 == s.length)
	    			{
	    				if(!f)
	    				{	
	    					locationX = lx;
	    					transitionX = tx;
	    				}
	    				return true;
	    			}
	    			
	    			if(transitionI.out && transitionI.getName().equals(s[i+1]+"!"))
	    				countb++;
    	    	}
	    		
	    	}
	
	    }
		
		return false;
	}
}
