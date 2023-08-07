package edu.kit.kastel.sdq.analysiscouplingframework;

import java.util.ArrayList;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class Logger {
	private ArrayList <String> loggedLines = new ArrayList<>();
	private boolean isSilent;
	
	public Logger() {
		this(false);
	}
	public Logger(boolean isSilent) {
		this.isSilent = isSilent;
	}
	
	public void print(Result result) {
		String resultLine = result.getMessage();
		
		if (!this.isSilent) {
			System.out.println(resultLine);
		}
		loggedLines.add(resultLine);
	}

	public static void saveLogFile() {
		// TODO write back loggedLines
	}
}
