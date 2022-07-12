package ajbc.doodle.calendar.notificationManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.daos.HTUserDao;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.services.MessagePushService;
import ajbc.doodle.calendar.services.UserService;

@Component
public class NotificationManager {
	

	@Autowired
	UserService userService;
	
	@Autowired
	private MessagePushService messagePushService;
	
	private ExecutorService executorService;
	
	List<Notification> notificationsList;

    Comparator<Notification> timeComparator = new Comparator<>() {
        @Override
        public int compare(Notification notification1, Notification notification2) {
        	if(notification1.getTimeOfNotification().isAfter(notification2.getTimeOfNotification()))
        		return -1;
        	else
        		return 1;
        }
    };
    
	private Queue<Notification> notificationsQueue = new PriorityQueue<>(timeComparator); 
	
	
	public void start(List<Notification> notificationsList) {
		System.out.println("in start ");
		this.notificationsList = notificationsList;
		removePastNotificationsFromList();
		fromListToQueue();
		startSupervisingThread();
	}

	private void removePastNotificationsFromList() {
		System.out.println("in removePastNotificationsFromList ");
//		for(Notification notification : notificationsList) {
//			if(notification.isHandled())
//				notificationsList.remove(notification);
//		}
	}

	private void fromListToQueue() {
		System.out.println("in fromListToQueue ");
		for(Notification notification : notificationsList) {
			notificationsQueue.add(notification);
		}
	}
	
	private void startSupervisingThread() {
		System.out.println("in startSupervisingThread ");
		executorService = Executors.newCachedThreadPool();
		Thread supervisingThread = new Thread(() -> {
			Notification firstInLineNotification;
			User user;
			boolean isUserLoggedin;
			
			while ( ! notificationsQueue.isEmpty()) {
				firstInLineNotification = notificationsQueue.poll();
				long timeToSleep = ChronoUnit.SECONDS.between(LocalDateTime.now(), firstInLineNotification.getTimeOfNotification());
				System.out.println("time to sleep: " + timeToSleep);
				try {
//					Thread.sleep(timeToSleep * 1000);
					Thread.sleep(1000);
					System.out.println("sleep");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				user = firstInLineNotification.getUser();
				System.out.println("awake");
				try {
					isUserLoggedin = userService.getUserById(user.getUserId()).isLoggedin();
					if(isUserLoggedin)
						executorService.execute(new NotificationDeliver(firstInLineNotification, messagePushService));
					firstInLineNotification.setHandled(true);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		supervisingThread.start();

	}
	
	
	
	
	
	
	
}
