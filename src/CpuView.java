import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
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
    private Label       lblCpuIndex;
    private TextField   txtCpuIndex;
    private Button      btnSave;
    private Button      btnDelete;
    private Label       lblStatus;

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
    void Start(Stage mainStage)
    {
        mainStage.setTitle( "Fox CPU Viewer" );
        FlowPane root = new FlowPane( Orientation.VERTICAL, 20, 20 );
        root.setAlignment( Pos.CENTER );
        Scene scene01 = new Scene( root, 500, 700 );
        mainStage.setScene( scene01 );

        lblCpuList = new Label( "CPU Information List" );
        lblCpuIndex = new Label("CPU Index");
        txtCpuIndex = new TextField("");
        lblCpuName = new Label("CPU Name");
        txtCpuName = new TextField("");
        lblPrice = new Label("Price");
        txtPrice = new TextField("");
        lblPerformance = new Label("Performance");
        txtPerformance = new TextField("");
        btnSave = new Button( "Save" );
        btnDelete = new Button("Delete");
        lblStatus = new Label("");

        //Set the button handler for the Save pushbutton
        btnSave.setOnAction( (wombat) -> { SaveButtonHandler(); } );
        btnDelete.setOnAction((wombat) -> {DeleteButtonHandler(); });

        listViewCpu = new ListView< CPU >(  );
        listViewCpu.setPrefSize( 320, 160 );

        // Update the CPU list
        UpdateCpuList();

        MultipleSelectionModel< CPU > msm01 = listViewCpu.getSelectionModel();

        //Capture the selection and alter the labels
        msm01.selectedItemProperty().addListener
        (
            (changedValue, oldValue, newValue) ->
            {
                UpdateCpuTextboxes(newValue);
            }
        );

        //Add the controls to the dialog
        root.getChildren().addAll(  lblCpuList,
                                    listViewCpu,
                                    lblCpuIndex,
                                    txtCpuIndex,
                                    lblCpuName,
                                    txtCpuName,
                                    lblPerformance,
                                    txtPerformance,
                                    lblPrice,
                                    txtPrice,
                                    btnSave,
                                    btnDelete,
                                    lblStatus);
        mainStage.show();
    }

    /**
     * Update the CPU text boxes
     * @param cpu - Input CPU object
     */
    void UpdateCpuTextboxes(CPU cpu)
    {
        if(cpu != null)
        {
            //If a valid CPU object then update the text boxes
            if (cpu.getValid() == true)
            {
                txtCpuIndex.setText(Integer.toString(cpu.getIdentifier()));

                String strPrice = String.format("%5.2f", cpu.getPrice());
                txtPrice.setText(strPrice);

                String strPerformance = Integer.toString(cpu.getPerformance());
                txtPerformance.setText(strPerformance);

                txtCpuName.setText((cpu.getCpuName()));
            }
        }
        else
        {
            txtCpuIndex.setText("");
            txtPrice.setText("");
            txtCpuName.setText("");
            txtPerformance.setText("");
        }

    }

    /**
     * Update the list view with the data
     */
    void UpdateCpuList()
    {
        ArrayList< CPU > cpuList = new ArrayList<>();

        cpuList = cpuController.getCpuList();

        System.out.printf("The list has %d\n", cpuList.size());

        // The backing data structure for the ListView
        ObservableList< CPU > list01 = FXCollections.observableArrayList( cpuList );

        listViewCpu.getItems().clear();
        listViewCpu.setItems(list01);
        listViewCpu.refresh();

    }


    /**
     * Handle the Save push button hit
     */
    private void SaveButtonHandler()
    {
        int     iPerformance;
        double  dPrice;
        String  strCpuName;
        String  strStatus;

        //Save the CPU information via the controller
        if((txtCpuName.getText().isEmpty() == false) &&
           (txtPerformance.getText().isEmpty() == false) &&
           (txtPrice.getText().isEmpty() == false))
        {
            iPerformance = Integer.parseInt(txtPerformance.getText());
            dPrice = Double.parseDouble(txtPrice.getText());
            strCpuName = txtCpuName.getText();

            cpuController.Save( strCpuName, iPerformance, dPrice);  //Save to the db

            UpdateCpuList();    //based on the database update the ListView

            strStatus = String.format("Saved %s", strCpuName);
            lblStatus.setText(strStatus);
        }
        else
        {
            lblStatus.setText("All fields must be filled in before saving");
        }

    }

    /**
     * If the index is valid then delete the record from the database
     */
    private void DeleteButtonHandler()
    {
        int iIdentifier;
        String strStatus;

        if(txtCpuIndex.getText().isEmpty() == false)
        {
            iIdentifier = Integer.parseInt(txtCpuIndex.getText());

            cpuController.Delete(iIdentifier);

            UpdateCpuList();

            strStatus = String.format("Deleted %s", txtCpuName.getText());
            lblStatus.setText(strStatus);

        }
        else
        {
            lblStatus.setText("Could not delete the record");
        }
    }
}

