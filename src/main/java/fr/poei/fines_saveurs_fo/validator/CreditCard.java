package fr.poei.fines_saveurs_fo.validator;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.LuhnCheck;


@LuhnCheck
@NotNull
@Data @NoArgsConstructor
public class CreditCard {

    private String cardNumber;
    private String cardHolder;
    int expirationMonth;
    int expirationYear;
    int cvc;
}
