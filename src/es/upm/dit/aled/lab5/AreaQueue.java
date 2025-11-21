package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */
public class AreaQueue extends Area {
	
	//atributos 
	private Queue<Patient> waitQueue;
	
	//constructor: no tengo que hacer el this. con los atributos porque ya lo habíamos hecho en la clase padre que es 
	//Área. Lo que sí tendremos que hacer es usar el super y poner entre () sus atributos 
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		//esta lista la tenemos que declarar como LinkedList. Es la "novedad" que tiene esta clase y no la otra. 
		this.waitQueue = new LinkedList<Patient>();	
	}
	//vamos a reprogramar este enter que ya teníamos en la otra clase. 
	@Override 
	public synchronized void enter(Patient p) {
		
		this.waiting ++;
		this.waitQueue.add(p); //meto al paciente en la cola de espera 
		while(this.numPatients >= this.capacity || this.waitQueue.peek()!= p) { //el entrar o no ya no solo depende 
			//de si cabe o no, sino que depende tmb de si es el siguiente en la cola o no. 
			
			try {
				wait();
				
			} catch (InterruptedException e) {
			}
		}
		this.waitQueue.remove(); //le borramos de la lista de espera 
		this.waiting --;
		this.numPatients ++;		
	}
}
	
	
	



