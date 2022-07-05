package ajbc.doodle.calendar.entities;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String firstName; 
	private String lastName; 
	private String email; 
	private Timestamp birthDate; 
	private Timestamp joinDate; 
	@JsonIgnore
	private List<Integer> eventsIdList; 
	
	
	public User() {}
	
	public User(String firstName, String lastName, String email, Timestamp birthDate, Timestamp joinDate) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setBirthDate(birthDate);
		setJoinDate(joinDate);
	}
	
	public void addEvent(Integer eventId) {
		eventsIdList.add(eventId);
	}


}
