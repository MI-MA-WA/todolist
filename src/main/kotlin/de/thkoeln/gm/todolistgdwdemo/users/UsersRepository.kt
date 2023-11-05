package de.thkoeln.gm.todolistgdwdemo.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UsersRepository : CrudRepository<User, UUID> {
}