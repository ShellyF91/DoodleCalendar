package ajbc.doodle.calendar.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	private int eventId;
	private Timestamp timeOfNotification;
	private int minutesBeforeEvent;
	private String title;
 
	public Notification() {}
	
	public Notification(Timestamp timeOfNotification, int minutesBeforeEvent, String title) {
		setTimeOfNotification(timeOfNotification);
		setMinutesBeforeEvent(minutesBeforeEvent);
		setTitle(title);
	}
 
 

}
