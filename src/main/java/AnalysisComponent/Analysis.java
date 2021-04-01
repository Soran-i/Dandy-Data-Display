package AnalysisComponent;

import java.util.*;

/**
 * This is the abstract class defining the method perform Analysis that are implemented in the concrete Analyses. 
 * This class is part of the strategy design pattern that provides the interface for use of concrete strategies.
 * @author stephan
 *
 */
abstract class Analysis {
	public abstract ResultsStruct performAnalysis(ParamStruct params) throws Exception; 
	
	
//	public static void main(String[] args)
//	{
//		Analysis Analysis = new EducationHealthAnalysis(); 
//		ParamStruct Params = new ParamStruct(); 
//		
//		Params._country = "CAN"; 
//		Params._yearStart = "2000"; 
//		Params._yearEnd = "2004";
//		
//		
//		ResultsStruct Res = new ResultsStruct();
//		try {
//			Res = Analysis.performAnalysis(Params); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Vector<String> Units = Res.Units; 
//		
//		Enumeration vEnum = Units.elements();
//		System.out.println("\nElements in Units vector:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");
//		
//		
//		Vector<String> Labels = Res.Labels; 
//		
//		vEnum = Labels.elements();
//		System.out.println("\nElements in Labels vector:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");
//		
//		Vector<Integer> Years = Res.Years; 
//	
//		vEnum = Years.elements();
//		System.out.println("\nElements in Years vector:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");
//		
//		
//		Vector<Vector<Double>> Results =Res.Results;
//		for (Vector<Double> v : Results) {
//			System.out.println("\nElements in Results vector:");
//		    for (Double D : v) {
//		    	System.out.print(D + " ");
//		    }
//		}
//
//		
//	}
	
}
