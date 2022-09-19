package com.miniprojeto2.miniprojeto2.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.miniprojeto2.miniprojeto2.model.Avatar;

@Service
public interface AvatarService {

    public Avatar salvarAvatar(Avatar avatar);

    public void deletarAvatar(Avatar avatar);

    public Avatar getAvatarbyId(String nomeFantasia);

    public List<Avatar> getListaAvatar();
}
