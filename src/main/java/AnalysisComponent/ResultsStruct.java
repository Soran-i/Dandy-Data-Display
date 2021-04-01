package AnalysisComponent;

import java.util.*;

/**
 * A structure to contain the returned results from the analyses. Stores data in vectors. 
 * @author stephan
 *
 */
public class ResultsStruct {
	public Vector<Vector<Double>> Results = new Vector<Vector<Double>>();
	public Vector<Integer> Years = new Vector<Integer>();
	public Vector<String> Units = new Vector<String>();
	public Vector<String> Labels = new Vector<String>();
	public String Title;
}