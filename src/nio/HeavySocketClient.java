package nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeavySocketClient {

	private static ExecutorService tp= Executors.newCachedThreadPool();
	
	private static final int sleepTime = 1000*1000*1000;
	
}
