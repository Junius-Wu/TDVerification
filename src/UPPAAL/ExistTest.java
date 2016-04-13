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
		
		SAXReader reader=new SAXReader();//��ȡ������
	    Document dom= reader.read("UPPAAL.xml");//����XML��ȡ���������ĵ���dom����
	    Element root=dom.getRootElement();//��ȡ���ڵ�
	    
	    Read uppaal=new Read();
	    uppaal.load(root);
	    
	    
	    templates = uppaal.getUppaalTemplates();
	    
	    /*ArrayList <UppaalTransition> messageList = new  ArrayList <UppaalTransition>();������Ϣ��
	   
	    for(Object OTemplate:templates)
	    {
	    	UppaalTemPlate templateI = (UppaalTemPlate) OTemplate;
	    	
	    	for(Object OTransition:templateI.getTransitions())
	    	{
	    		UppaalTransition transitionI = (UppaalTransition) OTransition;
	    		if(transitionI.getName().charAt(transitionI.getName().length()-1) == '!')
	    			messageList.add(transitionI);//��ӷ��ͷ���transition
	    	}
	    }
	    
	    Collections.sort(messageList,comparator);//��messageList����ʱ���������
	     */	    
	   
	    System.out.println("����Ҫ���е�һ���Բ���:1�����ԣ�2ǰ��һ���ԣ�3����һ���ԣ�4˫��һ���Բ��ԣ�5ʵʱ���� ");
	    String temp = null;
	    while(cin.hasNext()) 
	    {
	    	String c = cin.next();
	    	switch(c)
	    	{
	    	case "1":
	    		System.out.println("����ӿ�Ǩ������xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		break;
	    	
	    	case "2":
	    		System.out.println("����ӿ�Ǩ������1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		System.out.println("����ӿ�Ǩ������2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		
	    		if(compare(time1,time2))
	    			System.out.println("����ǰ��һ����");
	    		else
	    			System.out.println("������ǰ��һ����");
	    		break;
	    	case "3":
	    		System.out.println("����ӿ�Ǩ������1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		System.out.println("����ӿ�Ǩ������2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		
	    		if(!compare(time1,time2))
	    			System.out.println("��������һ����");
	    		else
	    			System.out.println("����������һ����");
	    		break;
	    	case "4":
	    		System.out.println("����ӿ�Ǩ������1xxx_xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		
	    		System.out.println("����ӿ�Ǩ������2xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time2 = findTimeAccordingToS0();
	    		if(time2.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		
	    		System.out.println("����ӿ�Ǩ������3xxx_xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 s = temp.split("_");
	    		
	    		 time3 = findTimeAccordingToS0();
	    		if(time3.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������");
	    		
	    		if(compare(time1,time2) && compare(time2,time3))
	    			System.out.println("����˫��һ���Բ���");
	    		else
	    			System.out.println("������˫��һ���Բ���");
	    		break;
	    	case "5":
	    		 f = false;
	    		 locationX = 0;
	    		 transitionX = 0;
	    		System.out.println("����ӿ�Ǩ������xxx_xxx");
	    		temp = cin.nextLine(); 
	    		temp = cin.nextLine();
	    		
	    		 s = temp.split("_");
	    		
	    		 time1 = findTimeAccordingToS0();
	    		if(time1.isEmpty())
	    			System.out.println("�����ڴ˽ӿ�Ǩ������");
	    		else
	    			System.out.println("���ڴ˽ӿ�Ǩ������"+"\nlocation���ʱ�䣺"+locationX+"\ntransition��Сʱ��:"+transitionX);
	    		
	    		
	    		
	    		
	    		break;
	    	default:
	    		System.out.println("�������");
	    		break;
	    		
	    	}
	    
	    	System.out.println("����Ҫ���е�һ���Բ���:1�����ԣ�2ǰ��һ���ԣ�3����һ���ԣ�4˫��һ���Բ��ԣ�5ʵʱ���� ");
		    
	    }
	    
	    
	    
	    
	}
	private static boolean compare(ArrayList<Double> t1, ArrayList<Double> t2) {
		// TODO �Զ����ɵķ������
		for(int i = 0 ;i<t1.size(); i++)
			for(int j=0;j<t2.size();j++)
			{
				
				if(t1.get(i)>t2.get(j))
					return false;
			}
		return true;
	}
	private static ArrayList<Double> findTimeAccordingToS0() {
		// TODO �Զ����ɵķ������
		ArrayList<Double> times =new ArrayList<Double>();
		
		
		for(Object OTemplate:templates)
	    {
	    	UppaalTemPlate templateI = (UppaalTemPlate) OTemplate;
	    	
	    	int count = 0;//�����ڼ���a��
	    	for(Object OTransition:templateI.getTransitions())
	    	{
	    		
	    		UppaalTransition transitionI = (UppaalTransition) OTransition;
	    		if(transitionI.out && transitionI.getName().equals(s[0]+"!"))//�ҵ�a��
	    		{
	    			count++;
	    			//��toName���ҵ���count��a?
	    			if(DFS(transitionI.getToName(),count,s[0]+"?",0,
	    					templateI.getLocations().get(transitionI.getSource()).getX(),//1
	    						transitionI.getX()))//��a��
	    				
	    				times.add(transitionI.getTime());//�����з������еĿ�ʼʱ���¼��times
	    			
	    		}
	    	}
	    }		
		return times;
		
		
	}
	/*static Comparator<UppaalTransition> comparator = new Comparator<UppaalTransition>() {
		@Override
		public int compare(UppaalTransition o1, UppaalTransition o2) {//����
			// TODO �Զ����ɵķ������
			if(o1.getTime()>o2.getTime())
				return 1;
			else
				return 0;
		}     
	};*/
	private static boolean DFS(String templateName, int count, String name, int i,double lx, double tx) {
		// TODO �Զ����ɵķ������
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
	    	if(templateI.getName().equals(templateName))//�ҵ�a�����ڵ�template
	    	{
	    		Iterator transition_Iterator = (Iterator) templateI.getTransitions().iterator();
	    		while(transition_Iterator.hasNext()){
	    			
	    			UppaalTransition transitionI = (UppaalTransition) transition_Iterator.next();
	    			if(transitionI.out && transitionI.getName().equals(name))//���ⲿ�ӿ� ���� ����ӿڵ�message����a��
	    			{
	    				count--;
	    				if(count == 0)//�ҵ���count��a��
	    				{
	    					lx+= templateI.getLocations().get(transitionI.getTarget()).getX();//1+(5) .. (3)
	    					if(!transition_Iterator.hasNext())//û����һ��
	    						break;
	    					
	    					
		    				while(transition_Iterator.hasNext() )
	    					{
		    					transitionI = (UppaalTransition) transition_Iterator.next();
		    					
		    					tx+= transitionI.getX();//a!+(b+c!)
		    					
		    					
		    					if(transitionI.out == true)//��һ��transition�ǽӿ�
		    						break;
		    					else
		    						lx+= templateI.getLocations().get(transitionI.getTarget()).getX();//1+5+(6)
	    					}
		    				if(transitionI.out && transitionI.getName().equals(s[i+1]+"!"))//��һ����b��
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
