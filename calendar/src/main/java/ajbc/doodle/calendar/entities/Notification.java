package ajbc.doodle.calendar.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notificationId;
	
	@Column(insertable = false, updatable = false)
	private Integer eventId;
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "eventId")
	private Event event;
	
	@Column(insertable = false, updatable = false)
	private Integer userId;
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private LocalDateTime timeOfNotification;
	private String title;
	boolean isDeleted;
 
	public Notification() {}
	
	public Notification(Event event, User user, LocalDateTime timeOfNotification, String title) {
		setTimeOfNotification(timeOfNotification);
		setTitle(title);
		setEvent(event);
		setUser(user);
		isDeleted = false;
		eventId = event.getEventId();
		userId = user.getUserId();
	}
 
 

}
