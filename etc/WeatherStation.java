// Example code from Head First Design Patterns: A Brain-Friendly Guide
// By Eric Freeman,‎ Bert Bates,‎ Kathy Sierra,‎ Elisabeth Robson 
// O'Reilly Media; 1st edition (October 2004)

public class WeatherStation {

	public static void main(String[] args) {
		// WeatherData implements "Subject" interface, so it is the "Publisher"
		// It is Loosely Coupled with the Observers, so it doesn't know much about 
		// them, other than it can add/remove them, and update them.
		WeatherData weatherData = new WeatherData();
		
	
		// 3 different Display types...all implementing Observer interface 
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

		// *** Add new Display type!!  If we follow the Observer pattern,
		// --> HeatIndexDisplay takes in Publisher object, weatherData, registers itself in constructor
		// --> When there is an "event" that Observers should be updated on, Publisher will fire off update() to all
		// e.g. Publisher's setMeasurements(f,f,f) calls measurementsChanged(), which calls notifyObservers()
		// which in turn looops though and calls update() on each registered Observer.  Since our HeatIndexDisplay
		// registered it will now receive those updates, with NO changes needed in the Publisher class!  
		//HeatIndexDisplay heat = new HeatIndexDisplay(weatherData);


		weatherData.setMeasurements(80, 65, 30.4f);
		System.out.println();
		weatherData.setMeasurements(82, 70, 29.2f);
		System.out.println();
		weatherData.setMeasurements(78, 90, 29.2f);
		
	}
}
