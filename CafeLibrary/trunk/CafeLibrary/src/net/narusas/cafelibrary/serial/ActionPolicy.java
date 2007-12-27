package net.narusas.cafelibrary.serial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.narusas.cafelibrary.LibrarySerilizePolicy;

public class ActionPolicy extends LibrarySerilizePolicy implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		fire(e);
	}

}
