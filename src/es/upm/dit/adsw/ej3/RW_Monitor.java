package es.upm.dit.adsw.ej3;

/**
 * Monitor de lecturas-escrituras.
 * @author Luis Alberto López Álvarez
 * @author Carlos Daniel Ramirez Santana
 * @version 10.4.2019
 */

public class RW_Monitor extends RW_Monitor_0 {
	private int nReadersIn = 0;
	private int nWrittersIn = 0;
    /**
     * Getter.
     * @return numero de lectores autorizados en este momento.
     */
    public synchronized int getNReadersIn() {
        return nReadersIn;
    }

    /**
     * Getter.
     *
     * @return numero de escritores autorizados en este momento.
     */
    public synchronized int getNWritersIn() {
        return nWrittersIn;
    }

    /**
     * Solicitud de permiso para hacer una lectura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public synchronized void openReading() {
    	while(nWrittersIn > 0)
    		waiting();
    	nReadersIn++;
//        System.out.println("openReading()");
    }

    /**
     * Devolucion del permiso de lectura.
     *
     * @throws IllegalMonitorStateException si no hay algun lector dentro.
     */
    public synchronized void closeReading()
            throws IllegalMonitorStateException {
    	if(nReadersIn <= 0)
    		throw new IllegalMonitorStateException();
    	nReadersIn--;
    	notifyAll();
//        System.out.println("closeReading()");
    }

    /**
     * Solicitud de permiso para hacer una escritura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public synchronized void openWriting() {
    	while(nWrittersIn > 0 || nReadersIn > 0)
    		waiting();
    	nWrittersIn++;
//        System.out.println("openWriting()");
    }

    /**
     * Devolucion del permiso de escritura.
     *
     * @throws IllegalMonitorStateException si no hay un escritor.
     */
    public synchronized void closeWriting()
            throws IllegalMonitorStateException {
    	if(nWrittersIn <= 0)
    		throw new IllegalMonitorStateException();
    	nWrittersIn--;
    	notifyAll();
//        System.out.println("closeWriting()");
    }

/*
    void invariant() {
        if (getNReadersIn() > 0 && getNWritersIn() > 0)
            System.err.println("ERROR");
        if (getNWritersIn() > 1)
            System.err.println("ERROR");
    }
*/

    private void waiting() {
        try {
            wait();
        } catch(Exception e) {
        }
    }
}
