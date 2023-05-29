package fr.poei.fines_saveurs_fo.validator;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.LuhnCheck;


@LuhnCheck
@NotNull
public class CreditCardNumber {


}
