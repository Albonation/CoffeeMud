package com.planet_ink.coffee_mud.system;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
public class DBConnector
{
	private static DBConnections DBs=null;
	public static boolean DBConfirmDeletions=false;
	
	public static void connect (String NEWDBClass,
					  		    String NEWDBService, 
								String NEWDBUser, 
								String NEWDBPass, 
								int NEWnumConnections,
								boolean DoErrorQueueing)
	{
		DBs=new DBConnections(NEWDBClass,NEWDBService,NEWDBUser,NEWDBPass,NEWnumConnections,DoErrorQueueing);
	}
	
	public static int update(String updateString)
	{ return DBs.update(updateString);}
	
	/** 
	 * Fetch a single, not in use DBConnection object. 
	 * You can then call DBConnection.query and DBConnection.update on this object.
	 * The user must ALWAYS call DBDone when done with the object.
	 * 
	 * <br><br><b>Usage: DB=DBFetch();</b> 
	 * @param NA
	 * @return DBConnection	The DBConnection to use
	 */
	public static DBConnection DBFetch()	
	{return DBs.DBFetch();}
	/** 
	 * Fetch a single, not in use DBConnection object. 
	 * You can then call DBConnection.query and DBConnection.update on this object.
	 * The user must ALWAYS call DBDone when done with the object.
	 * 
	 * <br><br><b>Usage: DB=DBFetchPrepared();</b> 
	 * @param SQL	The prepared statement SQL
	 * @return DBConnection	The DBConnection to use
	 */
	public static DBConnection DBFetchPrepared(String SQL)	
	{ return DBs.DBFetchPrepared(SQL);}
	/** 
	 * Return a DBConnection object fetched with DBFetch()
	 * 
	 * <br><br><b>Usage:</b> 
	 * @param D	The Database connection to return to the pool
	 * @return NA
	 */
	public static void DBDone(DBConnection D)
	{ DBs.DBDone(D);}

	/** 
	 * When reading a database table, this routine will read in
	 * the given Field NAME, returning the value.  The value
	 * will be trim()ed, and will not be NULL.
	 * 
	 * <br><br><b>Usage:</b> str=getLongRes(R,"FIELD");
	 * @param Results	The ResultSet object to use
	 * @param Field		Field name to return
	 * @return String	The value of the field being returned
	 */
	public static String getRes(ResultSet Results, String Field)
	throws SQLException
	{ return DBs.getRes(Results,Field);}

	public static String getResQuietly(ResultSet Results, String Field)
	throws SQLException
	{ return DBs.getResQuietly(Results, Field);}

	/** 
 	 * When reading a database table, this routine will read in
	 * the given Field NAME, returning the value.  The value
	 * will be trim()ed, and will not be NULL.
	 * 
	 * <br><br><b>Usage:</b> str=getLongRes(R,"FIELD");
	 * @param Results	The ResultSet object to use
	 * @param Field		Field name to return
	 * @return String	The value of the field being returned
	 */
	public static long getLongRes(ResultSet Results, String Field)
	{ return DBs.getLongRes(Results,Field);}
	
	/** 
	 * When reading a database table, this routine will read in
	 * the given One index number, returning the value.  The value
	 * will be trim()ed, and will not be NULL.
	 * 
	 * <br><br><b>Usage:</b> str=getRes(R,1);
	 * @param Results	The ResultSet object to use
	 * @param One		Field number to return
	 * @return String	The value of the field being returned
	 */
	public static String getRes(ResultSet Results, int One)
	{ return DBs.getRes(Results,One);}
	
	/** 
	 * Destroy all database connections, effectively
	 * shutting down this class.
	 * 
	 * <br><br><b>Usage:</b> killConnections();
	 * @param NA
	 * @return NA
	 */
	public static void killConnections()
	{ DBs.killConnections();}
	
	/** 
	 * Return the happiness level of the connections
	 * <br><br><b>Usage:</b> amIOk()
	 * @param NA
	 * @return boolean	true if ok, false if not ok
	 */
	public static boolean amIOk()
	{ return DBs.amIOk();}
	
	/** 
	 * Queue up a failed write/update for later processing.
	 * 
	 * <br><br><b>Usage:</b> enQueueError("UPDATE SQL","error string");
	 * @param SQLString	UPDATE style SQL statement
	 * @param SQLError	The error message being reported
	 * @return NA
	 */
	public static void enQueueError(String SQLString, String SQLError)
		throws IOException
	{ DBs.enQueueError(SQLString, SQLError);}
	
	
	/** 
	 * Queue up a failed write/update for later processing.
	 * 
	 * <br><br><b>Usage:</b> RetryQueuedErrors();
	 * @param NA
	 * @return NA
	 */
	public static void retryQueuedErrors()
	{ DBs.retryQueuedErrors();}
	
	/** write a buffer of data to numerous SQL records by
	 * breaking the buffer into small chunks
	 * 
	 * <br><br><b>Usage:</b> writeCheeseWheelBuffer(SQL, buf);
	 * @param SQL	The prepared statement string
	 * @param buf	the buffer to send
	 * @return int	the response code, or -1 for an error
	 */
	public static int writeCheeseWheelBuffer(String SQL, byte[] buf)
	{ return DBs.writeCheeseWheelBuffer(SQL,buf);}
	
	/** read a buffer of data to numerous SQL records by
	 * breaking the buffer into small chunks
	 * 
	 * <br><br><b>Usage:</b> buf=readCheeseWheelBuffer(SQL);
	 * @param SQL	The query string
	 * @param buf	the buffer to send
	 * @return byte[]	the buffer of data
	 */
	public static byte[] readCheeseWheelBuffer(String SQL)
	{ return DBs.readCheeseWheelBuffer(SQL);}
	
	/** list the connections 
	 * 
	 * <br><br><b>Usage:</b> listConnections(out);
	 * @param PrintStream	place to send the list out to
	 * @return NA
	 */
	public static void listConnections(PrintStream out)
	{ DBs.listConnections(out);}
	
	/** return a status string, or "" if everything is ok.
	 * 
	 * <br><br><b>Usage:</b> errorStatus();
	 * @param NA
	 * @return StringBuffer	complete error status
	 */
	public static StringBuffer errorStatus()
	{ return DBs.errorStatus();}
}
