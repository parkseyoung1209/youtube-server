package com.server.youtube.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;
}
