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
			throw new RuntimeException("Error! Invalid token!");
		
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
	
	// If the reference passed in is an id, uses MyParserHelper to get the CoordinatesWorld object.
	// Otherwise parses reference (latitude/longitude) to build the CoordinatesWorld object
	public static CoordinatesWorld parseCoordinatesWorld(String reference, MyParserHelper parser) {	
		if(reference == null || parser == null)
			throw new RuntimeException("Error! Invalid token!");
		
		// Is a reference id, example: $id
		if(reference.startsWith("$")) {
			return parser.getReference(reference);
		}
		
		//Lat/Lon doubles should be length 3 x / y, Full writeouts should be length 12 x * y ' z ".
//		if(!reference.contains("/"))
//				throw new RuntimeException("Error! Invalid token!");
		if(!reference.matches(".*/.*")) {
			throw new RuntimeException("Error! Invalid token!");
		}
		String latlong[] = reference.split("/");
		
		String latLonRegex = "[0-9]+\\*[0-9]+\\'[0-9]+\\.*[0-9]*\\\"";
		
		String lat = latlong[0];
		if(!lat.matches(latLonRegex)) {
			throw new RuntimeException("Error! Invalid token!");
		}
		
		String lon = latlong[1];
		if(!lon.matches(latLonRegex)) {
			throw new RuntimeException("Error! Invalid token!");
		}
		
		String[] latList = lat.split("\\*|\'|\"");
		String[] lonList = lon.split("\\*|\'|\"");
		
		int latDegrees = Integer.parseInt(latList[0]);
		int latMinutes = Integer.parseInt(latList[1]);
		double latSeconds = Double.parseDouble(latList[2]);
		
		int lonDegrees = Integer.parseInt(lonList[0]);
		int lonMinutes = Integer.parseInt(lonList[1]);
		double lonSeconds = Double.parseDouble(lonList[2]);
		
		return new CoordinatesWorld(new Latitude(latDegrees, latMinutes, latSeconds), new Longitude(lonDegrees, lonMinutes, lonSeconds));
	}
	
	public static CoordinatesDelta parseCoordinatesDelta(String token) {
		if(!token.matches(".*:.*")) {
			throw new RuntimeException("Error! Invalid token!");
		}
		String [] doubles = token.split(":");
		
		return new CoordinatesDelta(Double.parseDouble(doubles[0]), Double.parseDouble(doubles[1]));
	}
	
	// Checks whether the provided String can be parsed to a double value
	public static boolean checkStringIsDouble(String string) {
		if(string == null)
			return false;
		try {
			Double.parseDouble(string);
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	// Checks that the given string equals one of the strings within the passed in String array
	public static boolean checkStringIsOneOfTheseValues(String string, String[] values) {
		if(string == null || values == null || values.length == 0)
			return false;
		
		for(String value: values) {
			if(string.equalsIgnoreCase(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	//Check for standard Java variable name, underscore included
	public static boolean isStringStandardJavVar(String str) 
	{ 
		return SourceVersion.isName(str);
	}
	
	public static boolean checkKeyword(String str) {
		boolean key = false;
		if(str.equalsIgnoreCase("BOX"))
			key = true;
		if(str.equalsIgnoreCase("ACTUATOR"))
			key = true;
		if(str.equalsIgnoreCase("ACTUATORS"))
			key = true;
		if(str.equalsIgnoreCase("AND"))
			key = true;
		if(str.equalsIgnoreCase("ANGLE"))
			key = true;
		if(str.equalsIgnoreCase("AS"))
			key = true;
		if(str.equalsIgnoreCase("AT"))
			key = true;
		if(str.equalsIgnoreCase("BACKSLASH"))
			key = true;
		if(str.equalsIgnoreCase("BACKWARD"))
			key = true;
		if(str.equalsIgnoreCase("BRAKE"))
			key = true;
		if(str.equalsIgnoreCase("BRIDGE"))
			key = true;
		if(str.equalsIgnoreCase("CABOOSE"))
			key = true;
		if(str.equalsIgnoreCase("CAR"))
			key = true;
		if(str.equalsIgnoreCase("CATENARIES"))
			key = true;
		if(str.equalsIgnoreCase("CATENARY"))
			key = true;
		if(str.equalsIgnoreCase("CAUTION"))
			key = true;
		if(str.equalsIgnoreCase("CLOCKWISE"))
			key = true;
		if(str.equalsIgnoreCase("CLOSE"))
			key = true;
		if(str.equalsIgnoreCase("COLLISIONS"))
			key = true;
		if(str.equalsIgnoreCase("COLON"))
			key = true;
		if(str.equalsIgnoreCase("COMMIT"))
			key = true;
		if(str.equalsIgnoreCase("COUNTERCLOCKWISE"))
			key = true;
		if(str.equalsIgnoreCase("COUPLE"))
			key = true;
		if(str.equalsIgnoreCase("CREATE"))
			key = true;
		if(str.equalsIgnoreCase("CROSSBUCK"))
			key = true;
		if(str.equalsIgnoreCase("CROSSING"))
			key = true;
		if(str.equalsIgnoreCase("CROSSOVER"))
			key = true;
		if(str.equalsIgnoreCase("CURVE"))
			key = true;
		if(str.equalsIgnoreCase("DEFAULT"))
			key = true;
		if(str.equalsIgnoreCase("DELTA"))
			key = true;
		if(str.equalsIgnoreCase("DIESEL"))
			key = true;
		if(str.equalsIgnoreCase("DIGIT"))
			key = true;
		if(str.equalsIgnoreCase("DISABLE"))
			key = true;
		if(str.equalsIgnoreCase("DISTANCE"))
			key = true;
		if(str.equalsIgnoreCase("DO"))
			key = true;
		if(str.equalsIgnoreCase("DOLLAR"))
			key = true;
		if(str.equalsIgnoreCase("DOT"))
			key = true;
		if(str.equalsIgnoreCase("DOWN"))
			key = true;
		if(str.equalsIgnoreCase("DRAW"))
			key = true;
		if(str.equalsIgnoreCase("DRAWBRIDGE"))
			key = true;
		if(str.equalsIgnoreCase("ELECTRIC"))
			key = true;
		if(str.equalsIgnoreCase("ENABLE"))
			key = true;
		if(str.equalsIgnoreCase("END"))
			key = true;
		if(str.equalsIgnoreCase("ENGINE"))
			key = true;
		if(str.equalsIgnoreCase("ENTRY"))
			key = true;
		if(str.equalsIgnoreCase("EOF"))
			key = true;
		if(str.equalsIgnoreCase("EQUAL"))
			key = true;
		if(str.equalsIgnoreCase("FACING"))
			key = true;
		if(str.equalsIgnoreCase("FLATBED"))
			key = true;
		if(str.equalsIgnoreCase("FLOW"))
			key = true;
		if(str.equalsIgnoreCase("FOR"))
			key = true;
		if(str.equalsIgnoreCase("FORCE"))
			key = true;
		if(str.equalsIgnoreCase("FORWARD"))
			key = true;
		if(str.equalsIgnoreCase("FROM"))
			key = true;
		if(str.equalsIgnoreCase("GATE"))
			key = true;
		if(str.equalsIgnoreCase("GREATER"))
			key = true;
		if(str.equalsIgnoreCase("HASH"))
			key = true;
		if(str.equalsIgnoreCase("HEIGHT"))
			key = true;
		if(str.equalsIgnoreCase("IDENTIFIER"))
			key = true;
		if(str.equalsIgnoreCase("INTEGER"))
			key = true;
		if(str.equalsIgnoreCase("LAYOUT"))
			key = true;
		if(str.equalsIgnoreCase("LENGTH"))
			key = true;
		if(str.equalsIgnoreCase("LESS"))
			key = true;
		if(str.equalsIgnoreCase("LETTER"))
			key = true;
		if(str.equalsIgnoreCase("LIGHT"))
			key = true;
		if(str.equalsIgnoreCase("LOCATE"))
			key = true;
		if(str.equalsIgnoreCase("MAP"))
			key = true;
		if(str.equalsIgnoreCase("META_CLOCK"))
			key = true;
		if(str.equalsIgnoreCase("META_DO"))
			key = true;
		if(str.equalsIgnoreCase("META_EXIT"))
			key = true;
		if(str.equalsIgnoreCase("META_RUN"))
			key = true;
		if(str.equalsIgnoreCase("META_SCHEDULE"))
			key = true;
		if(str.equalsIgnoreCase("META_WAIT"))
			key = true;
		if(str.equalsIgnoreCase("NETWORK"))
			key = true;
		if(str.equalsIgnoreCase("NORTH"))
			key = true;
		if(str.equalsIgnoreCase("OCCUPANCY"))
			key = true;
		if(str.equalsIgnoreCase("OFF"))
			key = true;
		if(str.equalsIgnoreCase("ON"))
			key = true;
		if(str.equalsIgnoreCase("OPEN"))
			key = true;
		if(str.equalsIgnoreCase("ORIGIN"))
			key = true;
		if(str.equalsIgnoreCase("PASSENGER"))
			key = true;
		if(str.equalsIgnoreCase("PATH"))
			key = true;
		if(str.equalsIgnoreCase("PAUSE"))
			key = true;
		if(str.equalsIgnoreCase("POLE"))
			key = true;
		if(str.equalsIgnoreCase("POLES"))
			key = true;
		if(str.equalsIgnoreCase("POSITION"))
			key = true;
		if(str.equalsIgnoreCase("POWER"))
			key = true;
		if(str.equalsIgnoreCase("PRIMARY"))
			key = true;
		if(str.equalsIgnoreCase("PROCEED"))
			key = true;
		if(str.equalsIgnoreCase("QUOTE"))
			key = true;
		if(str.equalsIgnoreCase("RANGE"))
			key = true;
		if(str.equalsIgnoreCase("RATE"))
			key = true;
		if(str.equalsIgnoreCase("REAL"))
			key = true;
		if(str.equalsIgnoreCase("REFERENCE"))
			key = true;
		if(str.equalsIgnoreCase("RESPOND"))
			key = true;
		if(str.equalsIgnoreCase("RESUME"))
			key = true;
		if(str.equalsIgnoreCase("ROUNDHOUSE"))
			key = true;
		if(str.equalsIgnoreCase("SCREEN"))
			key = true;
		if(str.equalsIgnoreCase("SECONDARY"))
			key = true;
		if(str.equalsIgnoreCase("SEGMENT"))
			key = true;
		if(str.equalsIgnoreCase("SELECT"))
			key = true;
		if(str.equalsIgnoreCase("SEMAPHORE"))
			key = true;
		if(str.equalsIgnoreCase("SEMICOLON"))
			key = true;
		if(str.equalsIgnoreCase("SENSOR"))
			key = true;
		if(str.equalsIgnoreCase("SENSORS"))
			key = true;
		if(str.equalsIgnoreCase("SET"))
			key = true;
		if(str.equalsIgnoreCase("SIGNAL"))
			key = true;
		if(str.equalsIgnoreCase("SINGLE_LINE_COMMENT"))
			key = true;
		if(str.equalsIgnoreCase("SLASH"))
			key = true;
		if(str.equalsIgnoreCase("SPEED"))
			key = true;
		if(str.equalsIgnoreCase("SPURS"))
			key = true;
		if(str.equalsIgnoreCase("STAR"))
			key = true;
		if(str.equalsIgnoreCase("START"))
			key = true;
		if(str.equalsIgnoreCase("STATION"))
			key = true;
		if(str.equalsIgnoreCase("STEAM"))
			key = true;
		if(str.equalsIgnoreCase("STOCK"))
			key = true;
		if(str.equalsIgnoreCase("STOP"))
			key = true;
		if(str.equalsIgnoreCase("STRAIGHT"))
			key = true;
		if(str.equalsIgnoreCase("STRING"))
			key = true;
		if(str.equalsIgnoreCase("SUBSTATION"))
			key = true;
		if(str.equalsIgnoreCase("SUBSTATIONS"))
			key = true;
		if(str.equalsIgnoreCase("SUPPLY"))
			key = true;
		if(str.equalsIgnoreCase("SWITCH"))
			key = true;
		if(str.equalsIgnoreCase("SWITCHER"))
			key = true;
		if(str.equalsIgnoreCase("SYNC"))
			key = true;
		if(str.equalsIgnoreCase("TANK"))
			key = true;
		if(str.equalsIgnoreCase("TENDER"))
			key = true;
		if(str.equalsIgnoreCase("THAN"))
			key = true;
		if(str.equalsIgnoreCase("TICK"))
			key = true;
		if(str.equalsIgnoreCase("TO"))
			key = true;
		if(str.equalsIgnoreCase("TOKENIMAGE"))
			key = true;
		if(str.equalsIgnoreCase("TOWARD"))
			key = true;
		if(str.equalsIgnoreCase("TRACK"))
			key = true;
		if(str.equalsIgnoreCase("TRACKS"))
			key = true;
		if(str.equalsIgnoreCase("TURNOUT"))
			key = true;
		if(str.equalsIgnoreCase("TURNTABLE"))
			key = true;
		if(str.equalsIgnoreCase("UNCOUPLE"))
			key = true;
		if(str.equalsIgnoreCase("UNDERSCORE"))
			key = true;
		if(str.equalsIgnoreCase("UNSYNC"))
			key = true;
		if(str.equalsIgnoreCase("UP"))
			key = true;
		if(str.equalsIgnoreCase("UPDATE"))
			key = true;
		if(str.equalsIgnoreCase("USE"))
			key = true;
		if(str.equalsIgnoreCase("VIEW"))
			key = true;
		if(str.equalsIgnoreCase("WATER"))
			key = true;
		if(str.equalsIgnoreCase("WHEN"))
			key = true;
		if(str.equalsIgnoreCase("WIDTH"))
			key = true;
		if(str.equalsIgnoreCase("WITH"))
			key = true;
		if(str.equalsIgnoreCase("WORLD"))
			key = true;
		if(str.equalsIgnoreCase("WYE"))
			key = true;
		if(str.equalsIgnoreCase("DIRECTION"))
			key = true;
		if(str.equalsIgnoreCase("ACTUATORS"))
			key = true;
		
		return key;
	}
}