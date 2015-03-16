package com.excilys.computerdb.printing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.excilys.computerdb.beans.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class Paging.
 */
public class Paging implements KeyListener {
	
	/** The list computer. */
	private List<Computer> listComputer;
	
	/** The size. */
	private int size;
	
	/** The finished. */
	private boolean finished;
	
	/** The page seize. */
	private static int PAGE_SEIZE = 5;
	
	/** The cptr. */
	private int cptr;
	
	/**
	 * Instantiates a new paging.
	 *
	 * @param listComputer the list computer
	 */
	public Paging(List<Computer> listComputer) {
		this.cptr = 0;
		this.listComputer = listComputer;
		this.size = listComputer.size();
		this.finished = false;
		if (this.size > PAGE_SEIZE) {
			for (cptr = 0; cptr < PAGE_SEIZE; cptr++) {
				System.out.println(listComputer.get(cptr));
			}
		} else {
			System.out.println(this.listComputer);
			this.finished = true;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		/*if (e.)
		while (finished) {
			
		}*/
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
