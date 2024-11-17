package com.Carlos.FinalProject.Repositories;

import com.Carlos.FinalProject.ECommerce.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}