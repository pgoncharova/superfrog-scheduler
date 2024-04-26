package edu.tcu.cs.superfrogscheduler.user;

import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperfrogUserService implements UserDetailsService {

    private final SuperfrogUserRepository superfrogUserRepository;
    private final SuperfrogRepository superfrogRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperfrogUserService(SuperfrogUserRepository superfrogUserRepository, SuperfrogRepository superfrogRepository, PasswordEncoder passwordEncoder) {
        this.superfrogUserRepository = superfrogUserRepository;
        this.superfrogRepository = superfrogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<SuperfrogUser> findAll() {
        return this.superfrogUserRepository.findAll();
    }

    public SuperfrogUser findById(Integer userId) {
        return this.superfrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public SuperfrogUser save(SuperfrogUser newSuperfrogUser) {
        newSuperfrogUser.setPassword(this.passwordEncoder.encode(newSuperfrogUser.getPassword()));
        return this.superfrogUserRepository.save(newSuperfrogUser);
    }

    public SuperfrogUser update(Integer userId, SuperfrogUser update) {
        SuperfrogUser oldSuperfrogUser = this.superfrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        oldSuperfrogUser.setEnabled(update.isEnabled());
        oldSuperfrogUser.setRoles(update.getRoles());
        return this.superfrogUserRepository.save(oldSuperfrogUser);
    }

    public void delete(Integer userId) {
        this.superfrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.superfrogUserRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.superfrogUserRepository.findByUsername(username)
                .map(user -> new MyUserPrincipal(user))
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found."));

    }

    public SuperfrogUser disableUser(String username) throws UsernameNotFoundException {
        SuperfrogUser superfrogUserToBeDisabled = this.superfrogUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found."));
        superfrogUserToBeDisabled.setEnabled(false);
        this.superfrogUserRepository.save(superfrogUserToBeDisabled);
        UserDetails userToBeDisabledDetails = new MyUserPrincipal(superfrogUserToBeDisabled);
        Superfrog superFrogToBeDisabled = this.superfrogRepository.findByEmail(username);
        superFrogToBeDisabled.setEnabled(false);
        this.superfrogRepository.save(superFrogToBeDisabled);
        return superfrogUserToBeDisabled;
    }

    public SuperfrogUser enableUser(String username) throws UsernameNotFoundException {
        SuperfrogUser superfrogUserToBeEnabled = this.superfrogUserRepository.findByUsername(username).orElseThrow();
        superfrogUserToBeEnabled.setEnabled(true);
        UserDetails userToBeEnabledDetails = new MyUserPrincipal(superfrogUserToBeEnabled);
        this.superfrogUserRepository.save(superfrogUserToBeEnabled);
        Superfrog superfrogToBeEnabled = this.superfrogRepository.findByEmail(username);
        superfrogToBeEnabled.setEnabled(true);
        this.superfrogRepository.save(superfrogToBeEnabled);
        return superfrogUserToBeEnabled;
    }
}
