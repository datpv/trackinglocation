package tmg.cargolink.tracking.location.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tmg.cargolink.tracking.location.entity.User;
import tmg.cargolink.tracking.location.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginUserDetailService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
    }

    public UserDetails loadUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(userId.toString());
        }
        return new CustomUserDetails(user.get());
    }
}
