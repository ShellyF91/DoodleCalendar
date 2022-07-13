package ajbc.doodle.calendar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter

@Entity
@Table(name = "Users_Events")
public class UserEvent {
	
	@Column(insertable = false, updatable = false)
	private Integer eventId;
	@Column(insertable = false, updatable = false)
	private Integer userId;
}
