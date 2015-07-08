package org.cy3sbml;


import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CyActivator extends AbstractCyActivator {
	private static final Logger logger = LoggerFactory.getLogger(CyActivator.class);
	
	public CyActivator() {
		super();
	}
	
	public void start(BundleContext bc) {
		try {
			logger.info("cy3sbmlviz starting ... ");
			
			// access some cy3sbml things
			logger.info("INFO:" + SBML.NODETYPE_COMPARTMENT);
			
			SBMLManager.getInstance();
			
			
			logger.info("... cy3sbmlviz started.");
		} catch (Throwable e){
			logger.error("Could not start server!", e);
			e.printStackTrace();
		}
	}
}

