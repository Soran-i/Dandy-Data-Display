package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacadeMocked;

public class EducationAnalysis extends Analysis {
	private String  EducationIndicator = "SE.XPD.TOTL.GD.ZS";

	private String  EducationLabel = "Education expenditure";
	
	private String  EducationUnits = "%";
	
	private String Title = "Government Expenditure on education, total (% of GDP)"; 
	
	private WorldBankFacadeMocked Reader; 
	
	public EducationAnalysis(){
		Reader = new WorldBankFacadeMocked(); 
	}
	
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults Education = Reader.RequestData(EducationIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> EducationData = Education.NumericData;
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(EducationData);
		
		ResultReturn.Years = Education.Years;
		
		ResultReturn.Labels.add(EducationLabel); 
		
		ResultReturn.Units.add(EducationUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
} 