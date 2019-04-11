package listener;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;

import service.FileService;
import service.impl.FileServiceImpl;

public class DemonListener implements ServletContextListener, Runnable {
	private Thread t;
	private static final long BREAK_TIME = 1000 * 30;
	private static FileService fs = new FileServiceImpl();
	
	public DemonListener() {
		System.out.println("난 일빠따!");
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("난 생성후 바로 실행되지");
		if (t == null) {
			t = new Thread(this);
			t.setDaemon(true);
		}
		t.start();
	}
	private void readAddrfiles() {
		String path = "D:\\study\\addr\\input";
		String targetPath = "D:\\study\\addr\\input\\ok";
		File targetFolder = new File(targetPath);
		File root = new File(path);
		File[] files = root.listFiles();
		for (File file : files) {
			System.out.println("총 파일 갯수 : " + (files.length-1));
			if (!file.isDirectory()) {
				String name = file.getName();
				System.out.println(name + "파일시작!");
				Map<String,String> rMap = fs.insertAddrFromFile(file);
				int targetCnt = Integer.parseInt(rMap.get("targetCnt"));
				int totalCnt = Integer.parseInt(rMap.get("totalCnt"));
				if(targetCnt==totalCnt) {
					try {
						FileUtils.moveFileToDirectory(file, targetFolder, false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("실행시간 : " + rMap.get("executeTime"));
				System.out.println(name + "파일종료!");
				return;
			}
		}
	}
	public void run() {
		while (Thread.currentThread() == t) {
			try {
				Thread.sleep(BREAK_TIME);
				readAddrfiles();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
