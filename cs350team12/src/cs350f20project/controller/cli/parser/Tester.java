package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

/* This is just a tester class for our purposes. Not a part of the solution
1 ( ( Rule#66 | Rule#67 ) ( ';' )? )*

2  DO BRAKE id
6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE ): IMPLEMENTED
8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY ): IMPLEMENTED
11 DO SET id DIRECTION ( FORWARD | BACKWARD ): IMPLEMENTED
12 DO SET REFERENCE ENGINE id
15 DO SET id SPEED number

22 CREATE POWER CATENARY id1 WITH POLES idn+: IMPLEMENTED
23 CREATE POWER POLE id1 ON TRACK id2 DISTANCE number FROM ( START | END ): IMPLEMENTED
24 CREATE POWER STATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH ( SUBSTATION | SUBSTATIONS )idn+
25 CREATE POWER SUBSTATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH CATENARIES idn+
28 CREATE STOCK CAR id AS BOX CommandCreateStockCarBox
29 CREATE STOCK CAR id AS CABOOSE CommandCreateStockCarCaboose
30 CREATE STOCK CAR id AS FLATBED CommandCreateStockCarFlatbed
31 CREATE STOCK CAR id AS PASSENGER CommandCreateStockCarPassenger
32 CREATE STOCK CAR id AS TANK CommandCreateStockCarTank
33 CREATE STOCK CAR id AS TENDER CommandCreateStockCarTender
34 CREATE STOCK ENGINE id1 AS DIESEL ON TRACK id2 DISTANCE number FROM ( START | END ) FACING ( START | END ) CommandCreateStockEngineDiesel
39 CREATE TRACK BRIDGE DRAW id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ANGLE angle
40 CREATE TRACK BRIDGE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
41 CREATE TRACK CROSSING id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
42 CREATE TRACK CROSSOVER id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 START coordinates_delta3 END coordinates_delta4
43 CREATE TRACK CURVE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ( ( DISTANCE ORIGIN number ) | ( ORIGIN coordinates_delta3 ) )
44 CREATE TRACK END id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
45 CREATE TRACK LAYOUT id1 WITH TRACKS idn+
46 CREATE TRACK ROUNDHOUSE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA ORIGIN coordinates_delta1 ANGLE ENTRY angle1 START angle2 END angle3 WITH integer SPURS LENGTH number1 TURNTABLE LENGTH number2
47 CREATE TRACK STRAIGHT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
48 CREATE TRACK SWITCH TURNOUT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) STRAIGHT DELTA START coordinates_delta1 END coordinates_delta2 CURVE DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number
49 CREATE TRACK SWITCH WYE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 DISTANCE ORIGIN number1 DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number2

51 @EXIT
52 @RUN string
55 CLOSE VIEW id
56 OPEN VIEW id1 ORIGIN ( coordinates_world | ( '$' id2 ) ) WORLD WIDTH integer1 SCREEN WIDTH integer2 HEIGHT integer3
60 COMMIT
61 COUPLE STOCK id1 AND id2
62 LOCATE STOCK id1 ON TRACK id2 DISTANCE number FROM ( START | END )
65 UNCOUPLE STOCK id1 AND id2
66 USE id AS REFERENCE coordinates_world
67 Rule#2 through Rule#65
*/

import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;

public class Tester {

	public static void main(String[] args) {
		/*ArrayList<String> testarr = new ArrayList();
		for(int i = 0; i < 2; ++i) {
		testarr.add("1");
		testarr.add("*");
		testarr.add("1");
		testarr.add("'");
		testarr.add("1.0");
		testarr.add("\"");
		}
		CoordinatesWorld delt = Checks.parseCoordinatesWorld(testarr, false);
		System.out.print(delt.toString());*/
		MyParserHelper parserHelper = new MyParserHelper(new ActionProcessor(new CommandLineInterface(new Controller())));
		String commandText = "DO SELECT SWITCH id PATH Secondary";
		CommandParser parser = new CommandParser(parserHelper, commandText);
		parser.parse();

	}
	
}
