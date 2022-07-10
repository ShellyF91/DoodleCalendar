package ajbc.doodle.calendar.entities;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Users")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String firstName; 
	private String lastName; 
	@Column(unique = true)
	private String email; 
	private LocalDateTime birthDate; 
	private LocalDateTime joinDate; 
	private boolean isDeleted;
	private boolean isLoggedin;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "participants")
	private Set<Event> events; 
	
//for login
	@JsonProperty(access = Access.WRITE_ONLY)
	private String endPoint;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String p256dh;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String auth;

	
	public User() {
		isDeleted = false;
		isLoggedin = false;
		events = new HashSet<Event>();
	}
	
	public User(String firstName, String lastName, String email, LocalDateTime birthDate, LocalDateTime joinDate) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setBirthDate(birthDate);
		setJoinDate(joinDate);
		isDeleted = false;
		isLoggedin = false;
		endPoint = null;
		p256dh = null; 
		auth = null; 
		
		events = new HashSet<Event>();
	}



}
