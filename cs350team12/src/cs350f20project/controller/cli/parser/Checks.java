package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import javax.lang.model.SourceVersion;

import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;
import cs350f20project.datatype.Latitude;
import cs350f20project.datatype.Longitude;

public class Checks {
	//will check valid id and whether it starts with $ if the id is a reference
	public static boolean checkID(String id, boolean reference) {
		if(id == null)
			return false;
		
		String toCheck;
		if(reference) {
			if(id.startsWith("$")) {
				toCheck = id.substring(1);
			}
			else {
				return false;
			}
		}
		else {
			toCheck = id;
		}
		
		return isStringStandardJavVar(toCheck);
	}
	
	public static boolean expectedString(String check, String expect) {
		if(check.equalsIgnoreCase(expect))
			return true;
		return false;
	}
	
	public static boolean booleanFromString(String check, String t, String f) {
		if(check.equalsIgnoreCase(t))
			return true;
		if(check.equalsIgnoreCase(f))
			return false;
		throw new RuntimeException("Error! invalid token!");
	}
	
	public static CoordinatesWorld parseCoordinatesWorld(ArrayList<String> list, boolean canBeReference, MyParserHelper parser) {
		String conv = "";
		for(int i = 0; i < list.size(); ++i) {
			conv+=list.get(i);
		}
		CoordinatesWorld coords = new CoordinatesWorld(new Latitude(0.0), new Longitude(0.0));
		//Check based on length. ID should be length one, Lat/Lon doubles should be length 3 x / y, Full writeouts should be length 12 x * y ' z ".
		if(conv.startsWith("$")  && canBeReference) {
			
			if(!list.get(0).startsWith("$"))
				throw new RuntimeException("Error! Invalid token!");
			String id2 = list.get(0);
			Checks.checkID(id2, true);
			parser.addReference(id2, coords);
			coords = parser.getReference(id2);
			
		}
		else {
			if(!conv.contains("/"))
				throw new RuntimeException("Error! Invalid token!");
			String latlong[] = conv.split("/");
			String lat = latlong[0];
			String lon = latlong[1];
			String[] latlist = lat.split("\\*");
			String[] lonlist = lon.split("\\*");
			String[] latlist2 = latlist[1].split("\\'");
			String[] lonlist2 = lonlist[1].split("\\'");
			String latz = latlist2[1].replace('"', ' ');
			latz = latz.strip();
			String lonz = lonlist2[1].replace('"', ' ');
			lonz = lonz.strip();
			String[] lonlist3 = {lonlist[0], lonlist2[0], lonz};
			String[] latlist3 = {latlist[0], latlist2[0], latz};
			int x1, x2, y1, y2;
			double x3, y3;
			x1 = Integer.parseInt(latlist3[0]);
			x2 = Integer.parseInt(lonlist3[1]);
			y1 = Integer.parseInt(latlist3[0]);
			y2 = Integer.parseInt(lonlist3[1]);
			x3 = Double.parseDouble(latlist3[2]);
			y3 = Double.parseDouble(lonlist3[2]);
			coords = new CoordinatesWorld(new Latitude(x1, x2, x3), new Longitude(y1, y2, y3));
			
		}
		return coords;
	}
	
	public static CoordinatesWorld parseCoordinatesWorld(String list, boolean canBeReference, MyParserHelper parser) {
		ArrayList<String> pass = new ArrayList<String>();
		pass.add(list);
		return parseCoordinatesWorld(pass, canBeReference, parser);
	}
	
	public static CoordinatesDelta parseCoordinatesDelta(String list) {
		ArrayList<String> pass = new ArrayList<String>();
		pass.add(list);
		return parseCoordinatesDelta(pass);
	}
	
	public static CoordinatesDelta parseCoordinatesDelta(ArrayList<String> list) {
		String[] doubles = new String[2];
		String conv = "";
		for(int i = 0; i < list.size(); ++i) {
			conv+=list.get(i);
		}
		try {
			doubles = conv.split(":");
		} catch (Exception e) {
			throw new RuntimeException("Error! Invalid token!");
		}
		double x = Double.parseDouble(doubles[0]);
		double y = Double.parseDouble(doubles[1]);
		return new CoordinatesDelta(x, y);
	}
	
	//Check for standard Java variable name, underscore included
	public static boolean isStringStandardJavVar(String str) 
	{ 
		return SourceVersion.isName(str);
	}
}
