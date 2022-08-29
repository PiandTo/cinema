package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="users", schema = "cinemas")
public class User {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long id;

	@Column(name="first_name")
	@NotNull(message = "You can't leave this field empty")
	private String firstName;

	@NotNull (message = "You can't leave this field empty")
	@Column(name="last_name")
	private String lastName;

	@NotNull (message = "You can't leave this field empty")
	@Column(name="email")
	private String email;

	@NotNull (message = "You can't leave this field empty")
	@Column(name="phone_number")
	private String phoneNumber;

	@NotNull (message = "You can't leave this field empty")
	@Column(name="password")
	private String password;

	@Column(name="is_admin")
	private Boolean isAdmin;

	@Column(name="avatar")
	private String avatar;
}
