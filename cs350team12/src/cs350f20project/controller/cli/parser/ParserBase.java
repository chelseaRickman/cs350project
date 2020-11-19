package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;

public class ParserBase {
	public Tokenizer tokens;
	
	public A_Command parse() {return null;}
	
	public ParserBase(Tokenizer tokens) {
		this.tokens = tokens;
		tokens.addCmd(tokens.getLast());
		
	}
	
	public void setLast() {
		tokens.setLast(this.getClass().getSimpleName());
	}
	
	public boolean getArgs(String token) {
		if(token!=null) {
			tokens.addArg(token);
			return true;
		}
		return false;
	}
	
	public A_Command checkArgs(String token) {
		if(getArgs(token) == true) {
			return parse();
		}
		return tokens.InvalidToken();
	}
}
