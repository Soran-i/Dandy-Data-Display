package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacade;

public class EducationHealthAnalysis extends Analysis {
	private String  HealthExpendGDPIndicator = "SH.XPD.CHEX.GD.ZS";
	private String  EducationIndicator = "SE.XPD.TOTL.GD.ZS";
	
	private String  Label = "Ratio of Health Expenditure and Current Health Expenditure";
	
	private String  Units = "%/%";
	
	private String Title = "Ratio of Government expenditure on education, total and Current health expenditure"; 
	
	private WorldBankFacade Reader; 
	
	public EducationHealthAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults Education = Reader.RequestData(EducationIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults HealthExpendGDP = Reader.RequestData(HealthExpendGDPIndicator,params._yearStart,params._yearEnd,params._country);  

		Vector<Double> EducationData = Education.NumericData;
		Vector<Double> HealthExpendGDPData = HealthExpendGDP.NumericData;

		
		Vector<Double> Ratio = ConvertCalculateRatio(EducationData,HealthExpendGDPData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(Ratio);
		
		ResultReturn.Years = Education.Years;
		
		ResultReturn.Labels.add(Label); 
		
		ResultReturn.Units.add(Units); 
		 
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
	private Vector<Double> ConvertCalculateRatio(Vector<Double> Element1,Vector<Double>Element2) {
		Vector<Double> Ratio = new Vector<Double>();
		
		for(int i = 0; i < Element1.size(); i++)
		{
			if (Element2.get(i) != 0){
				Ratio.add(Element1.get(i)/Element2.get(i));
			}
			else {
				Ratio.add(0.0);
			}
		}
		
		return Ratio; 
	}
} 
