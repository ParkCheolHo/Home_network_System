

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Logger;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class Racar {
    private static final int sizeBuf = 50;
    private static int port = 8888;
    final static GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pinlpwm = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "lpwm", PinState.LOW);
    final GpioPinDigitalOutput pinlfwd = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "lfwd", PinState.LOW);
    public static int a = 1;
    
    
    

    public static void main(String[] args) throws IOException {
	try {
	Thread pushthread = new Thread(new pushbutton(pushButton));	
        pushthread.start();
	ServerSocket serverSock = new ServerSocket(port);
        Logger logger = Logger.getLogger("Racar");
	
        while (true) {
	    
            Socket clientSock = serverSock.accept();
            SocketAddress clientAddress = clientSock.getRemoteSocketAddress();
            System.out.println("Thread started");
            Thread thread = new Thread(new RcvThread(clientSock, clientAddress, logger, pinlfwd, pinlpwm));
	    //Thread athread = new Thread(new aThread(clientSock, clientAddress, logger, aButton));
	    thread.start();
	    //athread.start();
	   
            if(clientSock.isConnected())
                System.out.println("Connected! Client IP : " + clientAddress);
        }
	}catch (IOException e) {
	    	System.out.println("Exception: " + e);
	}
    }
}
