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
    public static ArrayList<CPU> getAllCpu()
    {
        ArrayList<CPU> cpuList = new ArrayList<>();

        //Loop through the records sets and populate the CPU list
        try
        {
            if (bConnected == false)
            {
                bConnected = Connect("cpudb", "tcc2016", "tcc2016");
            }

            if (bConnected == true)
            {
                System.out.println("Connected db");
            } else
            {
                System.out.println("****Could not Connect to db****");
            }

            s = c.createStatement();

            r = s.executeQuery("SELECT * from cputable order by price DESC");

            while (r.next())
            {
                cpuList.add(new CPU(r.getInt("id"), r.getString("cpuname"), r.getInt("performance"), r.getFloat("price")));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return cpuList;
    }


    /**
     * Function to take care of connecting to the database
     *
     * @param strDatabase -  name of the database
     * @param strUser     - user name
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
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return (bConnectStatus);
    }


    /**
     * Save the cpu information into the database
     * @param strCpuName - name of the CPU
     * @param iPerformance - Performance rating of the CPU
     * @param dPrice - the price of the CPU
     */
    public static void save(String strCpuName, int iPerformance, double dPrice)
    {
        String strSql;

        try
        {
            if (bConnected == false)
            {
                bConnected = Connect("cpudb", "tcc2016", "tcc2016");
            }

            s = c.createStatement();

            strSql = "insert into cputable( cpuname, performance, price) values('"
                    + strCpuName + "',"
                    + iPerformance + ","
                    + dPrice + ")";

            //System.out.println(strSql);
            s.execute(strSql);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

//    // Creates a new book in the database.
//    public static void save( String t, int pc, String pd, int g ){
//        try{
//            c = DriverManager.getConnection( "jdbc:mysql://localhost/books2016", "tcc2016", "tcc2016" );
//            s = c.createStatement();
//            s.execute( "insert into book (title,pagecount,pubdate,genre) values ('"
//                    + t + "',"
//                    + pc + ",'"
//                    + pd + "',"
//                    + g + ")" );
//        }catch( Exception e ){
//            e.printStackTrace();
//        }
//    }
//
//    // Deletes a book from the database
//    public static void delete( int id ){
//        try{
//            c = DriverManager.getConnection( "jdbc:mysql://localhost/books2016", "tcc2016", "tcc2016" );
//            s = c.createStatement();
//            s.execute( "delete from book where id = " + id );
//        }catch( Exception e ){
//            e.printStackTrace();
//        }
//    }
//
//    // Updates an existing book in the database.
//    public static void update( int id, String t, int pc, String pd, int g ){
//        try{
//            c = DriverManager.getConnection( "jdbc:mysql://localhost/books2016", "tcc2016", "tcc2016" );
//            s = c.createStatement();
//            s.execute( "update book set title = '"
//                    + t + "', pagecount ="
//                    + pc + ", pubdate ='"
//                    + pd + "', genre ="
//                    + g + " where id = "
//                    + id );
//        }catch( Exception e ){
//            e.printStackTrace();
//        }
//    }
//
//    // Returns a Book for a given ID.
//    // used by both edit and get details for a given book
//    public static Book getBookByID( int id ){
//        Book newBook = null;
//        try{
//            c = DriverManager.getConnection( "jdbc:mysql://localhost/books2016", "tcc2016", "tcc2016" );
//            s = c.createStatement();
//            r = s.executeQuery( "Select id, title, pagecount, pubdate, genre from book where id = " + id );
//            r.next();
//            newBook = new Book( r.getInt( 1 ), r.getString( 2 ), r.getInt( 3 ), r.getDate( 4 ), r.getInt( 5 ) );
//        }catch( Exception e ){
//            e.printStackTrace();
//        }
//        return newBook;
//    }
//
