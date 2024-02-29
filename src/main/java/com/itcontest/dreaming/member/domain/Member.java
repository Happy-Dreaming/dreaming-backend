package com.itcontest.dreaming.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String name;

    private String picture;

    private String nickname;

    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, String name, String picture, String nickname, Role role) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
        this.point = 0;
        this.role = role;
    }
}
