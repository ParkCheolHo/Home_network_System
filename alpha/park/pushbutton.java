import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class pushbutton extends Thread{	
	private boolean input_value = true;
	private Logger logger;
  	static GpioPinDigitalInput pushButton;
	public pushbutton(GpioPinDigitalInput aButton) {
    
	this.pushButton = aButton;

    }
public void run(){
	
	
	Sender sender = new Sender("AIzaSyD1ziPeLXV3Tpv_RegCr6jRORim9fwjfDM");
        String regId = "APA91bFS0Z92GX7LZ2VfR-OGVJ2N69WGnzMb4NAKX-zMFFFAQBgQdaWyq-yPE-9kssj5IyaedGPQ_jAbXWMKCACiEdQmJjJdS-EYKrJh5qpsNLMZkQEVU2GlRs12oTrLlpQOKVUNud6S";
        Message message = new Message.Builder().addData("msg", "push notify")
	.build();
	List<String> list = new ArrayList<String>();
	list.add(regId);
	MulticastResult multiResult;
	

	

  while(true)
	{
	if(input_value == pushButton.isHigh()){	
	try {
	multiResult = sender.send(message, list, 5);

	if (multiResult != null) {
	List<Result> resultList = multiResult.getResults();
			}
	}catch (IOException e)
	{
	e.printStackTrace();
	}
	System.out.println("aaa");

	}
	try{
	pushbutton.sleep(1000);
	} catch(InterruptedException e){}
	            
           }  
	}
}
 