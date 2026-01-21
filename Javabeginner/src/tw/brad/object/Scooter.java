package tw.brad.object;

public class Scooter extends Bike {
	private int gear;
	private String color;
	
	public Scooter() {
		super(1);
		System.out.println("Scooter()");
		color = "Yellow";
	}
	
	// Overload
	public Scooter(String color) {
		this.color = color;
	}
	
	public String getColor() {return color;}
	
	public Scooter upSpeed() {
		speed = speed < 1 ? 1 : speed * 1.8 * gear;
		return this;
	}
	
	// Overload，增加一個參數int level
	public void upSpeed(int level) {
		speed = speed < 1 ? 1 : speed * 1.8 * level;
	}
	
	public void setGear(int gear) {
		if (gear >=0 && gear <= 4) {
			// this.gear 是 private int gear
			this.gear = gear;
		}
	}
	
	public int getGear() {
		return gear;
	}
}
