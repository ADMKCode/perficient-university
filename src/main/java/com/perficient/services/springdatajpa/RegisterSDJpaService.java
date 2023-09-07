package com.perficient.services.springdatajpa;

import com.perficient.domain.Register;
import com.perficient.domain.repositories.RegisterRepository;
import com.perficient.services.RegisterService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterSDJpaService implements RegisterService {
  private final RegisterRepository registerRepository;

  public RegisterSDJpaService(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  @Override
  public Set<Register> findAll() {
    Set<Register> registers = new HashSet<>();
    registerRepository.findAll().forEach(registers::add);
    return registers;
  }

  @Override
  public Register findByID(Long aLong) {
    return registerRepository.findById(aLong).orElse(null);
  }

  @Override
  public Register save(Register object) {
    return registerRepository.save(object);
  }

  @Override
  public void delete(Register object) {
    registerRepository.delete(object);
  }

  @Override
  public void deleteByID(Long aLong) {
    registerRepository.deleteById(aLong);
  }
}
