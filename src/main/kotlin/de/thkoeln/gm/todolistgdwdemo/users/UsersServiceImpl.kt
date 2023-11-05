package de.thkoeln.gm.todolistgdwdemo.users

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsersServiceImpl (private val usersRepository: UsersRepository) : UsersService {
    override fun findById(id: UUID): User? {
        return usersRepository.findByIdOrNull(id)
    }

    override fun save(user: User) {
        usersRepository.save(user)
    }

    override fun delete(user: User) {
        usersRepository.delete(user)
    }
}