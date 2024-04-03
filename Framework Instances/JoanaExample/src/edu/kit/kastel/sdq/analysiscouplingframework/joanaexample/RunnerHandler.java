package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

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


/**
 * The Handler is the entrypoint for the Plugin execution via UI command.
 * 
 * @author Jonas Lehmann
 *
 * @param <T> Type of IResource, for the UIs selection, which should be a file CONFIG.xml
 */
public class RunnerHandler <T extends IResource> extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<IFile> filteredSelection = filterSelection(selection);

		Runner runner = new Runner();
		IFile file = filteredSelection.get(0);
		
		runner.start(file.getLocation().toString());
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
