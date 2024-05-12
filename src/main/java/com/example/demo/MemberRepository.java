package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where (:username is null or m.username = :username or :username = '')"
        + " and (:email is null or m.email = :email or :email = '')"
    )
    List<Member> findByUsernameAndEmail(@Param("username") String username,@Param("email") String email);

    Optional<Member> findByEmail(String email);
}
