package kr.bit.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
    
	@Id
	private String username; // ID
	private String password;
	//oAuth2 추가
	private String email;
	private String name;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled; // true, false 
	private String provider;
	private String providerId;
}
