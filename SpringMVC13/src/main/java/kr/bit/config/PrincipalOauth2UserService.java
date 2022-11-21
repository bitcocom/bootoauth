package kr.bit.config;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.bit.entity.CustomUser;
import kr.bit.entity.Member;
import kr.bit.entity.Role;
import kr.bit.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	 private final MemberRepository memberRepository;
	 private final BCryptPasswordEncoder passwordEncoder;
	 
	 @Override
	    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	        OAuth2User oAuth2User = super.loadUser(userRequest);
	        //log.info("getAttributes : {}", oAuth2User.getAttributes());

	        String provider = userRequest.getClientRegistration().getClientId();
	        String providerId = oAuth2User.getAttribute("sub");
	        String email = oAuth2User.getAttribute("email");
	        String loginId = provider + "_" +providerId;
	        String password = passwordEncoder.encode("aa");	       
            String name=oAuth2User.getAttribute("name");
	        
	        Optional<Member> user = memberRepository.findById(loginId);
	        if(!user.isPresent()) {
		        Member member = new Member();
		        member.setUsername(loginId);
		        member.setEmail(email);
		        member.setPassword(password);
		        member.setName(name);
		        member.setRole(Role.ROLE_MEMBER);
		        member.setProvider(provider);
		        member.setProviderId(providerId);           
	            memberRepository.save(member);
	            return new CustomUser(member, oAuth2User.getAttributes());
	        }
	      	 return new CustomUser(user.get(), oAuth2User.getAttributes());
	    }	
}   
