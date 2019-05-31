package controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/libros",method = RequestMethod.GET)
    public Libro consulta(@RequestParam(value="id") String id) {
        return new DAO().consultar(id);
    }


    @RequestMapping(value="/libros",method = RequestMethod.PUT)
    public int modifica(@RequestParam(value="id") String id,
                        @RequestParam(value="nombre") String nombre,
                       @RequestParam(value="costo") String costo,
                       @RequestParam(value="descripcion") String descripcion,
                       @RequestParam(value="inventario") String inventario) {
        return new DAO().modificar(id,nombre,costo,descripcion, inventario);
    }

    @RequestMapping(value="/libros",method = RequestMethod.POST)
    public int inserta(@RequestParam(value="nombre") String nombre,
                       @RequestParam(value="costo") String costo,
                       @RequestParam(value="descripcion") String descripcion,
                       @RequestParam(value="inventario") String inventario) {
        return new DAO().insertar(nombre,costo,descripcion, inventario);
    }
}