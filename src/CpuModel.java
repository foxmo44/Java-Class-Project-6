import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>Advanced Java - Project06Fox</h1>
 * <h1>CpuModel  Class</h1>
 * This is the class is the interface to the database of CPUs
 * <p>
 * <b>Create Date: 11/6/2016</b>
 * <b>Due Date: 11/16/2016</b>
 *
 * @author Michael Fox
 */
public class CpuModel
{
    /**
     * The interface objects for the database
     */
    private static Connection c;
    private static Statement s;
    private static ResultSet r;
    private static boolean bConnected = false;

    /**
     * constructore for the CpuModel that will connect to the database
     */
    public CpuModel()
    {

    }

    /**
     * @return - Get the list of CPU
     */
    public static ArrayList< CPU > getAllCpu()
    {
        ArrayList< CPU > cpuList = new ArrayList<>();

        //Loop through the records sets and populate the CPU list
        try
        {
            bConnected = Connect("cpudb", "tcc2016", "tcc2016");

            if(bConnected == true)
            {
                System.out.println("Connected db");
            }
            else
            {
                System.out.println("****Could not Connect to db****");
            }

            s = c.createStatement();

            r = s.executeQuery("SELECT * from cputable order by price DESC");

            while( r.next() )
            {
                cpuList.add( new CPU(r.getString( "cpuname" ),r.getInt( "performance" ),r.getFloat( "price" )));
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return cpuList;
    }


    /**
     * Function to take care of connecting to the database
     * @param strDatabase -  name of the database
     * @param strUser - user name
     * @param strPassword - password for the user
     * @return status either true or false when connecting
     */
    private static boolean Connect(String strDatabase, String strUser, String strPassword)
    {
        boolean bConnectStatus = false;

        try
        {
            //Had to suppress a SSL warning message that kept popping up
            c = DriverManager.getConnection("jdbc:mysql://localhost/" + strDatabase + "?autoReconnect=true&useSSL=false", strUser, strPassword);

            System.out.println("Database connection made\n");

            bConnectStatus = true;
        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return(bConnectStatus);
    }

}
