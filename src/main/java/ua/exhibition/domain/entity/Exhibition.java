package ua.exhibition.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Exhibition {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String name;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String showroom;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 2048, message = "Description too long (more than 2kB)")
    private String description;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    @NonNull
    private Long price;

    @NonNull
    private Date date;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "admin";
    }

}
