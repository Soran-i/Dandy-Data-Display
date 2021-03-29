
package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacade;

public class HospitalAnalysis extends Analysis {
	private String  HospitalBedsIndicator = "SH.MED.BEDS.ZS";
	private String  HealthExpendIndicator = "SH.XPD.CHEX.PC.CD";
	
	private String  Label = "Ratio of hospital beds to health care expenditure";
	
	private String  Units = "beds/US$";
	
	private String Title = "Ratio of Hospital beds and Current health expenditure"; 
	
	private WorldBankFacade Reader; 
	
	public HospitalAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults HospitalBeds = Reader.RequestData(HospitalBedsIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults HealthExpend = Reader.RequestData(HealthExpendIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> HospitalBedsData = HospitalBeds.NumericData;
		Vector<Double> HealthExpendData = HealthExpend.NumericData;
		
		Vector<Double> Ratio = ConvertCalculateRatio(HospitalBedsData,HealthExpendData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(Ratio);
		
		ResultReturn.Years = HospitalBeds.Years;
		
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
				Ratio.add(Element1.get(i)/(Element2.get(i)*1000));
			}
			else {
				Ratio.add(0.0);
			}
		}
		
		return Ratio; 
	}
} 