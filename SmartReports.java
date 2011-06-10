package br.com.smartcoders.smartreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class SmartReports implements EntryPoint {
	
	private DockLayoutPanel layout;
	private Frame reportFrame;
	
	public void onModuleLoad() {
		
		layout = new DockLayoutPanel(Unit.EM);
		reportFrame = new Frame();
		reportFrame.setUrl("http://www.uol.com.br");
		
		HTML header = new HTML("Report Filter");
		header.setStyleName("header");
		
		layout.addNorth(header, 10);
		layout.add(reportFrame);
		
		RootLayoutPanel.get().add(layout);
//		layout.setWidth("100%");
//		layout.setHeight("100%");
		
//		layout.setCellHeight(reportFrame, "1000px");
//		reportFrame.setHeight("100%");
//		reportFrame.setWidth("100%");
	}
}
