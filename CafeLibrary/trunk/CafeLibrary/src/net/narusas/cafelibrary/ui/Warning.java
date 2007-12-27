package net.narusas.cafelibrary.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Warning implements ActionListener {

	private static final Color WARNING_COLOR = Color.red;

	private final JComponent target;

	Color original;

	private Timer timer;
	static int count;

	public Warning(JButton target, String message) {
		timer = new Timer(300, this);

		this.target = target;
		this.target.setToolTipText(message);
		original = target.getBackground();
		timer.start();
	}

	protected void update() {
		System.out.println("UPDATE");
		Color c = null;
		if (count++ > 10) {
			c = original;
			timer.stop();
		}
		if (target.getBackground().equals(original)) {
			c = WARNING_COLOR;
		} else {
			c = original;
		}
		setColor(c);
	}

	private void setColor(final Color c) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				target.setBackground(c);
			}
		});
	}

	
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
