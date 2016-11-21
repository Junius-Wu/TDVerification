package UPPAAL;

import java.util.ArrayList;


public class Automatic {
	
	String name;
	ArrayList<OutInterFace> outerInterFace;//½Ó¿Ú
	ArrayList<UppaalTemPlate> temPlates;
	
	
	
	public ArrayList<UppaalTemPlate> getTemPlates() {
		return temPlates;
	}

	public void setTemPlates(ArrayList<UppaalTemPlate> temPlates) {
		this.temPlates = temPlates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<OutInterFace> getOuterInterFace() {
		return outerInterFace;
	}

	public void setOuterInterFace(ArrayList<OutInterFace> outerInterFace) {
		this.outerInterFace = outerInterFace;
	}

	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "name="+name+outerInterFace.toString();
		}
}
