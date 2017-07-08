package br.com.meutrabalho.trabalho.usuario;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
public class LoginController {
	private static final int CLIENTE = 1;
	private static final int EMPRESA = 2;

	@Autowired
	private UserService userService;

	@Autowired
	private UserLogado userLogado;
	
	@RequestMapping(value = "/consultarUsuarioLogado", method = GET)
	public User consultarUsuarioLogado() {
		return userLogado.getUsuarioLogado();
	}

	@RequestMapping(value = { "/", "/login" }, method = GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "Já existe usuário cadastrado com o email informado");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			user.setCategoria(user.getCategoria().toUpperCase());
			user.setSetor(user.getSetor().toUpperCase());
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", consultarUsuarioLogado().getName() + " (" + consultarUsuarioLogado().getEmail() + ")");
		modelAndView.addObject("adminMessage", "Conteúdo disponível para administradores");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping("/listarClientes")
	public Iterable<User> listarClientes() {
		return userService.findByTipo(CLIENTE);
	}

	@RequestMapping("/listarEmpresas")
	public Iterable<User> listarEmpresas() {
		return userService.findByTipo(EMPRESA);
	}

	@RequestMapping(value = "/atualizar", method = POST)
	public Long atualizar(@RequestBody User user) {
		userService.saveUser(user);
		return user.getId();
	}

	@RequestMapping(value = "/consultar", method = GET)
	public User consultar(@RequestParam Long userId) {
		return userService.findUserById(userId);
	}
}