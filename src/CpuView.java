import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * <h1>Advanced Java - Project06Fox</h1>
 * <h1>CpuView  Class</h1>
 * This is the class description provided the JavaFx viewing of the CPU database information along with editing
 * <p>
 * <b>Create Date: 11/6/2016</b>
 * <b>Due Date: 11/16/2016</b>
 *
 * @author Michael Fox
 */
public class CpuView
{
    private CpuController cpuController;

    private Label lblCpuList;
    private Label lblPrice;
    private Label lblPerformance;

    private ListView< CPU > listViewCpu;

    /**
     * Default constructor for the Cpu View class.
     * @param c - CpuController object
     */
    public CpuView(CpuController c)
    {
        cpuController = c;

    }

    /**
     * This is the primary class for setting up the Java Fx GUI for the View class
     * @param mainStage - main stage of the Java Fx GUI
     */
    void Start(Stage mainStage, ArrayList< CPU > cpuList)
    {
        mainStage.setTitle( "Fox CPU Viewer" );
        FlowPane root = new FlowPane( Orientation.VERTICAL, 20, 20 );
        root.setAlignment( Pos.CENTER );
        Scene scene01 = new Scene( root, 500, 500 );
        mainStage.setScene( scene01 );

        lblCpuList = new Label( "CPU Information List" );
        lblPrice = new Label("?????");
        lblPerformance = new Label("?????");

        // The backing data structure for the ListView
        ObservableList< CPU > list01 = FXCollections.observableArrayList( cpuList );
        listViewCpu = new ListView< CPU >( list01 );
        listViewCpu.setPrefSize( 320, 160 );

        MultipleSelectionModel< CPU > msm01 = listViewCpu.getSelectionModel();

        //Capture the selection and alter the labels
        msm01.selectedItemProperty().addListener(
                (changedValue, oldValue, newValue) -> {
                    lblPrice.setText( Double.toString(newValue.getPrice()) );
                    lblPerformance.setText(Double.toString(newValue.getPerformance()));
                }
        );

        root.getChildren().addAll( lblCpuList, listViewCpu, lblPerformance, lblPrice);
        mainStage.show();
    }
}
