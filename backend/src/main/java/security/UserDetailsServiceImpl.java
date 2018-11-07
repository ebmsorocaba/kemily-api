package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jdbc.dao.UsuarioDAO;
import model.Usuario;

 @Service
public class UserDetailsServiceImpl implements UserDetailsService {
 	
	@Autowired
	private UsuarioDAO usuDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usu;
		
			usu = usuDAO.getUsuarioByEmail(email);
			
			if (usu == null) {
				throw new UsernameNotFoundException(email);
			}
		return new UserSS(usu.getCodigo(), usu.getEmail(), usu.getSenha(), usu.getPerfis());
	}
}