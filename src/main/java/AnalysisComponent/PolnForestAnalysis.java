package AnalysisComponent;

import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;
import statsVisualiser.gui.ReaderException;

/**
 * This class is a concrete analysis that implements the performAnalysis
 * function from the abstract analysis. Fetches Air Pollution data and forest
 * area data with no calculations. Returns the results structure.
 * 
 * @author stephan
 *
 */
public class PolnForestAnalysis extends Analysis {
	private String AirPolnIndicator = "EN.ATM.PM25.MC.M3";
	private String ForestAreaIndicator = "AG.LND.FRST.ZS";

	private String AirPolnLabel = "PM2.5 air pollution, mean annual exposure";
	private String ForestAreaLabel = "Forest Area";

	private String AirPolnUnits = "micrograms/m^3";
	private String ForestAreaUnits = "%";

	private String Title = "PM2.5 air Pollution vs Forest Area";

	private WorldBankFacade Reader;

	/**
	 * A constructor for the analysis to create a reader object
	 */
	public PolnForestAnalysis() {
		Reader = new WorldBankFacade();
	}

	/**
	 * Implements the performAnalysis method from the abstract class. Fetches Air
	 * Pollution data and forest area data with no calculations. Returns the results
	 * structure. Also populates the labels, units and title required for the
	 * analysis.
	 * 
	 * @param params a structure of ParamStruct used to pass parameters to this
	 *               analysis.
	 * @return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {

		ReaderResults AirPoln = new ReaderResults();
		ReaderResults ForestArea = new ReaderResults();
		try {
			AirPoln = Reader.RequestData(AirPolnIndicator, params._yearStart, params._yearEnd, params._country);
			ForestArea = Reader.RequestData(ForestAreaIndicator, params._yearStart, params._yearEnd, params._country);
		} catch (ReaderException e) {
			throw e;
		}

		Vector<Double> AirPolnsData = AirPoln.NumericData;
		Vector<Double> ForestAreaData = ForestArea.NumericData;

		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(AirPolnsData);
		ResultReturn.Results.add(ForestAreaData);

		ResultReturn.Years = ForestArea.Years;

		ResultReturn.Labels.add(AirPolnLabel);
		ResultReturn.Labels.add(ForestAreaLabel);

		ResultReturn.Units.add(AirPolnUnits);
		ResultReturn.Units.add(ForestAreaUnits);

		ResultReturn.Title = Title;

		return ResultReturn;

	}
}