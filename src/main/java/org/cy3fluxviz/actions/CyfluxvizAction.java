package org.cy3fluxviz.actions;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.cy3sbml.SBMLManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyfluxvizAction extends AbstractCyAction{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CyfluxvizAction.class);
	
	
	public CyfluxvizAction(CySwingApplication cySwingApplication){
		super("CyfluxvizAction");
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo-cyfluxviz.png"));
		putValue(LARGE_ICON_KEY, icon);
		
		this.putValue(SHORT_DESCRIPTION, "cyfluxviz action");
		setToolbarGravity((float) 120.0);
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
		logger.debug("actionPerformed()");
		
		logger.info("Access SBMLmanager instance");
		SBMLManager.getInstance();
		
	}
}

