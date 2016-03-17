package org.cy3fluxviz;

import java.io.File;
import java.util.Properties;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cytoscape.application.CyApplicationConfiguration;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cy3sbml.BundleInformation;
import org.cy3sbml.SBML;
import org.cy3fluxviz.CyActivator;
import org.cy3fluxviz.actions.CyfluxvizAction;

/**
 * Starts the cy3fluxviz OSGI bundle.
 */
public class CyActivator extends AbstractCyActivator {
	private static Logger logger;
	
	public CyActivator() {
		super();
	}
	
	public void start(BundleContext bc) {
		try {
			BundleInformation bundleInfo = new BundleInformation(bc);
			
			// Default configuration directory used for all cy3sbml files 
			// Used for retrieving
			CyApplicationConfiguration configuration = getService(bc, CyApplicationConfiguration.class);
			File cyDirectory = configuration.getConfigurationDirectoryLocation();
			File appDirectory = new File(cyDirectory, bundleInfo.getName());
			
			if(appDirectory.exists() == false) {
				appDirectory.mkdir();
			}
			// store bundle information (for display of dependencies, versions, ...)
			File logFile = new File(appDirectory, bundleInfo.getInfo() + ".log");
			System.setProperty("logfile.name", logFile.getAbsolutePath());
			logger = LoggerFactory.getLogger(CyActivator.class);
			
			logger.info("----------------------------");
			logger.info("Start " + bundleInfo.getInfo());
			logger.info("----------------------------");
			logger.info("directory = " + appDirectory.getAbsolutePath());
			logger.info("logfile = " + logFile.getAbsolutePath());
						
			// static access of cy3sbml information is no problem
			logger.info("cy3sbml static access:" +  SBML.NODETYPE_COMPARTMENT);
			
			// but instance information like the SBMLmanager are not accessible	
			// at statup
			CySwingApplication cySwingApplication = getService(bc, CySwingApplication.class);
			CyServiceRegistrar cyServiceRegistrar = getService(bc, CyServiceRegistrar.class);
			
			CyfluxvizAction action = new CyfluxvizAction(cySwingApplication, cyServiceRegistrar);
			registerService(bc, action, CyAction.class, new Properties());
			
			
			logger.info("----------------------------");
		} catch (Throwable e){
			logger.error("Could not start server!", e);
			e.printStackTrace();
		}
	}
}