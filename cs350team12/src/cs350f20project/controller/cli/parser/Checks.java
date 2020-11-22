package cs350f20project.controller.cli.parser;

public class Checks {
	public static boolean checkID(String id) {
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
	
	//Check for standard Java variable name, underscore included
	public static boolean isStringStandardJavVar(String str) 
	{ 
	    return (str.matches("[a-zA-Z_$]")); 
	}
}
