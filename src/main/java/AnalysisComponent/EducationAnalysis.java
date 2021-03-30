package AnalysisComponent;
import java.text.DecimalFormat;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;

public class EducationAnalysis extends Analysis {
	private String  EducationIndicator = "SE.XPD.TOTL.GD.ZS";

	private String  EducationLabel = "Education expenditure";
	
	private String  EducationUnits = "%";
	
	private String Title = "Government Expenditure on education, total (% of GDP)"; 
	
	private WorldBankFacade Reader; 
	
	public EducationAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	
	public ResultsStruct performAnalysis(ParamStruct params) throws Exception {
		
		ReaderResults Education = Reader.RequestData(EducationIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> EducationData = Education.NumericData;
		
		EducationData =  DivBy100(EducationData); 
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(EducationData);
		
		ResultReturn.Years = Education.Years;
		
		ResultReturn.Labels.add(EducationLabel); 
		
		ResultReturn.Units.add(EducationUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
	private Vector<Double> DivBy100(Vector<Double> Element1) {
		Vector<Double> Ratio = new Vector<Double>();
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		for(int i = 0; i < Element1.size(); i++)
		{
			Ratio.add(Double.valueOf(df.format(Element1.get(i)/100.)));
		}
		
		return Ratio; 
	}
} 