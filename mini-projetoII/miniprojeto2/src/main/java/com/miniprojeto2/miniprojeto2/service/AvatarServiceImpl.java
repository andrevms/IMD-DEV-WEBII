package com.miniprojeto2.miniprojeto2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miniprojeto2.miniprojeto2.model.Avatar;
import com.miniprojeto2.miniprojeto2.repository.AvatarRepository;

@Component
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Override
    public Avatar salvarAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    @Override
    public void deletarAvatar(Avatar avatar) {
        avatarRepository.delete(avatar);
    }

    @Override
    public Avatar getAvatarbyId(String nomeFantasia) {
        return avatarRepository.findById(nomeFantasia).map(avatar -> {
            return avatar;
        }).orElseThrow(() -> null);
    }

    @Override
    public List<Avatar> getListaAvatar() {
        return avatarRepository.findAll();
    }
    
}
