package es.upm.dit.adsw.ej3;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Pruebas monitor de lecturas-escrituras.
 * @author Luis Alberto López Álvarez
 * @author Carlos Daniel Ramirez Santana
 * @version 10.4.2019
 */

public class RW_MonitorTest {
	
	/**
	 * Prueba una apertura y posterior cierre de un Reader
	 */
	@Test
	public void test0() {
		RW_Monitor monitor = new RW_Monitor();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(1, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
	}
	
	/**
	 * Prueba una apertura y posterior cierre de un Writter
	 */
	@Test
	public void test1() {
		RW_Monitor monitor = new RW_Monitor();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(1, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
	}
	
	/**
	 * Prueba varias aperturas y posteriores cierres de un Reader
	 */
	@Test
	public void test2() {
		RW_Monitor monitor = new RW_Monitor();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(1, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(2, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(3, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(2, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(1, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
	}
	
	@Test
	public void test3() {
		RW_Monitor monitor = new RW_Monitor();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(1, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(2, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.openReading();
		assertEquals(1, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
		monitor.closeReading();
		assertEquals(0, monitor.getNReadersIn());
		assertEquals(0, monitor.getNWritersIn());
	}
	
	
	@Test(expected = Exception.class)
	public void ow_cr() {
		RW_Monitor monitor = new RW_Monitor();
		monitor.openWriting();
		monitor.closeReading();
	}
	
	@Test(expected = Exception.class)
	public void ow_cw() {
		RW_Monitor monitor = new RW_Monitor();
		monitor.openReading();
		monitor.closeWriting();
	}
}
