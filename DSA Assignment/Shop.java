/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and maintaining a Shop object                    *
 *****************************************************************************************/
public class Shop 
{
    private String shopNumber; // Variable for holding shop number
    private String shopName; // Variable for holding shop name
    private String shopCategory; // Variable for holding shop category
    private String shopLocation; // Variable for holding shop location
    private int shopRating; // Variable for holding shop rating

    // Constructor - Initializes a Shop with parameters after validation
    public Shop(String inNumber, String inName, String inCategory, String inLocation, int inRating) 
    {
        // Validation for non-null and non-empty values, and valid rating range
        if (inNumber == null || inNumber.trim().isEmpty() || inName == null || inName.trim().isEmpty() || 
            inCategory == null || inCategory.trim().isEmpty() || inLocation == null || inLocation.trim().isEmpty() || 
            inRating < 1 || inRating > 5) 
        {
            throw new IllegalArgumentException("Invalid shop details provided");
        }

        // Sets fields with given parameter values
        setShopNumber(inNumber);
        setShopName(inName);
        setShopCategory(inCategory);
        setShopLocation(inLocation);
        setShopRating(inRating);
    }
    
    // Returns shop number
    public String getShopNumber() 
    {
        return shopNumber;
    }
    
    // Returns shop name
    public String getShopName() 
    {
        return shopName;
    }
    
    // Returns shop category
    public String getShopCategory() 
    {
        return shopCategory;
    }
    
    // Returns shop location
    public String getShopLocation() 
    {
        return shopLocation;
    }
    
    // Returns shop rating
    public int getShopRating() 
    {
        return shopRating;
    }

    // Sets shop number after validating it's non-null and non-empty
    public void setShopNumber(String inNumber) 
    {
        if (inNumber == null || inNumber.trim().isEmpty()) // Check if shop number input is not null or empty
        {
            throw new IllegalArgumentException("Shop number cannot be empty or null");
        }

        shopNumber = inNumber;
    }

    // Sets shop name after validating it's non-null and non-empty
    public void setShopName(String inName) 
    {
        if (inName == null || inName.trim().isEmpty()) // Check if shop name input is not null or empty
        {
            throw new IllegalArgumentException("Shop name cannot be empty or null");
        }

        shopName = inName;
    }

    // Sets shop category after validating it's non-null and non-empty
    public void setShopCategory(String inCategory) 
    {
        if (inCategory == null || inCategory.trim().isEmpty()) // Check if shop category input is not null or empty
        {
            throw new IllegalArgumentException("Category cannot be empty or null");
        }

        shopCategory = inCategory;
    }

    // Sets shop location after validating it's non-null and non-empty
    public void setShopLocation(String inLocation) 
    {
        if (inLocation == null || inLocation.trim().isEmpty()) // Check if shop location input is not null or empty
        {
            throw new IllegalArgumentException("Location cannot be empty or null");
        }

        shopLocation = inLocation;
    }

    // Sets shop rating after validating it's within a range of 1-5
    public void setShopRating(int inRating) 
    {
        if (inRating < 1 || inRating > 5) // Check if shop rating input is within accepted range
        {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        shopRating = inRating;
    }
}
