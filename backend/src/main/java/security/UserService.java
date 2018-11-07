package security;

import org.springframework.security.core.context.SecurityContextHolder;
// esse serviço retorna o usuario logado (autenticado)
public class UserService {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}