package com.sparta.auth.domain.model;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity(name="p_users")
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="user_id")
    private UUID id;

    @Column(name="user_slack_id",unique=true,nullable = false)
    private String slack_account;

    @Column(unique=true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private UserRole role;

    public static User create(String username,String slack_account,String password,UserRole role,String email){
        return User.builder()
                .username(username)
                .slack_account(slack_account)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

    public void update(String email,UserRole role) {
        this.email = email;
        this.role=role;
    }

}
