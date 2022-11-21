package kr.smhrd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.smhrd.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    //JPQL
	Optional<Member> findByUsernameAndAddress(String username, String address);
		
	// @Query
	@Query("SELECT u FROM Member u WHERE u.username LIKE %:char% and u.age > :maxAge")
    List<Member> findByLetterWithConditions(@Param("char") char letter,
                                            @Param("maxAge") int age);
}
