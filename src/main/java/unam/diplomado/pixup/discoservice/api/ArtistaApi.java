package unam.diplomado.pixup.discoservice.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import unam.diplomado.pixup.discoservice.domain.Artista;

@RequestMapping(path="api/artistas", produces="application/json")
@Tag(name="artista", 
description="API del Recurso Artista")
@CrossOrigin(origins="*")
public interface ArtistaApi {
	
	@Operation(summary = "Crear Artista")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", 
		description = "Artista Creado Exitosamente", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=Artista.class)) }),
	  @ApiResponse(responseCode = "409", 
	  	description="Ya existe un Artista con el nombre especificado", 
	    content = @Content) })
	@PostMapping(path="registro", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	Artista crearArtista(@RequestBody Artista artista);

	// Documentar similar al método crearArtista pero para esta operación (Revisión: Pasa prueba)
	@GetMapping("{id}")
	ResponseEntity<Artista> obtenerArtistaPorId(@PathVariable("id") String id);
	
	// Documentar similar al método crearArtista pero para esta operación (Revisión: Pasa prueba)
	@GetMapping
	List<Artista> obtenerArtistas();
	
	// Documentar similar al método crearArtista pero para esta operación (Revisión: Pasa prueba)
	@PutMapping(path="{id}", consumes="application/json")
	ResponseEntity<Artista> actualizarArtista(
			@PathVariable("id") String id, @RequestBody Artista artista);
	
	// Documentar similar al método crearArtista pero para esta operación (Revisión: Pasa prueba)
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void eliminarArtista(@PathVariable("id") String id);

}
