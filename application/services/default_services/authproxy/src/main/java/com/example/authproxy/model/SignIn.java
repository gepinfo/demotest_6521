package com.example.authproxy.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.UUID;

@Document(collection = "signins")
@Data
@AllArgsConstructor
@Table
public class SignIn {

        @Id
        private String id;

        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private String role;
        private Boolean expired;
        private String idToken;
        private Date loggedInDate;
        private Date loggedOutDate;

        public SignIn() {
            this.id = UUID.randomUUID().toString();
        }

}
