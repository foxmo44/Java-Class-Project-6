/**
 * <h1>Advanced Java - Project 4</h1>
 * <h1>CPU Class</h1>
 * This class used to store the particulars of the CPU read from each input file line
 * <p>
 * <b>Note:</b> Regular expression input: ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" will pick out the commas only
 *
 * @author   Michael Fox
 * @version  Project 04
 * @since   2016.10.16
 */
public class CPU
{
    private boolean m_bValid;
    private String m_strCPULine;
    private String m_strCPUName;
    private int m_iPerformance;
    private double m_dPrice;
    private double m_dValue; //Performance / Price
    private static final double NA_VALUE = 9999.99;
    private int m_iIdentifier = -1;

    /**
     * @param strCPULine is the CPU line from the file
     */
    public CPU(String strCPULine)
    {
        m_strCPULine = strCPULine;
        m_bValid = ParseCPULine(strCPULine);
    }

    /**
     * Constructor to be used when the fields are already known
     * @param iIdentifier - CPU identifier
     * @param strCPUName - name of the CPU
     * @param iPerformance - performance factor of the CPU
     * @param dPrice - price of the CPU
     */
    public CPU(int iIdentifier, String strCPUName, int iPerformance, double dPrice)
    {
        m_strCPUName = strCPUName;
        m_iPerformance = iPerformance;
        m_dPrice = dPrice;
        m_dValue = m_iPerformance / m_dPrice; //Performance / Price
        m_bValid = true;
        m_iIdentifier = iIdentifier;
    }

    /**
     * @param m_strCPULine - the input file line to be parsed
     * @return Indicate if the ParseCPULin succeeded or not
     */
    private boolean ParseCPULine(String m_strCPULine)
    {
        boolean bRetValue = true;
        String  strTemp;
        String strNumeric;

        //Use a regular expression to parse the line into the individual members

        //TODO - Figure out why regex not working for 1,509.00
        //messes up on the comma in the value
        String[] tokens = m_strCPULine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //http://rubular.com/ is helpful for regex

        //Do we have the correct number of tokens from the split.  If not then mark as invalid
        if(tokens.length != 3)
        {
            bRetValue = false;
        }

        m_strCPUName = tokens[0];

        //Get the performance
        try
        {
            strTemp = tokens[1];
            strNumeric = strTemp.replaceAll("[^0-9.]+", "");  //Get rid of non digit characters
            m_iPerformance = Integer.parseInt(strNumeric);
        }
        catch(NumberFormatException ex)
        {
            m_iPerformance = (int)NA_VALUE;
            bRetValue = false;
        }

        //Get the price
        try
        {
            strTemp = tokens[2];
            strNumeric = strTemp.replaceAll("[^0-9.]+", "");   //Get rid of non digit characters
            m_dPrice = Double.parseDouble(strNumeric);
        }
        catch(NumberFormatException ex)
        {
            m_dPrice = NA_VALUE; //Bogus Value
            bRetValue = false;
        }

        //If we have valid Performance and Price values then return the calculated value otherwise set to zero
        if(bRetValue)
        {
            m_dValue = m_iPerformance / m_dPrice;
        }
        else
        {
            m_dValue = 0.0;
        }

//        for(String strTemp1 : tokens)
//        {
//            System.out.printf("%s\t", strTemp1);
//        }
//
//        System.out.printf("\n");

        return bRetValue;
    }

    /**
     * @return  A string representing the CPU
     */
    public String toString()
    {
        //return String.format("[%b]\t%s\t\t[%5.2f]\t[%5.2f]\n", m_bValid, m_strCPUName, m_dPerformance, m_dPrice);
        //return String.format("%s\n", m_strCPULine); //Just return the original line
        return String.format("%s", m_strCPUName);
    }

    /**
     * Getter
     * @return the performance as an double
     */
    public int getPerformance(){return(m_iPerformance);};


    /**
     * Getter
     * @return the price as a double
     */
    public double getPrice(){return(m_dPrice);};

    /**
     * Getter
     * @return the value as a double
     */
    public double getValue(){return(m_dValue);};


    /**
     * Getter
     * @return the CPU Name
     */
    public String getCpuName(){return(m_strCPUName);};

    /**
     * @return if the cpu's line was parsed with all valid values for the fields or not
     */
    public boolean getValid(){return(m_bValid);};

    /**
     * @return return the identifier of the CPU
     */
    public int getIdentifier(){return(m_iIdentifier);};
}
