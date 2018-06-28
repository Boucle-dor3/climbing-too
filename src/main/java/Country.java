import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String countryName;
}
