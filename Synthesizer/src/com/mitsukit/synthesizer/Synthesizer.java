package com.mitsukit.synthesizer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Synthesizer 
{
	private JFrame frame = new JFrame("Synthesizer");
	
	Synthesizer()
	{
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(613, 357);
		frame.setResizable(false);
		frame.setLayout(null);
		// will open in the middle of screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static class AudioInfo
	{
		public static final int SAMPLE_RATE = 44100;
	}

}
