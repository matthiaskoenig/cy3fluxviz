package org.cy3fluxviz.actions;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.cy3sbml.SBMLManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test access to the cy3sbml instance information.
 */
public class CyfluxvizAction extends AbstractCyAction{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CyfluxvizAction.class);
	private CyServiceRegistrar cyServiceRegistrar;
	
	public CyfluxvizAction(CySwingApplication cySwingApplication, CyServiceRegistrar cyServiceRegistrar){
		super("CyfluxvizAction");
		this.cyServiceRegistrar = cyServiceRegistrar;
				
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo-cyfluxviz.png"));
		putValue(LARGE_ICON_KEY, icon);
		
		this.putValue(SHORT_DESCRIPTION, "cyfluxviz action");
		setToolbarGravity((float) 500.0);
	}
	
	public boolean insertSeparatorBefore(){
		return true;
	}
	
	public boolean isInToolBar() {
		return true;
	}
	public boolean isInMenuBar() {
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		// try to get the the SBMLManager service
		try{
			SBMLManager sbmlManager = cyServiceRegistrar.getService(SBMLManager.class);
			logger.info("Access SBMLmanager instance");
			logger.info(sbmlManager.toString());
		} catch (Throwable e){
			logger.error("Could not get SBMLManager service", e);
			e.printStackTrace();
		}
	}
}
