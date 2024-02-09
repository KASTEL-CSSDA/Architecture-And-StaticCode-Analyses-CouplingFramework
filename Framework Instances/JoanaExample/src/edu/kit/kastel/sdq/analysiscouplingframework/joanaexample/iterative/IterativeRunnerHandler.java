package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class IterativeRunnerHandler <T extends IResource> extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<IFile> filteredSelection = filterSelection(selection);

		IterativeRunner runner = new IterativeRunner();
		String configFilePath = "", partitionerJSONFilePath = "";
		
		for(IFile f : filteredSelection) {
			String path = f.getLocation().toString();
			if(path.endsWith("CONFIG.xml")) {
				configFilePath = path;
			} else if (path.endsWith("ITERATION_CONFIG.json")) {
				partitionerJSONFilePath = path;
			}
		}
		
		runner.start(configFilePath, partitionerJSONFilePath);
		return null;
	}
	
	public String getPlugInID() {
	    return "edu.kit.kastel.sdq.analysiscouplingframework.joanaexample";
	  }
	
	List<IFile> filterSelection(ISelection selection) {
		ArrayList<IFile> files = new ArrayList<IFile>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			for (Object o : structuredSelection) {
				if (o instanceof IFile) {
					files.add((IFile) o);
				}
			}
		}
		return files;
	}
}
