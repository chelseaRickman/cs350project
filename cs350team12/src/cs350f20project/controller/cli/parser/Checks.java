package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;
import cs350f20project.datatype.Latitude;
import cs350f20project.datatype.Longitude;

public class Checks {
	//will check valid id and wether it starts with $
	public static boolean checkID(String id, boolean reference) {
		if(!id.startsWith("$") && reference == true)
			throw new RuntimeException("Error! Id references should start with $!");
		//check this for alphanumerics
		String check = id.substring(1);
		return true;
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
	
	public static CoordinatesWorld parseCoordinatesWorld(ArrayList<String> list, boolean canBeReference) {
		CoordinatesWorld coords = new CoordinatesWorld(new Latitude(0.0), new Longitude(0.0));
		//Check based on length. ID should be length one, Lat/Lon doubles should be length 3 x / y, Full writeouts should be length 12 x * y ' z ".
		if(list.size() == 1  && canBeReference) {
			
			if(!list.get(0).startsWith("$"))
				throw new RuntimeException("Error! Invalid token!");
			String id2 = list.get(0);
			Checks.checkID(id2, true);
			MyParserHelper parserHelper = new MyParserHelper(new ActionProcessor(new CommandLineInterface(new Controller())));
			coords = parserHelper.getReference(id2);
		}
		//parse a lat long /
		else if(list.size() == 3) {
			double x= Double.parseDouble(list.get(0));
			if(list.get(1) != "/")
				throw new RuntimeException("Error! Invalid token!");
			double y = Double.parseDouble(list.get(2));
			coords = new CoordinatesWorld(new Latitude(x), new Longitude(y));
		}
		//parse the ultimate: A full writeout
		else if(list.size() == 12) {
			int x1, x2;
			double x3;
			int y1, y2;
			double y3;
			//try to remove any non-relevant coords
			try {
				list.remove("*");
				list.remove("*");
				list.remove("'");
				list.remove("'");
				list.remove("\"");
				list.remove("\"");
			} catch (Exception e) {
				throw new RuntimeException("Error! Invalid token!");
			}
			x1 = Integer.parseInt(list.get(0));
			x2 = Integer.parseInt(list.get(1));
			x3 = Double.parseDouble(list.get(2));
			y1 = Integer.parseInt(list.get(3));
			y2 = Integer.parseInt(list.get(4));
			y3 = Double.parseDouble(list.get(5));
			coords = new CoordinatesWorld(new Latitude(x1, x2, x3), new Longitude(y1, y2, y3));
		}
		else {throw new RuntimeException("Error! Invalid token!");}
		return coords;
	}
	
	public static CoordinatesDelta parseCoordinatesDelta(ArrayList<String> list) {
		try {
		list.remove(":");
		} catch (Exception e) {
			throw new RuntimeException("Error! Invalid token!");
		}
		double x = Double.parseDouble(list.get(0));
		double y = Double.parseDouble(list.get(1));
		return new CoordinatesDelta(0.0,0.0);
	}
	
	//Check for standard Java variable name, underscore included
	public static boolean isStringStandardJavVar(String str) 
	{ 
	    return (str.matches("[a-zA-Z_$]")); 
	}
}
