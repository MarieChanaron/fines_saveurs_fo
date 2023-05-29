package fr.poei.fines_saveurs_fo.validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CreditCard {

    @NotNull
    @Pattern(regexp = "^[0-9]{13,16}$")
    private String cardNumber;
    @NotNull
    @Pattern(regexp = "^[A-Za-z]{5,50}\\s[A-Za-z]{5,50}$")
    private String cardHolder;
    @NotNull
    @Pattern(regexp = "^(0[1-9]|1[0-2])$")
    String expirationMonth;
    @NotNull
    @Pattern(regexp = "^\\d{2}$")
    String expirationYear;
    @NotNull
    @Pattern(regexp = "^\\d{3}$")
    String cvc;
}
