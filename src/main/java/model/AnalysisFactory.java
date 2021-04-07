package model;

import AnalysisComponent.Analysis;
import statsVisualiser.gui.ParamStruct;

/**
 * an abstract factory that is meant to allow for extensible factory methods for creating analyses
 * @author steph
 *
 */
public abstract class AnalysisFactory {
	/**
	 * a method for getting a specific type of analysis
	 * @param params a param struct containing the parameters for the analyses
	 * @return the specific analyses object to run the analyses
	 */
	abstract public Analysis GetAnalysis(ParamStruct params);
}
