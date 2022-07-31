package com.argentinaprograma.portfolio.service;
import com.argentinaprograma.portfolio.model.Persona;
import com.argentinaprograma.portfolio.model.Usuario;
import com.argentinaprograma.portfolio.repository.PersonaRepository;
import com.argentinaprograma.portfolio.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    
    
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Override
        public List<Usuario> getUsuarios() {
            List<Usuario> listaUsuarios = usuarioRepository.findAll();
            return listaUsuarios;
        }

        @Override
        public void saveUsuario(Usuario user) {
            usuarioRepository.save(user);
        }

        @Override
        public void deleteUsuario(Long id) {
            usuarioRepository.deleteById(id);
        }

        @Override
        public Usuario findUsuario(Long id) {
            Usuario user = usuarioRepository.findById(id).orElse(null);
            return user;
        }
    }

