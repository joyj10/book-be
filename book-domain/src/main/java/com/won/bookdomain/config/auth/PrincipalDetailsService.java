package com.won.bookdomain.config.auth;
//
//import com.cos.jwt.model.User;
//import com.won.book.domain.member.Member;
//import com.won.book.domain.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadMemberByEmail(String email) throws UsernameNotFoundException {
//        log.debug("### email = {} ", email);
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow();
//        return new PrincipalDetails(member);
//    }
//}
