import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    private Label       lblCpuList;
    private Label       lblPrice;
    private Label       lblPerformance;
    private TextField   txtPrice;
    private TextField   txtPerformance;
    private Label       lblCpuName;
    private TextField   txtCpuName;
    private Button      btnSave;

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
        lblCpuName = new Label("CPU Name");
        txtCpuName = new TextField("");
        lblPrice = new Label("Price");
        txtPrice = new TextField("");
        lblPerformance = new Label("Performance");
        txtPerformance = new TextField("");
        btnSave = new Button( "Save" );

        //Set the button handler for the Save pushbutton
        btnSave.setOnAction( (wombat) -> { SaveButtonHandler(); } );

        // The backing data structure for the ListView
        ObservableList< CPU > list01 = FXCollections.observableArrayList( cpuList );
        listViewCpu = new ListView< CPU >( list01 );
        listViewCpu.setPrefSize( 320, 160 );

        MultipleSelectionModel< CPU > msm01 = listViewCpu.getSelectionModel();

        //Capture the selection and alter the labels
        msm01.selectedItemProperty().addListener(
                (changedValue, oldValue, newValue) -> {
                    txtPrice.setText( Double.toString(newValue.getPrice()) );
                    txtPerformance.setText(Double.toString(newValue.getPerformance()));
                    txtCpuName.setText((newValue.getCpuName()));
                }
        );

        //Add the controls to the dialog
        root.getChildren().addAll(  lblCpuList,
                                    listViewCpu,
                                    lblCpuName,
                                    txtCpuName,
                                    lblPerformance,
                                    txtPerformance,
                                    lblPrice,
                                    txtPrice,
                                    btnSave);
        mainStage.show();
    }

    void UpdateCpuList()
    {
        // The backing data structure for the ListView
//        ObservableList< CPU > list01 = FXCollections.observableArrayList( cpuList );
//        listViewCpu = new ListView< CPU >( list01 );
//        listViewCpu.setPrefSize( 320, 160 );

    }


    /**
     * Handle the Save push button hit
     */
    private void SaveButtonHandler()
    {
        //Save the CPU information via the controller
        if((txtCpuName.getText().isEmpty() == false) &&
           (txtPerformance.getText().isEmpty() == false) &&
           (txtPrice.getText().isEmpty() == false))
        {
            cpuController.Save( txtCpuName.getText(),
                                Integer.parseInt(txtPerformance.getText()),
                                Double.parseDouble(txtPrice.getText()));
        }

        UpdateCpuList();

        //System.out.printf("Save [%s][%s][%s]\n", txtCpuName.getText(), txtPerformance.getText(), txtPrice.getText());
    }
}

