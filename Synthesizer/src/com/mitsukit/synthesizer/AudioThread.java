package com.mitsukit.synthesizer;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioThread extends Thread
{
	//how many samples each buffer will contain
	static final int BUFFER_SIZE = 512;
	//how many buffers will be in cue
	static final int BUFFER_COUNT = 8;
	
	private final int[] buffers = new int[BUFFER_COUNT];
	//open default device
	private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
	//passing no attributes = 0
	//context evrything you need to know about the device / current stage 
	private final long context = alcCreateContext(device, new int[0]);
	private final int source;
	
	/*Cue mechanism
	 * will initially start from 0, then go through up to 7
	 * reset to 0
	 */
	private int bufferIndex;
	private boolean closed;
	private boolean running;
	
	AudioThread()
	{
		alcMakeContextCurrent(context);
		AL.createCapabilities(ALC.createCapabilities(device));
		source = alGenSources();
		for(int i = 0; i < BUFFER_COUNT; i++) 
		{
			//set dummy buffer
			bufferSamples(new short[0]);
			
		}
		
		alSourcePlay(source);
		catchInternalException();
		start();
	}
	
	@Override
	public synchronized void run() 
	{
		while(!closed) 
		{
			while(!running) 
			{
				
			}
			
		}
		
	}
	
	//audio data we want to buffer
	private void bufferSamples(short[] samples) 
	{
		int buf = buffers[bufferIndex++];
		alBufferData(buf, AL_FORMAT_MONO16, samples, Synthesizer.AudioInfo.SAMPLE_RATE);
		alSourceQueueBuffers(source, buf);
		bufferIndex %= BUFFER_COUNT; //if buffer index = 1, buffer_count = 8 
	}
	
	private void catchInternalException() 
	{
		int err = alcGetError(device);
		if(err != ALC_NO_ERROR) 
		{
			throw new OpenALException(err);
		}
	}
}
