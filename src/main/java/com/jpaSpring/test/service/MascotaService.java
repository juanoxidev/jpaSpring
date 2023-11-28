package com.jpaSpring.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpaSpring.test.model.Mascota;
import com.jpaSpring.test.model.Persona;
import com.jpaSpring.test.repository.MascotaRepository;

@Service
public class MascotaService implements IMascotaService {

	@Autowired
	private MascotaRepository mascotaRepository;

	@Override
	public List<Mascota> mascota() {
		List<Mascota> listaMascotas = this.mascotaRepository.findAll();
		return listaMascotas;
	}

	@Override
	public void saveMascota(Mascota mascota) {
		this.mascotaRepository.save(mascota);
	}

	@Override
	public void deleteMascota(Long id) {
		this.mascotaRepository.deleteById(id);
	}

	@Override
	public Mascota findMascota(Long id) {
		return this.mascotaRepository.findById(id).orElse(null);
	}

	@Override
	public void editMascota(Long idOriginal, String nuevoNombre, String nuevaEspecie, String nuevaRaza,
			String nuevoColor) {
		Mascota mascotaAEditar = this.findMascota(idOriginal);
		mascotaAEditar.setNombre(nuevoNombre);
		mascotaAEditar.setColor(nuevoColor);
		mascotaAEditar.setEspecie(nuevaEspecie);
		mascotaAEditar.setRaza(nuevaRaza);
		this.saveMascota(mascotaAEditar);

	}

}
