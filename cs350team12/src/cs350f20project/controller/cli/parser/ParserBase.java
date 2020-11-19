package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;

//ParserBase will not need editing. It extends every single command class.
public class ParserBase {
	public Tokenizer tokens;
	
	//Establishes an interface so any parser can call parse
	public A_Command parse() {return null;}
	
	//Constructor adds commands, sets the last one, and checks if we should make a new argument list
	//Don't worry about this.
	public ParserBase(Tokenizer tokens) {
		this.tokens = tokens;
		tokens.addCmd(tokens.getLast());
		tokens.setLast();
		tokens.addCmd(this.getClass().getSimpleName());
		tokens.nextArgList();
	}
	
	//verifyArg checks if a token can be added as an argument. If there are no more tokens, this return false.
	//In the case that a command ends, you can check if verifyArg returns false
	public boolean verifyArg(String token) {
		if(token!=null) {
			tokens.addArg(token);
			return true;
		}
		return false;
	}
	
	public A_Command checkArgs(String token) {
		if(verifyArg(token) == true) {
			return parse();
		}
		return tokens.InvalidToken();
	}
}
