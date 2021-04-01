package viewer;

import org.jfree.chart.ChartPanel;

/**
* This class is an abstract observer which can be attached to a subject. 
*
* @author	Matthew Da Silva
*/
public abstract class Viewer {	
	public Viewer() { }
	
	public abstract void update(); 
}