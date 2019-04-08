package okeefes;

import java.io.IOException;

import com.google.maps.*;
import com.google.maps.model.*;
import com.google.maps.errors.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping
	public String home() {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(System.getenv("GOOGLE_MAPS_API_KEY"))
			    .build();
		String input = "O'Keefe's, Balboa Street, San Francisco, CA";
		TextSearchRequest query = PlacesApi.textSearchQuery(context, input);
		try {
			PlacesSearchResponse result = query.await();
			if(result.results[0].openingHours.openNow) {
				return "O'Keefe's is open. Woot! Let's go for a pint.";
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
		return "The bar is closed. :(";
		
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}