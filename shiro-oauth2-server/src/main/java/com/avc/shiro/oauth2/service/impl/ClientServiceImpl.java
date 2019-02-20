package com.avc.shiro.oauth2.service.impl;

import com.avc.shiro.oauth2.entity.Client;
import com.avc.shiro.oauth2.mapper.ClientMapper;
import com.avc.shiro.oauth2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端服务类
 * */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client createClient(Client client) {
        // 暂时没有用到
        return null;
    }

    @Override
    public Client updateClient(Client client) {
        // 暂时没有用到
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {

    }

    @Override
    public Client findOne(Long clientId) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client findByClientId(String clientId) {
        return clientMapper.selectByClientId(clientId);
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        return clientMapper.selectBySecret(clientSecret);
    }
}
