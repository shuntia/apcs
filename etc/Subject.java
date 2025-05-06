// Example code from Head First Design Patterns: A Brain-Friendly Guide
// By Eric Freeman,‎ Bert Bates,‎ Kathy Sierra,‎ Elisabeth Robson 
// O'Reilly Media; 1st edition (October 2004)

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
