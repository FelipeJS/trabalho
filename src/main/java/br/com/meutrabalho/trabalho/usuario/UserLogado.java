package br.com.meutrabalho.trabalho.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userLogado")
public final class UserLogado {

	@Autowired
	private UserService userService;
	
	public final User getUsuarioLogado(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByEmail(auth.getName());
	}
}