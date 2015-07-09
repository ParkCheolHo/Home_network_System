




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

public class RcvThread extends Thread{
    private static final int sizeBuf = 50;
    private Socket clientSock;
    private Logger logger;
    private SocketAddress clientAddress;
    private boolean input_value;
    private GpioPinDigitalOutput pinlfwd, pinlpwm;
    
    
  
    

    public RcvThread(Socket clntSock, SocketAddress clientAddress, Logger logger, GpioPinDigitalOutput pinlfwd, GpioPinDigitalOutput pinlpwm) {
        this.clientSock = clntSock;
        this.logger = logger;
        this.clientAddress = clientAddress;
	this.pinlfwd = pinlfwd;
	this.pinlpwm = pinlpwm;
    }
    
    
	public void run(){
        try {
	    
	    
            InputStream ins   = clientSock.getInputStream();
            OutputStream outs = clientSock.getOutputStream();
	    
            int rcvBufSize, exBufSize;
            byte[] rcvBuf = new byte[sizeBuf];
	    byte[]  exBuf = new byte[sizeBuf];
	    String str0 = "open";
	    byte[] bstbyte = str0.getBytes();
	    exBufSize = 4;
            int i = 0;
	    
	
		
	while ((rcvBufSize = ins.read(rcvBuf)) != -1) {
		System.out.println("alpha");
                String rcvData = new String(rcvBuf, 0, rcvBufSize, "UTF-8");
		
		
                if (rcvData.compareTo("S.Up") == 0) {
                pinlpwm.high();    
		pinlfwd.high();
	        outs.write(rcvBuf, 0, rcvBufSize);
                try{Thread.sleep(8000);}
                catch(InterruptedException e) {}
		pinlpwm.low();
		pinlfwd.low();

                System.out.println("up!");
                }
		else if (rcvData.compareTo("S.Down") == 0) {
                pinlpwm.low();    
		pinlfwd.high();
	        outs.write(rcvBuf, 0, rcvBufSize);
                try{Thread.sleep(8000);}
                catch(InterruptedException e) {}
		pinlpwm.low();
		pinlfwd.low();

                System.out.println("Down!");
                }
                logger.info("Received data : " + rcvData + " (" + clientAddress + ")");
                i++;
		System.out.println(""+ i);
	}
		
            
            logger.info(clientSock.getRemoteSocketAddress() + " Closed");
	    
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Exception in RcvThread", ex);
        } finally {
            try {
		
                clientSock.close();
		
                System.out.println("Disconnected! Client IP : " + clientAddress);
            } catch (IOException e) { }
        }
    }
}