import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * <h1>Advanced Java - Project06Fox</h1>
 * <h1>CpuController  Class</h1>
 * This is the class has the business logic associated with this CPU list project
 * <p>
 * <b>Create Date: 11/6/2016</b>
 * <b>Due Date: 11/16/2016</b>
 *
 * @author Michael Fox
 */
public class CpuController
{
    private CpuView cpuView;

    /**
     * The default constructor for the Cpu Controller class.  This will instantiate the Cpu View class
     */
    public CpuController()
    {
        cpuView = new CpuView(this);
    }

    /**
     * Simply pass on the main stage to the view class
     * @param mainStage - main stage for the Java Fx GUI
     */
    void Start(Stage mainStage)
    {
        cpuView.Start(mainStage);
    }

    /**
     * Save to the model
     * @param strCpuName
     * @param iPerformance
     * @param dPrice
     */
    void Save(String strCpuName, int iPerformance, double dPrice)
    {
        CpuModel.save(strCpuName, iPerformance, dPrice);
    }

    /**
     * Get the latest cpu list from the Model
     */
    ArrayList< CPU > getCpuList()
    {
        return (CpuModel.getAllCpu());
    }
}
