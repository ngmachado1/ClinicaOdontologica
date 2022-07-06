package clinica_odontologica.clinica.serviceTests;

import clinica_odontologica.clinica.Repository.OdontologoRepository;
import clinica_odontologica.clinica.Service.ServiceImp.OdontologoServiceImp;
import clinica_odontologica.clinica.dto.OdontologoDTO;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class OdontologoServiceImpTest {

    @Autowired
    private OdontologoServiceImp serviceOdontologoDto;

    @Autowired
    private OdontologoRepository repository;

    private OdontologoDTO o;

    public void cargarDataSet () {
        o.setNombre("Henry");
        o.setApellido("Gomez Ortiz");
        o.setMatricula("154ads");
        o.setTelefono("19241233");
        serviceOdontologoDto.guardar(o);
    }

    @BeforeEach
    public void setUp(){
        o = new OdontologoDTO();
    }

    @Test
    public void borraUnOdontologo() throws Exception {
        cargarDataSet();
        String respuestaEsperada = "Eliminado con éxito";
        String respuesta = serviceOdontologoDto.eliminar(1);
        Assert.assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void crearUnOdontologo() throws ResourceNotFoundException {
        //Dado
        Integer totalOdontologosAntesCreacion = repository.findAll().size();
        Integer totalOdontologosEsperado = totalOdontologosAntesCreacion+1;


        //Cuando
        cargarDataSet();
        Integer totalOdontologosDespuesCreacion = repository.findAll().size();


        //Entonces
        Assert.assertEquals(totalOdontologosEsperado, totalOdontologosDespuesCreacion);
    }

    @Test
    public void actualizarUnOdontologo() throws ResourceNotFoundException {
        //Dado
        cargarDataSet();
        OdontologoDTO original = serviceOdontologoDto.buscarPorId(1);

        //Cuando
        OdontologoDTO o = new OdontologoDTO();
        o.setId(1);
        o.setMatricula("df0000");
        serviceOdontologoDto.actualizar(o);
        OdontologoDTO trucho = serviceOdontologoDto.buscarPorId(1);

        //Entonces
        Assert.assertFalse(original.equals(trucho));
    }

    @Test
    public void traerTodos() throws ResourceNotFoundException {
        //Dado
        cargarDataSet();
        cargarDataSet();
        cargarDataSet();
        int respuestaEsperada = 3;

        //Cuando
        List<OdontologoDTO> odontologoDTOS = serviceOdontologoDto.buscarTodos();

        //Entonces
        Assert.assertEquals(respuestaEsperada, odontologoDTOS.size());
    }
}
