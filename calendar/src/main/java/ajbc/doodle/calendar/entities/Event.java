package ajbc.doodle.calendar.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Events")
@JsonIgnoreProperties({"participantsId", "notificationsId"})
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	@JsonIgnore
	@Column(insertable = false, updatable = false)
	private Integer ownerUserId;
	@ManyToOne
	@JoinColumn(name = "ownerUserId")
	private User owner;
	private String title; 
	private boolean isAllDay; 
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String address; 
	private String description; 
	private int numberOfParticipants;
	boolean isDeleted;
	
	public enum Repeating{
		NONE,
		DAILY, 
		WEEKLY
	} 
	@Enumerated(EnumType.STRING)
	private Repeating Repeating;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(cascade = { CascadeType.MERGE },fetch = FetchType.EAGER)
	@JoinTable(name = "Users_Events", joinColumns = @JoinColumn(name = "eventId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<User> participants;

	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(mappedBy = "event", cascade = { CascadeType.MERGE } , fetch = FetchType.EAGER)
	private Set<Notification> notifications;

	public Event() {
		isDeleted = false;
		participants = new HashSet<>();
		notifications =  new HashSet<>();
	}
	
	public Event(Integer ownerUserId, User owner, String title, boolean isAllDay, LocalDateTime startTime, LocalDateTime endTime, 
			String address, String description, int numberOfParticipants, Repeating Repeating, Set<User> participants) {
		setOwnerUserId(ownerUserId);
		setOwner(owner);
		setTitle(title);
		setAllDay(isAllDay);
		setStartTime(startTime);
		setEndTime(endTime);
		setAddress(address);
		setDescription(description);
		setNumberOfParticipants(numberOfParticipants);
		setRepeating(Repeating);
		setParticipants(participants);
		
		isDeleted = false;
		participants = new HashSet<>();
		notifications =  new HashSet<>();
	}
	
}
