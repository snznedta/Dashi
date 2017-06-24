
package model;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class RestaurantTest {
    Restaurant restaurant;

    @Test
    public void testJsonArrayToString() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Chinese");
        jsonArray.put("Japanese");
        jsonArray.put("Italian");
        assertEquals("Chinese,Japanese,Italian",
                Restaurant.jsonArrayToString(jsonArray));
    }

    @Test
    public void testJsonArrayToStringCornerCases() {
        JSONArray jsonArray = new JSONArray();
        assertEquals("", Restaurant.jsonArrayToString(jsonArray));
        jsonArray.put("Chinese");
        assertEquals("Chinese", Restaurant.jsonArrayToString(jsonArray));
        jsonArray.put("Japanese");
        jsonArray.put("");
        String str = Restaurant.jsonArrayToString(jsonArray);
        assertEquals("Chinese,Japanese,", str);
    }

    @Rule public TestName name = new TestName();

    @Before
    public void setUp() {
        restaurant = new Restaurant("yam-leaf-bistro-mountain-view", "Yam Leaf Bistro",
                "Vegetarian,vegetarian,Vegan,vegan,Gluten-Free,gluten_free",
                "Mountain View", "CA", 4.5, "699 Calderon Ave,Mountain View, CA 94041",
                37.3851249, -122.075775,
                "http://s3-media1.fl.yelpcdn.com/bphoto/6NchHRhvHpVj4DXs2WQATw/ms.jpg",
                "http://www.yelp.com/biz/yam-leaf-bistro-mountain-view");
    }

    @Test
    public void testRestaurantConstructor() {
        String jsonString = "{\"is_claimed\": true, \"rating\": 4.5, \"mobile_url\": \"http://m.yelp.com/biz/yam-leaf-bistro-mountain-view\", \"rating_img_url\": \"http://s3-media2.fl.yelpcdn.com/assets/2/www/img/99493c12711e/ico/stars/v1/stars_4_half.png\", \"review_count\": 204, \"name\": \"Yam Leaf Bistro\", \"snippet_image_url\": \"http://s3-media4.fl.yelpcdn.com/photo/JYmqUtFxgYe-dbbcTqqzkw/ms.jpg\", \"rating_img_url_small\": \"http://s3-media2.fl.yelpcdn.com/assets/2/www/img/a5221e66bc70/ico/stars/v1/stars_small_4_half.png\", \"url\": \"http://www.yelp.com/biz/yam-leaf-bistro-mountain-view\","
                +  "\"categories\": [{\"title\": \"Vegetarian\"}, {\"title\": \"vegetarian\"}, {\"title\": \"Vegan\"}, {\"title\": \"vegan\"},  {\"title\": \"Gluten-Free\"},  {\"title\": \"gluten_free\"}], \"phone\": \"6509409533\", \"snippet_text\": \"Phenomenal Pan-Latin vegetarian, vegan (any dish can be made vegan), and gluten-free dishes. There selection of organic wines and beers is incredible--I go...\", \"image_url\": \"http://s3-media1.fl.yelpcdn.com/bphoto/6NchHRhvHpVj4DXs2WQATw/ms.jpg\", \"location\": {\"city\": \"Mountain View\", \"display_address\": [\"699 Calderon Ave\", \"Mountain View, CA 94041\"], \"geo_accuracy\": 9.5, \"postal_code\": \"94041\", \"country_code\": \"US\", \"address\": [\"699 Calderon Ave\"], \"state\": \"CA\"}, \"coordinates\": {\"latitude\": 37.3851249, \"longitude\": -122.075775}, \"display_phone\": \"+1-650-940-9533\", \"rating_img_url_large\": \"http://s3-media4.fl.yelpcdn.com/assets/2/www/img/9f83790ff7f6/ico/stars/v1/stars_large_4_half.png\", \"id\": \"yam-leaf-bistro-mountain-view\", \"is_closed\": false, \"distance\": 681.2472686205965}";

        Restaurant new_restaurant = null;
        try {
            new_restaurant = new Restaurant(new JSONObject(jsonString));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(restaurant.getBusinessId(), new_restaurant.getBusinessId());
        assertEquals(restaurant.getName(), new_restaurant.getName());
        assertEquals(restaurant.getCategories(), new_restaurant.getCategories());
        assertEquals(restaurant.getCity(), new_restaurant.getCity());
        assertEquals(restaurant.getState(), new_restaurant.getState());
        assertEquals(restaurant.getFullAddress(), new_restaurant.getFullAddress());
        assertEquals(restaurant.getStars(), new_restaurant.getStars(), 0);
        assertEquals(restaurant.getLatitude(), new_restaurant.getLatitude(), 0);
        assertEquals(restaurant.getLongitude(), new_restaurant.getLongitude(), 0);
        assertEquals(restaurant.getImageUrl(), new_restaurant.getImageUrl());
        assertEquals(restaurant.getUrl(), new_restaurant.getUrl());
    }

    @After
    public void tearDown() {
        System.out.println("Test finished: " + name.getMethodName());
    }


}
