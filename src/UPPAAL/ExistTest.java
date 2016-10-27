package UPPAAL;

import java.awt.List;
import java.io.File;
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
	static String []inputTransitionNames = null;
	static ArrayList<UppaalTransition> transitions = new ArrayList<>();
	static double locationX = -1;
	static double transitionX = -1;
	static boolean f = false;
	static ArrayList<Double> time1 = new ArrayList<Double>();
	static ArrayList<Double> time2 = new ArrayList<Double>();
	static ArrayList<Double> time3 = new ArrayList<Double>();
	static ArrayList<Double> time2First = new ArrayList<Double>();
	static ArrayList<Double> time2Last = new ArrayList<Double>();
	
	static HashMap<String, UppaalLocation> locationByKey = new HashMap<>();
	static ArrayList<UppaalLocation> isVisitedLocaiton = new ArrayList<>();
	public static void main(String[] args) throws Exception 
	{
		
		SAXReader reader=new SAXReader();//��ȡ������
	    Document dom= reader.read("1.xml");//����XML��ȡ���������ĵ���dom����
	    Element root=dom.getRootElement();//��ȡ���ڵ�
	    
	    Read uppaal=new Read();
	    uppaal.load(root);
	    
	    
	    
	    // ���transiton��sourceLocation��transitionList��
	    templates = uppaal.getUppaalTemplates();
	    
	    for(UppaalLocation locationI : templates.get(0).getLocations()) {
	    	locationByKey.put(locationI.getId(), locationI);
	    }
	    for(UppaalTransition transitionI : templates.get(0).getTransitions()) {
	    	String sourceId = "id" + transitionI.getSource();
	    	UppaalLocation sourceLocation = locationByKey.get(sourceId);
	    	sourceLocation.getTransitions().add(transitionI);
	    }
	    
	    // ��transition ����ʱ���������
	    transitions = templates.get(0).transitions;
	    Collections.sort(transitions, new Comparator<UppaalTransition>() {
	        @Override
	        public int compare(UppaalTransition o1, UppaalTransition o2) {
	          return (int)(o1.getTime() - o2.getTime());
	        }
	      });
	    for (UppaalTransition transition : templates.get(0).getTransitions()) {
			System.out.println(transition.getName() + " " + transition.getTime());
			
		}
	    
	    System.out.println("����Ҫ���е�һ���Բ���:1�����ԣ�2ǰ��һ���ԣ�3����һ���ԣ�4˫��һ���Բ��ԣ�5ʵʱ���� ");
	    String temp = null;
	    while(cin.hasNext()) 
	    {
	    	
	    	String c = cin.next();
	    	switch(c)
	    	{
	    	case "1":
	    		System.out.println("������Ϣ����xxx xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time1 = findTimeAccordingToInputMessage().get(0);
	    		if(time1.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		break;
	    	
	    	case "2":
	    		System.out.println("������Ϣ����1  xxx xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time1 = findTimeAccordingToInputMessage().get(1);
	    		if(time1.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		System.out.println("������Ϣ����2  xxx xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time2 = findTimeAccordingToInputMessage().get(0);
	    		if(time2.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		
	    		if(compare(time1,time2))
	    			System.out.println("***����ǰ��һ����");
	    		else
	    			System.out.println("***������ǰ��һ����");
	    		break;
	    	case "3":
	    		System.out.println("������Ϣ����1  xxx xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time2 = findTimeAccordingToInputMessage().get(0);
	    		if(time2.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		System.out.println("������Ϣ����2  xxx xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time1 = findTimeAccordingToInputMessage().get(1);
	    		if(time1.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		
	    		if(!compare(time1,time2))
	    			System.out.println("***��������һ����");
	    		else
	    			System.out.println("***����������һ����");
	    		break;
	    	case "4":
	    		System.out.println("������Ϣ����1  xxx xxx");
	    		 temp = cin.nextLine();
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time1 = findTimeAccordingToInputMessage().get(1);
	    		if(time1.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		
	    		System.out.println("������Ϣ����2  xxx xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time2First = findTimeAccordingToInputMessage().get(0);
	    		 time2Last =findTimeAccordingToInputMessage().get(1);
	    		if(time2First.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		
	    		System.out.println("������Ϣ����3  xxx xxx");
	    		 
	    		 temp = cin.nextLine();
	    		 inputTransitionNames = temp.split(" ");
	    		
	    		 time3 = findTimeAccordingToInputMessage().get(0);
	    		if(time3.isEmpty())
	    			{System.out.println("***�����ڴ���Ϣ����"); break;}
	    		else
	    			System.out.println("***���ڴ���Ϣ����");
	    		
	    		if(compare(time1,time2First) && compare(time2Last,time3))
	    			System.out.println("***����˫��һ���Բ���");
	    		else
	    			System.out.println("***������˫��һ���Բ���");
	    		break;
	    	case "5":
	    		 f = false;
	    		 locationX = 0;
	    		 transitionX = 0;
	    		System.out.println("���� ��Сʱ��    ���ʱ��");
	    		double min = cin.nextDouble();
	    		double max = cin.nextDouble();
	    		UppaalLocation initLocation = templates.get(0).getLocations().get(0);
	    		UppaalLocation virtualLocation = new UppaalLocation();
	    		virtualLocation.init = initLocation.init;
	    		virtualLocation.setTransitions(initLocation.transitions);
	    		System.out.println("��ʼ״̬��" + initLocation.getName());
	    		// isVisitedLocaiton.add(initLocation);
	    		isVisitedLocaiton.clear();
	    		if (searchPathWithRangeOfTime(new UppaalTransition(), virtualLocation, 0, 0, min, max)) {
					System.out.println("***�������Լ��***");
				} else {
					System.out.println("***���������Լ��***");
				}
	    		
	    		break;
	    	default:
	    		System.out.println("�������");
	    		break;
	    		
	    	}
	    
	    	System.out.println("����Ҫ���е�һ���Բ���:1�����ԣ�2ǰ��һ���ԣ�3����һ���ԣ�4˫��һ���Բ��ԣ�5ʵʱ���� ");
		    
	    }
	    
	    
	    
	    
	}
	// �Ƚ�t1<t2 ֻҪ����һ�����㼴��
	private static boolean compare(ArrayList<Double> t1, ArrayList<Double> t2) {
		for(int i = 0 ; i < t1.size(); i++)
			for(int j = 0; j < t2.size();j++)
			{
				if(t1.get(i) < t2.get(j))
					return true;
			}
		return false;
	}
	// �����������Ϣ����  ������Ϣ���� �� ��ʼʱ�� �� ����ʱ��
	private static ArrayList<ArrayList<Double>> findTimeAccordingToInputMessage() {
		HashSet<String> isPrintedSet = new HashSet<>();
		ArrayList<ArrayList<Double>> res = new ArrayList<>();
		ArrayList<Double> startTimes =new ArrayList<Double>();
		ArrayList<Double> endTimes =new ArrayList<Double>();
		res.add(startTimes);
    	res.add(endTimes);
		double time0 = 0;
		double time1 = 0;
    	int count = 0;//�����ڼ���a
    	for (int i = 0; i < transitions.size(); i++) {
    		isPrintedSet.clear();
    		if (!(transitions.get(i).out && transitions.get(i).getName().charAt(transitions.get(i).getName().length() - 1) != '?')) {
				continue;
			}
			for (int j = i; j < transitions.size(); j++) {
				UppaalTransition transitionI = transitions.get(j);
				if(transitionI.out && transitionI.getName().charAt(transitionI.getName().length() - 1) != '?')
	    		{
	    			if (transitionI.getName().equals(inputTransitionNames[count])) { // ��һ���ⲿ��Ϣ�����������һ��
						
						if (count == 0) { // ��¼��һ��ʱ��
							time0 = transitionI.getTime();
						}
	    				if (count == inputTransitionNames.length - 1) { // ��¼���һ��ʱ��
							time1 = transitionI.getTime();
						}
	    				if (!(isPrintedSet.contains(inputTransitionNames[count] + "--" + transitionI.getTime()))) {
							System.out.println("********************");
	    					System.out.println("*�ҵ���Ϣ��" + inputTransitionNames[count]);
							System.out.println("*����Ϣ������ʱ��Ϊ��" + transitionI.getTime());
							isPrintedSet.add(inputTransitionNames[count] + "--" + transitionI.getTime());
						} 
	    				
						count++;
		    			if (count == inputTransitionNames.length) {
							break;
						}
					} else { // ��һ���ⲿ��Ϣ�������������һ��
						System.out.println("-ӳ���" + (count+1) + "����Ϣʱ������");
						System.out.println("-��" + (count+1) + "����Ϣ��" + transitionI.getName());
						System.out.println("-�����" + (count+1) + "����Ϣ��" + inputTransitionNames[count]);
						break;
					}
	    			
	    		} 
			}
			
			if (count == inputTransitionNames.length) {
				
				if (!startTimes.contains(time0)) { // �����ظ���·��
					startTimes.add(time0);
					endTimes.add(time1);
					System.out.println("********************�ҵ�"+ startTimes.size() +"������������Ϣ���е�·��**********************");

				}
				
				
			}
			// ��� ��������һ����ʼ
			count = 0;
		}	
    	
		return res;
	}
	static Comparator<UppaalTransition> comparator = new Comparator<UppaalTransition>() {
		@Override
		public int compare(UppaalTransition o1, UppaalTransition o2) {//����
			// TODO �Զ����ɵķ������
			if(o1.getTime()>o2.getTime())
				return 1;
			else
				return 0;
		}     
	};
	private static boolean searchPathWithRangeOfTime(UppaalTransition lastTransition, UppaalLocation location, double locationMinTime, double transitionMaxTime, double requestMin, double resquestMax) {
		if (location.getFinl().equals("true")) { // ������ֹ�ڵ�
			if (locationMinTime >= requestMin && locationMinTime + transitionMaxTime <= resquestMax) {
				System.out.println("+��ֹ״̬��" + location.getName() + "���������ʱ��Ҫ��");
				System.out.println("+��Сʱ�䣺" + locationMinTime + "  ���ʱ�䣺" + (locationMinTime + transitionMaxTime));
				System.out.println("+����ʱ��Ҫ���·��Ϊ��");
				UppaalLocation initLocation = templates.get(0).getLocations().get(0);
				System.out.print("+" + initLocation.getName());
				for(UppaalLocation locationI : isVisitedLocaiton) {
					System.out.print("->" + locationI.getName());
				}
				return true;
			} else {
				System.out.println("-������ֹ�ڵ㣬�������������ʱ��Ҫ��");
				System.out.println("-��Сʱ�䣺" + locationMinTime + "  ���ʱ�䣺" + (locationMinTime + transitionMaxTime));
				return false;
			}
		}
		
		for(UppaalTransition transitionI : location.getTransitions()) {
			
			if (!transitionI.getName().equals("null")) {
				int nameLenth = transitionI.getName().length();
				if (transitionI.getName().charAt(nameLenth - 1) == '?') {
					if (!lastTransition.getName().equals(transitionI.getName().substring(0, nameLenth - 1))) {
						continue;
					}
				}
			}
			
			String targetId = "id" + transitionI.getTarget();
			UppaalLocation targetLocation = locationByKey.get(targetId);
			if (!isVisitedLocaiton.contains(targetLocation)) {
				System.out.println("����״̬��" + targetLocation.getName());
				isVisitedLocaiton.add(targetLocation);
				if (searchPathWithRangeOfTime(transitionI, targetLocation, locationMinTime + location.getX(), 
						transitionMaxTime + transitionI.getDuration(), requestMin, resquestMax)) {
					return true;
				}
				isVisitedLocaiton.remove(isVisitedLocaiton.size() - 1); // huisu
			}
			
		}
		
		return false;
	}
	private static boolean DFS(String templateName, int count, String name, int i,double lx, double tx) {
		// TODO �Զ����ɵķ������
		if(i == inputTransitionNames.length)
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
		    				if(transitionI.out && transitionI.getName().equals(inputTransitionNames[i+1]+"!"))//��һ����b��
		    				{
		    					countb++;
		    						
		    						if(DFS(transitionI.getToName(), countb, inputTransitionNames[i+1]+"?", i+1,lx,tx))
		    						return true;
		    					
		    					
		    			
		    				}
	    			
	    				}	    			
	    			}
	    			
	    			if(i+1 == inputTransitionNames.length)
	    			{
	    				if(!f)
	    				{	
	    					locationX = lx;
	    					transitionX = tx;
	    				}
	    				return true;
	    			}
	    			
	    			if(transitionI.out && transitionI.getName().equals(inputTransitionNames[i+1]+"!"))
	    				countb++;
    	    	}
	    		
	    	}
	
	    }
		
		return false;
	}
}
