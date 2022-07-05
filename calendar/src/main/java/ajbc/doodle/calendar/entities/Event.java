package ajbc.doodle.calendar.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;
import java.util.List;

@ToString
@Getter
@Setter

@Entity
@Table(name = "Events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	private String title; 
	private boolean isAllDay; 
	Timestamp startTime;
	Timestamp endTime;
	private String address; 
	private String description; 
	private int numberOfParticipants;
	private List<User> participants; 
	private List<Notification> notifications; 
	
	enum Repeating{
		NONE,
		DAILY, 
		WEEKLY
	} 
	@Enumerated(EnumType.STRING)
	private Repeating Repeating;

	public Event() {}
	
	public Event(String title, boolean isAllDay, Timestamp startTime, Timestamp endTime, 
			String address, String description, int numberOfParticipants) {
		setTitle(title);
		setAllDay(isAllDay);
		setStartTime(startTime);
		setEndTime(endTime);
		setAddress(address);
		setDescription(description);
		setNumberOfParticipants(numberOfParticipants);
	}
	
	
}
