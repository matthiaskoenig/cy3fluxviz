package org.cy3fluxviz;

import java.util.Properties;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cy3sbml.SBML;
import org.cy3sbml.SBMLManager;
import org.cy3fluxviz.CyActivator;
import org.cy3fluxviz.actions.CyfluxvizAction;

/** 
 * First test to get access to the cy3sbml instance information. 
 * Creates an app action for querying the SBMLManager instance. 
 */

public class CyActivator extends AbstractCyActivator {
	private static final Logger logger = LoggerFactory.getLogger(CyActivator.class);
	
	public CyActivator() {
		super();
	}
	
	public void start(BundleContext bc) {
		try {
			logger.info("cy3fluxviz starting ... ");
			
			// static access of cy3sbml information is no problem
			logger.info("INFO:" + SBML.NODETYPE_COMPARTMENT);
			
			// get the SBMLManager service
			SBMLManager sbmlManager = getService(bc, SBMLManager.class);
			
			// but instance information like the SBMLmanager are not accessible		
			CySwingApplication cySwingApplication = getService(bc, CySwingApplication.class);
			CyfluxvizAction action = new CyfluxvizAction(cySwingApplication, sbmlManager);
			registerService(bc, action, CyAction.class, new Properties());
			
			CyServiceRegistrar registrar;
			
			
			logger.info("... cy3fluxviz started.");
		} catch (Throwable e){
			logger.error("Could not start server!", e);
			e.printStackTrace();
		}
	}
}