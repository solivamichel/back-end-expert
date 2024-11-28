package br.com.soliva.userserviceapi.entity;

import lombok.*;
import models.enums.ProfileEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Document
public class User implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        private String id;

        private String name;

        private String email;

        private String password;

        private Set<ProfileEnum> profiles;
}
