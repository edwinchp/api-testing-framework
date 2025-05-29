package models.gorest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoRestUser {
    private String name;
    private String gender;
    private String email;
    private String status;
}
