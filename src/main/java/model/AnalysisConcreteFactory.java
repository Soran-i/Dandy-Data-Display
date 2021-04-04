package model;

import AnalysisComponent.Analysis;
import AnalysisComponent.C02GDPAnalysis;
import AnalysisComponent.CO2EnergyPolnAnalysis;
import AnalysisComponent.EducationAnalysis;
import AnalysisComponent.EducationHealthAnalysis;
import AnalysisComponent.ForestAnalysis;
import AnalysisComponent.HealthMortalityAnalysis;
import AnalysisComponent.HospitalAnalysis;
import AnalysisComponent.PolnForestAnalysis;
import statsVisualiser.gui.ParamStruct;

/**
 * a class that is part of the factory method used to create analyses for the modeler client. 
 * @author steph
 *
 */
public class AnalysisConcreteFactory extends AnalysisFactory {
	
	/**
	 * method defining the creation operation of the factory to create specific types of analyses
	 * @return Analysis defining the type of analysis to be performed
	 */
	public Analysis GetAnalysis(ParamStruct params) {
		
		if(params._analysis.equals("Emissions vs Energy vs Pollution")) {
			return new CO2EnergyPolnAnalysis();
		}
		else if(params._analysis.equals("Pollution vs Forest")) {
			return new PolnForestAnalysis();
		}
		else if(params._analysis.equals("C02 vs GDP")) {
			return new C02GDPAnalysis();
		}
		else if(params._analysis.equals("Forest")) {
			return new ForestAnalysis();
		}
		else if(params._analysis.equals("Education Expend")) {
			return new EducationAnalysis();
		}
		else if(params._analysis.equals("Hospital Beds vs Health Expend")) {
			return new HospitalAnalysis();
		}
		else if(params._analysis.equals("Health Expend vs Mortality")) {
			return new HealthMortalityAnalysis();
		}
		else if(params._analysis.equals("Education Expend vs Health Expend")) {
			return new EducationHealthAnalysis();
		}
		else {
			return new CO2EnergyPolnAnalysis();
		}
	}
}
