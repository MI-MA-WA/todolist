package de.thkoeln.gm.todolistgdwdemo.users

import java.util.UUID

interface UsersService {
    fun findById(id: UUID): User?
    fun getAllUsers(): List<User>
    fun save(user: User)
    fun delete(user: User)
}