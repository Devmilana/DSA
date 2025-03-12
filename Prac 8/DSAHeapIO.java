/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *								                                            			 *
 * LAST EDITED: 11/11/23                                                                 *
 *										                                            	 *
 * DESCRIPTION: Class file for reading and writing csv files for heaps        			 *                                                                
 * **************************************************************************************/
import java.util.*;
import java.io.*;

public class DSAHeapIO
{
    // Load CSV file
    public static void loadCSV(DSAHeap heap, String fileName)
    {
        FileInputStream strm = null;
        InputStreamReader rdr;
        BufferedReader bfr;
        DSALinkedList entries = new DSALinkedList();
        String line;
        int dupes = 0;

        try
        {
            strm = new FileInputStream(fileName);
            rdr = new InputStreamReader(strm);
            bfr = new BufferedReader(rdr);

            line = bfr.readLine();
            while(line != null)
            {
                entries.insertLast(line);

                line = bfr.readLine();
            }

            bfr.close();
        }
        catch(FileNotFoundException error)
        {
            throw new IllegalArgumentException("File " + fileName + " not found");
        }
        catch(IOException error)
        {
            if(strm != null)
            {
                try
                {
                    strm.close();
                }
                catch(IOException error2)
                {
                    throw new IllegalArgumentException("File I/O error. The file cannout be closed");
                }
            }

            throw new IllegalArgumentException("File I/O error");
        }

        for(Object o : entries)
        {
            try
            {
                heap.add(Integer.parseInt(((String)o).split(",")[0]), ((String)o).split(",")[1]);
            }
            catch(IllegalArgumentException err)
            {
                dupes++;
            }
        }

        if(dupes != 0)
        {
            System.out.println("File IO Message: " + dupes + " duplicate keys found in file");
        }
    }
    
    // Save CSV file
    public static void saveCSV(DSAHeap heap, String fileName)
    {
        FileOutputStream strm = null;
        PrintWriter pw;
        
        try
        {
            strm = new FileOutputStream(fileName);
            pw = new PrintWriter(strm);

            for(String line : heap.export())
            {
                pw.println(line);
            }

            pw.close();
        }
        catch(IOException error)
        {
            if(strm != null)
            {
                try
                {
                    strm.close();
                }
                catch(IOException error2)
                {
                    throw new IllegalArgumentException("File I/O error. The file cannout be closed");
                }
            }

            throw new IllegalArgumentException("File I/O error");
        }
    }
}
