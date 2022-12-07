package com.rafael.fazumbem.services;

import com.rafael.fazumbem.email.EmailService;
import com.rafael.fazumbem.models.Token;
import com.rafael.fazumbem.models.User;
import com.rafael.fazumbem.repositories.UserRepository;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user ==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User SignUpUser(User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        String password = generatePassword();
        if(user1 != null) {
            throw new IllegalStateException("Email already in use");
        }
        try {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            //user.setPassword(bCryptPasswordEncoder.encode("123"));
        }
        catch(NullPointerException e) {
            System.out.println("NullPointerException thrown!");
        }
        user.setGroup("Institution");
        userRepository.save(user);
        String uuid = UUID.randomUUID().toString();
        //Token token = new Token(uuid, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), user);
        //Token token = new Token(uuid, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        //tokenService.saveToken(token);
        //String link = "https://faz-um-bem.herokuapp.com/user/confirm?token=" + uuid;
        emailService.send(user.getUsername(), buildEmail(password,user.getUsername()));
        return user;

    }

    public void resetPassword(String username) {
        User user = userRepository.findByUsername(username);
        String password = generatePassword();
        if(user == null) {
            throw new IllegalStateException("Usuário não encontrado.");
        }
        try {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }
        catch(NullPointerException e) {
            System.out.println("NullPointerException thrown!");
        }
        userRepository.save(user);
        emailService.send(user.getUsername(), buildEmail(password,user.getUsername()));
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public String generatePassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(65, 122)
                .build();
        return pwdGenerator.generate(8);
    }

    private String buildEmail(String senha, String email) {
        return "<p>Olá, dados para login: </p><p>Email : " + email + "</p><p>Senha : " + senha + "</p>";
    }


}

