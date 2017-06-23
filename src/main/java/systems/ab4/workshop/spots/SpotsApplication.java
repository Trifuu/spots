package systems.ab4.workshop.spots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.DateFormatter;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class SpotsApplication {

	private static final Logger log= (Logger) LoggerFactory.getLogger(SpotsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpotsApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(LocationRepository locationRepository,SpotRepository spotRepository){
		return (String... args) ->{
			Location romania = new Location("Romania",null, LocationType.COUNTRY);

			Location ilfov = new Location( "Ilfov", romania,LocationType.REGION);
			Location brasov = new Location("Brasov",romania,LocationType.REGION);
			Location constanta = new Location("Constanta",romania,LocationType.REGION);

			//ilfov
			Location bucuresti=new Location("Bucuresti",ilfov,LocationType.CITY);
			//brasov
			Location predeal = new Location("Predeal",brasov,LocationType.CITY);
			Location sinaia = new Location("Sinaia",brasov,LocationType.CITY);

			//constanta
			Location mamaia = new Location( "Mamaia",constanta,LocationType.CITY);
			Location neptun = new Location("Neptun",constanta,LocationType.CITY);
			Location vama =new Location("Vama",constanta,LocationType.CITY);


            List<Location> locations= Arrays.asList(romania,ilfov,brasov,constanta,bucuresti,predeal,sinaia,mamaia,neptun,vama);
            //save whole list
            locationRepository.save(locations);

            log.info("Locations found with findAll():");
            log.info("-------------------------------");
            for(Location location: locationRepository.findAll()){
                log.info(location.toString());
            }
            log.info("");

            //fetch location by name
            log.info("Location found with findByLastName('Brasov')");
            log.info("-------------------------------------------");
            for(Location location : locationRepository.findByName("Brasov")){
                log.info(location.toString());
            }
            log.info("");
            DateFormatter df = new DateFormatter("dd/MM/yyyy");
            Locale ro = Locale.forLanguageTag("ro");

            List<Activity> ts = Arrays.asList(Activity.CLIMB);
            Spot predealClimbing= new Spot("Predeal Climbing School",predeal,df.parse("15/06/2017",ro),df.parse("15/10/2017",ro),29.99,
                    new HashSet<>(ts));
            List<Spot> spots=Arrays.asList(predealClimbing);
            spotRepository.save(spots);

            log.info("Location found with findOne(1L):");
            log.info("----------------------------------");
            log.info(spotRepository.findOne(1L).toString());

        };
	}
}