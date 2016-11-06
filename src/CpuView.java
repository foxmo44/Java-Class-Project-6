import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import javafx.stage.Stage;

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
        Scene scene01 = new Scene( root, 500, 500 );
        mainStage.setScene( scene01 );

        lblCpuList = new Label( "CPU Information List" );

//        lbl02 = new Label( "???" );
//        lbl03 = new Label( "???" );
//        lbl04 = new Label( "???" );
//
//        // The backing data structure for the ListView
//        ObservableList< Book > list01 = FXCollections.observableArrayList( getAllBooks() );
//        listView01 = new ListView< Book >( list01 );
//        listView01.setPrefSize( 160, 160 );
//
//
//        MultipleSelectionModel< Book > msm01 = listView01.getSelectionModel();
//
//        msm01.selectedItemProperty().addListener(
//                (changedValue, oldValue, newValue) -> {
//                    lbl01.setText( newValue.getTitle() );
//                    lbl02.setText( Integer.toString( newValue.getPageCount() ) );
//                    lbl03.setText( newValue.getPubDate().toString() );
//                    lbl04.setText( Integer.toString( newValue.getID() ) );
//                }
//        );

        root.getChildren().addAll( lblCpuList);
        mainStage.show();

    }
}
