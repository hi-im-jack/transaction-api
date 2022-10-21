package jack.transaction.api.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Transaction {

    @NotNull @Id public String id;

    @NotNull public String item;

    @NotNull public String price;

    public long date_sold;
}
