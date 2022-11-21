package kr.bit.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class CustomUser implements UserDetails,OAuth2User{

	private Member member;

	private Map<String, Object> attributes;
	
	public CustomUser(Member member) {
		this.member=member;	
	}
	
	public CustomUser(Member member, Map<String, Object> attributes) {
		this.member=member;
		this.attributes=attributes;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(()->{			
			return member.getRole().toString();
		});
		System.out.println("Call~");
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * public CustomUser(Member member) { super(member.getUsername(),
	 * member.getPassword(),
	 * AuthorityUtils.createAuthorityList(member.getRole().toString()));
	 * this.member=member; }
	 */
}
