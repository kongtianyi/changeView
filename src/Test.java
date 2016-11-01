package src;

import javax.swing.JFrame;

public class Test extends JFrame{
	
	public Test() {
		super("≤‚ ‘");		
		new ChangeView(this, 5, 40, 570, 600, true, "≤‚ ‘");
		this.setVisible(true);
		this.setSize(640, 400);
	}
	
	public static void main(String[] args) {
		Test test = new Test();
	}

}
