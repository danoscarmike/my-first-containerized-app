package firstcontainer;

import static spark.Spark.*;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;

public class App {
	
	public static void main(String[] args) {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(System.getenv("GOOGLE_MAPS_API_KEY"))
			    .build();
		String input = "O'Keefe's Balboa St San Francisco";
		TextSearchRequest query = PlacesApi.textSearchQuery(context, input);
		try {
			PlacesSearchResponse result = query.await();
			if(result.results[0].openingHours.openNow) {
				get("/hello", (req, res) -> "🍺O'Keefe's is open, let's go for a pint!");
			}
			else {
				get("/hello", (req, res) -> "Dang, O'Keefe's is closed.😔");
			}
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.shutdown();
	}

}
