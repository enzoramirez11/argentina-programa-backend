package com.argentinaprograma.portfolio.Controller;

import com.argentinaprograma.portfolio.model.Contacto;
import com.argentinaprograma.portfolio.model.ContactoForm;
import com.argentinaprograma.portfolio.model.Educacion;
import com.argentinaprograma.portfolio.model.EducacionForm;
import com.argentinaprograma.portfolio.model.PasswordForm;
import com.argentinaprograma.portfolio.model.Persona;
import com.argentinaprograma.portfolio.model.RegistroForm;
import com.argentinaprograma.portfolio.model.Respuesta;
import com.argentinaprograma.portfolio.model.Tecnologia;
import com.argentinaprograma.portfolio.model.Usuario;
import com.argentinaprograma.portfolio.model.UsuarioForm;
import com.argentinaprograma.portfolio.model.eliminar;
import com.argentinaprograma.portfolio.service.IContactoService;
import com.argentinaprograma.portfolio.service.IEducacionService;
import com.argentinaprograma.portfolio.service.IPersonaService;
import com.argentinaprograma.portfolio.service.ITecnologiaService;
import com.argentinaprograma.portfolio.service.IUsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class Controller {
    
    
    @Autowired
    private IUsuarioService interUsuarios;
    @Autowired
    private IPersonaService interPersona;
    @Autowired
    private IEducacionService interEdu;
    @Autowired
    private IContactoService interContacto;
    @Autowired
    private ITecnologiaService interTecno;
    
    //Traducir token para obtener Id del usuario
    public String traducirToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("user");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("usuario")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("id").asString();
        }
        catch (JWTCreationException exception) {
            return "Error";
        }
    }
    //Verificar si esta registrado
    public Boolean estaRegistrado(Usuario user) {
        List<Usuario> usuariosRegistrados = interUsuarios.getUsuarios();
        Boolean estaRegistrado=false;
        for (Usuario usuario:usuariosRegistrados) {
            if (usuario.getEmail().equals(user.getEmail())) {
                estaRegistrado=true;
            }
        }
        return estaRegistrado;
    }
    
    public Boolean puedeIniciarSesion(Usuario user) {
        List<Usuario> usuariosRegistrados = interUsuarios.getUsuarios();
        Boolean estaRegistrado=false;
        for (Usuario usuario:usuariosRegistrados) {
            if (usuario.getEmail().equals(user.getEmail()) && usuario.getPassword().equals(user.getPassword())) {
                estaRegistrado=true;
            }
        }
        return estaRegistrado;
    }
    
    //Obtener id en log in
    public Long obtenerId(Usuario user) {
        List<Usuario> usuariosRegistrados = interUsuarios.getUsuarios();
        Long id = null;
        for (Usuario usuario:usuariosRegistrados) {
            if (user.getEmail().equals(usuario.getEmail())) {
                id= usuario.getId();
            }
        }
        return id;
        
    }
    //Encontrar contacto
    public Contacto encontrarContacto(Long id) {
        Contacto cont=null;
        for (Contacto contacto: interContacto.getContactos()) {
            if (Long.parseLong(contacto.getUsuario_id())==id) {
                cont=contacto;
            }
        }
        return cont;
    }
    
    
    
    
    
    
    
    
    @GetMapping("/personas/mail/{mail}")
    public String traerMail(@PathVariable String mail) {
        List<Usuario> listaUsuarios = interUsuarios.getUsuarios();
        Respuesta resp = null;
        for(Usuario usuario:listaUsuarios) {
            if(usuario.getEmail().equals(mail)) {
                resp= new Respuesta(true,mail,null);
            }
        }
        return resp.getMensaje();
    }
    
    @GetMapping ("/personas/traer")
    public Object getPersonas() {
        List<Persona> prueba = interPersona.getPersonas();
        if(!prueba.isEmpty()) {
            return interPersona.getPersonas();
        }
        else{
            return "Esta vacia";
        }
        
    }
    
    @PostMapping("/crearPersona")
    public void createPersona(@RequestBody Persona perso) {
       interPersona.savePersona(perso);
    }
    //Registro
    @PostMapping("/registro")
    public Boolean registrarUsuario(@RequestBody RegistroForm user) {
        Usuario usuario = new Usuario();
        usuario.setEmail(user.getEmail());
        usuario.setPassword(user.getPassword());
        if (!estaRegistrado(usuario)) {
            interUsuarios.saveUsuario(usuario);
            Persona perso = new Persona();
            Contacto contacto= new Contacto();
            contacto.setUsuario_id(usuario.getId().toString());
            interContacto.saveContacto(contacto);
            perso.setNombre(user.getNombre());
            perso.setApellido(user.getApellido());
            perso.setUsuario_id(usuario.getId().toString());
            interPersona.savePersona(perso);
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
    //login
    @PostMapping("/personas/login")
    public Respuesta login(@RequestBody Usuario user) {
        String token=null;
        if(puedeIniciarSesion(user)) {
            Long id= obtenerId(user);
            try {
                Algorithm algorithm = Algorithm.HMAC256("user");
                token = JWT.create()
                       .withIssuer("usuario")
                       .withClaim("id", id.toString())
                       .sign(algorithm);
            }
            catch (JWTCreationException exception){
                //Invalid Signing configuration / Couldn't convert Claims.
            }
            return new Respuesta(true, "El usuario se encuentra registrado y puede iniciar sesión", token);
            }
        else {
            return new Respuesta(false, "El usuario no se encuentra registrado", null);
        }
    };
     //Obtener datos
    @GetMapping("/datosUsuario/{token}") 
    public Object datosUsuario(@PathVariable String token) {
        Persona persona=null;
        if (token.equals("admin")) {
            persona= interPersona.findPersona(Long.parseLong("2"));
        }
        else {
            Long user_id=Long.parseLong(traducirToken(token));
            Usuario user = interUsuarios.findUsuario(user_id);
            for (Persona perso:interPersona.getPersonas()) {
                if (Long.parseLong(perso.getUsuario_id())==user_id) {
                    persona= perso;
                }
            }
        }
        return persona;
    }
    
    //Obtiene email
    @GetMapping("/infoMiCuenta/{token}") 
    public String infoMiCuenta(@PathVariable String token) {
        Long user_id=Long.parseLong(traducirToken(token));
        Usuario user = interUsuarios.findUsuario(user_id);
        return user.getEmail();
    }
    //Obtiene info contacto
    @GetMapping("/infoContacto/{token}") 
    public Contacto infoContacto(@PathVariable String token) {
        Contacto cont = null;
        if (token.equals("admin")) {
            for (Contacto contacto:interContacto.getContactos()) {
            if (Long.parseLong("1")==Long.parseLong(contacto.getUsuario_id())) {
                cont=contacto;
            }
        }
        }
        else {
            Long user_id=Long.parseLong(traducirToken(token));
            for (Contacto contacto:interContacto.getContactos()) {
            if (user_id==Long.parseLong(contacto.getUsuario_id())) {
                cont=contacto;
            }
        }
        }
        return cont;
        
    }
    //Obtiene estudios
    @GetMapping("/infoEducacion/{token}")
    public List<Educacion> infoEducacion(@PathVariable String token) {
        List<Educacion> lista= new ArrayList<Educacion>();
        if (token.equals("admin")) {
                for (Educacion estudio:interEdu.getEducacion()) {
                if (estudio.getUsuario_id()==Long.parseLong("1")) {
                    lista.add(estudio);
                }
            }
        }
        else {
            for (Educacion estudio:interEdu.getEducacion()) {
            if (estudio.getUsuario_id()==Long.parseLong(traducirToken(token))) {
                lista.add(estudio);
            }
        }
        }
        return lista;
    }
    //Obtiene tecnologias
    @GetMapping("/infoTecnologia/{token}")
    public List<Tecnologia> infoTecnologia(@PathVariable String token) {
        List<Tecnologia> lista = new ArrayList<Tecnologia>();
        if (token.equals("admin")) {
            for (Tecnologia tecno:interTecno.getTecnologia()) {
                if (tecno.getUsuario_id()==Long.parseLong("1")) {
                    lista.add(tecno);
                }
            }
        }
        else {
            for (Tecnologia tecno:interTecno.getTecnologia()) {
                if (tecno.getUsuario_id()==Long.parseLong(traducirToken(token))) {
                    lista.add(tecno);
                }
            }
        }
        return lista;
    }
    
    
    //Editar Contacto
    @PutMapping("/editarContacto")
    public Boolean editarContacto(@RequestBody ContactoForm contacto) {
        Long user_id = Long.parseLong(traducirToken(contacto.getUser_id()));
        Contacto cont = encontrarContacto(user_id);
        if (cont!=null) {
            cont.setCiudad(contacto.getCiudad());
            cont.setNro_telefono(contacto.getNro_telefono());
            cont.setPais(contacto.getPais());
            cont.setProvincia(contacto.getProvincia());
            cont.setGithub(contacto.getGithub());
            cont.setLinkedin(contacto.getLinkedin());
            interContacto.saveContacto(cont);
            return true;
        }
        else {
            return false;
        }
    }
    
    //Editar email
    @PutMapping("/editarEmail")
    public Boolean editarEmail(@RequestBody UsuarioForm user) {
        Usuario usuario = interUsuarios.findUsuario(Long.parseLong(traducirToken(user.getUser_id())));
        if (estaRegistrado(user)) {
            return false;
        }
        else {
            usuario.setEmail(user.getEmail());
            interUsuarios.saveUsuario(usuario);
            return true;
        }
    }
    
    //Editar contraseña
    @PutMapping("/editarPassword")
    public Boolean editarPassword(@RequestBody PasswordForm user_pass) {
        Usuario usuario= interUsuarios.findUsuario(Long.parseLong(traducirToken(user_pass.getUser_id())));
        if (user_pass.getPassword_actual().equals(usuario.getPassword()) && !user_pass.getPassword_actual().equals(user_pass.getPassword_nuevo())) {
            usuario.setPassword(user_pass.getPassword_nuevo());
            interUsuarios.saveUsuario(usuario);
            return true;
        }
        else {
            return false;
        }
    }
    
    @GetMapping("/personas/auth/{token}")
    public Boolean auth(@PathVariable String token ) {
        Boolean autorizacion=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("user"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("usuario")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            autorizacion=true;
        } 
        catch (JWTVerificationException exception){
            autorizacion=false;
        }
        System.out.println(autorizacion);
        return autorizacion;
        
    }
    //Agregar tecnologia
    @PostMapping("/agregarTecnologia/{token}")
    public boolean agregarTecnologia(@PathVariable String token, @RequestBody Tecnologia tecno ) {
        Long user_id = Long.parseLong(traducirToken(token));
        tecno.setUsuario_id(user_id);
        interTecno.saveTecnologia(tecno);
        return true;
        
    }
    //Para crear un Educacion
    @PostMapping("personas/crearEducacion/")
    public String crearEd(@RequestBody EducacionForm ed) {
        Long id = null;
        Educacion edu= new Educacion();
        try {
            Algorithm algorithm = Algorithm.HMAC256("user");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("usuario")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(ed.getToken());
            System.out.println(jwt.getClaim("id").asString());
            id=Long.parseLong(jwt.getClaim("id").asString());
            edu= new Educacion( edu.getId(),
                                ed.getTitulo(),
                                ed.getInstitucion(),
                                ed.getUrl_img(),
                                ed.getDescripcion(),
                                ed.getFechaInicio(),
                                ed.getFechaFin(),
                                ed.getUrl_cert(),
                                id);
            interEdu.saveEducacion(edu);
            return "Se registro el estudio con exito";
        }
        catch (JWTVerificationException exception){
            System.out.println("error");
            return "No se pudo registrar el estudio";
        }
    }
    
    //Eliminar educacion
    @DeleteMapping("/eliminarEducacion/{token}")
    public String eliminarEducacion(@PathVariable String token, @RequestBody String id) {
        Long user_id = Long.parseLong(traducirToken(token));
        Educacion edu = interEdu.findEducacion(Long.parseLong(id));
        if (edu.getUsuario_id()==user_id) {
            interEdu.deleteEducacion(Long.parseLong(id));
            return "Se elimino con exito";
        }
        else {
            return "Hubo un error";
        }
    }
    @DeleteMapping("/eliminarTecnologia/{id}")
    public String eliminarTecnologia(@PathVariable String id, @RequestBody String token) {
        Long user_id = Long.parseLong(traducirToken(token));
        Tecnologia tecno = interTecno.findTecnologia(Long.parseLong(id));
        if (tecno.getUsuario_id()==user_id) {
            interTecno.deleteTecnologia(Long.parseLong(id));
            return "Se eliimino con exito";
        }
        else {
            return "Hubo un error";
        }
        
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id) {
        interPersona.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PutMapping("/editar/{id}")
    public Boolean editarInfoPersonal(@PathVariable Long id,@RequestBody Persona usuario) {
        Persona perso =interPersona.findPersona(id);
        perso.setNombre(usuario.getNombre());
        perso.setApellido(usuario.getApellido());
        perso.setOcupacion(usuario.getOcupacion());
        perso.setSobre_mi(usuario.getSobre_mi());
        perso.setUrl_foto(usuario.getUrl_foto());
        interPersona.savePersona(perso);
        System.out.println(usuario.getNombre());
        return true;
    }
    
    /*@PutMapping("/personas/editar/{id}")
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam ("nombre") String nuevoNombre,
                                @RequestParam ("apellido") String nuevoApellido,
                                @RequestParam ("email") String nuevoEmail,
                                @RequestParam ("password") String nuevoPassword) {
        //busco la persona a editar
        Persona perso = interPersona.findPersona(id);
        
        perso.setApellido(nuevoApellido);
        perso.setNombre(nuevoNombre);
        perso.setEmail(nuevoEmail);
        perso.setPassword(nuevoPassword);
        
        interPersona.savePersona(perso);
        return perso;
    }*/
}
