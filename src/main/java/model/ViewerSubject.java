package model;

import java.util.*;

import viewer.*;

/**
* This class is an abstract subject which can be observed by a dynamic number of viewers. 
* <p>
* Viewers can be attached to or detached from the subject such that the subject can notify 
* all attached viewers of any changes to the subject's state.
*
* @author	Matthew Da Silva
*/
public abstract class ViewerSubject {
	protected Vector<Viewer> _observers = new Vector<Viewer>();
	
	/**
	* This method attaches a Viewer type object to the subject. 
	*
	* @param	observer	A Viewer which wants to be notified to changes in the subject
	*/
	public void attach(Viewer observer) {
		_observers.add(observer);
	}
	
	/**
	* This method detaches a Viewer type object from the subject (if it is attached). 
	*
	* @param	observer	A Viewer which no longer wants to be notified to changes in the subject
	*/
	public void detach(Viewer observer) {
		_observers.remove(observer);
	}
	
	/**
	* This method notifies all attached Viewer objects that the subject has changed (attached Viewers are updated). 
	*/
	public void notify_viewers() {
		Iterator<Viewer> i = _observers.iterator();
		while(i.hasNext()) {
			(i.next()).update();
		}
	}
}