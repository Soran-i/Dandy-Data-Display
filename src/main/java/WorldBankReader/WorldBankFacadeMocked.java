package WorldBankReader;
import java.util.Vector;

import AnalysisComponent.ReaderResults; 

public class WorldBankFacadeMocked {
	
	public ReaderResults RequestData(String Indicator,String YearStart, String YearEnd, String Country) {
		Vector<Double> Data = new Vector<Double>(); 
		Vector<Integer> YearsVect = new Vector<Integer>(); 
		String unit = new String(); 	
		
		Data.add(1.); 
		Data.add(0.); 
		Data.add(3.); 
		Data.add(4.); 
		Data.add(0.); 
		
		
		YearsVect.add(2000); 
		YearsVect.add(2001);
		YearsVect.add(2002);
		YearsVect.add(2003);
		YearsVect.add(2004);
		
		unit = "all"; 
		
		ReaderResults Results = new ReaderResults(); 
		
		Results.NumericData = Data; 
		Results.Years = YearsVect; 
		Results.units = unit; 
		
		
		return Results; 
	}

}
