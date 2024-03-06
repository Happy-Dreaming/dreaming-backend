package com.itcontest.dreaming.auth.application;

import com.itcontest.dreaming.auth.api.dto.response.MemberLoginResDto;
import com.itcontest.dreaming.auth.api.dto.response.UserInfo;
import com.itcontest.dreaming.member.domain.Member;
import com.itcontest.dreaming.member.domain.Role;
import com.itcontest.dreaming.member.domain.repository.MemberRepository;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthMemberService {

    private final MemberRepository memberRepository;

    private final Random random;

    public AuthMemberService(MemberRepository memberRepository) {
        this.random = new Random();
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberLoginResDto saveUserInfo(UserInfo userInfo) {
        Member member = memberRepository.findByEmail(userInfo.email()).orElseGet(() -> createMember(userInfo));

        return MemberLoginResDto.from(member);
    }

    private Member createMember(UserInfo userInfo) {
        String nickname = generateUniqueNickname();

        return memberRepository.save(
                Member.builder()
                        .email(userInfo.email())
                        .name(userInfo.nickname())
                        .picture(userInfo.picture())
                        .nickname(nickname)
                        .role(Role.ROLE_USER)
                        .build()
        );
    }

    private String generateUniqueNickname() {
        String nickname;

        do {
            nickname = String.format("User-%06d", random.nextInt(1000000));
        } while (memberRepository.existsByNickname(nickname));

        return nickname;
    }

}
