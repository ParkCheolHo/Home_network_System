
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class eThread extends Thread{
    
    final static GpioController gpio = GpioFactory.getInstance();	
    final static GpioPinDigitalOutput pushButton = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "lfwd", PinState.LOW);
    Thread PushThread = new Thread(new pushbutton());
    
	public void run(){
        
	while (true) {
		
		if(pushButton.isHigh()){
    		
		System.out.println("isHigh");	
    		}
       
	}
		
            
	    
        	
             
       
    }
}
