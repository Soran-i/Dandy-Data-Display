package model;

import java.util.*;

public abstract class ViewerSubject {
	protected Vector<viewer.Viewer> _observers = new Vector<viewer.Viewer>();
	
	public void attach(viewer.Viewer observer) {
		_observers.add(observer);
	}
	
	public void detach(viewer.Viewer observer) {
		_observers.remove(observer);
	}
	
	public void notify_viewers() {
		Iterator<viewer.Viewer> i = _observers.iterator();
		while(i.hasNext()) {
			(i.next()).update();
		}
	}
}