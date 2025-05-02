// Example code from Head First Design Patterns: A Brain-Friendly Guide
// By Eric Freeman,‎ Bert Bates,‎ Kathy Sierra,‎ Elisabeth Robson 
// O'Reilly Media; 1st edition (October 2004)
public class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	
	public void display() {
		System.out.println("---Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity---");
	}
}
