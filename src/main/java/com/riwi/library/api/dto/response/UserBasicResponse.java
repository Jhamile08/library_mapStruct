package com.riwi.library.api.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private String role;

}
