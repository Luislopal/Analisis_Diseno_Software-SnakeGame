package es.upm.dit.adsw.ej3;
import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene y protege la lista de manzanitas.
 * @author Luis Alberto Lopez Alvarez
 * @author Carlos Daniel Ramirez Santana
 * @version 10.04.2019
 */

public class AppleListMonitor {
	private List<Apple> appleList = new ArrayList<Apple>();
	private RW_Monitor monitor = new RW_Monitor();
	
	/**
	 * Tenemos una manzana mas.
	 * @param apple manzana.
	 */
	public void add(Apple apple) {
		monitor.openWriting();
		appleList.add(apple);
		monitor.closeWriting();
	}
	
	/**
	 * Tenemos una manzana menos.
	 * @param apple manzana
	 */
	public void remove(Apple apple) {
		monitor.openWriting();
		appleList.remove(apple);
		apple.quit();
		monitor.closeWriting();
	}
	
	/**
	 * Informa de si hay alguna manzana cerca del segmento P1-P2
	 * @param P1 un extremo del segmento
	 * @param P2 el otro extremo del segmento
	 * @return la manzana cercana, si la hubiera; null si no
	 */
	public Apple getCloseApple(XY P1, XY P2) {
		monitor.openReading();
		for(Apple apple: appleList)
			if(apple.getXY().isCloseTo(P1, P2)) {
				monitor.closeReading();
				return apple;
			}
		monitor.closeReading();
		return null;
	}
	
	/**
	 * Actua sobre una manzana cerca del segmento P1-P2
	 * @param P1 un extremo del segmento
	 * @param P2 el otro extremo del segmento
	 * @return la manzana cercana, si la hubiera; null si no	 
	 */
	public Apple hitCloseApple(XY P1, XY P2) {
		monitor.openWriting();
		Apple apple = null;
		for(Apple a: appleList)
			if(a.getXY().isCloseTo(P1, P2)) {
				apple = a;
				break;
			}
		if(apple!= null) {
			appleList.remove(apple);
			apple.quit();
		}
		monitor.closeWriting();
		return apple;
	}

}
