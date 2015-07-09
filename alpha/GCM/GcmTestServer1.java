import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class GcmTestServer1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sender sender = new Sender("AIzaSyCPpFbJ0RGDdi5VLbUSaBJUZDCncc_WhuA");
		String regId = "APA91bGr0N-kA0sIqlKHiMctumNuONufrG1IqA-RJ2ogOh5g4uTJjIFEnMaHYHg4vsHC3ezi7L4cRc2BpiswymntaL9CHkcVRWIo_5T6CFtgUXOsgcATTaCoX68tHqiBVUOvY8fO2AdE9CUCGh84ry0kjGYplNK0RQ";
		 

		Message message = new Message.Builder().addData("msg", "push notify")
		.build();

		List<String> list = new ArrayList<String>();
		list.add(regId);

		MulticastResult multiResult;
		try {
		multiResult = sender.send(message, list, 5);

		if (multiResult != null) {
		List<Result> resultList = multiResult.getResults();
		for (Result result : resultList) {
		System.out.println(result.getMessageId());
		}
		}
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}


}
