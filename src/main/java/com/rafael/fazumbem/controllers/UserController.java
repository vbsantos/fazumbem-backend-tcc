package com.rafael.fazumbem.controllers;

import com.rafael.fazumbem.models.Campaign;
import com.rafael.fazumbem.models.User;
import com.rafael.fazumbem.repositories.UserRepository;
import com.rafael.fazumbem.security.JwtRequest;
import com.rafael.fazumbem.security.JwtResponse;
import com.rafael.fazumbem.security.JwtTokenUtil;
import com.rafael.fazumbem.security.JwtUserDetailsService;
import com.rafael.fazumbem.services.TokenService;
import com.rafael.fazumbem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    /*@PostMapping("/new")
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }
     */

    @GetMapping("/institution")
    public List<User> getUsers() {
        return userRepository.findByGroup("Institution");
    }

    @PutMapping
    public User putUser(@RequestBody User user) {

        Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

        User u = userRepository.findById(user.getIdUser()).get();
        if(u == null) {
            return null;
        }

        if(!BCRYPT_PATTERN.matcher(user.getPassword()).matches()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.SignUpUser(user));
    }

    @GetMapping("{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUserByUsername(@PathVariable String username){
        userRepository.deleteByUsername(username);
    }

    @PostMapping("/resetpassword/{username}")
    public void resetPassword(@PathVariable String username) {
        userService.resetPassword(username);
    }

}
