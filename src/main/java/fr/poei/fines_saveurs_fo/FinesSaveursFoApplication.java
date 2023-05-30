package fr.poei.fines_saveurs_fo;

import fr.poei.fines_saveurs_fo.entity.*;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.*;
import fr.poei.fines_saveurs_fo.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class FinesSaveursFoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinesSaveursFoApplication.class, args);
    }

}
