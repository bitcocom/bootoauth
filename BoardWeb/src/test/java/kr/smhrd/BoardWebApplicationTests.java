package kr.smhrd;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.smhrd.entity.Member;
import kr.smhrd.repository.MemberRepository;
// Spring Boot DevTools 의존성 추가
@SpringBootTest
class BoardWebApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	/*
	@Test
	void saveMemberTest() {
	  	long id=2l;
	  	String username="Member B";
	  	int age=21;
	  	
	  	Member member=Member.builder()
	  			.username(username)
	  			.age(age)
	  			.build();
	  	
	  	Member saveMember=memberRepository.save(member);
	  	Assertions.assertEquals(saveMember.getAge(), age);
	  	Assertions.assertEquals(saveMember.getUsername(), username);
	}*/
	// JPQL, @Query()
	/*
	@Test
	void findUsernameAndAddressTest() {
		
		// given
        String jamesUsername = "James";
        String maryUsername = "Mary";

        Member james = Member.builder()
                .username(jamesUsername)
                .age(25)
                .build();

        Member mary = Member.builder()
                .username(maryUsername)
                .age(30)
                .build();

        memberRepository.save(james);
        memberRepository.save(mary);

        // when
        List<Member> optionalUsers = memberRepository.findByLetterWithConditions('a', 23);

        // then
        Assertions.assertEquals(optionalUsers.get(0).getUsername(), jamesUsername);
        Assertions.assertEquals(optionalUsers.get(1).getUsername(), maryUsername);
		
	}*/
	@Test
    @DisplayName("사용자 이름과 주소로 조회")
    void findUsernameAndAddressTest() {

        // given
        String username = "James";
        String address = "seoul";

        Member user = Member.builder()
                .username(username)
                .address(address)
                .age(25)                
                .build();
        memberRepository.save(user);

        // when
        Optional<Member> selectedUser = memberRepository.findByUsernameAndAddress(username, address);
        
        // then
        selectedUser.ifPresentOrElse(
                userOptional -> Assertions.assertEquals(userOptional.getUsername(), username),
                Assertions::fail
        );

        // then 람다를 쓰지 않은 경우
        if(selectedUser.isEmpty()) {
        	Assertions.fail();
        }else {
            Member userOptional = selectedUser.get();
            Assertions.assertEquals(userOptional.getUsername(), username);
        }
    }
}
