package com.jun.xsutil;

import com.jun.bean.Automata;
import com.jun.bean.Template;

import UPPAAL.Automatic;
import UPPAAL.UppaalTemPlate;

public class XMLBean2UppaalTemplate {

	private static Automata mAutomata;
	
	public static UppaalTemPlate XMLBean2UppaalTemplate(Automata xmlBeanAutomata) {
		mAutomata = xmlBeanAutomata;
		UppaalTemPlate resTemplate = new UppaalTemPlate();
		Template theOnlyOneTemplate = mAutomata.getTemplateList().get(0);
		resTemplate.setName(theOnlyOneTemplate.getName());
		
		
		
		
		return resTemplate;
	}
}
