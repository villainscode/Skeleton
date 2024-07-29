package com.codevillain.backend.entity;

import com.codevillain.backend.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Comment("사용자 ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 이메일")
    @Column(nullable = false, columnDefinition = "varchar(100)", unique = true)
    private String email;

    @Comment("사용자명")
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Comment("비밀번호")
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String password;

    @Comment("연락처")
    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    @Comment("탈퇴 여부")
    private boolean withdrawed;

    @Comment("사용자 레벨")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", columnDefinition = "varchar(20)")
    private Set<UserRoleEnum> roles;


    @Column(name = "created_at")
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @Comment("수정일")
    private LocalDateTime ModifiedAt;
}