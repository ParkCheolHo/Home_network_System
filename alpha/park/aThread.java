




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

public class aThread extends Thread{
    private static final int sizeBuf = 30;
    private Socket clientSock;
    private Logger logger;
    private SocketAddress clientAddress;
    private boolean input_value , num1;
    private GpioPinDigitalInput aButton;
    

    public aThread(Socket clntSock, SocketAddress clientAddress, Logger logger, GpioPinDigitalInput aButton) {
        this.clientSock = clntSock;
        this.logger = logger;
        this.clientAddress = clientAddress;
	this.aButton = aButton;

    }
    public void run(){
	try{	
	
	OutputStream outs = clientSock.getOutputStream();
    	int exBufSize = 4;
	int exBufSize1 = 5;
    	String str1 = "Open";
    	String str2 = "Closed";
    	byte[] bstbyte = str1.getBytes();
    	byte[] bstbyte1 = str2.getBytes();
    	
    	input_value = aButton.isHigh();
    	num1 = input_value;
	RcvThread ai; 
	
    	while(Racar.a == 1){
		
    		if(num1 != aButton.isHigh()){
    			if(num1 == false){
    				System.out.println("open");
    				num1 = true;
    				outs.write(bstbyte, 0, exBufSize);
				
				
    			}
			else if(num1 == true){
				System.out.println("close");
				num1 = false;
				outs.write(bstbyte1, 0, exBufSize1);
				
			}
    		}
	try{aThread.sleep(3000);}
	catch(InterruptedException e) {
		return;
		}
	}
    	} catch(IOException ex) {
    		logger.log(Level.WARNING, "Exception in aThread", ex);
		
    	} 
	
    	System.out.println("aThread Close");
    }
}


