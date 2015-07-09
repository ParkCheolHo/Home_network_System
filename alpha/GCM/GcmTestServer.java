import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class GcmTestServer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sender sender = new Sender("AIzaSyD1ziPeLXV3Tpv_RegCr6jRORim9fwjfDM");
		String regId = "APA91bFS0Z92GX7LZ2VfR-OGVJ2N69WGnzMb4NAKX-zMFFFAQBgQdaWyq-yPE-9kssj5IyaedGPQ_jAbXWMKCACiEdQmJjJdS-EYKrJh5qpsNLMZkQEVU2GlRs12oTrLlpQOKVUNud6S"; // 단말기 RegID 입력

		 

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
