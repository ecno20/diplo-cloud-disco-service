package unam.diplomado.pixup.discoservice.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import unam.diplomado.pixup.discoservice.domain.Artista;
import unam.diplomado.pixup.discoservice.repository.ArtistaRepository;
import unam.diplomado.pixup.discoservice.service.ArtistaService;

@RestController
public class ArtistaController implements ArtistaApi {
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private ArtistaService artistaService;
	
	@Override
	@GetMapping
	public List<Artista> obtenerArtistas() {
		return artistaRepository.findAll();
	}
	
	@Override
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Artista crearArtista(@RequestBody Artista artista) {
		return artistaRepository.save(artista);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<Artista> obtenerArtistaPorId(@PathVariable("id") String id) {
		Optional<Artista> artista = artistaRepository.findById(id);
		if (artista.isPresent()) {
			return new ResponseEntity<>(artista.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@Override
	@PutMapping(path="{id}", consumes="application/json")
	public ResponseEntity<Artista> actualizarArtista(
			@PathVariable("id") String id, @RequestBody Artista artista) {
		Artista artistaActualizado = artistaService.actualizarArtista(id, artista);
		if (artistaActualizado != null) {
			return new ResponseEntity<>(artistaActualizado, HttpStatus.OK) ;
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@Override
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarArtista(@PathVariable("id") String id) {
		artistaRepository.deleteById(id);
	}
}
