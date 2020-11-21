package cs350f20project.controller.cli.parser;

// This is just a tester class for our purposes. Not a part of the solution
import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;

public class Tester {

	public static void main(String[] args) {
		
		MyParserHelper parserHelper = new MyParserHelper(new ActionProcessor(new CommandLineInterface(new Controller())));
		String commandText = "DO SET id SPEED 1;DO BRAKE id ";
		CommandParser parser = new CommandParser(parserHelper, commandText);
		parser.parse();

	}

}
