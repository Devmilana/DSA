/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for loading shop details and pathways from a csv file         *
 *****************************************************************************************/
import java.util.*;
import java.io.*;

public class DataLoader 
{
    // Loads shops from CSV file
    public void loadShops(DSAGraph graph, DSAHashTable hashTable, String filepath) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) 
        {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");
                String shopNumber = values[0];
                String shopName = values[1];
                String category = values[2];
                String location = values[3];
                int rating = Integer.parseInt(values[4]);

                Shop shop = new Shop(shopNumber, shopName, category, location, rating);
                graph.addShop(shop);
                hashTable.put(category, shop);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Loads pathways from CSV file
    public void loadPathways(DSAGraph graph, String filepath) 
    {
	    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) 
        {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");
                String shopNumberFrom = values[0];
                String shopNumberTo = values[1];

                graph.addPathway(shopNumberFrom, shopNumberTo);
            }
	    } 
        catch (IOException e) 
        {
		    e.printStackTrace();
	    }
	}
}
