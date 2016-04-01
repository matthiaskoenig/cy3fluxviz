package org.cy3fluxviz;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cytoscape.application.CyApplicationConfiguration;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.io.util.StreamUtil;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.property.CyProperty;

import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.task.read.LoadVizmapFileTaskFactory;
import org.cytoscape.util.swing.FileUtil;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.SynchronousTaskManager;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.swing.DialogTaskManager;

import org.cy3sbml.BundleInformation;
import org.cy3sbml.SBML;

import org.cy3fluxviz.ServiceAdapter;
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
			// at startup
			CySwingApplication cySwingApplication = getService(bc, CySwingApplication.class);
			CyServiceRegistrar cyServiceRegistrar = getService(bc, CyServiceRegistrar.class);
	
			
			CyApplicationManager cyApplicationManager = getService(bc, CyApplicationManager.class);
			CyNetworkManager cyNetworkManager = getService(bc, CyNetworkManager.class);
			CyNetworkViewManager cyNetworkViewManager = getService(bc, CyNetworkViewManager.class);
			VisualMappingManager visualMappingManager = getService(bc, VisualMappingManager.class);
			CyLayoutAlgorithmManager cyLayoutAlgorithmManager = getService(bc, CyLayoutAlgorithmManager.class);
			
			
			CyNetworkFactory cyNetworkFactory = getService(bc, CyNetworkFactory.class);
			CyNetworkViewFactory cyNetworkViewFactory = getService(bc, CyNetworkViewFactory.class);
			
			DialogTaskManager dialogTaskManager = getService(bc, DialogTaskManager.class);
			@SuppressWarnings("rawtypes")
			SynchronousTaskManager synchronousTaskManager = getService(bc, SynchronousTaskManager.class);
			@SuppressWarnings("rawtypes")
			TaskManager taskManager = getService(bc, TaskManager.class);
			
			@SuppressWarnings("unchecked")
			CyProperty<Properties> appProperties = getService(bc, CyProperty.class, "(cyPropertyName=cy3fluxviz.props)");
			StreamUtil streamUtil = getService(bc, StreamUtil.class);
			OpenBrowser openBrowser = getService(bc, OpenBrowser.class);
			FileUtil fileUtil = getService(bc, FileUtil.class);
			
			LoadNetworkFileTaskFactory loadNetworkFileTaskFactory = getService(bc, LoadNetworkFileTaskFactory.class);
		
						
			/** Create ServiceAdapter */
			ServiceAdapter adapter = ServiceAdapter.getInstance(
					cySwingApplication,
					cyApplicationManager,
					cyNetworkManager,
					cyNetworkViewManager,
					visualMappingManager,
					cyLayoutAlgorithmManager,
					dialogTaskManager,
					synchronousTaskManager,
					taskManager,
					
					cyNetworkFactory,
					cyNetworkViewFactory,
					
					appProperties,
					appDirectory,
					streamUtil,
					openBrowser,
					loadNetworkFileTaskFactory,
					fileUtil
			);
			
			// visual styles (normal and dark)
			LoadVizmapFileTaskFactory loadVizmapFileTaskFactory =  getService(bc, LoadVizmapFileTaskFactory.class);
			InputStream streamCy3sbml = getClass().getResourceAsStream("/styles/cy3sbml.xml");
			loadVizmapFileTaskFactory.loadStyles(streamCy3sbml);
			InputStream streamCy3sbmlDark = getClass().getResourceAsStream("/styles/cy3sbml-dark.xml");
			loadVizmapFileTaskFactory.loadStyles(streamCy3sbmlDark);
			
			// init actions
			CyfluxvizAction action = new CyfluxvizAction(cySwingApplication, cyServiceRegistrar);
			registerService(bc, action, CyAction.class, new Properties());
					
			
			// panels
			// registerService(bc, resultsPanel, CytoPanelComponent.class, new Properties());
			
			// listeners
			/*
			registerService(bc, resultsPanel, RowsSetListener.class, new Properties());
			registerService(bc, connectionProxy, PropertyUpdatedListener.class, new Properties());
			registerService(bc, sbmlManager, SetCurrentNetworkListener.class, new Properties());
			registerService(bc, sbmlManager, NetworkAddedListener.class, new Properties());
			registerService(bc, sbmlManager, NetworkAddedListener.class, new Properties());
			registerService(bc, sbmlManager, NetworkViewAboutToBeDestroyedListener.class, new Properties());
			*/
			
			logger.info("----------------------------");
		} catch (Throwable e){
			logger.error("Could not start server!", e);
			e.printStackTrace();
		}
	}
}