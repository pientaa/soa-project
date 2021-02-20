package com.pientaa.restservice.model

import org.springframework.data.repository.Repository

interface UserRepository : Repository<UserEntity, String>